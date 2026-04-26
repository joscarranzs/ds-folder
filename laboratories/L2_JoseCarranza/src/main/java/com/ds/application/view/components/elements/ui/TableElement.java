package com.ds.application.view.components.elements.ui;

import javafx.scene.control.TableView;

/**
 * Envoltorio para {@link TableView} que expone un nodo configurado y
 * permite alternar estilos básicos.
 */
public class TableElement {
    private TableView<String> tableView;

    /**
     * Crea una tabla vacía y aplica el estilo inicial (inactivo).
     */
    public TableElement(){
        tableView = new TableView<>();
        setActive(false);
    }

    /**
     * Alterna el estilo visual de la tabla.
     *
     * @param active true para estilo activo, false para inactivo
     */
    public void setActive(boolean active){
        if(active){
            tableView.setStyle(
                "-fx-background-color: #2563eb;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
            );
        } else {
            tableView.setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-border-color: #dbe1ea;"
            );
        }
    }

    /**
     * Devuelve el nodo {@link TableView} asociado.
     *
     * @return TableView creada
     */
    public TableView<String> getNode(){
        return tableView;
    }
}
