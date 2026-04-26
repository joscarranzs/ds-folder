// Elemento de UI usado para construir componentes como header, sidebar y controles de formulario.
package com.ds.application.view.components.elements.ui;

import javafx.scene.control.TextField;
public class InputElement {
    // TODO: implement input component for JavaFX
    private TextField input;
    
 public InputElement(String placeholder){
    input = new TextField();
    input.setPromptText(placeholder);
    applyStyles();
 }
   private void applyStyles(){
      //clase CSS
      input.getStyleClass();
   }

 public String getValue(){
    return input.getText();
 }
 public TextField getNode(){
    return input;
 }
}
