package com.ds.application.view.components.header;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Logo {

  /**
   * Componente simple para representar un logo a partir de un recurso.
   * Lo uso en el header para mantener la logica de carga centralizada.
   */

  private final ImageView imageView;

  /**
   * Crea un Logo cargando la imagen desde recursos.
   *
   * @param path ruta del recurso dentro del jar (ej: /images/foo.png)
   * @param size tamaño (ancho/alto) para ajustar la imagen
   */
  public Logo(String path, double size) {
    imageView = new ImageView();
    imageView.setFitWidth(size);
    imageView.setFitHeight(size);
    imageView.setPreserveRatio(true);

    java.io.InputStream stream = getClass().getResourceAsStream(path);
    if (stream != null) {
      imageView.setImage(new Image(stream));
    }
  }

  /**
   * Devuelve el ImageView que contiene la imagen cargada.
   *
   * @return ImageView con la imagen del logo
   */
  public ImageView getNode() {
    return imageView;
  }
}
