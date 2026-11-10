import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Account implements Serializable {
    private String name;
    private String surname;
    private String password;
    private double balance = 0.0;
    private int ID;
    private ArrayList<String> history;
    private static final long serailVersionUID = 1L;

    Account(String name,String surname,String password,double deposit,int ID){
        this.name=name;
        this.surname=surname;
        this.password=password;
        this.balance+=deposit;
        this.ID=ID;
        this.history=new ArrayList<>();
        history.add("Account created.Initial balance: "+getBalance());
    }


    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void depositMoney(double depositAmount) {
        this.balance += depositAmount;
        System.out.println("Money deposited successfully!.Your current balance: "+getBalance());
        history.add("Money deposit: "+depositAmount+" "+"||Current balance: "+getBalance());
    }
    public boolean withdrawMoney(double withdrawAmount){
        if(this.balance<withdrawAmount){
            System.out.println("Your balance don't have that amount of money.Your current balance:" + getBalance());
            return false;
        }
        else {
            this.balance -= withdrawAmount;
            System.out.println("Money withdrawed successfully!.Your current balance: "+getBalance());
            history.add("Money withdraw: "+withdrawAmount+" "+ "||Current balance: "+getBalance());
            return true;
        }
    }
    public static Account authenticateAccount(Scanner input, HashMap<Integer,Account> accounts){
            System.out.print("Enter your Account ID:");
            int currentID = input.nextInt();
            input.nextLine();
            if (accounts.containsKey(currentID)) {
                Account currentAccount = accounts.get(currentID);
                System.out.print("Enter password:");
                String enteredPassword = input.nextLine();
                if (currentAccount.getPassword().equals(enteredPassword)) {
                    return currentAccount;
                    }
                else {
                        System.out.println("Wrong password,try again.");
                        return null;
                    }
                }
            else {
                    System.out.println("Wrong ID,try again.");
                    return null;
                }
    }
    public void showHistory(){
        System.out.println("\n_____________________History for "+ID+"_______________-");
        for (String record:history){
            System.out.println(record);
        }
        System.out.println("_____________________________");
    }
}
