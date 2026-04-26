// Elemento de UI usado para construir componentes como header, sidebar y controles de formulario.

package com.ds.application.view.components.elements.ui;
import javafx.scene.control.RadioButton;
public class RadioButtonElement {
    // TODO: implement radio button element for JavaFX
    private RadioButton radioButton;

    public RadioButtonElement(String label){
        radioButton = new RadioButton(label);
        setActive(false);
    }
   public void setActive(boolean active){
    if(active){
        radioButton.setStyle(
            "-fx-background-color: #2563eb;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );
    } else {
        radioButton.setStyle(
            "-fx-background-color: #ffffff;" +
            "-fx-border-color: #dbe1ea;"
        );
    }
}

    public RadioButton getNode(){
        return radioButton;
    }
}
