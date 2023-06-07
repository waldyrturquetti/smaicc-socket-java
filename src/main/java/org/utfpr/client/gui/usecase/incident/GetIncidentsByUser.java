package org.utfpr.client.gui.usecase.incident;

import org.utfpr.client.auth.ClientSection;
import org.utfpr.client.exception.ServerFailureException;
import org.utfpr.client.gui.usecase.UseCaseGuiForClient;
import org.utfpr.client.infra.ClientAppSocket;
import org.utfpr.common.dto.common.CommonDataServerToClient;
import org.utfpr.common.dto.incident.IncidentData;
import org.utfpr.common.dto.incident.deleteIncident.DeleteIncidentDataClientToServer;
import org.utfpr.common.dto.incident.getIncidentsByUser.GetIncidentsByUserDataClientToServer;
import org.utfpr.common.dto.incident.getIncidentsByUser.GetIncidentsByUserDataServerToClient;
import org.utfpr.common.gui.Dialogs;
import org.utfpr.common.util.Status;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class GetIncidentsByUser extends JFrame implements UseCaseGuiForClient {

    private JPanel getIncidentsByUserPanel;
    private JButton getIncidentsByUserButton;
    private JComboBox<Integer> incidentsComboBox;
    private JButton deleteIncidentButton;
    private static IncidentTable incidentTable;
    private static String operation;
    private static final String GET = "GET";
    private static final String DELETE = "DELETE";

    public GetIncidentsByUser() {
        this.getIncidentsByUserButton.addActionListener(e ->{
            operation = GET;
            this.executeOperation();
        });
        this.deleteIncidentButton.addActionListener(e -> {
            operation = DELETE;
            this.executeOperation();
        });
    }

    @Override
    public void buildScreen() {
        this.setContentPane(this.getIncidentsByUserPanel);
        this.setTitle("Buscar os Incidentes Criados por Mim");
        this.setSize(370, 200);
        this.setVisible(true);
    }

    @Override
    public void closeScreen() {
        this.setVisible(false);
    }

    @Override
    public void executeOperation() {
        try {
            if (operation.equals(GET)) {
                this.sendForGet();
                List<IncidentData> incidentDataListReturned = this.returnFromGet();
                this.buildTable(incidentDataListReturned);
                this.setIncidentsInComboBox(incidentDataListReturned);
            } else {
                Integer incidentId = this.sendForDelete();
                if (incidentId != null) {
                    this.returnFromDelete();
                    this.removeIncidentFromComboBox(incidentId);
                    incidentTable.closeScreen();
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            Dialogs.showErrorMessage(e.getMessage(), this);
        }
    }

    private void sendForGet(){
        GetIncidentsByUserDataClientToServer getIncidentsByUserDataClientToServer =
                new GetIncidentsByUserDataClientToServer(ClientSection.getId(), ClientSection.getToken());
        ClientAppSocket.sendMessage(getIncidentsByUserDataClientToServer);
    }

    private List<IncidentData> returnFromGet() throws IOException {
        GetIncidentsByUserDataServerToClient getIncidentsByUserDataServerToClient =
                (GetIncidentsByUserDataServerToClient) ClientAppSocket.receiveMessage(GetIncidentsByUserDataServerToClient.class);

        if (!Objects.equals(getIncidentsByUserDataServerToClient.getStatus().trim(), Status.OK)) {
            throw new ServerFailureException(getIncidentsByUserDataServerToClient.getStatus());
        }

        return getIncidentsByUserDataServerToClient.getIncidentDataList();
    }

    private Integer sendForDelete(){
        if (this.incidentsComboBox.getSelectedItem() == null) {
            Dialogs.showInfoMessage("Para remover o incidente selecione o mesmo no Combo Box.");
            return null;
        }

        Integer incidentId = (Integer) this.incidentsComboBox.getSelectedItem();

        DeleteIncidentDataClientToServer deleteIncidentDataClientToServer =
                new DeleteIncidentDataClientToServer(ClientSection.getId(), incidentId, ClientSection.getToken());

        ClientAppSocket.sendMessage(deleteIncidentDataClientToServer);

        return incidentId;
    }

    private void returnFromDelete() throws IOException {
        CommonDataServerToClient commonDataServerToClient =
                (CommonDataServerToClient) ClientAppSocket.receiveMessage(CommonDataServerToClient.class);

        if (!Objects.equals(commonDataServerToClient.getStatus().trim(), Status.OK)) {
            throw new ServerFailureException(commonDataServerToClient.getStatus());
        }

        Dialogs.showInfoMessage("Incidente removido com sucesso.");
    }

    private void setIncidentsInComboBox(List<IncidentData> incidentDataList) {
        this.incidentsComboBox.removeAllItems();
        incidentsComboBox.addItem(null);
        for (IncidentData incidentData : incidentDataList) {
            this.incidentsComboBox.addItem(incidentData.getIncidentId());
        }
    }

    private void removeIncidentFromComboBox(Integer incidentId) {
        this.incidentsComboBox.removeItem(incidentId);
    }

    private void buildTable(List<IncidentData> incidentDataList) {
        if (incidentDataList.isEmpty()) {
            Dialogs.showInfoMessage("Não existe incidentes cadastrados por você.", this);
        } else {
            incidentTable = new IncidentTable(incidentDataList);
            incidentTable.buildScreen();
        }
    }
}
