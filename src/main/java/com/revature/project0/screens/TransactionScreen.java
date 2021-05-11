package com.revature.project0.screens;
import com.revature.project0.daos.UserDAO;
import com.revature.project0.models.AppUser;
import com.revature.project0.models.UserAccount;
import com.revature.project0.models.UserTransactionHistory;
import com.revature.project0.util.LinkedList;
import com.revature.project0.util.ScreenRouter;
import java.io.BufferedReader;
import java.io.IOException;


public class TransactionScreen extends Screen{

    private UserDAO userDao = new UserDAO();
    private BufferedReader consoleReader;
    private ScreenRouter router;

    public TransactionScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("Transaction Screen","/transaction");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    @Override
    public void render() {

    }
    @Override
    public void render(AppUser currentUser){
        String userChoice;
        LinkedList accountHistory;
        try{
            System.out.println("Would you like to view your entire transaction history?(y/n)");
            userChoice = consoleReader.readLine();
            if (userChoice.equalsIgnoreCase("y")){
                accountHistory = userDao.getAllTransactions(currentUser.getId());
                accountHistory.printLinkedList();
            }else if (userChoice.equalsIgnoreCase("n")){
                String accountName;
                System.out.println("Name/Type of Account:");
                accountName = consoleReader.readLine();
                accountHistory = userDao.getTransactions(currentUser.getId(), accountName);
                accountHistory.printLinkedList();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
