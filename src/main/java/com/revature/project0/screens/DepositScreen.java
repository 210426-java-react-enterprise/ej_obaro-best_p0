package com.revature.project0.screens;

import com.revature.project0.daos.UserDAO;
import com.revature.project0.models.AppUser;
import com.revature.project0.models.UserAccount;
import com.revature.project0.util.ScreenRouter;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DepositScreen extends Screen{
    private UserDAO userDao = new UserDAO();
    private BufferedReader consoleReader;
    private ScreenRouter router;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


    public DepositScreen(BufferedReader consoleReader, ScreenRouter router){
        super("DepositScreen","/deposit");
        this.consoleReader = consoleReader;
        this.router= router;
    }

    @Override
    public void render() {

    }

    @Override
    public void render(AppUser currentUser) {
        Double balance;
        Double depositAmount;
        String accountName;

        try{
            System.out.println("Name/Type of Account:");
            System.out.print(":::>");
            accountName = consoleReader.readLine();

            UserAccount currentAccount = userDao.getUserAccount(accountName,currentUser.getId());

            if(currentAccount != null){

                balance = currentAccount.getBalance();
                System.out.println(balance);
                System.out.println("Amount to Deposit:");
                System.out.print(":::> $");
                depositAmount = Double.parseDouble(consoleReader.readLine());
                balance += depositAmount;
                userDao.updateUserBalance(currentUser,accountName,balance);
                System.out.printf("Your current balance is now: $%d",balance);
                Date date = new Date();
                userDao.updateTransactionsTable("Deposit", currentUser,currentAccount, (java.sql.Date) date);
            }else{
                System.out.printf("You have no accounts called %s",accountName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
