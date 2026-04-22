package com.ds;

import com.ds.application.core.view.WelcomeScreen;

import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Punto de entrada de la aplicación para el visualizador de árboles binarios.
 *
 * <p>Esta clase inicia el runtime de JavaFX y muestra la pantalla de bienvenida.</p>
 */
public class App extends Application {

    /**
     * Inicializa y muestra el escenario primario de JavaFX.
     *
     * @param stage el escenario primario proporcionado por el runtime de JavaFX
     */
    @Override
    public void start(Stage stage) {
        new WelcomeScreen().show(stage);
    }

    /**
     * Inicia la aplicación JavaFX.
     *
     * @param args argumentos de línea de comandos suministrados por el runtime
     */
    public static void main(String[] args) {
        launch(args);
    }
}