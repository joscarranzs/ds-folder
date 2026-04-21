package com.ds.application.core.controller;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;

public final class TreeDialogService {

    private TreeDialogService() {
    }

    public static Optional<Integer> requestInteger(String title, String prompt) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(null);
        dialog.setContentText(prompt);

        return dialog.showAndWait()
                .map(String::trim)
                .flatMap(TreeDialogService::parseInteger);
    }

    private static Optional<Integer> parseInteger(String rawValue) {
        if (rawValue.isEmpty()) {
            showError("Invalid input", "Empty value", "Please enter a valid integer.");
            return Optional.empty();
        }

        try {
            return Optional.of(Integer.parseInt(rawValue));
        } catch (NumberFormatException ignored) {
            showError("Invalid input", "Non-numeric value", "Please enter a valid integer.");
            return Optional.empty();
        }
    }

    public static void showError(String title, String header, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showInformation(String title, String header, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
