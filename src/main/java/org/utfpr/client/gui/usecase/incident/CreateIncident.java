package org.utfpr.client.gui.usecase.incident;

import org.utfpr.client.auth.ClientSection;
import org.utfpr.client.exception.InvalidFieldException;
import org.utfpr.client.exception.ServerFailureException;
import org.utfpr.client.gui.usecase.UseCaseGui;
import org.utfpr.client.infra.ClientAppSocket;
import org.utfpr.client.util.ClientCheck;
import org.utfpr.client.util.ComboBoxValues;
import org.utfpr.client.util.Configure;
import org.utfpr.common.dto.common.CommonDataServerToClient;
import org.utfpr.common.dto.incident.createIncident.CreateIncidentDataClientToServer;
import org.utfpr.common.gui.Dialogs;
import org.utfpr.common.util.Status;

import javax.swing.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.Objects;

public class CreateIncident extends JFrame implements UseCaseGui {

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
        createButton.addActionListener(e -> this.executeOperation());
    }

    @Override
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

    @Override
    public void closeScreen() {
        Dialogs.showInfoMessage("Incidente Cadastrado com Sucesso!", this);
        this.setVisible(false);
    }

    @Override
    public void executeOperation() {
        try {
            this.send();
            this.returned();
            this.closeScreen();
        } catch (InvalidFieldException invalidFieldException) {
            System.err.println(invalidFieldException.getMessage());
            Dialogs.showWarningMessage(invalidFieldException.getMessage(), this);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            Dialogs.showErrorMessage(ex.getMessage(), this);
        }
    }

    private void send() throws ParseException {
        ClientCheck.checkDate(this.dateFormattedTextField.getText());
        ClientCheck.checkHour(this.hourFormattedTextField.getText());
        ClientCheck.checkState(this.stateComboBox.getSelectedItem());
        ClientCheck.checkCity(this.cityField.getText().toUpperCase());
        ClientCheck.checkNeighborhood(this.neighborhoodField.getText().toUpperCase());
        ClientCheck.checkStreet(this.streetField.getText().toUpperCase());
        ClientCheck.checkIncidentType((String) this.incidentTypesComboBox.getSelectedItem());

        CreateIncidentDataClientToServer createIncidentDataClientToServer =
                new CreateIncidentDataClientToServer(ClientSection.getId(), ClientSection.getToken(),
                        Configure.configureDateToServer(this.dateFormattedTextField.getText()),
                        this.hourFormattedTextField.getText(),
                        Objects.requireNonNull(this.stateComboBox.getSelectedItem()).toString(),
                        this.cityField.getText().toUpperCase(), this.neighborhoodField.getText().toUpperCase(),
                        this.streetField.getText().toUpperCase(), this.incidentTypesComboBox.getSelectedIndex()
                );

        ClientAppSocket.sendMessage(createIncidentDataClientToServer);
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
