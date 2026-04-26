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
        /*
        boton activo estara de azul */
        button.setStyle(
            "-fx-background-color: #2563eb;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );
    } else {
        /*
        boton inactivo tendra fondo blanco y borde gris */
        button.setStyle(
            "-fx-background-color: #ffffff;" +
            "-fx-border-color: #dbe1ea;"
        );
    }
}
/*
devuelve el nodo JavaFX del boton */
    public Button getNode(){
        return button;
    }

}