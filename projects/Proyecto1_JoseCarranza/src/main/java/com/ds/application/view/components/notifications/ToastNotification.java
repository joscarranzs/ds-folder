package com.ds.application.view.components.notifications;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class ToastNotification extends StackPane {

    // Caja visual pequeña de la notificación
    private final StackPane toastBox;

    // Texto de la notificación
    private final Label messageLabel;

    public ToastNotification() {
        // No bloquea interacción con la app
        setPickOnBounds(false);
        setMouseTransparent(true);

        // Posición arriba a la derecha
        setAlignment(Pos.TOP_RIGHT);
        setPadding(new Insets(74, 32, 0, 0));

        toastBox = new StackPane();
        toastBox.setVisible(false);
        toastBox.setOpacity(0);
        toastBox.setMinHeight(0);
        toastBox.setPrefHeight(52);
        toastBox.setMaxHeight(52);
        toastBox.setMaxWidth(360);
        toastBox.setPadding(new Insets(12, 18, 12, 18));

        messageLabel = new Label();
        messageLabel.setWrapText(true);
        messageLabel.setMaxWidth(310);
        messageLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;"
        );

        toastBox.getChildren().add(messageLabel);
        getChildren().add(toastBox);
    }

    public void showError(String message) {
        show(message, "#fee2e2", "#991b1b", "#ef4444");
    }

    public void showWarning(String message) {
        show(message, "#fef3c7", "#92400e", "#f59e0b");
    }

    public void showSuccess(String message) {
        show(message, "#dcfce7", "#166534", "#22c55e");
    }

    public void showInfo(String message) {
        show(message, "#dbeafe", "#1e40af", "#2563eb");
    }

    private void show(String message, String bgColor, String textColor, String borderColor) {
        messageLabel.setText(message);
        messageLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + textColor + ";"
        );

        toastBox.setStyle(
                "-fx-background-color: " + bgColor + ";" +
                "-fx-background-radius: 16;" +
                "-fx-border-color: " + borderColor + ";" +
                "-fx-border-radius: 16;" +
                "-fx-border-width: 1.5;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.18), 18, 0.25, 0, 6);"
        );

        toastBox.setVisible(true);
        toastBox.setOpacity(0);
        toastBox.setTranslateY(-14);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(180), toastBox);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        TranslateTransition slideIn = new TranslateTransition(Duration.millis(180), toastBox);
        slideIn.setFromY(-14);
        slideIn.setToY(0);

        PauseTransition wait = new PauseTransition(Duration.seconds(2.2));

        FadeTransition fadeOut = new FadeTransition(Duration.millis(260), toastBox);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished(e -> toastBox.setVisible(false));

        fadeIn.play();
        slideIn.play();

        wait.setOnFinished(e -> fadeOut.play());
        wait.play();
    }
}