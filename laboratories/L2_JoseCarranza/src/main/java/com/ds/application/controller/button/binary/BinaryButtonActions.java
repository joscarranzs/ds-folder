package com.ds.application.controller.button.binary;

import com.ds.application.view.components.visualizers.BinaryTreeVisualizer;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class BinaryButtonActions {

    private BinaryTreeVisualizer visualizer;

    public BinaryButtonActions(BinaryTreeVisualizer visualizer) {
        this.visualizer = visualizer;
    }

    public void insertNode() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Insertar nodo");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingrese el valor:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            String value = result.get().trim();

            if (value.isEmpty()) {
                showMessage("Debe ingresar un valor.");
                return;
            }

            visualizer.addNode(value);
        }
    }

    public void searchNode() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Buscar nodo");
        dialog.setHeaderText(null);
        dialog.setContentText("Valor a buscar:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            String value = result.get().trim();

            if (value.isEmpty()) {
                showMessage("Debe ingresar un valor.");
                return;
            }

            boolean found = visualizer.searchNode(value);

            if (!found) {
                showMessage("No se encontro el nodo: " + value);
            }
        }
    }

    public void showTraversal(String type) {
        showMessage("Recorrido seleccionado: " + type);
    }

    public void clearTree() {
        visualizer.clear();
    }

    private void showMessage(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mensaje");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
}