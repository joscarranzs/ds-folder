package com.ds.application.view.window;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Gestor que centraliza la ventana principal (Stage) y la Scene.
 *
 * Acepta cualquier Parent de JavaFX como raíz de layout (por ejemplo BorderPane,
 * StackPane, etc.) y proporciona un método seguro para reemplazar la raíz de la
 * escena en tiempo de ejecución.
 */
public class Primary {

    private final Stage stage;
    private final Scene scene;
    private final String DEFAULT_TITLE = "Visualizador de Árboles";

    /**
     * Crea el gestor de ventana principal.
     *
     * @param stage Stage provisto por Application.start
     * @param initialRoot nodo raíz inicial (cualquier Pane / Parent)
     * @param width ancho inicial de la Scene
     * @param height alto inicial de la Scene
     */
    public Primary(Stage stage, Parent initialRoot, double width, double height) {
        this.stage = stage;
        this.stage.setTitle(DEFAULT_TITLE);
        this.scene = new Scene(initialRoot, width, height);

        stage.setScene(scene);
        // Configuración de tamaño mínimo y centrado en pantalla
        stage.setMinWidth(1100);
        stage.setMinHeight(700);
        stage.centerOnScreen();
    }

    /** Muestra el stage (se ejecuta en el hilo de JavaFX). */
    public void show() {
        Platform.runLater(() -> {
            if (!stage.isShowing()) {
                stage.show();
            } else {
                stage.toFront();
            }
        });
    }

    /**
     * Reemplaza el nodo raíz de la Scene. Usar para cambiar vistas en tiempo de ejecución.
     * El método es seguro para invocarse desde cualquier hilo; la operación se realizará
     * en el hilo de la aplicación JavaFX.
     *
     * @param newRoot nuevo nodo raíz (por ejemplo BorderPane, StackPane, VBox...)
     */
    public void setRoot(Parent newRoot) {
        Platform.runLater(() -> scene.setRoot(newRoot));
    }

    /**
     * Actualiza el título de la ventana. Seguro para invocarse desde cualquier hilo.
     *
     * @param title nuevo título de la ventana
     */
    public void setTitle(String title) {
        Platform.runLater(() -> stage.setTitle(title));
    }

    // Stage y Scene son privados para evitar acceso directo desde otras clases, garantizando que
    // todas las modificaciones se realicen a través de métodos controlados.
}
