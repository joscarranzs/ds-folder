// Elemento de UI usado para construir componentes como header, sidebar y controles de formulario.

package com.ds.application.view.components.elements.ui;
public class ToggleSwitchElement {
    // TODO: implement toggle switch element for JavaFX
    private ToggleButton toggleButton;
    
    public ToggleSwitchElement(String label){
        toggleButton = new ToggleButton(label);
        applyStyles();
    }

    private void applyStyles(){
        toggleButton.getStylesheets();
    }

    public ToggleButton getNode(){
        return toggleButton;
    }
}