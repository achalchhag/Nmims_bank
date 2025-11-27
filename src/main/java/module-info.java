module com.nmimsbank.bank {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires javafx.graphics;


    opens com.nmimsbank.bank to javafx.fxml;
    exports com.nmimsbank.bank;
    exports com.nmimsbank.bank.Controllers;
    exports com.nmimsbank.bank.Controllers.Admin;
    exports com.nmimsbank.bank.Controllers.Client;
    exports com.nmimsbank.bank.Models;
    exports com.nmimsbank.bank.Views;
}