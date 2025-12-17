# 🏦 NMIMS Bank – JavaFX Banking Application

## 📘 Overview
The **NMIMS Bank** is a full-featured **JavaFX-based desktop banking system** built with an **MVC architecture** and powered by **SQLite** for persistent data storage.  
It provides two main user interfaces:

- 👨‍💼 **Admin Panel:** For managing clients, accounts, and banking operations.  
- 👤 **Client Panel:** For performing transactions, transfers, and viewing account summaries.

The application offers a realistic banking simulation with a secure login system, an interactive UI using **Font Awesome icons**, and database-driven account management.

---

## 🚀 Features

### 🔸 Admin Features
- Create, update, and delete client accounts  
- Manage checking and savings accounts  
- View all clients and their transaction histories  
- Monitor account balances and creation dates  

### 🔹 Client Features
- Secure login with authentication via database  
- Dashboard with balance summaries and transaction history  
- Transfer funds between **checking** and **savings** accounts  
- Profile view with personal and account details  
- Quick “Report Suggestion/Bug” popup for feedback  

---

## 🧩 Technology Stack

| Category | Technology |
|-----------|-------------|
| **Language** | Java (JDK 21) |
| **Framework** | JavaFX (FXML-based UI) |
| **Database** | SQLite (`mazebank.db`) |
| **Libraries / Tools** | Font Awesome (de.jensd.fx.glyphs.fontawesome), JDBC |
| **Architecture** | Model–View–Controller (MVC) |
| **IDE Used** | IntelliJ IDEA Ultimate |

---

## 🧠 System Architecture

The project follows a clear **MVC pattern**:

- **Model:** Handles data, logic, and database interaction (Client, Account, Transaction, DatabaseDriver, Model).  
- **View:** Contains all FXML files, custom UI components, and CSS styling.  
- **Controller:** Manages user input and updates between the Model and View (Admin and Client Controllers).

---

## ⚙️ Installation & Setup

### Prerequisites
- JDK 21+  
- IntelliJ IDEA or Eclipse  
- JavaFX SDK configured in project settings  

### Steps
1. Clone this repository:
   ```bash
   https://github.com/achalchhag/Nmims_bank.git
2. Open the project in IntelliJ IDEA.
3.Configure JavaFX libraries under
File → Project Structure → Libraries.
4.Run the main class (e.g., Main.java).
5. The application will open with the login screen for Client or Admin.
