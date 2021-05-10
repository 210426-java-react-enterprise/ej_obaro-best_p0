package com.revature.project0.screens;
import com.revature.project0.daos.UserDAO;
import com.revature.project0.models.AppUser;
import com.revature.project0.util.ScreenRouter;
import com.revature.project0.models.UserAccount;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Locale;

public class CreateAccountScreen extends Screen {
    private UserDAO userDao = new UserDAO(); // ok for now, but actually gross -- fix later
    private BufferedReader consoleReader;
    private ScreenRouter router;
    public CreateAccountScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("CreateAccountScreen","/newAccount");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    @Override
    public void render() {

    }
    @Override
    public void render(AppUser currentUser) {
        //Grab current user's id and send it to userDao
        int userId = currentUser.getId();
        double balance=0.00;//
        String accountType;//
        boolean makeFirstDeposit = false;
        String choice;

        try {
            System.out.print("Name/Type of Account: ");
            accountType = consoleReader.readLine(); // throws Exception here

            System.out.print("Would you like to make your first deposit today?(y/n): ");
            choice = consoleReader.readLine();
            if (choice.equalsIgnoreCase("y")) {
                System.out.print("Amount to be deposited: ");
                System.out.printf("%d was deposited into your account.\n" +
                        "Please log back in to access your funds");
                balance = Double.parseDouble(consoleReader.readLine());
            }else if (choice.equalsIgnoreCase("n")){
                balance = 0.00;

            }else{
                System.out.println("Entry invalid. Please log back in and try again !");
            }
            UserAccount newAccount = new UserAccount(userId,balance,accountType);
            userDao.saveAccount(newAccount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
