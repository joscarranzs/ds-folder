package com.ds.application.view.components.elements.ui;

import javafx.scene.control.TextField;

/**
 * Envoltorio para {@link TextField} que provee un placeholder y estilos básicos
 * según un estado activo o inactivo.
 */
public class InputElement {
    private TextField input;

    /**
     * Crea un campo de texto con un texto de ayuda (placeholder).
     *
     * @param placeholder texto de ayuda mostrado cuando el campo está vacío
     */
    public InputElement(String placeholder){
        input = new TextField();
        input.setPromptText(placeholder);
        setActive(false);
    }

    /**
     * Aplica estilo visual según el estado activo/inactivo.
     *
     * @param active true para estilo activo, false para inactivo
     */
    public void setActive(boolean active){
        if(active){
            input.setStyle(
                "-fx-background-color: #2563eb;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
            );
        } else {
            input.setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-border-color: #dbe1ea;"
            );
        }
    }

    /**
     * Obtiene el texto actual del campo.
     *
     * @return contenido del TextField
     */
    public String getValue(){
        return input.getText();
    }

    /**
     * Devuelve el nodo {@link TextField} asociado.
     *
     * @return TextField creado
     */
    public TextField getNode(){
        return input;
    }
}
