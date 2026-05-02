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

    // Panel donde se dibuja el árbol Huffman
    private final GraphPane graphPane;

    // Caja lateral donde muestro resultados, frecuencias, códigos y resumen
    private final VBox resultBox;

    public HuffmanAlgorithmVisualizer() {
        setStyle("-fx-background-color: #f8fafc;");

        // Área gráfica principal
        graphPane = new GraphPane();

        // Panel de resultados del lado derecho
        resultBox = new VBox(10);
        resultBox.setPadding(new Insets(16));
        resultBox.setPrefWidth(340);
        resultBox.setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-border-color: #e5e7eb;" +
                "-fx-background-radius: 14;" +
                "-fx-border-radius: 14;"
        );

        // Uso ScrollPane por si el texto o la tabla de códigos crece mucho
        ScrollPane scroll = new ScrollPane(resultBox);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color: transparent; -fx-background: transparent;");

        // Coloco el árbol al centro y los resultados a la derecha
        setCenter(graphPane);
        setRight(scroll);

        // Mensaje inicial antes de generar Huffman
        showMessage("Ingrese un texto para comenzar.");
    }

    // Muestra el resultado cuando se trabaja con texto normal
    public void showTextResult(
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
        addInfo("Modo", "Texto");
        addInfo("Texto original", input);
        addInfo("Texto codificado", encoded);

        addTitle("Texto | Peso | Codigo");

        Map<Integer, Integer> sortedFrequencies = new TreeMap<>(frequencies);
        for (Map.Entry<Integer, Integer> entry : sortedFrequencies.entrySet()) {
            int symbol = entry.getKey();

            addSymbolPill(
                    formatSymbol(symbol),
                    String.valueOf(entry.getValue()),
                    codes.get(symbol)
            );
        }
    }

    // Muestra el resultado cuando se trabaja con pesos definidos por el usuario
    public void showWeightResult(
            Map<String, Integer> weights,
            HuffmanNode root,
            Map<String, String> codes,
            long totalWeight,
            long weightedPath
    ) {
        graphPane.clearGraph();
        resultBox.getChildren().clear();

        if (root != null) {
            drawTree(root);
        }

        addTitle("Resultado Huffman");
        addInfo("Modo", "Peso");
        addInfo("Peso total", String.valueOf(totalWeight));
        addInfo("LCI ponderada", String.valueOf(weightedPath));

        addTitle("Texto | Peso | Codigo");

        Map<String, Integer> sortedWeights = new TreeMap<>(weights);
        for (Map.Entry<String, Integer> entry : sortedWeights.entrySet()) {
            String symbol = entry.getKey();

            addSymbolPill(
                    symbol,
                    String.valueOf(entry.getValue()),
                    codes.get(symbol)
            );
        }
    }

    // Muestra un mensaje simple cuando no hay datos cargados o hay un error de entrada
    public void showMessage(String message) {
        graphPane.clearGraph();
        resultBox.getChildren().clear();

        Label label = new Label(message);
        label.setStyle("-fx-font-size: 14px; -fx-text-fill: #64748b;");
        label.setLayoutX(220);
        label.setLayoutY(200);

        graphPane.addShape(label);
    }

    // Reinicia la visualización de Huffman
    public void clear() {
        showMessage("Ingrese un texto para comenzar.");
    }

    // Inicia el dibujo del árbol desde la raíz
    private void drawTree(HuffmanNode root) {
        double centerX = graphPane.getPrefWidth() / 2;
        drawNode(root, centerX, 60, 170);
    }

    // Dibuja cada nodo del árbol usando recursividad
    private void drawNode(HuffmanNode node, double x, double y, double gap) {
        if (node == null) {
            return;
        }

        // Dibujo el hijo izquierdo y su conexión
        if (node.getLeft() != null) {
            double childX = x - gap;
            double childY = y + 85;

            graphPane.addShape(new EdgeElement(x, y, childX, childY));
            drawNode(node.getLeft(), childX, childY, gap / 1.6);
        }

        // Dibujo el hijo derecho y su conexión
        if (node.getRight() != null) {
            double childX = x + gap;
            double childY = y + 85;

            graphPane.addShape(new EdgeElement(x, y, childX, childY));
            drawNode(node.getRight(), childX, childY, gap / 1.6);
        }

        // Agrego el nodo actual después de las líneas para que quede encima
        graphPane.addShape(createNodeElement(node, x, y));
    }

    // Crea el nodo visual dependiendo de si es hoja o nodo interno
    private NodeElement createNodeElement(HuffmanNode node, double x, double y) {
        boolean isLeaf = node.getLeft() == null && node.getRight() == null;

        String text;

        // Si es hoja y tiene id, significa que viene del modo peso
        if (isLeaf && node.getId() != null) {
            text = node.getId() + ":" + node.getFrequency();
        } else if (isLeaf) {
            // Si es hoja sin id, viene del modo texto
            text = formatSymbol(node.getCharacter()) + ":" + node.getFrequency();
        } else {
            // Si es interno, solo muestro la suma de frecuencias
            text = String.valueOf(node.getFrequency());
        }

        // Los nodos hoja van blancos con borde azul
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

        // Los nodos internos van oscuros porque representan suma de frecuencias
        return new NodeElement(
                x,
                y,
                text,
                "#0f172a",
                "#ffffff",
                "#ffffff"
        );
    }

    // Agrega un título dentro del panel de resultados
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

    // Crea una tarjeta para mostrar información importante
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

    // Crea una fila tipo pill para mostrar símbolo, peso y código
    private void addSymbolPill(String textValue, String weightValue, String codeValue) {
        HBox pill = new HBox(8);
        pill.setAlignment(Pos.CENTER_LEFT);
        pill.setPadding(new Insets(8, 10, 8, 10));
        pill.setStyle(
                "-fx-background-color: #eef6ff;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: #dbeafe;" +
                "-fx-border-radius: 18;"
        );

        Label text = new Label(textValue);
        text.setPrefWidth(90);
        text.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #334155;");

        Label weight = new Label(weightValue);
        weight.setPrefWidth(55);
        weight.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #334155;");

        Label code = new Label(codeValue == null ? "-" : codeValue);
        code.setPrefWidth(120);
        code.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #2563eb;");

        pill.getChildren().addAll(text, weight, code);
        resultBox.getChildren().add(pill);
    }

    // Formatea el símbolo cuando viene como código numérico
    private String formatSymbol(int symbol) {
        if (symbol == (int) ' ') {
            return "espacio";
        }

        if (symbol == (int) '\n') {
            return "\\n";
        }

        // Si es un ASCII visible, muestro el número y el carácter para que se entienda mejor
        if (symbol >= 32 && symbol <= 126) {
            return symbol + "(" + (char) symbol + ")";
        }

        // Si no es imprimible, solo muestro el número
        return String.valueOf(symbol);
    }
}