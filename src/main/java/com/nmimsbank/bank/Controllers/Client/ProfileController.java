package com.nmimsbank.bank.Controllers.Client;

import com.nmimsbank.bank.Models.Client;
import com.nmimsbank.bank.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    public Label firstName_lbl;
    public Label lastName_lbl;
    public Label payeeAddress_lbl;
    public Label date_lbl;
    public Label checking_lbl;
    public Label savings_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Client client = Model.getInstance().getClient();

        firstName_lbl.setText(client.firstNameProperty().get());
        lastName_lbl.setText(client.lastNameProperty().get());
        payeeAddress_lbl.setText(client.pAddressProperty().get());
        date_lbl.setText(client.dateProperty().get().toString());

        if (client.checkingAccountProperty().get() != null) {
            checking_lbl.setText(client.checkingAccountProperty().get().accountNumberProperty().get());
        } else {
            checking_lbl.setText("Not Available");
        }

        if (client.savingsAccountProperty().get() != null) {
            savings_lbl.setText(client.savingsAccountProperty().get().accountNumberProperty().get());
        } else {
            savings_lbl.setText("Not Available");
        }
    }
}
