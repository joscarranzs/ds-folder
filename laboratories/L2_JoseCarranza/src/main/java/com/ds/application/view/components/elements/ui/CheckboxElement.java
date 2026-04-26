// Elemento de UI usado para construir componentes como header, sidebar y controles de formulario.
package com.ds.application.view.components.elements.ui;

import javafx.scene.control.CheckBox;
    /*
    *Componente reutilizable para botones en JavaFX,
    *con estilos dinámicos para estados activos e inactivos.
    */
public class CheckboxElement {
       /*
    instancia interna del boton de JavaFX */
    private CheckBox checkBox;
  /*
    *Constructor que inicializa el botón con un texto y 
    *aplica estilos predeterminados para el estado inactivo.
    */
    public CheckboxElement(String label){
        checkBox = new CheckBox(label);
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
        checkBox.setStyle(
            "-fx-background-color: #2563eb;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );
    } else {
              /*
        boton inactivo tendra fondo blanco y borde gris */
        checkBox.setStyle(
            "-fx-background-color: #ffffff;" +
            "-fx-border-color: #dbe1ea;"
        );
    }

}
/*
devuelve el nodo JavaFX del boton */
public CheckBox getNode(){
        return checkBox;
}
}


