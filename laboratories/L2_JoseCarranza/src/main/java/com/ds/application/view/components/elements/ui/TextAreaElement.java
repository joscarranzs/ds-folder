// Elemento de UI usado para construir componentes como header, sidebar y controles de formulario.
package com.ds.application.view.components.elements.ui;

import javafx.scene.control.TextArea;

public class TextAreaElement {
    // TODO: implement text area element for JavaFX
    private TextArea textArea;
    
    public TextAreaElement(String placeholder){
        textArea = new TextArea();
        textArea.setPromptText(placeholder);
        applyStyles();
    }

    private void applyStyles(){
        //clase CSS
        textArea.getStyleClass();
    }

    public String getValue(){
        return textArea.getText();
    }
    public TextArea getNode(){
        return textArea;
    }

}