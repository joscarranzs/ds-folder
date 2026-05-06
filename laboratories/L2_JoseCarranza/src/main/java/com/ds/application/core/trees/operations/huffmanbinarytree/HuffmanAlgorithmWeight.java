package com.ds.application.core.trees.operations.huffmanbinarytree;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import com.ds.application.core.structures.HuffmanNode;
import com.ds.application.core.trees.HuffmanBinaryTree;

/**
 * Implementación de Huffman para trabajar con pesos (no texto).
 * Cada símbolo es un String (id) con un peso asociado.
 */
public class HuffmanAlgorithmWeight {

    /**
     * Construye el árbol de Huffman a partir de un mapa de pesos.
     */
    public static HuffmanBinaryTree buildTreeFromWeights(Map<String, Integer> weight) {
        if (weight == null || weight.isEmpty()) {
            return new HuffmanBinaryTree();
        }
        return buildTreeFromHashMap(weight);
    }

    /**
     * Construcción del árbol usando PriorityQueue (montículo mínimo).
     */
    public static HuffmanBinaryTree buildTreeFromHashMap(Map<String, Integer> weight) {
        if (weight == null || weight.isEmpty()) {
            return new HuffmanBinaryTree();
        }

        // Cola de prioridad ordenada por frecuencia (peso)
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(
                Comparator.comparingLong(HuffmanNode::getFrequency)
                        .thenComparing(node -> node.getId() == null ? "" : node.getId())
        );

        // Crear nodos hoja
        for (Map.Entry<String, Integer> entry : weight.entrySet()) {
            pq.offer(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // Construcción del árbol
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();

            // Nodo interno (sin id)    
            HuffmanNode parent = new HuffmanNode((String) null, left.getFrequency() + right.getFrequency());
            parent.setLeft(left);
            parent.setRight(right);

            pq.offer(parent);
        }

        HuffmanNode root = pq.poll();
        return new HuffmanBinaryTree(root);
    }

    /**
     * Genera los códigos Huffman para cada símbolo (id).
     */
    public static Map<String, String> buildCodes(HuffmanNode root) {
        Map<String, String> codes = new HashMap<>();
        if (root == null) return codes;

        // Caso especial: solo un nodo
        if (root.getLeft() == null && root.getRight() == null) {
            codes.put(root.getId(), "0");
            return codes;
        }

        buildCodesRec(root, "", codes);
        return codes;
    }

    /**
     * Método recursivo para generar códigos.
     */
    private static void buildCodesRec(HuffmanNode node, String prefix, Map<String, String> codes) {
        if (node == null) return;

        if (node.isLeaf()) {
            codes.put(node.getId(), prefix);
            return;
        }

        buildCodesRec(node.getLeft(), prefix + "0", codes);
        buildCodesRec(node.getRight(), prefix + "1", codes);
    }

    /**
     * Calcula el peso total del árbol (suma de frecuencias).
     */
    public static long totalWeight(Map<String, Integer> frequencies) {
        if (frequencies == null) return 0L;

        long sum = 0L;
        for (int v : frequencies.values()) {
            sum += v;
        }
        return sum;
    }

    /**
     * Calcula la Longitud de Camino Interno Ponderada (LCI):
     * P = Σ(Wi * Li)
     * donde Wi = frecuencia del símbolo y Li = profundidad (longitud del código).
     */
    public static long calculateWeightedPathLength(HuffmanNode root) {
        if (root == null) return 0L;

        // Caso especial: un solo nodo (un solo símbolo). Su profundidad se trata
        // como 1, por lo que la contribución es su frecuencia.
        if (root.isLeaf()) {
            return root.getFrequency();
        }

        return calculateWeightedPathLengthRec(root, 0);
    }

    private static long calculateWeightedPathLengthRec(HuffmanNode node, int depth) {
        if (node == null) return 0L;

        if (node.isLeaf()) {
            return node.getFrequency() * depth;
        }

        return calculateWeightedPathLengthRec(node.getLeft(), depth + 1)
                + calculateWeightedPathLengthRec(node.getRight(), depth + 1);
    }
}
