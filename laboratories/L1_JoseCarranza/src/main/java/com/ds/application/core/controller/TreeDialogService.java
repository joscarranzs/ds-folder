package com.ds.application.core.controller;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;

/**
 * Clase de utilidad para interacciones de diálogo entre el usuario y el visualizador de árboles.
 *
 * <p>Esta clase centraliza las solicitudes de entrada entera y las alertas informativas/de error
 * usadas por los componentes de controlador y vista.</p>
 */
public final class TreeDialogService {

    private TreeDialogService() {
    }

    /**
     * Muestra un cuadro de diálogo para que el usuario ingrese un valor entero.
     *
     * @param title el título de la ventana de diálogo
     * @param prompt el texto de invitación mostrado al usuario
     * @return un {@link Optional} que contiene el entero parseado si es válido, de lo contrario vacío
     */
    public static Optional<Integer> requestInteger(String title, String prompt) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(null);
        dialog.setContentText(prompt);

        return dialog.showAndWait()
                .map(String::trim)
                .flatMap(TreeDialogService::parseInteger);
    }

    /**
     * Intenta convertir la entrada del usuario en un entero y muestra un diálogo de error si falla la conversión.
     *
     * @param rawValue el texto bruto ingresado por el usuario
     * @return un {@link Optional} con el entero parseado si es válido, de lo contrario vacío
     */
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

    /**
     * Muestra una alerta modal de error con el título, encabezado y mensaje proporcionados.
     *
     * @param title el título de la alerta
     * @param header el encabezado que resume el problema
     * @param message el mensaje detallado mostrado al usuario
     */
    public static void showError(String title, String header, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Muestra una alerta modal informativa con el título, encabezado y mensaje proporcionados.
     *
     * @param title el título de la alerta
     * @param header el encabezado que describe la información
     * @param message el mensaje detallado mostrado al usuario
     */
    public static void showInformation(String title, String header, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
