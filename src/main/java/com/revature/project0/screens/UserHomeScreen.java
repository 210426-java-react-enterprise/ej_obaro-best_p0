package com.revature.project0.screens;

import com.revature.project0.util.LinkedList;
import com.revature.project0.daos.UserDAO;
import com.revature.project0.models.AppUser;
import com.revature.project0.util.ScreenRouter;
import java.io.BufferedReader;
import java.io.IOException;

import static com.revature.project0.Driver.app;

public class UserHomeScreen extends Screen {
    private UserDAO userDAO = new UserDAO();
    private BufferedReader consoleReader;
    private ScreenRouter router;

    public UserHomeScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("LoginScreen", "/homescreen");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    @Override
    public void render() {
    }

    @Override
    public void render(AppUser currentUser) {
        //testing to see if it has current user
        String userSelection = null;
        System.out.print(
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                        + "\t+Welcome back, " + currentUser.getFirstName() + "!\n"
                        + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        LinkedList currentUserAccounts;
        currentUserAccounts = userDAO.getAllCurrentUserAccounts(currentUser.getId());
        currentUserAccounts.printLinkedList();

        System.out.print(
                "Please Choose an option from the list below:\n" +
                        "\t(5) Create Account\n" +
                        "\t(6) Make a Deposit\n" +
                        "\t(7) Make a Withdrawl\n" +
                        "\t(8) Delete Account" +
                        "\n:::");

        try {
            System.out.print(">");
             userSelection = consoleReader.readLine();
            switch (userSelection) {
                case "5":
                    router.navigate("/newAccount",currentUser);
                    break;
                case "6":
                    System.out.println("Navigating to Deposit Screen...");
                    router.navigate("/deposit",currentUser);
                    break;
                case "7":
                    System.out.println("Exiting Banking App.");
                    app().setAppRunning(false);
                    break;
                case "8":
                    break;
                default:
                    System.out.println("Invalid input selected.");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}