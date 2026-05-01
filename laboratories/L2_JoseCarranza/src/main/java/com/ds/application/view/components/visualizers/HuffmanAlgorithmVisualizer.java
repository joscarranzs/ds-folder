package com.ds.application.view.components.visualizers;

import com.ds.application.core.structures.HuffmanNode;
import com.ds.application.view.components.elements.shapes.EdgeElement;
import com.ds.application.view.components.elements.shapes.GraphPane;
import com.ds.application.view.components.elements.shapes.NodeElement;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Map;
import java.util.TreeMap;

public class HuffmanAlgorithmVisualizer extends BorderPane {

    private final GraphPane graphPane;
    private final VBox resultBox;

    public HuffmanAlgorithmVisualizer() {
        setStyle("-fx-background-color: #f8fafc;");

        graphPane = new GraphPane();

        resultBox = new VBox(10);
        resultBox.setPadding(new Insets(16));
        resultBox.setPrefWidth(320);
        resultBox.setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-border-color: #e5e7eb;" +
                "-fx-background-radius: 14;" +
                "-fx-border-radius: 14;"
        );

        ScrollPane scroll = new ScrollPane(resultBox);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color: transparent; -fx-background: transparent;");

        setCenter(graphPane);
        setRight(scroll);

        showMessage("Ingrese un texto para comenzar.");
    }

    public void showResult(
            String input,
            HuffmanNode root,
            Map<Integer, Integer> frequencies,
            Map<Integer, String> codes,
            String encoded
    ) {
        graphPane.clearGraph();
        resultBox.getChildren().clear();

        if (root != null) {
            drawTree(root);
        }

        addTitle("Resultado Huffman");
        addInfo("Texto original", input);
        addInfo("Texto codificado", encoded);

        addTitle("Frecuencias");
        Map<Integer, Integer> sortedFrequencies = new TreeMap<>(frequencies);
        for (Map.Entry<Integer, Integer> entry : sortedFrequencies.entrySet()) {
            addRow(formatSymbol(entry.getKey()), String.valueOf(entry.getValue()));
        }

        addTitle("Codigos");
        Map<Integer, String> sortedCodes = new TreeMap<>(codes);
        for (Map.Entry<Integer, String> entry : sortedCodes.entrySet()) {
            addRow(formatSymbol(entry.getKey()), entry.getValue());
        }
    }

    public void showMessage(String message) {
        graphPane.clearGraph();
        resultBox.getChildren().clear();

        Label label = new Label(message);
        label.setStyle("-fx-font-size: 14px; -fx-text-fill: #64748b;");
        label.setLayoutX(220);
        label.setLayoutY(200);

        graphPane.addShape(label);
    }

    public void clear() {
        showMessage("Ingrese un texto para comenzar.");
    }

    private void drawTree(HuffmanNode root) {
        double centerX = graphPane.getPrefWidth() / 2;
        drawNode(root, centerX, 60, 170);
    }

    private void drawNode(HuffmanNode node, double x, double y, double gap) {
        if (node == null) {
            return;
        }

        if (node.getLeft() != null) {
            double childX = x - gap;
            double childY = y + 85;

            graphPane.addShape(new EdgeElement(x, y, childX, childY));
            drawNode(node.getLeft(), childX, childY, gap / 1.6);
        }

        if (node.getRight() != null) {
            double childX = x + gap;
            double childY = y + 85;

            graphPane.addShape(new EdgeElement(x, y, childX, childY));
            drawNode(node.getRight(), childX, childY, gap / 1.6);
        }

        graphPane.addShape(createNodeElement(node, x, y));
    }

    private NodeElement createNodeElement(HuffmanNode node, double x, double y) {
        boolean isLeaf = node.getLeft() == null && node.getRight() == null;

        String text = isLeaf
                ? formatSymbol(node.getCharacter()) + ":" + node.getFrequency()
                : String.valueOf(node.getFrequency());

        if (isLeaf) {
            return new NodeElement(
                    x,
                    y,
                    text,
                    "#ffffff",
                    "#2563eb",
                    "#111827"
            );
        }

        return new NodeElement(
                x,
                y,
                text,
                "#0f172a",
                "#ffffff",
                "#ffffff"
        );
    }

    private void addTitle(String text) {
        Label label = new Label(text);
        label.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #111827;" +
                "-fx-padding: 8 0 2 0;"
        );
        resultBox.getChildren().add(label);
    }

    private void addInfo(String title, String value) {
        VBox card = new VBox(5);
        card.setPadding(new Insets(10));
        card.setStyle(
                "-fx-background-color: #f1f5f9;" +
                "-fx-background-radius: 10;"
        );

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #64748b;");

        Label valueLabel = new Label(value);
        valueLabel.setWrapText(true);
        valueLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #111827;");

        card.getChildren().addAll(titleLabel, valueLabel);
        resultBox.getChildren().add(card);
    }

    private void addRow(String left, String right) {
        HBox row = new HBox();
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(6, 10, 6, 10));
        row.setStyle(
                "-fx-background-color: #f8fafc;" +
                "-fx-background-radius: 8;"
        );

        Label leftLabel = new Label(left);
        leftLabel.setPrefWidth(80);
        leftLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #334155;");

        Label rightLabel = new Label(right);
        rightLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #111827;");

        row.getChildren().addAll(leftLabel, rightLabel);
        resultBox.getChildren().add(row);
    }

    // Formatea el símbolo cuando ahora es un código numérico (int).
    private String formatSymbol(int symbol) {
        if (symbol == (int) ' ') {
            return "espacio";
        }

        if (symbol == (int) '\n') {
            return "\\n";
        }

        // Por defecto mostramos el valor numérico y su carácter cuando sea imprimible
        if (symbol >= 32 && symbol <= 126) { // rango ASCII imprimible
            return symbol + "(" + (char) symbol + ")";
        }

        return String.valueOf(symbol);
    }
}
