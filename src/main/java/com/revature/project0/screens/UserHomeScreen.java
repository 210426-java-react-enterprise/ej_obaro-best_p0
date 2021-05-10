package com.revature.project0.screens;

import com.revature.project0.daos.UserDAO;
import com.revature.project0.models.AppUser;
import com.revature.project0.util.ScreenRouter;
import java.io.BufferedReader;

public class UserHomeScreen extends Screen{
    private UserDAO userDAO = new UserDAO();
    private BufferedReader consoleReader;
    private ScreenRouter router;
    public UserHomeScreen(BufferedReader consoleReader,ScreenRouter router) {
        super("LoginScreen","/homescreen");
        this.consoleReader = consoleReader;
        this.router = router;
    }

    @Override
    public void render() {
    }

    @Override
    public void render(AppUser currentUser){
        //testing to see if it has current user
        System.out.println(currentUser.getFirstName());

    }
}
