import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        HashMap<Integer,Account> accounts = loadData();
        int ID = 1000;
        for(int key : accounts.keySet()){
            if(key>ID){
                ID=key;
            }
        }
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n--- Welcome to Kentzz Bank ---");
            System.out.println("1. Create account");
            System.out.println("2. Invest (Deposit) money");
            System.out.println("3. Withdraw money");
            System.out.println("4. Check balance");
            System.out.println("5. Transfer money");
            System.out.println("6. Transaction history");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            String choice = input.nextLine();
            switch (choice) {
                case "1" -> {
                    System.out.print("Enter Name: ");
                    String name = input.nextLine();
                    System.out.print("Enter Surname: ");
                    String surname = input.nextLine();
                    String password = null;
                    while (password == null) {
                        System.out.print("Set password: ");
                        String pass1 = input.nextLine();
                        System.out.print("Confirm password: ");
                        String pass2 = input.nextLine();
                        if (pass1.equals(pass2)) {
                            password = pass1;
                        } else {
                            System.out.println("Passwords do not match. Try again!");
                        }
                    }
                    System.out.print("Deposit money: ");
                    double deposit = input.nextDouble();
                    input.nextLine();
                    ID++;
                    Account account = new Account(name, surname, password, deposit, ID);
                    accounts.put(ID,account);
                    System.out.println("Account Created Successfully! Your Account ID is: " + ID);
                }
                case "2" -> {
                    Account currentAccount=null;
                    while(currentAccount==null){
                     currentAccount = Account.authenticateAccount(input,accounts);
                    }
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = input.nextDouble();
                    input.nextLine();
                    currentAccount.depositMoney(depositAmount);
                }
                case "3" -> {
                    Account currentAccount=null;
                    while(currentAccount==null){
                        currentAccount = Account.authenticateAccount(input,accounts);
                    }
                    while(true) {
                        System.out.print("Enter withdraw amount: ");
                        double withdrawAmount = input.nextDouble();
                        input.nextLine();
                        if (currentAccount.withdrawMoney(withdrawAmount)) {
                            break;
                        }
                    }
                }
                case "4" -> {
                    Account currentAccount=null;
                    while(currentAccount==null){
                        currentAccount = Account.authenticateAccount(input,accounts);
                    }
                    System.out.println("Your current balance is: " + currentAccount.getBalance());
                }
                case "5" -> {
                    Account senderAccount=null;
                    while(senderAccount==null){
                        senderAccount = Account.authenticateAccount(input,accounts);
                    }
                    Account transferAccount = null;
                    boolean IDcheck = false;
                    while(!IDcheck){
                    System.out.print("Enter ID of account you want to transfer: ");
                    int transferID = input.nextInt();
                    input.nextLine();
                    if (accounts.containsKey(transferID) && transferID!=senderAccount.getID()) {
                        IDcheck = true;
                        transferAccount = accounts.get(transferID);
                    } else {
                        System.out.println("Wrong ID");
                    }
                    }
                    System.out.print("Enter amount of money you want to send: ");
                    double amount = input.nextDouble();
                    input.nextLine();
                    if(senderAccount.withdrawMoney(amount)){
                        transferAccount.depositMoney(amount);
                    }
                }
                case "6" -> {
                    Account currentAccount = null;
                    while(currentAccount==null){
                        currentAccount=Account.authenticateAccount(input,accounts);
                    }
                    currentAccount.showHistory();
                }
                case "0" -> {
                    System.out.println("Thank you for using Kentzz Bank. Goodbye!");
                    saveData(accounts);
                    isRunning = false;
                }
                default -> {
                    System.out.println("Invalid choice! You need to enter a number between 0-6.");
                }
            }
        }
        input.close();
    }
    public static void saveData(HashMap<Integer,Account> accounts){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("bank_data.txt"))) {
                oos.writeObject(accounts);
                System.out.println();
        }
        catch(IOException e){
            System.out.println("Something went wrong.Accounts not saved.");
        }
    }

    public  static HashMap<Integer,Account> loadData() {
        File file = new File("bank_data.txt");
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                return (HashMap<Integer, Account>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Data couldn't read.New database starting...");
            }
        }
        return new HashMap<>();
    }

}