package com.ds.application.core.trees.operations.huffmanbinarytree;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import com.ds.application.core.structures.HuffmanNode;
import com.ds.application.core.trees.HuffmanBinaryTree;

/** Operaciones básicas para construir y usar un árbol de Huffman trabajando con símbolos numéricos. */
public class HuffmanAlgorithmOperations {

    /**
     * Cuenta las frecuencias de los símbolos en el texto. Se usa el código int
     * de cada carácter para representar el símbolo (compatibilidad con datos
     * numéricos).
     *
     * @param text texto de entrada
     * @return mapa símbolo(int) -> frecuencia
     */
    public static Map<Integer, Integer> countFrequenciesInt(String text) {
        Map<Integer, Integer> freq = new HashMap<>();
        if (text == null) return freq;

        for (int i = 0; i < text.length(); i++) {
            int symbol = text.charAt(i);
            freq.put(symbol, freq.getOrDefault(symbol, 0) + 1);
        }

        return freq;
    }

    /**
     * Versión de compatibilidad que devuelve un mapa con claves Character.
     *
     * @param text texto de entrada
     * @return mapa Character -> frecuencia
     */
    public static Map<Character, Integer> countFrequencies(String text) {
        Map<Integer, Integer> m = countFrequenciesInt(text);
        Map<Character, Integer> out = new HashMap<>();
        for (Map.Entry<Integer, Integer> e : m.entrySet()) {
            out.put((char) (int) e.getKey(), e.getValue());
        }
        return out;
    }

    /**
     * Construye el árbol de Huffman a partir del texto. Cada símbolo es el
     * código int del carácter. Retorna un HuffmanBinaryTree con la raíz
     * construida (o vacío si no hay símbolos).
     *
     * @param text texto de entrada
     * @return árbol de Huffman construido
     */
    public static HuffmanBinaryTree buildTreeFromTextInt(String text) {
        Map<Integer, Integer> frequencies = countFrequenciesInt(text);
        if (frequencies.isEmpty()) return new HuffmanBinaryTree();

        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(Comparator.comparingLong(HuffmanNode::getFrequency)
                .thenComparingInt(HuffmanNode::getCharacter));

        for (Map.Entry<Integer, Integer> e : frequencies.entrySet()) {
            pq.add(new HuffmanNode(e.getKey(), e.getValue()));
        }

        while (pq.size() > 1) {
            HuffmanNode a = pq.poll();
            HuffmanNode b = pq.poll();

            HuffmanNode parent = new HuffmanNode(-1, a.getFrequency() + b.getFrequency());
            parent.setLeft(a);
            parent.setRight(b);
            pq.add(parent);
        }

        HuffmanNode root = pq.poll();
        return new HuffmanBinaryTree(root);
    }

    /**
     * Compatibilidad: wrapper que usa la versión int del constructor.
     *
     * @param text texto de entrada
     * @return árbol de Huffman construido
     */
    public static HuffmanBinaryTree buildTreeFromText(String text) {
        return buildTreeFromTextInt(text);
    }

    /**
     * Construye los códigos de Huffman a partir de la raíz del árbol.
     * Devuelve un mapa símbolo(int) -> código binario (String).
     *
     * @param root raíz del árbol de Huffman
     * @return mapa de códigos
     */
    public static Map<Integer, String> buildCodesInt(HuffmanNode root) {
        Map<Integer, String> codes = new HashMap<>();
        if (root == null) return codes;

        // Caso especial: sólo un símbolo
        if (root.getLeft() == null && root.getRight() == null) {
            codes.put(root.getCharacter(), "0");
            return codes;
        }

        buildCodesRec(root, "", codes);
        return codes;
    }

    /**
     * Versión de compatibilidad que devuelve los códigos con claves Character.
     *
     * @param root raíz del árbol de Huffman
     * @return mapa Character -> código binario
     */
    public static Map<Character, String> buildCodes(HuffmanNode root) {
        Map<Integer, String> mi = buildCodesInt(root);
        Map<Character, String> out = new HashMap<>();
        for (Map.Entry<Integer, String> e : mi.entrySet()) {
            out.put((char) (int) e.getKey(), e.getValue());
        }
        return out;
    }

