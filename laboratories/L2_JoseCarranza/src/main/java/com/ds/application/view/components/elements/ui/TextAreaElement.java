package com.ds.application.view.components.elements.ui;

import javafx.scene.control.TextArea;

/**
 * Wrapper simple para TextArea con estilos aplicados por defecto.
 * Usamos este componente en los paneles laterales para mantener
 * consistencia visual del input de texto.
 */
public class TextAreaElement {

    private TextArea textArea;

    /**
     * Crea el elemento con placeholder y comportamiento inicial.
     *
     * @param placeholder texto de ayuda.
     */
    public TextAreaElement(String placeholder) {
        textArea = new TextArea();
        textArea.setPromptText(placeholder);
        textArea.setWrapText(true);
        setActive(false);
    }

    /**
     * Activa o desactiva el estilo de foco del TextArea.
     *
     * @param active true para aplicar el estilo activo; false para el estilo por defecto
     */
    public void setActive(boolean active) {
        if (active) {
            textArea.setStyle(
                    "-fx-background-color: #ffffff;" +
                    "-fx-border-color: #2563eb;" +
                    "-fx-border-width: 2;" +
                    "-fx-border-radius: 8;" +
                    "-fx-background-radius: 8;" +
                    "-fx-padding: 8 10;"
            );
        } else {
            textArea.setStyle(
                    "-fx-background-color: #ffffff;" +
                    "-fx-border-color: #dbe1ea;" +
                    "-fx-border-radius: 8;" +
                    "-fx-background-radius: 8;" +
                    "-fx-padding: 8 10;"
            );
        }
    }

    /**
     * Obtiene el texto actual del TextArea.
     *
     * @return el texto ingresado por el usuario
     */
    public String getValue() {
        return textArea.getText();
    }

    /**
     * Devuelve el nodo JavaFX subyacente usado por este wrapper.
     *
     * @return el TextArea interno
     */
    public TextArea getNode() {
        return textArea;
    }
}
