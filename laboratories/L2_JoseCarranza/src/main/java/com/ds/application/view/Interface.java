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

    private BorderPane root;

    public Interface() {
        root = new BorderPane();

        Views header = new Views(
            this::showBinaryTreeView,
            this::showHuffmanView
        );

        root.setTop(header);
        root.setStyle("-fx-background-color: #eef2f7;");

        showBinaryTreeView();
    }

    private void showBinaryTreeView() {
        NodeInspector inspector = new NodeInspector();
        BinaryTreeVisualizer visualizer = new BinaryTreeVisualizer();
        StackPane inspectorWrap = new StackPane(inspector);
        inspectorWrap.setPadding(new Insets(28, 28, 28, 0));
        inspectorWrap.setStyle("-fx-background-color: #f8fafc;");

        root.setLeft(new BinaryTreeControlPanel(visualizer, inspector));
        root.setCenter(visualizer);
        root.setRight(inspectorWrap);
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
