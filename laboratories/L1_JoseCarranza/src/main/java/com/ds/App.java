package com.ds;

import com.ds.application.core.view.WelcomeScreen;

import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application {

    @Override
    public void start(Stage stage) {
        new WelcomeScreen().show(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}