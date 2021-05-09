package com.revature.project0.util;

import com.revature.project0.daos.UserDAO;
import com.revature.project0.screens.LoginScreen;
import com.revature.project0.screens.RegisterScreen;
import com.revature.project0.screens.WelcomeScreen;
import com.revature.project0.services.UserService;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {
    private BufferedReader consoleReader;
    private ScreenRouter router;
    private boolean appRunning;

    public AppState(){
        System.out.println("Initializing applicaton...");

        appRunning = true;
        consoleReader = new BufferedReader(new InputStreamReader(System.in));

        final UserDAO userDAO = new UserDAO();
        final UserService userService = new UserService(userDAO);

        router = new ScreenRouter();
        router.addScreen(new WelcomeScreen(consoleReader,router))
                .addScreen(new LoginScreen(consoleReader))
                .addScreen(new RegisterScreen(consoleReader));

        System.out.println("Application Initialized");
    }
    public ScreenRouter getRouter(){ return router; }

    public boolean isAppRunning(){
        return appRunning;
    }
    public void setAppRunning(boolean appRunning) {
        this.appRunning = appRunning;
    }
}
