package com.ds.application.view.components.inspector;



public class NodeInspector extends VBox {

    private Label valueLabel;
    private Label levelLabel;
    private Label heightLabel;
    private Label sizeLabel;
    private Label traversalLabel;
    private Label statusLabel;

    private Integer selectedValue;
    private Consumer<Integer> onDelete;

    public NodeInspector() {
        setPadding(new Insets(18));
        setSpacing(12);
        setPrefWidth(240);
        setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-border-color: #e5e7eb;" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;"
        );

        Label title = new Label("Inspector de nodo");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        valueLabel = createLabel("Valor: -");
        levelLabel = createLabel("Nivel: -");
        heightLabel = createLabel("Altura: -");
        sizeLabel = createLabel("Nodos: -");

        Label traversalTitle = new Label("Recorrido");
        traversalTitle.setStyle("-fx-font-weight: bold; -fx-text-fill: #374151;");

        traversalLabel = createLabel("-");
        traversalLabel.setWrapText(true);

        statusLabel = createLabel("-");
        statusLabel.setWrapText(true);

        Button deleteButton = new Button("Eliminar nodo seleccionado");
        deleteButton.setMaxWidth(Double.MAX_VALUE);
        deleteButton.setStyle(
                "-fx-background-color: #ef4444;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10;" +
                "-fx-padding: 9 12;" +
                "-fx-cursor: hand;"
        );

        deleteButton.setOnAction(e -> {
            if (selectedValue != null && onDelete != null) {
                onDelete.accept(selectedValue);
            } else {
                updateStatus("Seleccione o busque un nodo primero.");
            }
        });

        getChildren().addAll(
                title,
                valueLabel,
                levelLabel,
                heightLabel,
                sizeLabel,
                traversalTitle,
                traversalLabel,
                statusLabel,
                deleteButton
        );
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 13px; -fx-text-fill: #4b5563;");
        return label;
    }

    public void updateNodeInfo(int value, int level, int height, int size) {
        selectedValue = value;

        valueLabel.setText("Valor: " + value);
        levelLabel.setText("Nivel: " + level);
        heightLabel.setText("Altura: " + height);
        sizeLabel.setText("Nodos: " + size);
    }

    public void updateTraversal(String type, String result) {
        traversalLabel.setText(type + ": " + result);
    }

    public void updateStatus(String message) {
        statusLabel.setText(message);
    }

    public void setOnDelete(Consumer<Integer> onDelete) {
        this.onDelete = onDelete;
    }

    public void clearInfo() {
        selectedValue = null;

        valueLabel.setText("Valor: -");
        levelLabel.setText("Nivel: -");
        heightLabel.setText("Altura: -");
        sizeLabel.setText("Nodos: -");
        traversalLabel.setText("-");
        statusLabel.setText("-");
    }
}