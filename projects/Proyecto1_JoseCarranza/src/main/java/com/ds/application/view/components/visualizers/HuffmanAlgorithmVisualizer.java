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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.TreeMap;

public class HuffmanAlgorithmVisualizer extends BorderPane {

    // Panel donde se dibuja el árbol Huffman
    private final GraphPane graphPane;

    // Caja lateral donde muestro resultados, frecuencias, códigos y resumen
    private final VBox resultBox;

    // Scroll del panel de resultados. Se oculta hasta que exista un resultado.
    private final ScrollPane resultScroll;

    public HuffmanAlgorithmVisualizer() {
        // Fondo transparente para respetar el layout principal
        setStyle("-fx-background-color: transparent;");

        // Área gráfica principal
        graphPane = new GraphPane();
        graphPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        graphPane.setStyle(
                "-fx-background-color: #F7FBF6;" +
                "-fx-background-radius: 18;" +
                "-fx-border-radius: 18;" +
                "-fx-border-color: #BFCFBB;" +
                "-fx-border-width: 1;"
        );

        // Panel de resultados del lado derecho
        resultBox = new VBox(10);
        resultBox.setPadding(new Insets(16));
        resultBox.setPrefWidth(340);
        resultBox.setMinWidth(320);
        resultBox.setMaxWidth(360);
        resultBox.setStyle(
                "-fx-background-color: #BFCFBB;" +
                "-fx-border-color: #8EA58C;" +
                "-fx-background-radius: 18;" +
                "-fx-border-radius: 18;" +
                "-fx-border-width: 1;"
        );

        // Uso ScrollPane por si el texto o la tabla de códigos crece mucho
        resultScroll = new ScrollPane(resultBox);
        resultScroll.setFitToWidth(true);
        resultScroll.setPrefWidth(360);
        resultScroll.setMinWidth(340);
        resultScroll.setMaxWidth(380);
        resultScroll.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background: transparent;" +
                "-fx-border-color: transparent;"
        );

        // Wrapper del área gráfica para mantener separación interna
        StackPane graphWrap = new StackPane(graphPane);
        graphWrap.setPadding(new Insets(0));
        graphWrap.setStyle("-fx-background-color: transparent;");

        // Coloco el árbol al centro. Los resultados aparecen solo al generar Huffman
        setCenter(graphWrap);
        setRight(null);

        // Mensaje inicial antes de generar Huffman
        showMessage("Área de visualización de Huffman");
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
        setRight(resultScroll);

        if (root != null) {
            drawTree(root);
        }

        addTitle("Resultado Huffman");
        addInfo("Modo", "Texto");
        addInfo("Texto original", input);
        addInfo("Texto codificado", encoded);
        addInfo("Raíz", formatRoot(root));

        addTableHeader("Texto", "Peso", "Codigo");

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
        setRight(resultScroll);

        if (root != null) {
            drawTree(root);
        }

        addTitle("Resultado Huffman");
        addInfo("Modo", "Peso");
        addInfo("Peso", String.valueOf(weightedPath));
        addInfo("Raíz", formatRoot(root));

        addTableHeader("Texto", "Peso", "Codigo");

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
        setRight(null);

