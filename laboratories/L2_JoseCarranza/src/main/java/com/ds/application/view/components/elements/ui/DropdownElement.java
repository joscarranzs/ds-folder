package com.ds.application.view.components.elements.ui;

import javafx.scene.control.ComboBox;

/**
 * Envoltorio sencillo para {@link ComboBox} que facilita la creación
 * con un conjunto de opciones y la aplicación de estilos básicos.
 */
public class DropdownElement {
    private ComboBox<String> comboBox;

    /**
     * Crea un combo con las opciones provistas.
     *
     * @param options arreglo de opciones a añadir
     */
    public DropdownElement(String[] options){
        comboBox = new ComboBox<>();
        comboBox.getItems().addAll(options);
        setActive(false);
    }

    /**
     * Aplica estilo visual dependiendo del estado activo/inactivo.
     *
     * @param active true para estilo activo, false para inactivo
     */
    public void setActive(boolean active){
        if(active){
            comboBox.setStyle(
                "-fx-background-color: #2563eb;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
            );
        } else {
            comboBox.setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-border-color: #dbe1ea;"
            );
        }
    }

    /**
     * Devuelve el nodo {@link ComboBox} asociado.
     *
     * @return ComboBox con las opciones añadidas
     */
public ComboBox<String> getNode(){
        return comboBox;
    }
}
