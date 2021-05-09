package com.revature.project0.screens;

import com.revature.project0.daos.UserDAO;
import com.revature.project0.models.AppUser;

import java.io.BufferedReader;


public class LoginScreen extends Screen {
    private UserDAO userDao = new UserDAO(); // ok for now, but actually gross -- fix later
    private BufferedReader consoleReader;

    public LoginScreen(BufferedReader consoleReader) {
        super("LoginScreen","/login");
        this.consoleReader = consoleReader;
    }

    public void render() {

        try {
            String username;
            String password;
            // risky code that might through an exception

            System.out.println("Welcome Back! Login Below:");
            System.out.println("+-------------------------+");

            System.out.print("Username: ");
            username = consoleReader.readLine();

            System.out.print("Password: ");
            password = consoleReader.readLine();

            if (username != null && !username.isEmpty() && password != null && !password.isEmpty()){
                AppUser authenticatedUser = userDao.loginValidation(username,password);
                if (authenticatedUser != null){
                    System.out.println("Login Successful!");
                }else{
                    System.out.println(authenticatedUser);
                    System.out.println("Login failed.");
                }
            }else{
                System.out.println("It looks like you didnt provide valid credentials");
            }
        }


        catch (Exception e) {
            e.printStackTrace(); // include this line while developing/debugging the app!
            // should be logged to a file in a production environment
        }



    }
}

