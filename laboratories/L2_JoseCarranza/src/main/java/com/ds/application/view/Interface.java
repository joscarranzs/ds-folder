package com.ds.application.view;

import com.ds.application.view.components.header.Views;
import com.ds.application.view.components.inspector.NodeInspector;
import com.ds.application.view.components.sidebar.BinaryTreeControlPanel;
import com.ds.application.view.components.sidebar.HuffmanAlgorithmControlPanel;
import com.ds.application.view.components.visualizers.BinaryTreeVisualizer;
import com.ds.application.view.components.visualizers.HuffmanAlgorithmVisualizer;
import javafx.scene.layout.BorderPane;

public class Interface {

    private BorderPane root;

    public Interface() {
        root = new BorderPane();

        Views header = new Views(
            this::showBinaryTreeView,
            this::showHuffmanView
        );

        root.setTop(header);
        root.setStyle("-fx-background-color: #f5f7fb;");

        showBinaryTreeView(); // vista inicial
    }

    private void showBinaryTreeView() {
        NodeInspector inspector = new NodeInspector();
        BinaryTreeVisualizer visualizer = new BinaryTreeVisualizer(inspector);

        root.setLeft(new BinaryTreeControlPanel(visualizer));
        root.setCenter(visualizer);
        root.setRight(inspector);
    }

    private void showHuffmanView() {
        HuffmanAlgorithmVisualizer visualizer = new HuffmanAlgorithmVisualizer();

        root.setLeft(new HuffmanAlgorithmControlPanel(visualizer));
        root.setCenter(visualizer);
        root.setRight(null);
    }

    public BorderPane getRoot() {
        return root;
    }
}