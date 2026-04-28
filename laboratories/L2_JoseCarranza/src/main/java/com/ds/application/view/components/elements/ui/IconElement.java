// Elemento de UI usado para construir componentes como header, sidebar y controles de formulario.
package com.ds.application.view.components.elements.ui;


public class IconElement {
    // TODO: implement icon component for JavaFX
    private Image image;

    public IconElement(String imagePath){
        image = new Image(imagePath);
        applyStyles();
    }

    private void applyStyles(){
        //Clase CSS
        image.getUrl();
    }

    public Image getnode(){
        return image;
    }
}