package com.ds.application.view.components.elements.ui;

import javafx.scene.control.TextArea;

/**
 * Envoltorio para {@link TextArea} que soporta placeholder, extracción de
 * valor y estilos básicos por estado.
 */
public class TextAreaElement {
    private TextArea textArea;

    /**
     * Crea un TextArea con texto de ayuda.
     *
     * @param placeholder texto que aparece cuando el área está vacía
     */
    public TextAreaElement(String placeholder){
        textArea = new TextArea();
        textArea.setPromptText(placeholder);
        setActive(false);
    }

    /**
     * Aplica estilo según el estado activo/inactivo.
     *
     * @param active true para estilo activo, false para inactivo
     */
    public void setActive(boolean active){
        if(active){
            textArea.setStyle(
                "-fx-background-color: #2563eb;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
            );
        } else {
            textArea.setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-border-color: #dbe1ea;"
            );
        }
    }

    /**
     * Obtiene el texto contenido en el TextArea.
     *
     * @return contenido actual del TextArea
     */
    public String getValue(){
        return textArea.getText();
    }

    /**
     * Devuelve el nodo {@link TextArea} asociado.
     *
     * @return TextArea creada
     */
    public TextArea getNode(){
        return textArea;
    }
}
