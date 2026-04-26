package com.ds.application.view.components.elements.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Componente para imágenes que devuelve un {@link ImageView} y permite
 * comprobar si la carga de la imagen falló.
 */
public class ImageElement {
    private Image image;
    private ImageView imageView;

    /**
     * Crea una imagen con dimensiones de ajuste específicas.
     *
     * @param imagePath ruta o URL de la imagen
     * @param fitWidth ancho de ajuste
     * @param fitHeight alto de ajuste
     */
    public ImageElement(String imagePath, double fitWidth, double fitHeight){
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
     * Crea una imagen con tamaño por defecto (100x100).
     *
     * @param imagePath ruta o URL de la imagen
     */
    public ImageElement(String imagePath){
        this(imagePath, 100, 100);
    }

    /**
     * Devuelve el nodo {@link ImageView} asociado.
     *
     * @return ImageView creado
     */
    public ImageView getNode(){
        return imageView;
    }

    /**
     * Indica si hubo un error al cargar la imagen.
     *
     * @return true si la imagen no está disponible o tiene error
     */
    public boolean isError(){
        return image == null || image.isError();
    }
}
