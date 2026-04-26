// Elemento de UI usado para construir componentes como header, sidebar y controles de formulario.
package com.ds.application.view.components.elements.ui;

import javafx.scene.control.Label;

public class LabelElement {
    // TODO: implement label/text element for JavaFX
    private Label label;

    public LabelElement(String text){
        label = new Label(text);
        applyStyles();
    }

    private void applyStyles(){
        //Class CSS
        label.getStylesheets();
    }

    public Label getNode(){
        return label;
    }
}