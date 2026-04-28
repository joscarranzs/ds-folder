package com.ds.application.controller.button.huffman;

import com.ds.application.core.structures.HuffmanNode;
import com.ds.application.core.trees.HuffmanBinaryTree;
import com.ds.application.core.trees.operations.HuffmanAlgorithmOperations;
import com.ds.application.view.components.visualizers.HuffmanAlgorithmVisualizer;

import java.util.Map;

public class EncodingController {

    private final HuffmanAlgorithmVisualizer visualizer;

    public EncodingController(HuffmanAlgorithmVisualizer visualizer) {
        this.visualizer = visualizer;
    }

    public void generate(String text) {
        if (text == null || text.trim().isEmpty()) {
            visualizer.showMessage("Ingrese un texto para generar Huffman.");
            return;
        }

        String cleanText = text.trim();

        HuffmanBinaryTree tree = HuffmanAlgorithmOperations.buildTreeFromText(cleanText);
        HuffmanNode root = tree.getRoot();

        Map<Character, Integer> frequencies = HuffmanAlgorithmOperations.countFrequencies(cleanText);
        Map<Character, String> codes = HuffmanAlgorithmOperations.buildCodes(root);
        String encoded = HuffmanAlgorithmOperations.encode(cleanText, codes);

        visualizer.showResult(cleanText, root, frequencies, codes, encoded);
    }

    public void clear() {
        visualizer.clear();
    }
}