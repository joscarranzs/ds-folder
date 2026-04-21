package com.ds;

import com.ds.application.core.view.WelcomeScreen;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Punto de entrada principal de la aplicación.
 *
 * <p>Arranca la pantalla de bienvenida antes de cargar el visualizador principal.</p>
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        new WelcomeScreen().show(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}