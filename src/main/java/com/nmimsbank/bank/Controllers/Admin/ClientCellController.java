package com.nmimsbank.bank.Controllers.Admin;

import com.nmimsbank.bank.Models.Client;
import com.nmimsbank.bank.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ClientCellController implements Initializable {

    public Label fName_lbl;
    public Label lName_lbl;
    public Label pAddress_lbl;
    public Label date_lbl;
    public Button delete_btn;

    private final Client client;

    public ClientCellController(Client client) {
        this.client = client;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize label text
        fName_lbl.setText(client.firstNameProperty().get());
        lName_lbl.setText(client.lastNameProperty().get());
        pAddress_lbl.setText(client.pAddressProperty().get());
        date_lbl.setText(client.dateProperty().get().toString());

        // Delete button functionality
        delete_btn.setOnAction(e -> onDeleteClient());
    }

    private void onDeleteClient() {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Delete Client");
        confirm.setHeaderText("Delete " + client.firstNameProperty().get() + " " + client.lastNameProperty().get() + "?");
        confirm.setContentText("This will permanently remove the client and their accounts.");

        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Model.getInstance().getDatabaseDriver().deleteClient(client.pAddressProperty().get());
            Model.getInstance().getClients().remove(client);
        }
    }
}
