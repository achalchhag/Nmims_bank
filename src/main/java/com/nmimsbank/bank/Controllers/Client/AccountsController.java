package com.nmimsbank.bank.Controllers.Client;

import com.nmimsbank.bank.Models.CheckingAccount;
import com.nmimsbank.bank.Models.Model;
import com.nmimsbank.bank.Models.SavingsAccount;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountsController implements Initializable {

    public Label ch_acc_num;
    public Label transaction_limit;
    public Label ch_acc_date;
    public Label ch_acc_bal;

    public Label sv_acc_num;
    public Label withdrawal_limit;
    public Label sv_acc_date;
    public Label sv_acc_bal;

    public TextField amount_to_sv;
    public Button trans_to_sv_btn;

    public TextField amount_to_ch;
    public Button trans_to_cv_btn;

    private CheckingAccount checkingAccount;
    private SavingsAccount savingsAccount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkingAccount = (CheckingAccount) Model.getInstance().getClient().checkingAccountProperty().get();
        savingsAccount = (SavingsAccount) Model.getInstance().getClient().savingsAccountProperty().get();

        setupAccountData();
        addTransferHandlers();
    }

    private void setupAccountData() {
        // Checking Account info
        ch_acc_num.setText(checkingAccount.accountNumberProperty().get());
        transaction_limit.setText(String.valueOf(checkingAccount.transactionLimitProp().get()));
        ch_acc_date.setText(Model.getInstance().getClient().dateProperty().get().toString());
        ch_acc_bal.setText(String.format("$%.2f", checkingAccount.balanceProperty().get()));

        // Savings Account info
        sv_acc_num.setText(savingsAccount.accountNumberProperty().get());
        withdrawal_limit.setText(String.format("$%.0f", savingsAccount.withdrawalLimitProp().get()));
        sv_acc_date.setText(Model.getInstance().getClient().dateProperty().get().toString());
        sv_acc_bal.setText(String.format("$%.2f", savingsAccount.balanceProperty().get()));
    }

    private void addTransferHandlers() {
        // From Checking → Savings
        trans_to_sv_btn.setOnAction(event -> transferToSavings());

        // From Savings → Checking
        trans_to_cv_btn.setOnAction(event -> transferToChecking());
    }

    private void transferToSavings() {
        if (amount_to_sv.getText().isEmpty()) {
            showAlert("Please enter an amount to transfer.");
            return;
        }

        double amount = Double.parseDouble(amount_to_sv.getText());
        double checkingBalance = checkingAccount.balanceProperty().get();

        if (amount <= 0) {
            showAlert("Transfer amount must be greater than zero.");
            return;
        }

        if (checkingBalance < amount) {
            showAlert("Insufficient funds in Checking Account!");
            return;
        }

        // Update balances
        checkingAccount.balanceProperty().set(checkingBalance - amount);
        savingsAccount.balanceProperty().set(savingsAccount.balanceProperty().get() + amount);

        // Reflect in DB
        Model.getInstance().getDatabaseDriver().updateCheckingAccountBalance(checkingAccount.ownerProperty().get(), checkingAccount.balanceProperty().get());
        Model.getInstance().getDatabaseDriver().updateSavingsAccountBalance(savingsAccount.ownerProperty().get(), savingsAccount.balanceProperty().get());

        setupAccountData();
        amount_to_sv.clear();
        showInfo("Funds transferred successfully to Savings Account.");
    }

    private void transferToChecking() {
        if (amount_to_ch.getText().isEmpty()) {
            showAlert("Please enter an amount to transfer.");
            return;
        }

        double amount = Double.parseDouble(amount_to_ch.getText());
        double savingsBalance = savingsAccount.balanceProperty().get();

        if (amount <= 0) {
            showAlert("Transfer amount must be greater than zero.");
            return;
        }

        if (savingsBalance < amount) {
            showAlert("Insufficient funds in Savings Account!");
            return;
        }

        // Update balances
        savingsAccount.balanceProperty().set(savingsBalance - amount);
        checkingAccount.balanceProperty().set(checkingAccount.balanceProperty().get() + amount);

        // Reflect in DB
        Model.getInstance().getDatabaseDriver().updateCheckingAccountBalance(checkingAccount.ownerProperty().get(), checkingAccount.balanceProperty().get());
        Model.getInstance().getDatabaseDriver().updateSavingsAccountBalance(savingsAccount.ownerProperty().get(), savingsAccount.balanceProperty().get());

        setupAccountData();
        amount_to_ch.clear();
        showInfo("Funds transferred successfully to Checking Account.");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Transfer Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfo(String message) {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Transfer Successful");
        info.setHeaderText(null);
        info.setContentText(message);
        info.showAndWait();
    }
}