        Label label = new Label(message);
        label.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #94a3b8;"
        );

        // Centro automático del mensaje dentro del área gráfica
        label.layoutXProperty().bind(graphPane.widthProperty().divide(2).subtract(label.widthProperty().divide(2)));
        label.layoutYProperty().bind(graphPane.heightProperty().divide(2).subtract(label.heightProperty().divide(2)));

        graphPane.addShape(label);
    }

    // Reinicia la visualización de Huffman
    public void clear() {
        showMessage("Área de visualización de Huffman");
    }

    // Inicia el dibujo del árbol desde la raíz usando layout compacto por hojas
    private void drawTree(HuffmanNode root) {
        int leafCount = Math.max(1, countLeaves(root));
        int height = Math.max(1, treeHeight(root));

        double paneWidth = graphPane.getWidth() > 0 ? graphPane.getWidth() : graphPane.getPrefWidth();
        double paneHeight = graphPane.getHeight() > 0 ? graphPane.getHeight() : graphPane.getPrefHeight();

        // Márgenes internos para que no se esconda detrás de paneles o bordes
        double marginX = 70;
        double marginTop = 60;
        double marginBottom = 70;

        // Espaciado dinámico: más hojas = menos separación para que todo quepa
        double availableWidth = Math.max(520, paneWidth - (marginX * 2));
        double leafSpacing = availableWidth / Math.max(1, leafCount - 1);

        // Limito para que no se abra demasiado ni se aplaste demasiado
        leafSpacing = clamp(leafSpacing, 58, 115);

        // Espaciado vertical dinámico para árboles altos
        double availableHeight = Math.max(360, paneHeight - marginTop - marginBottom);
        double levelSpacing = availableHeight / Math.max(1, height - 1);
        levelSpacing = clamp(levelSpacing, 72, 95);

        Map<HuffmanNode, Double> xPositions = new IdentityHashMap<>();
        int[] leafIndex = {0};

        assignLeafBasedX(root, xPositions, leafIndex, marginX, leafSpacing);

        // Centro el árbol completo dentro del área visible
        double minX = findMinX(xPositions);
        double maxX = findMaxX(xPositions);
        double treeWidth = maxX - minX;
        double desiredStartX = (paneWidth / 2) - (treeWidth / 2);
        double offsetX = desiredStartX - minX;

        drawEdges(root, xPositions, offsetX, marginTop, levelSpacing, 1);
        drawNodes(root, xPositions, offsetX, marginTop, levelSpacing, 1);
    }

    // Asigna posición X a las hojas y centra los padres entre sus hijos
    private double assignLeafBasedX(
            HuffmanNode node,
            Map<HuffmanNode, Double> xPositions,
            int[] leafIndex,
            double startX,
            double spacing
    ) {
        if (node == null) {
            return startX;
        }

        boolean isLeaf = node.getLeft() == null && node.getRight() == null;

        if (isLeaf) {
            double x = startX + (leafIndex[0] * spacing);
            xPositions.put(node, x);
            leafIndex[0]++;
            return x;
        }

        double leftX = assignLeafBasedX(node.getLeft(), xPositions, leafIndex, startX, spacing);
        double rightX = assignLeafBasedX(node.getRight(), xPositions, leafIndex, startX, spacing);

        double x;

        if (node.getLeft() != null && node.getRight() != null) {
            x = (leftX + rightX) / 2;
        } else if (node.getLeft() != null) {
            x = leftX;
        } else {
            x = rightX;
        }

        xPositions.put(node, x);
        return x;
    }

    // Dibuja primero las líneas para que queden detrás de los nodos
    private void drawEdges(
            HuffmanNode node,
            Map<HuffmanNode, Double> xPositions,
            double offsetX,
            double startY,
            double levelSpacing,
            int level
    ) {
        if (node == null) {
            return;
        }

        double x = xPositions.get(node) + offsetX;
        double y = startY + ((level - 1) * levelSpacing);

        if (node.getLeft() != null) {
            double childX = xPositions.get(node.getLeft()) + offsetX;
            double childY = startY + (level * levelSpacing);

            graphPane.addShape(new EdgeElement(x, y, childX, childY));
            drawEdges(node.getLeft(), xPositions, offsetX, startY, levelSpacing, level + 1);
        }

        if (node.getRight() != null) {
            double childX = xPositions.get(node.getRight()) + offsetX;
            double childY = startY + (level * levelSpacing);

            graphPane.addShape(new EdgeElement(x, y, childX, childY));
            drawEdges(node.getRight(), xPositions, offsetX, startY, levelSpacing, level + 1);
        }
    }

    // Dibuja los nodos después de las líneas
    private void drawNodes(
            HuffmanNode node,
            Map<HuffmanNode, Double> xPositions,
            double offsetX,
            double startY,
            double levelSpacing,
            int level
    ) {
        if (node == null) {
            return;
        }

        drawNodes(node.getLeft(), xPositions, offsetX, startY, levelSpacing, level + 1);
        drawNodes(node.getRight(), xPositions, offsetX, startY, levelSpacing, level + 1);

        double x = xPositions.get(node) + offsetX;
        double y = startY + ((level - 1) * levelSpacing);

        graphPane.addShape(createNodeElement(node, x, y));
    }

    // Cuenta las hojas para calcular un ancho correcto
    private int countLeaves(HuffmanNode node) {
        if (node == null) {
            return 0;
        }

        if (node.getLeft() == null && node.getRight() == null) {
            return 1;
        }

        return countLeaves(node.getLeft()) + countLeaves(node.getRight());
    }

    // Calcula la altura del árbol para ajustar el espacio vertical
    private int treeHeight(HuffmanNode node) {
        if (node == null) {
            return 0;
        }

        return Math.max(treeHeight(node.getLeft()), treeHeight(node.getRight())) + 1;
    }

    // Busca el X mínimo del layout
    private double findMinX(Map<HuffmanNode, Double> positions) {
        double min = Double.MAX_VALUE;

        for (double value : positions.values()) {
            min = Math.min(min, value);
        }

        return min;
    }

    // Busca el X máximo del layout
    private double findMaxX(Map<HuffmanNode, Double> positions) {
        double max = -Double.MAX_VALUE;

        for (double value : positions.values()) {
            max = Math.max(max, value);
        }

        return max;
    }

    // Limita un número entre mínimo y máximo
    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    // Crea el nodo visual dependiendo de si es hoja o nodo interno
    private NodeElement createNodeElement(HuffmanNode node, double x, double y) {
        boolean isLeaf = node.getLeft() == null && node.getRight() == null;

        String text;

        if (isLeaf && node.getId() != null) {
            text = node.getId() + ":" + node.getFrequency();
        } else if (isLeaf) {
            text = formatSymbol(node.getCharacter()) + ":" + node.getFrequency();
        } else {
            text = String.valueOf(node.getFrequency());
        }

        if (isLeaf) {
            return new NodeElement(
                    x,
                    y,
                    text,
                    "#DCE8DA",
                    "#738A6E",
                    "#344C3D"
            );
        }

        return new NodeElement(
                x,
                y,
                text,
                "#344C3D",
                "#F7FBF6",
                "#F7FBF6"
        );
    }

    // Agrega un título dentro del panel de resultados
    private void addTitle(String text) {
        Label label = new Label(text);
        label.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #344C3D;" +
                "-fx-padding: 8 0 2 0;"
        );
        resultBox.getChildren().add(label);
    }

    // Crea una tarjeta para mostrar información importante
    private void addInfo(String title, String value) {
        VBox card = new VBox(5);
        card.setPadding(new Insets(10));
        card.setStyle(
                "-fx-background-color: #DCE8DA;" +
                "-fx-background-radius: 10;" +
                "-fx-border-color: #8EA58C;" +
                "-fx-border-radius: 10;" +
                "-fx-border-width: 1;"
        );

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #738A6E;");

        Label valueLabel = new Label(value);
        valueLabel.setWrapText(true);
        valueLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #344C3D;");

        card.getChildren().addAll(titleLabel, valueLabel);
        resultBox.getChildren().add(card);
    }

    // Crea una fila tipo pill para mostrar símbolo, peso y código
    private void addSymbolPill(String textValue, String weightValue, String codeValue) {
        HBox pill = new HBox(8);
        pill.setAlignment(Pos.CENTER_LEFT);
        pill.setPadding(new Insets(8, 10, 8, 10));
        pill.setStyle(
                "-fx-background-color: #DCE8DA;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: #8EA58C;" +
                "-fx-border-radius: 18;" +
                "-fx-border-width: 1;"
        );

        Label text = new Label(textValue);
        text.setPrefWidth(90);
        text.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #344C3D;");

        Label weight = new Label(weightValue);
        weight.setPrefWidth(55);
        weight.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #344C3D;");

        Label code = new Label(codeValue == null ? "-" : codeValue);
        code.setPrefWidth(120);
        code.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #738A6E;");

        pill.getChildren().addAll(text, weight, code);
        resultBox.getChildren().add(pill);
    }

    // Agrega una cabecera alineada para la tabla de símbolos
    private void addTableHeader(String textTitle, String weightTitle, String codeTitle) {
        HBox header = new HBox(8);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(4, 10, 4, 10));

        Label text = new Label(textTitle);
        text.setPrefWidth(90);
        text.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #344C3D;");

        Label weight = new Label(weightTitle);
        weight.setPrefWidth(55);
        weight.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #344C3D;");

        Label code = new Label(codeTitle);
        code.setPrefWidth(120);
        code.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #344C3D;");

        header.getChildren().addAll(text, weight, code);
        resultBox.getChildren().add(header);
    }

    // Formatea el símbolo cuando viene como código numérico
    private String formatSymbol(int symbol) {
        if (symbol == (int) ' ') {
            return "espacio";
        }

        if (symbol == (int) '\n') {
            return "\\n";
        }

        if (symbol >= 32 && symbol <= 126) {
            return String.valueOf((char) symbol);
        }

        return String.valueOf(symbol);
    }

    // Formatea la representación de la raíz para mostrarla en el panel de resultados
    private String formatRoot(HuffmanNode root) {
        if (root == null) return "-";

        boolean isLeaf = root.getLeft() == null && root.getRight() == null;

        if (isLeaf && root.getId() != null) {
            return root.getId() + ":" + root.getFrequency();
        } else if (isLeaf) {
            return formatSymbol(root.getCharacter()) + ":" + root.getFrequency();
        } else {
            return String.valueOf(root.getFrequency());
        }
    }
}