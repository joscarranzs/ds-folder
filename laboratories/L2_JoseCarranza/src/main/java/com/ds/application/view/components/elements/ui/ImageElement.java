// Elemento de UI usado para construir componentes como header, sidebar y controles de formulario.
package com.ds.application.view.components.elements.ui;

import javafx.scene.image.Image;

public class ImageElement {
    // TODO: implement image element for JavaFX
    private Image image;

    public ImageElement(String imagePath){
        image = new Image(imagePath);
        applyStyles();
    }
    private void applyStyles(){
        //Clse CSS
        image.getUrl();
    }
    public Image getNode(){
        return image;
    }
    
}