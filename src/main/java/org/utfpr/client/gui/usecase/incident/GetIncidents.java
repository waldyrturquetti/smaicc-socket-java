package org.utfpr.client.gui.usecase.incident;

import org.utfpr.client.exception.EmptyFieldException;
import org.utfpr.client.exception.ServerFailureException;
import org.utfpr.client.infra.ClientAppSocket;
import org.utfpr.client.util.ComboBoxValues;
import org.utfpr.client.util.Configure;
import org.utfpr.common.dto.incident.getIncidents.GetIncidentsDataClientToServer;
import org.utfpr.common.dto.incident.getIncidents.GetIncidentsDataServerToClient;
import org.utfpr.common.dto.incident.getIncidents.IncidentData;
import org.utfpr.common.gui.Dialogs;
import org.utfpr.common.util.Status;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class GetIncidents extends JFrame {
    private JPanel getIncidents;
    private JFormattedTextField dateFormattedTextField;
    private JComboBox<String> statesComboBox;
    private JTextField cityField;
    private JButton getButton;

    public GetIncidents(){
        this.getButton.addActionListener(e -> {
            try {
                if (this.dateFormattedTextField.getText().isBlank()
                        || this.statesComboBox.getSelectedItem() == null || this.cityField.getText().isBlank()
                ) {
                    throw new EmptyFieldException();
                }

                GetIncidentsDataClientToServer getIncidentsDataClientToServer =
                        new GetIncidentsDataClientToServer(Configure.configureDateToServer(dateFormattedTextField.getText()),
                                Objects.requireNonNull(statesComboBox.getSelectedItem()).toString(), cityField.getText().toUpperCase());

                ClientAppSocket.sendMessage(getIncidentsDataClientToServer);
                List<IncidentData> incidentDataListReturned = this.returned();
                if (incidentDataListReturned.isEmpty()) {
                    Dialogs.showInfoMessage("Não existe incidentes registrado nesse dia, Estado e Cidade.", this);
                } else {
                    new IncidentTable().buildScreen(incidentDataListReturned);
                }

            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                Dialogs.showErrorMessage(ex.getMessage(), this);
            }
        });
    }

    public void buildScreen() {
        this.setContentPane(this.getIncidents);
        this.setTitle("Buscar Incidentes");
        this.setSize(350, 300);
        this.setStatesInComboBox();
        Configure.configureDateFormatted(dateFormattedTextField);
        this.setVisible(true);
    }

    private void setStatesInComboBox() {
        String[] states = ComboBoxValues.getStateValues();
        for (String state : states) {
            this.statesComboBox.addItem(state);
        }
    }

    private List<IncidentData> returned() throws IOException {
        GetIncidentsDataServerToClient getIncidentsDataServerToClient = (GetIncidentsDataServerToClient) ClientAppSocket.receiveMessage(GetIncidentsDataServerToClient.class);

        if (!Objects.equals(getIncidentsDataServerToClient.getStatus().trim(), Status.OK)) {
            throw new ServerFailureException(getIncidentsDataServerToClient.getStatus());
        }

        return getIncidentsDataServerToClient.getIncidentDataList();
    }
}