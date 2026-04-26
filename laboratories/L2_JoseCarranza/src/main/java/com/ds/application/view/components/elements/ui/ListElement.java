package com.ds.application.view.components.elements.ui;

import javafx.scene.control.ListView;

/**
 * Envoltorio para {@link ListView} que expone un nodo ya configurado y
 * permite alternar un estilo simple entre activo e inactivo.
 */
public class ListElement {
    private ListView<String> listView;

    /**
     * Crea una lista vacía y aplica el estilo inicial (inactivo).
     */
    public ListElement(){
        listView = new ListView<>();
        setActive(false);
    }

    /**
     * Alterna el estilo visual de la lista.
     *
     * @param active true para estilo activo, false para inactivo
     */
    public void setActive(boolean active){
        if(active){
            listView.setStyle(
                "-fx-background-color: #2563eb;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
            );
        } else {
            listView.setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-border-color: #dbe1ea;"
            );
        }
    }

    /**
     * Devuelve el nodo {@link ListView} para su inserción en la escena.
     *
     * @return instancia de ListView
     */
    public ListView<String> getNode(){
        return listView;
    }
}
