// Elemento de UI usado para construir componentes como header, sidebar y controles de formulario.
package com.ds.application.view.components.elements.ui;

import javafx.scene.control.TextField;
    /*
    *Componente reutilizable para botones en JavaFX,
    *con estilos dinámicos para estados activos e inactivos.
    */
public class InputElement {
    /*
    instancia interna del boton de JavaFX */
    private TextField input;
        /*
    *Constructor que inicializa el botón con un texto y 
    *aplica estilos predeterminados para el estado inactivo.
    */
 public InputElement(String placeholder){
    input = new TextField();
    input.setPromptText(placeholder);
    setActive(false);
 }
     /*
    Cambia el estado visual del boton entre activo e inactivo
    * aplicando estilos CSS dinámicamente.
    */
   public void setActive(boolean active){
    if(active){
              /*
        boton activo estara de azul */
        input.setStyle(
            "-fx-background-color: #2563eb;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );
    } else {
              /*
        boton inactivo tendra fondo blanco y borde gris */
        input.setStyle(
            "-fx-background-color: #ffffff;" +
            "-fx-border-color: #dbe1ea;"
        );
    }
}
 public String getValue(){
    return input.getText();
 }
 /*
devuelve el nodo JavaFX del boton */
 public TextField getNode(){
    return input;
 }
}