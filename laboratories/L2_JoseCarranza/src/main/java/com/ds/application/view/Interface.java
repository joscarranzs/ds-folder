package com.ds.application.view;

import com.ds.application.view.components.header.Views;
import com.ds.application.view.components.inspector.NodeInspector;
import com.ds.application.view.components.sidebar.BinaryTreeControlPanel;
import com.ds.application.view.components.sidebar.HuffmanAlgorithmControlPanel;
import com.ds.application.view.components.visualizers.BinaryTreeVisualizer;
import com.ds.application.view.components.visualizers.HuffmanAlgorithmVisualizer;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class Interface {

    // Contenedor principal de toda la aplicación
    private BorderPane root;

    public Interface() {
        root = new BorderPane();

        // Header superior con las opciones para cambiar de vista
        Views header = new Views(
                this::showBinaryTreeView,
                this::showHuffmanView
        );

        // Coloco el header arriba y defino el color base de la app
        root.setTop(header);
        root.setStyle("-fx-background-color: #f1f5f9;");

        // Al iniciar la app, muestro primero la vista del árbol binario
        showBinaryTreeView();
    }

    // Construye la vista completa del árbol binario
    private void showBinaryTreeView() {
        // Inspector derecho para mostrar información del nodo seleccionado
        NodeInspector inspector = new NodeInspector();

        // Visualizador central donde se dibuja el árbol
        BinaryTreeVisualizer visualizer = new BinaryTreeVisualizer();

        // Panel izquierdo con botones y controles del árbol
        BinaryTreeControlPanel panel = new BinaryTreeControlPanel(visualizer, inspector);

        // Wrapper del panel izquierdo para darle margen y que no quede pegado al borde
        StackPane leftWrap = new StackPane(panel);
        leftWrap.setPadding(new Insets(18, 10, 18, 18));

        // Wrapper del centro donde va la visualización del árbol
        StackPane centerWrap = new StackPane(visualizer);
        centerWrap.setPadding(new Insets(18, 10, 18, 10));
        centerWrap.setStyle("-fx-background-color: #f8fafc;");

        // Wrapper del inspector derecho para mantener el mismo estilo de separación
        StackPane rightWrap = new StackPane(inspector);
        rightWrap.setPadding(new Insets(18, 18, 18, 10));

        // Organizo la pantalla en tres partes:
        // izquierda = controles, centro = árbol, derecha = inspector
        root.setLeft(leftWrap);
        root.setCenter(centerWrap);
        root.setRight(rightWrap);
    }

    // Construye la vista del algoritmo Huffman
    private void showHuffmanView() {
        // Visualizador central para mostrar el árbol Huffman y los resultados
        HuffmanAlgorithmVisualizer visualizer = new HuffmanAlgorithmVisualizer();

        // En Huffman solo uso panel izquierdo y visualización central
        root.setLeft(new HuffmanAlgorithmControlPanel(visualizer));
        root.setCenter(visualizer);

        // Quito el inspector derecho porque esta vista no lo necesita
        root.setRight(null);
    }

    // Devuelve la raíz principal para que App pueda ponerla en la Scene
    public BorderPane getRoot() {
        return root;
    }
}