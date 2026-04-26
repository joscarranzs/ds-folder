// Elemento de UI usado para construir componentes como header, sidebar y controles de formulario.
package com.ds.application.view.components.elements.ui;
import javafx.scene.control.TextArea;
       /*
    *Componente reutilizable para botones en JavaFX,
    *con estilos dinámicos para estados activos e inactivos.
    */
public class TextElement {
      /*
    instancia interna del boton de JavaFX */
    private String text;
            /*
    *Constructor que inicializa el botón con un texto y 
    *aplica estilos predeterminados para el estado inactivo.
    */
    public TextElement(String text){
        this.text= text;
        applyStyles();
    }
    private void applyStyles(){
        //Clase CSS para el texto
        applyStyles();
    }
/*
devuelve el nodo JavaFX del boton */
        public String getText(){
        return text;
    }
}