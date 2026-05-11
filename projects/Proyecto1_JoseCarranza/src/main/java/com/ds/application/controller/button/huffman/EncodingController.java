package com.ds.application.controller.button.huffman;

import java.util.LinkedHashMap;
import java.util.Map;

import com.ds.application.core.structures.HuffmanNode;
import com.ds.application.core.trees.HuffmanBinaryTree;
import com.ds.application.core.trees.operations.huffmanbinarytree.HuffmanAlgorithmOperations;
import com.ds.application.core.trees.operations.huffmanbinarytree.HuffmanAlgorithmWeight;
import com.ds.application.view.components.visualizers.HuffmanAlgorithmVisualizer;

/**
 * Controlador encargado de manejar la logica del algoritmo de Huffman y
 * coordinarla con su visualizador.
 *
 * Este controlador se encarga de validar la entrada del usuario, ejecutar el
 * algoritmo de Huffman para generar el árbol, los códigos y el texto
 * codificado,
 * y luego actualizar la interfaz para mostrar los resultados. También maneja la
 * generación basada en pesos personalizados ingresados por el usuario.
 */
public class EncodingController {
  private static final String MESSAGE_EMPTY_TEXT = "Ingrese un texto para generar Huffman.";
  private static final String MESSAGE_ONLY_LETTERS = "Solo se permiten letras.";
  private static final String MESSAGE_EMPTY_WEIGHTS = "Ingrese simbolos con peso. Ejemplo: A:5";
  private static final String MESSAGE_INVALID_WEIGHTS = "No se encontraron pesos validos.";
  private static final String MESSAGE_INVALID_FORMAT = "Formato invalido. Use: A:5, B:9, C:12";

  private final HuffmanAlgorithmVisualizer visualizer;

  /**
   * Crea el controlador que coordina la logica del algoritmo de Huffman
   * con su visualizador.
   *
   * @param visualizer componente de la vista que muestra resultados.
   */
  public EncodingController(HuffmanAlgorithmVisualizer visualizer) {
    this.visualizer = visualizer;
  }

  /**
   * Valida el texto y ejecuta el flujo de generacion por caracteres.
   *
   * @param text texto base para construir el arbol y el codificado.
   */
  /**
   * Validate text and generate Huffman data. Returns true on success.
   *
   * @param text input text
   * @return true if tree was generated and visualizer updated, false otherwise
   */
  public boolean generateText(String text) {
    String cleanText = normalizeText(text);

    // If text is invalid, normalizeText already shows a message in the visualizer
    // and we return false so callers (UI) can show an error toast instead of a
    // success message.
    if (cleanText == null) {
      return false;
    }

    HuffmanBinaryTree tree = HuffmanAlgorithmOperations.buildTreeFromText(cleanText);
    HuffmanNode root = tree.getRoot();

    Map<Integer, Integer> frequencies = HuffmanAlgorithmOperations.countFrequenciesInt(cleanText);
    Map<Integer, String> codes = HuffmanAlgorithmOperations.buildCodesInt(root);
    String encoded = HuffmanAlgorithmOperations.encodeInt(cleanText, codes);

    visualizer.showTextResult(cleanText, root, frequencies, codes, encoded);
    return true;
  }

  /**
   * Valida y procesa simbolos con peso ingresados por el usuario.
   *
   * @param input lineas con pares simbolo:peso.
   */
  /**
   * Parse and generate Huffman from weight input. Returns true on success.
   *
   * @param input lines with symbol:weight pairs
   * @return true if tree was generated and visualizer updated, false otherwise
   */
  public boolean generateWeight(String input) {
    // If input is blank, inform visualizer and return false so the caller can
    // display an error toast.
    if (isBlank(input)) {
      visualizer.showMessage(MESSAGE_EMPTY_WEIGHTS);
      return false;
    }

    try {
      Map<String, Integer> weights = parseWeights(input);

      if (weights.isEmpty()) {
        visualizer.showMessage(MESSAGE_INVALID_WEIGHTS);
        return false;
      }

      HuffmanBinaryTree tree = HuffmanAlgorithmWeight.buildTreeFromWeights(weights);
      HuffmanNode root = tree.getRoot();

      Map<String, String> codes = HuffmanAlgorithmWeight.buildCodes(root);
      long totalWeight = HuffmanAlgorithmWeight.totalWeight(weights);
      long weightedPath = HuffmanAlgorithmWeight.calculateWeightedPathLength(root);

      visualizer.showWeightResult(weights, root, codes, totalWeight, weightedPath);

      if (weightedPath > 1000) {
        visualizer.showMessage("El resultado es muy largo para mostrar completamente.");
      }
      return true;
    } catch (NumberFormatException ex) {
      visualizer.showMessage(MESSAGE_INVALID_FORMAT);
      return false;
    }
  }

