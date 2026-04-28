// Elemento de UI usado para construir componentes como header, sidebar y controles de formulario.

package com.ds.application.view.components.elements.ui;
import javafx.scene.control.ListView;
public class ListElement {
    // TODO: implement list element for JavaFX
    private ListView<String> listView;

    public ListElement(){
        listView = new ListView<>();
        applyStyles();
    }
    private void applyStyles(){
        listView.getStylesheets();
    }
    public ListView<String> getNode(){
        return listView;
    }
}