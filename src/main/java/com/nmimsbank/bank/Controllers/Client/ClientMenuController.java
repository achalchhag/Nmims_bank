package com.nmimsbank.bank.Controllers.Client;

import com.nmimsbank.bank.Models.Model;
import com.nmimsbank.bank.Views.ClientMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import java.util.Optional;


import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {
    public Button dashboard_btn;
    public Button transaction_btn;
    public Button accounts_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button report_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners(){
        dashboard_btn.setOnAction(event -> onDashboard());
        transaction_btn.setOnAction(event -> onTransactions());
        accounts_btn.setOnAction(event -> onAccounts());
        logout_btn.setOnAction(event -> onLogout());
        profile_btn.setOnAction(event -> onProfile());
        report_btn.setOnAction(event -> onReport());


    }

    private void onDashboard(){
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.DASHBOARD);
    }
    private void onTransactions(){
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.TRANSACTIONS);
    }

    private void onAccounts(){
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.ACCOUNTS);
    }

    private void onProfile() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.PROFILE);
    }


    private void onLogout(){
        // Get Stage
        Stage stage =  (Stage) dashboard_btn.getScene().getWindow();
        // Close the client window
        Model.getInstance().getViewFactory().closeStage(stage);
        // Show login window
        Model.getInstance().getViewFactory().showLoginWindow();
        // Set login success flag to false
        Model.getInstance().setClientLoginSuccessFlag(false);
    }
    private void onReport() {
        // Create a simple text area for user input
        TextArea input = new TextArea();
        input.setPromptText("Describe your issue or suggestion here...");
        input.setWrapText(true);
        input.setPrefSize(300, 150);

        Alert dialog = new Alert(AlertType.CONFIRMATION);
        dialog.setTitle("Report Feedback");
        dialog.setHeaderText("Report a Bug or Suggest an Improvement");
        dialog.getDialogPane().setContent(input);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String feedback = input.getText().trim();
            if (feedback.isEmpty()) {
                Alert warn = new Alert(AlertType.WARNING, "Please enter some details before submitting.");
                warn.showAndWait();
                return;
            }

            // For now, just show a success confirmation (later you can log or send it to DB/email)
            Alert success = new Alert(AlertType.INFORMATION, "Thank you! Your feedback has been recorded.");
            success.showAndWait();

            // Optionally, print to console for debugging
            System.out.println("User Feedback: " + feedback);
        }
    }

}
