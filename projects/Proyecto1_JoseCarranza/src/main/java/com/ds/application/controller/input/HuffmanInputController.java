package com.ds.application.controller.input;

import com.ds.application.controller.button.huffman.EncodingController;

/**
 * Controla la entrada para el algoritmo de Huffman.
 */
public class HuffmanInputController {
  // Controlador para manejar la generación de árboles de Huffman a partir de texto o pesos.
  private final EncodingController encodingController;

  /**
   * Constructor que recibe el controlador de codificación de Huffman.
   *
   * @param encodingController controlador para generar árboles de Huffman.
   */
  public HuffmanInputController(EncodingController encodingController) {
    this.encodingController = encodingController;
  }

  /**
   * Procesa el texto segun el modo seleccionado.
   *
   * @param text       entrada del usuario.
   * @param weightMode true si se trabaja con pesos.
   */
  public void generate(String text, boolean weightMode) {
    // Validar que el texto no sea nulo o vacío antes de procesar
    if (weightMode) {
      // Si se trabaja con pesos, generar el árbol de Huffman a partir de los pesos proporcionados en el texto
      encodingController.generateWeight(text);
    } else {
      // Si se trabaja con texto, generar el árbol de Huffman a partir del texto proporcionado
      encodingController.generateText(text);
    }
  }

  /**
   * Limpia el estado del algoritmo y el texto.
   */
  public void clear() {
    encodingController.clear();
  }
}
