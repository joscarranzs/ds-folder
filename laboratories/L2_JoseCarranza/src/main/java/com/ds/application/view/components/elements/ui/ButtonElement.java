// Elemento de UI usado para construir componentes como header, sidebar y controles de formulario.
package com.ds.application.view.components.elements.ui;

import javafx.scene.control.Button;

public class ButtonElement {
    // TODO: implement button component for JavaFX
    private Button button;
    public ButtonElement(String text){
        button = new Button(text);
        applyStyles();
    }
    private void applyStyles(){
        //clase CSS
        button.getStyleClass();
    }
    
    public Button getNode(){
        return button;
    }

}
