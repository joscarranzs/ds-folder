// Elemento de UI usado para construir componentes como header, sidebar y controles de formulario.

package com.ds.application.view.components.elements.ui;
import javafx.scene.control.TableView;
public class TableElement {
    // TODO: implement table element for JavaFX
    private TableView<String> tableView;

    public TableElement(){
        tableView = new TableView<>();
        applyStyles();
    }
    private void applyStyles(){
        tableView.getStylesheets();
    }
    public TableView<String> getNode(){
        return tableView;
    }
}