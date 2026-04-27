// Elemento de UI usado para construir componentes como header, sidebar y controles de formulario.
package com.ds.application.view.components.elements.ui;

import javafx.scene.control.Button;
    /*
    *Componente reutilizable para botones en JavaFX,
    *con estilos dinámicos para estados activos e inactivos.
    */
public class ButtonElement {
    /*
    instancia interna del boton de JavaFX */
    private Button button;
    /*
    *Constructor que inicializa el botón con un texto y 
    *aplica estilos predeterminados para el estado inactivo.
    */
    public ButtonElement(String text){
        button = new Button(text);
        setActive(false );
    }

    /*
    Cambia el estado visual del boton entre activo e inactivo
    * aplicando estilos CSS dinámicamente.
    */
    public void setActive(boolean active){
    if(active){
        button.setStyle(
            "-fx-background-color: #2563eb;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 10;" +
            "-fx-padding: 8 14;" +
            "-fx-cursor: hand;" +
            "-fx-effect: dropshadow(gaussian, rgba(37,99,235,0.3), 8, 0.3, 0, 2);"
        );
    } else {
        button.setStyle(
            "-fx-background-color: #f8fafc;" +
            "-fx-text-fill: #1e293b;" +
            "-fx-font-size: 13px;" +
            "-fx-background-radius: 10;" +
            "-fx-border-color: #e2e8f0;" +
            "-fx-border-radius: 10;" +
            "-fx-padding: 8 14;" +
            "-fx-cursor: hand;"
        );
    }
}
/*
devuelve el nodo JavaFX del boton */
    public Button getNode(){
        return button;
    }

}