#  Beginner Bank System 🏦

A robust, console-based banking application written in Java. This project demonstrates core Object-Oriented Programming (OOP) concepts, data structures, and data persistence using standard Java libraries.

## 🚀 Features
- **Account Management:** Create new accounts with secure IDs and passwords.
- **Transactions:** Deposit and withdraw money with sufficient balance validation.
- **Money Transfers:** Securely transfer funds between different accounts using their Account IDs.
- **Transaction History:** Each account maintains an independent, detailed log of all transactions.
- **Data Persistence (Save/Load):** Employs Java Serialization to save the entire database (`HashMap` of accounts) to a file upon exit, ensuring no data is lost between sessions.

## 🛠️ Technologies Used
- **Language:** Java
- **Data Structures:** `HashMap` (for rapid O(1) account retrieval), `ArrayList` (for transaction history).
- **Core Concepts:** Object-Oriented Programming (Encapsulation), File I/O, Serialization.

## 💻 How to Run
1. Clone the repository: 
   `git clone https://github.com/Kenanzz7/Beginner-Bank-System.git`
2. Compile and run the `Main.java` file in your preferred IDE (IntelliJ IDEA, Eclipse) or via terminal.

## 💡 What I Learned
During this project, I strengthened my understanding of managing states within loops, preventing edge-case bugs (e.g., preventing self-transfers, handling infinite loops on incorrect inputs), and safely serializing complex data structures.
