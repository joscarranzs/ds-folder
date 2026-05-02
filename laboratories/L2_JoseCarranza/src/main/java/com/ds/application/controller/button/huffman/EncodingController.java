package com.ds.application.controller.button.huffman;

import com.ds.application.core.structures.HuffmanNode;
import com.ds.application.core.trees.HuffmanBinaryTree;
import com.ds.application.core.trees.operations.huffmanbinarytree.HuffmanAlgorithmOperations;
import com.ds.application.core.trees.operations.huffmanbinarytree.HuffmanAlgorithmWeight;
import com.ds.application.view.components.visualizers.HuffmanAlgorithmVisualizer;

import java.util.LinkedHashMap;
import java.util.Map;

public class EncodingController {

    private final HuffmanAlgorithmVisualizer visualizer;

    public EncodingController(HuffmanAlgorithmVisualizer visualizer) {
        this.visualizer = visualizer;
    }

    public void generateText(String text) {
        if (text == null || text.trim().isEmpty()) {
            visualizer.showMessage("Ingrese un texto para generar Huffman.");
            return;
        }

        String cleanText = text.trim();

        HuffmanBinaryTree tree = HuffmanAlgorithmOperations.buildTreeFromText(cleanText);
        HuffmanNode root = tree.getRoot();

        Map<Integer, Integer> frequencies = HuffmanAlgorithmOperations.countFrequenciesInt(cleanText);
        Map<Integer, String> codes = HuffmanAlgorithmOperations.buildCodesInt(root);
        String encoded = HuffmanAlgorithmOperations.encodeInt(cleanText, codes);

        visualizer.showTextResult(cleanText, root, frequencies, codes, encoded);
    }

    public void generateWeight(String input) {
        if (input == null || input.trim().isEmpty()) {
            visualizer.showMessage("Ingrese simbolos con peso. Ejemplo: A:5");
            return;
        }

        try {
            Map<String, Integer> weights = parseWeights(input);

            if (weights.isEmpty()) {
                visualizer.showMessage("No se encontraron pesos validos.");
                return;
            }

            HuffmanBinaryTree tree = HuffmanAlgorithmWeight.buildTreeFromWeights(weights);
            HuffmanNode root = tree.getRoot();

            Map<String, String> codes = HuffmanAlgorithmWeight.buildCodes(root);
            long totalWeight = HuffmanAlgorithmWeight.totalWeight(weights);
            long weightedPath = HuffmanAlgorithmWeight.calculateWeightedPathLength(root);

            visualizer.showWeightResult(weights, root, codes, totalWeight, weightedPath);

        } catch (NumberFormatException ex) {
            visualizer.showMessage("Formato invalido. Use: A:5, B:9, C:12");
        }
    }

    private Map<String, Integer> parseWeights(String input) {
        Map<String, Integer> weights = new LinkedHashMap<>();
        String[] lines = input.split("\\R");

        for (String line : lines) {
            String cleanLine = line.trim();

            if (cleanLine.isEmpty()) {
                continue;
            }

            String[] parts = cleanLine.split("[:=,\\s]+");

            if (parts.length < 2) {
                throw new NumberFormatException();
            }

            String symbol = parts[0].trim();
            int weight = Integer.parseInt(parts[1].trim());

            if (symbol.isEmpty() || weight <= 0) {
                throw new NumberFormatException();
            }

            weights.put(symbol, weight);
        }

        return weights;
    }

    public void clear() {
        visualizer.clear();
    }
}