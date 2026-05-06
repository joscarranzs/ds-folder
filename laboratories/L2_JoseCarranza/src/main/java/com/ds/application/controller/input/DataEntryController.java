package com.ds.application.controller.input;

public class DataEntryController {
  /**
   * Controla la validacion de entradas numericas para el arbol.
   */

  /**
   * Valida y convierte un texto en entero.
   *
   * @param text entrada del usuario.
   * @return entero parseado.
   * @throws NumberFormatException si no es un entero valido.
   */
  public int parseInt(String text) {
    // Verificar que el texto no sea nulo o vacío
    if (text == null) {
      // Si el texto es nulo, lanzar una excepción
      throw new NumberFormatException();
    }

    // Eliminar espacios en blanco al inicio y al final
    String trimmed = text.trim();
    if (trimmed.isEmpty()) {
      // Si el texto es vacío después de recortar, lanzar una excepción
      throw new NumberFormatException();
    }

    // Intentar parsear el texto a entero. Si no es un número válido, se lanzará una NumberFormatException.
    return Integer.parseInt(trimmed);
  }
}
