package com.ds;

import com.ds.application.core.view.BinaryTree;

/**
 * Punto de entrada principal de la aplicación.
 *
 * <p>Esta clase delega el inicio de la aplicación en la clase {@link BinaryTree},
 * lo que permite ejecutar la interfaz gráfica JavaFX tanto desde Maven como desde
 * el JAR generado.</p>
 */
public class App {
    /**
     * Método principal que arranca la aplicación JavaFX.
     *
     * @param args argumentos de línea de comandos que se pasan a JavaFX
     */
    public static void main(String[] args) {
        BinaryTree.main(args);
    }
}