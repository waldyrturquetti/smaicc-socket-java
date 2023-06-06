package org.utfpr.client.gui.usecase.incident;

import org.utfpr.client.exception.EmptyFieldException;
import org.utfpr.client.exception.ServerFailureException;
import org.utfpr.client.gui.usecase.UseCaseGuiForClient;
import org.utfpr.client.infra.ClientAppSocket;
import org.utfpr.client.util.ComboBoxValues;
import org.utfpr.client.util.Configure;
import org.utfpr.common.dto.incident.getIncidents.GetIncidentsDataClientToServer;
import org.utfpr.common.dto.incident.getIncidents.GetIncidentsDataServerToClient;
import org.utfpr.common.dto.incident.IncidentData;
import org.utfpr.common.gui.Dialogs;
import org.utfpr.common.util.Status;

import javax.swing.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

public class GetIncidents extends JFrame implements UseCaseGuiForClient {
    private JPanel getIncidents;
    private JFormattedTextField dateFormattedTextField;
    private JComboBox<String> statesComboBox;
    private JTextField cityField;
    private JButton getButton;

    public GetIncidents(){
        this.getButton.addActionListener(e -> this.executeOperation());
    }

    @Override
    public void buildScreen() {
        this.setContentPane(this.getIncidents);
        this.setTitle("Buscar Incidentes");
        this.setSize(350, 300);
        this.setStatesInComboBox();
        Configure.configureDateFormatted(dateFormattedTextField);
        this.setVisible(true);
    }

    @Override
    public void closeScreen() {
        this.setVisible(false);
    }

    @Override
    public void executeOperation() {
        try {
            this.send();
            List<IncidentData> incidentDataListReturned = this.returned();
            this.buildTable(incidentDataListReturned);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            Dialogs.showErrorMessage(ex.getMessage(), this);
        }
    }

    private void send() throws ParseException {
        if (this.dateFormattedTextField.getText().isBlank()
                || this.statesComboBox.getSelectedItem() == null || this.cityField.getText().isBlank()
        ) {
            throw new EmptyFieldException();
        }

        GetIncidentsDataClientToServer getIncidentsDataClientToServer =
                new GetIncidentsDataClientToServer(Configure.configureDateToServer(dateFormattedTextField.getText()),
                        Objects.requireNonNull(statesComboBox.getSelectedItem()).toString(), cityField.getText().toUpperCase());

        ClientAppSocket.sendMessage(getIncidentsDataClientToServer);
    }

    private List<IncidentData> returned() throws IOException {
        GetIncidentsDataServerToClient getIncidentsDataServerToClient = (GetIncidentsDataServerToClient) ClientAppSocket.receiveMessage(GetIncidentsDataServerToClient.class);

        if (!Objects.equals(getIncidentsDataServerToClient.getStatus().trim(), Status.OK)) {
            throw new ServerFailureException(getIncidentsDataServerToClient.getStatus());
        }

        return getIncidentsDataServerToClient.getIncidentDataList();
    }

    private void buildTable(List<IncidentData> incidentDataList) {
        if (incidentDataList.isEmpty()) {
            Dialogs.showInfoMessage("Não existe incidentes registrado nesse dia, Estado e Cidade.", this);
        } else {
            new IncidentTable().buildScreen(incidentDataList);
        }
    }

    private void setStatesInComboBox() {
        String[] states = ComboBoxValues.getStateValues();
        for (String state : states) {
            this.statesComboBox.addItem(state);
        }
    }
}
