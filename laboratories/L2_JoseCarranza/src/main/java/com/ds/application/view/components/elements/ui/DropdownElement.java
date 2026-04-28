// Elemento de UI usado para construir componentes como header, sidebar y controles de formulario.

package com.ds.application.view.components.elements.ui;
public class DropdownElement {
    // TODO: implement dropdown/combo box element for JavaFX
    private ComboBox<String> comboBox;

    public DropdownElement(String[] options){
        comboBox = new ComboBox<>();
        comboBox.getItems().addAll(options);
        applyStyles();
    }

    private void applyStyles(){
        //CSS class
        comboBox.getStylesheets();
    }

    public ComboBox<String> getNode(){
        return comboBox;
    }
    
}