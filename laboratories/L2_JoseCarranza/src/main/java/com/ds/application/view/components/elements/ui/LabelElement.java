// Elemento de UI usado para construir componentes como header, sidebar y controles de formulario.
package com.ds.application.view.components.elements.ui;

import javafx.scene.control.Label;
    /*
    *Componente reutilizable para botones en JavaFX,
    *con estilos dinámicos para estados activos e inactivos.
    */
public class LabelElement {
     /*
    instancia interna del boton de JavaFX */
    private Label label;
        /*
    *Constructor que inicializa el botón con un texto y 
    *aplica estilos predeterminados para el estado inactivo.
    */
    public LabelElement(String text){
        label = new Label(text);
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
        label.setStyle(
            "-fx-background-color: #2563eb;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );
    } else {
                      /*
        boton inactivo tendra fondo blanco y borde gris */
        label.setStyle(
            "-fx-background-color: #ffffff;" +
            "-fx-border-color: #dbe1ea;"
        );
    }
}
 /*
devuelve el nodo JavaFX del boton */
    public Label getNode(){
        return label;
    }
}
