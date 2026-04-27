package com.ds.application.view.components.elements.ui;

/**
 * Representa un contenido de texto sencillo que puede ser utilizado por
 * componentes visuales (por ejemplo, un {@link javafx.scene.control.Label}).
 */
public class TextElement {
    private String text;

    /**
     * Crea un TextElement con la cadena proporcionada.
     *
     * @param text texto a almacenar
     */
    public TextElement(String text){
        this.text = text;
    }

    /**
     * Devuelve el texto almacenado en este elemento.
     *
     * @return texto contenido
     */
    public String getText(){
        return text;
    }
}
