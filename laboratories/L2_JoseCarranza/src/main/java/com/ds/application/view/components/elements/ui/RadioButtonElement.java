// Elemento de UI usado para construir componentes como header, sidebar y controles de formulario.

package com.ds.application.view.components.elements.ui;
public class RadioButtonElement {
    // TODO: implement radio button element for JavaFX
    private RadioButton radioButton;

    public RadioButtonElement(String label){
        radioButton = new RadioButton(label);
        applyStyles();
    }
    private void applyStyles(){
        radioButton.getStylesheets();
    }

    public RadioButton getNode(){
        return radioButton;
    }
}