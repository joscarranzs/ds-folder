package com.ds.application.view.window;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Helper that centralises primary window (Stage) and Scene management.
 *
 * It accepts any JavaFX Parent as the layout root (for example BorderPane, StackPane, etc.)
 * and exposes a safe method to replace the scene root at runtime.
 */
public class Primary {

    private final Stage stage;
    private final Scene scene;

    /**
     * Create a Primary window manager.
     *
     * @param stage the JavaFX Stage provided by Application.start
     * @param initialRoot initial root node (can be any Pane / Parent)
     * @param width initial scene width
     * @param height initial scene height
     */
    public Primary(Stage stage, Parent initialRoot, double width, double height) {
        this.stage = stage;
        this.scene = new Scene(initialRoot, width, height);

        stage.setScene(scene);
        // sensible defaults — App may override title or sizing
        stage.setMinWidth(1100);
        stage.setMinHeight(700);
        stage.centerOnScreen();
    }

    /** Show the stage (runs on JavaFX thread). */
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
     * Replace the root node of the scene. Use this to switch views at runtime.
     * This method is safe to call from any thread; the change will be executed
     * on the JavaFX application thread.
     *
     * @param newRoot new root node (for example a BorderPane, StackPane, VBox...)
     */
    public void setRoot(Parent newRoot) {
        Platform.runLater(() -> scene.setRoot(newRoot));
    }

    public Parent getRoot() {
        return scene.getRoot();
    }

    public Scene getScene() {
        return scene;
    }

    public Stage getStage() {
        return stage;
    }
}
