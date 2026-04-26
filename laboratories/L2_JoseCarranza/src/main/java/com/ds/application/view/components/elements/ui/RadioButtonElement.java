package com.ds.application.view.components.elements.ui;

import javafx.scene.control.RadioButton;

/**
 * Envoltorio para {@link RadioButton} que permite alternar estilos
 * y obtener el nodo visual.
 */
public class RadioButtonElement {
    private RadioButton radioButton;

    /**
     * Crea un RadioButton con la etiqueta indicada.
     *
     * @param label texto del RadioButton
     */
    public RadioButtonElement(String label){
        radioButton = new RadioButton(label);
        setActive(false);
    }

    /**
     * Aplica estilo según el estado activo/inactivo.
     *
     * @param active true para estilo activo, false para inactivo
     */
    public void setActive(boolean active){
        if(active){
            radioButton.setStyle(
                "-fx-background-color: #2563eb;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
            );
        } else {
            radioButton.setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-border-color: #dbe1ea;"
            );
        }
    }

    /**
     * Devuelve el nodo {@link RadioButton} asociado.
     *
     * @return RadioButton creado
     */
    public RadioButton getNode(){
        return radioButton;
    }
}
