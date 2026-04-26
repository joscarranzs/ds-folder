package com.ds.application.view.components.elements.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Componente visual para iconos basado en {@link ImageView}.
 * Realiza una carga segura de la imagen y expone métodos para
 * obtener el nodo y comprobar errores de carga.
 */
public class IconElement {
    private Image image;
    private ImageView imageView;

    /**
     * Crea un icono con dimensiones de ajuste específicas.
     *
     * @param imagePath ruta o URL de la imagen
     * @param fitWidth ancho objetivo del ImageView
     * @param fitHeight alto objetivo del ImageView
     */
    public IconElement(String imagePath, double fitWidth, double fitHeight){
        try {
            image = new Image(imagePath, fitWidth, fitHeight, true, true);
            imageView = new ImageView(image);
            imageView.setFitWidth(fitWidth);
            imageView.setFitHeight(fitHeight);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
        } catch (Exception e){
            imageView = new ImageView();
        }
    }

    /**
     * Crea un icono con tamaño por defecto (16x16).
     *
     * @param imagePath ruta o URL de la imagen
     */
    public IconElement(String imagePath){
        this(imagePath, 16, 16);
    }

    /**
     * Devuelve el {@link ImageView} a insertar en la escena.
     *
     * @return ImageView creado (vacío si la carga falló)
     */
    public ImageView getNode(){
        return imageView;
    }

    /**
     * Indica si la carga de la imagen falló o no está disponible.
     *
     * @return true si hay error en la imagen
     */
    public boolean isError(){
        return image == null || image.isError();
    }
}
