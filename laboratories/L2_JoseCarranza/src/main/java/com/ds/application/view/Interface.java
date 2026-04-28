package com.ds.application.view;


public class Interface {

    private BorderPane root;

    public Interface() {
        root = new BorderPane();

        Views header = new Views(
                this::showBinaryTreeView,
                this::showHuffmanView
        );

        root.setTop(header);
        root.setStyle("-fx-background-color: #f1f5f9;");

        showBinaryTreeView();
    }

    private void showBinaryTreeView() {
        NodeInspector inspector = new NodeInspector();
        BinaryTreeVisualizer visualizer = new BinaryTreeVisualizer();
        BinaryTreeControlPanel panel = new BinaryTreeControlPanel(visualizer, inspector);

        StackPane leftWrap = new StackPane(panel);
        leftWrap.setPadding(new Insets(18, 10, 18, 18));

        StackPane centerWrap = new StackPane(visualizer);
        centerWrap.setPadding(new Insets(18, 10, 18, 10));
        centerWrap.setStyle("-fx-background-color: #f8fafc;");

        StackPane rightWrap = new StackPane(inspector);
        rightWrap.setPadding(new Insets(18, 18, 18, 10));

        root.setLeft(leftWrap);
        root.setCenter(centerWrap);
        root.setRight(rightWrap);
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