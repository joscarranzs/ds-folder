package com.ds.application.view.components.elements.ui;

import javafx.scene.control.CheckBox;

/**
 * Componente envoltorio para {@link CheckBox} que facilita la aplicación
 * de estilos y la obtención del nodo visual.
 */
public class CheckboxElement {
    private CheckBox checkBox;
    /**
     * Crea un checkbox con la etiqueta indicada. Estado inicial: inactivo.
     *
     * @param label etiqueta del checkbox
     */
    public CheckboxElement(String label){
        checkBox = new CheckBox(label);
        setActive(false);
    }

    /**
     * Cambia el estilo visual entre activo e inactivo.
     *
     * @param active true para aplicar estilo activo, false para inactivo
     */
    public void setActive(boolean active){
        if(active){
            checkBox.setStyle(
                "-fx-background-color: #2563eb;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
            );
        } else {
            checkBox.setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-border-color: #dbe1ea;"
            );
        }
    }

    /**
     * Devuelve el nodo {@link CheckBox} que representa este componente.
     *
     * @return CheckBox instanciado
     */
    public CheckBox getNode(){
        return checkBox;
    }
}


