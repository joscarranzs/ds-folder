package com.ds.application.view.components.elements.ui;

import javafx.scene.control.Label;

/**
 * Envoltorio para {@link Label} que permite aplicar estilos simples
 * asociados a un estado activo/inactivo y obtener el nodo visual.
 */
public class LabelElement {
    private Label label;

    /**
     * Crea una etiqueta con el texto especificado.
     *
     * @param text texto de la etiqueta
     */
    public LabelElement(String text){
        label = new Label(text);
        setActive(false);
    }

    /**
     * Aplica estilo visual según el estado.
     *
     * @param active true para estilo activo, false para inactivo
     */
    public void setActive(boolean active){
        if(active){
            label.setStyle(
                "-fx-background-color: #2563eb;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
            );
        } else {
            label.setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-border-color: #dbe1ea;"
            );
        }
    }

    /**
     * Devuelve el nodo {@link Label} asociado.
     *
     * @return Label creado
     */
    public Label getNode(){
        return label;
    }
}
