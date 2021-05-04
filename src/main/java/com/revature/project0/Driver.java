package com.revature.project0;

import com.revature.project0.models.AppUser;
import com.revature.project0.screens.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Driver {
    public static void main(String[] args) {
        String key;

        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.printf("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                    "\tWelcome to Quizzard Wizard\n" +
                    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                    "Please Choose an option from the list below:\n" +
                    "\t(1) Sign Up\n" +
                    "\t(2) Log In\n:::");
            key = consoleReader.readLine();
            switch(key){
                case "1":
                    //makes a new
                    RegisterScreen registerScreen = new RegisterScreen(consoleReader);
                    registerScreen.render();
                    break;
                case "2":
                    LoginScreen loginScreen =  new LoginScreen(consoleReader);
                    loginScreen.render();
                    break;

            }


        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}
