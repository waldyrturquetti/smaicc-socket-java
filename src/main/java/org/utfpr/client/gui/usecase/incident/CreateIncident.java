package org.utfpr.client.gui.usecase.incident;

import org.utfpr.client.auth.ClientSection;
import org.utfpr.client.exception.EmptyFieldException;
import org.utfpr.client.exception.ServerFailureException;
import org.utfpr.client.infra.ClientAppSocket;
import org.utfpr.client.util.ComboBoxValues;
import org.utfpr.client.util.Configure;
import org.utfpr.common.dto.common.CommonDataServerToClient;
import org.utfpr.common.dto.incident.createIncident.CreateIncidentDataClientToServer;
import org.utfpr.common.gui.Dialogs;
import org.utfpr.common.util.Status;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

public class CreateIncident extends JFrame {

    private JPanel createIncidentPanel;
    private JFormattedTextField dateFormattedTextField;
    private JFormattedTextField hourFormattedTextField;
    private JComboBox<String> stateComboBox;
    private JTextField cityField;
    private JTextField neighborhoodField;
    private JTextField streetField;
    private JComboBox<String> incidentTypesComboBox;
    private JButton createButton;

    public CreateIncident() {
        createButton.addActionListener(e -> {
            try {
                if (this.dateFormattedTextField.getText().isBlank()
                        || this.hourFormattedTextField.getText().isBlank() || this.stateComboBox.getSelectedItem() == null
                        || this.cityField.getText().isBlank() || this.neighborhoodField.getText().isBlank()
                        || this.streetField.getText().isBlank() || this.incidentTypesComboBox.getSelectedItem() == null
                ) {
                    throw new EmptyFieldException();
                }

                CreateIncidentDataClientToServer createIncidentDataClientToServer =
                        new CreateIncidentDataClientToServer(ClientSection.getId(), ClientSection.getToken(),
                                this.dateFormattedTextField.getText(), this.hourFormattedTextField.getText(),
                                Objects.requireNonNull(this.stateComboBox.getSelectedItem()).toString(), this.cityField.getText(),
                                this.neighborhoodField.getText(), this.streetField.getText(), this.incidentTypesComboBox.getSelectedIndex()
                        );

                ClientAppSocket.sendMessage(createIncidentDataClientToServer);

                this.returned();
                Dialogs.showInfoMessage("Incidente Cadastrado com Sucesso!", this);
                this.setVisible(false);
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                Dialogs.showErrorMessage(ex.getMessage(), this);
            }
        });
    }

    public void buildScreen() {
        this.setContentPane(this.createIncidentPanel);
        this.setTitle("Criar Usário");
        this.setSize(350, 600);
        this.setStatesInComboBox();
        this.setIncidentTypesInComboBox();
        Configure.configureDateFormatted(dateFormattedTextField);
        Configure.configureHourFormatted(hourFormattedTextField);
        this.setVisible(true);
    }

    private void returned() throws IOException {
        CommonDataServerToClient commonDataServerToClient = (CommonDataServerToClient) ClientAppSocket.receiveMessage(CommonDataServerToClient.class);

        if (!Objects.equals(commonDataServerToClient.getStatus().trim(), Status.OK)) {
            throw new ServerFailureException(commonDataServerToClient.getStatus());
        }
    }

    private void setStatesInComboBox() {
        String[] states = ComboBoxValues.getStateValues();
        for (String state : states) {
            this.stateComboBox.addItem(state);
        }
    }

    private void setIncidentTypesInComboBox() {
        String[] incidentsTypes = ComboBoxValues.getTypeIncidents();
        for (String incidentsType : incidentsTypes) {
            this.incidentTypesComboBox.addItem(incidentsType);
        }
    }
}
