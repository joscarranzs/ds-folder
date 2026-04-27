package com.ds.application.view.components.elements.ui;

import javafx.scene.control.ToggleButton;

/**
 * Envoltorio para {@link ToggleButton} que permite crear un interruptor
 * y alternar su estilo entre activo e inactivo.
 */
public class ToggleSwitchElement {
    private ToggleButton toggleButton;

    /**
     * Crea un ToggleButton con la etiqueta indicada.
     *
     * @param label texto del interruptor
     */
    public ToggleSwitchElement(String label){
        toggleButton = new ToggleButton(label);
        setActive(false);
    }

    /**
     * Aplica estilo visual según el estado.
     *
     * @param active true para estilo activo, false para inactivo
     */
    public void setActive(boolean active){
        if(active){
            toggleButton.setStyle(
                "-fx-background-color: #2563eb;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
            );
        } else {
            toggleButton.setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-border-color: #dbe1ea;"
            );
        }
    }

    /**
     * Devuelve el nodo {@link ToggleButton} asociado.
     *
     * @return ToggleButton creado
     */
    public ToggleButton getNode(){
        return toggleButton;
    }
}