  /**
   * Interpreta el texto de entrada como pares simbolo-peso.
   *
   * @param input lineas con datos de simbolos.
   * @return mapa con pesos ya validados.
   */
  private Map<String, Integer> parseWeights(String input) {
    Map<String, Integer> weights = new LinkedHashMap<>();
    String[] lines = input.split("\\R");

    // Itero sobre cada linea de la entrada, limpiando espacios y validando el
    // formato de cada par simbolo-peso. Si el formato es incorrecto o los valores
    // no son validos, se lanza una excepción para mostrar un mensaje de error.
    for (String line : lines) {
      String cleanLine = line.trim();

      // Si la linea esta vacia despues de limpiar espacios, la ignoro y continuo con
      // la siguiente.
      if (cleanLine.isEmpty()) {
        continue;
      }

      String[] parts = cleanLine.split("[:=,\\s]+");

      // Si no se encuentran exactamente dos partes (simbolo y peso), el formato es
      // incorrecto, por lo que lanzo una excepción para mostrar un mensaje de error.
      if (parts.length < 2) {
        throw new NumberFormatException();
      }

      String symbol = parts[0].trim();
      int weight = Integer.parseInt(parts[1].trim());

      // Si el simbolo esta vacio o el peso no es un entero positivo, lanzo una
      // excepción
      // para mostrar un mensaje de error.
      if (symbol.isEmpty() || weight <= 0) {
        throw new NumberFormatException();
      }

      weights.put(symbol, weight);
    }

    // Devuelvo el mapa de pesos ya validado. Si no se encontraron pesos validos, el
    // mapa estará vacío, lo que se maneja en el método que llama a esta función
    // para mostrar un mensaje de error adecuado.
    return weights;
  }

  /**
   * Limpia y valida el texto antes de construir el arbol de Huffman.
   *
   * @param text texto original del usuario.
   * @return texto limpio o null si no es valido.
   */
  private String normalizeText(String text) {
    // Si el texto es nulo o solo tiene espacios, se muestra un mensaje y se
    // devuelve null para indicar que no es valido.
    if (isBlank(text)) {
      visualizer.showMessage(MESSAGE_EMPTY_TEXT);
      return null;
    }

    String cleanText = text.trim();
    // Si el texto contiene caracteres que no son letras, se muestra un mensaje y se
    // devuelve null para indicar que no es valido.
    if (!cleanText.matches("[a-zA-Z]+")) {
      visualizer.showMessage(MESSAGE_ONLY_LETTERS);
      return null;
    }

    // Si el texto es valido, se devuelve la versión limpia (sin espacios al inicio
    // o al final). Esto asegura que el algoritmo de Huffman se construya solo con
    // caracteres válidos y sin espacios innecesarios, lo que mejora la precisión de
    // los resultados y evita errores durante la construcción del árbol y la
    // generación de códigos.
    return cleanText;
  }

  /**
   * Verifica si un texto es nulo o solo tiene espacios.
   *
   * @param value texto a evaluar.
   * @return true si esta vacio o nulo.
   */
  private boolean isBlank(String value) {
    return value == null || value.trim().isEmpty();
  }

  /**
   * Limpia el estado visual del componente de Huffman.
   */
  public void clear() {
    visualizer.clear();
  }
}
