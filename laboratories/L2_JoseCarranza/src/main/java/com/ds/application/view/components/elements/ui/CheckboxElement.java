// Elemento de UI usado para construir componentes como header, sidebar y controles de formulario.
package com.ds.application.view.components.elements.ui;

import javafx.scene.control.CheckBox;

public class CheckboxElement {
    // TODO: implement checkbox element for JavaFX
    private CheckBox checkBox;

    public CheckboxElement(String label){
        checkBox = new CheckBox(label);
        applyStyles();
    }
    private void applyStyles(){
        //clase CSS
        applyStyles();
    }

    public CheckBox getNode(){
        return checkBox;
    }

}