    /**
     * Devuelve el peso total (suma de todas las frecuencias) para un mapa de frecuencias.
     *
     * @param frequencies mapa símbolo -> frecuencia
     * @return suma de frecuencias
     */
    public static long totalWeight(Map<Integer, Integer> frequencies) {
        if (frequencies == null) return 0L;
        long sum = 0L;
        for (int v : frequencies.values()) sum += v;
        return sum;
    }
        /**
 * Calcula P = Σ(Wi * Li)
 * donde:
 * Wi = frecuencia del símbolo
 * Li = profundidad en el árbol (longitud del código)
 */
    public static long calculateWeightedPathLength(HuffmanNode root) {
        if (root == null) return 0L;

        // Caso especial: un solo símbolo → se asigna código "0"
        if (root.isLeaf()) {
            return root.getFrequency(); // Wi * 1.
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

    /**
     * Construye y devuelve un montículo mínimo (PriorityQueue) a partir de un mapa de frecuencias.
     *
     * @param frequencies mapa símbolo -> frecuencia
     * @return PriorityQueue mínima de HuffmanNode
     */
    public static PriorityQueue<HuffmanNode> buildMinHeapFromFrequencies(Map<Integer, Integer> frequencies) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(Comparator.comparingLong(HuffmanNode::getFrequency)
                .thenComparingInt(HuffmanNode::getCharacter));
        if (frequencies == null) return pq;
        for (Map.Entry<Integer, Integer> e : frequencies.entrySet()) {
            pq.add(new HuffmanNode(e.getKey(), e.getValue()));
        }
        return pq;
    }

    /**
     * Construye y devuelve un montículo máximo (PriorityQueue con comparador invertido)
     * a partir del mapa de frecuencias.
     *
     * @param frequencies mapa símbolo -> frecuencia
     * @return PriorityQueue máxima de HuffmanNode
     */
    public static PriorityQueue<HuffmanNode> buildMaxHeapFromFrequencies(Map<Integer, Integer> frequencies) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>((a, b) -> {
            int cmp = Long.compare(b.getFrequency(), a.getFrequency());
            if (cmp != 0) return cmp;
            return Integer.compare(b.getCharacter(), a.getCharacter());
        });
        if (frequencies == null) return pq;
        for (Map.Entry<Integer, Integer> e : frequencies.entrySet()) {
            pq.add(new HuffmanNode(e.getKey(), e.getValue()));
        }
        return pq;
    }

    /**
     * Construye un montículo mínimo directamente a partir de un texto.
     *
     * @param text texto de entrada
     * @return montículo mínimo de HuffmanNode
     */
    public static PriorityQueue<HuffmanNode> buildMinHeapFromText(String text) {
        return buildMinHeapFromFrequencies(countFrequenciesInt(text));
    }

    /**
     * Construye un montículo máximo directamente a partir de un texto.
     *
     * @param text texto de entrada
     * @return montículo máximo de HuffmanNode
     */
    public static PriorityQueue<HuffmanNode> buildMaxHeapFromText(String text) {
        return buildMaxHeapFromFrequencies(countFrequenciesInt(text));
    }

    // Recursivo interno que construye los códigos (no público)
    private static void buildCodesRec(HuffmanNode node, String prefix, Map<Integer, String> codes) {
        if (node == null) return;

        if (node.getLeft() == null && node.getRight() == null) {
            codes.put(node.getCharacter(), prefix);
            return;
        }

        buildCodesRec(node.getLeft(), prefix + "0", codes);
        buildCodesRec(node.getRight(), prefix + "1", codes);
    }

    /**
     * Codifica el texto usando el mapa de códigos (versión int -> String).
     *
     * @param text  texto original
     * @param codes mapa símbolo(int) -> código binario
     * @return cadena binaria resultante
     */
    public static String encodeInt(String text, Map<Integer, String> codes) {
        if (text == null || codes == null) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            int symbol = text.charAt(i);
            String code = codes.get(symbol);
            if (code != null) sb.append(code);
        }
        return sb.toString();
    }

    /**
     * Versión de compatibilidad que acepta Map<Character, String>.
     *
     * @param text  texto original
     * @param codes mapa Character -> código binario
     * @return cadena binaria resultante
     */
    public static String encode(String text, Map<Character, String> codes) {
        if (codes == null) return "";
        Map<Integer, String> mi = new HashMap<>();
        for (Map.Entry<Character, String> e : codes.entrySet()) {
            mi.put((int) e.getKey(), e.getValue());
        }
        return encodeInt(text, mi);
    }

    /**
     * Decodifica un string binario usando el árbol de Huffman.
     *
     * @param encoded cadena binaria
     * @param root    raíz del árbol de Huffman
     * @return texto decodificado
     */
    public static String decode(String encoded, HuffmanNode root) {
        if (root == null || encoded == null) return "";
        StringBuilder sb = new StringBuilder();
        HuffmanNode node = root;
        for (int i = 0; i < encoded.length(); i++) {
            char b = encoded.charAt(i);
            if (b == '0') node = node.getLeft(); else node = node.getRight();

            if (node == null) break; // seguridad

            if (node.getLeft() == null && node.getRight() == null) {
                sb.append((char) node.getCharacter());
                node = root;
            }
        }

        return sb.toString();
    }
}
