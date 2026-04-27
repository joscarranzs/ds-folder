package com.ds.application.core.trees.operations;

import com.ds.application.core.structures.HuffmanNode;
import com.ds.application.core.trees.HuffmanBinaryTree;

import com.ds.application.core.structures.aux.Entry;
import com.ds.application.core.structures.aux.SimpleList;
import com.ds.application.core.structures.aux.AuxMap;
import com.ds.application.core.structures.aux.AuxPriorityQueue;
import com.ds.application.core.structures.aux.AuxQueue;
import com.ds.application.core.structures.aux.AuxComparator;

/**
 * Operaciones relacionadas con el algoritmo y uso del árbol de Huffman.
 *
 * Incluye funciones para construir el árbol a partir de un texto o mapas de
 * frecuencias, generar códigos, codificar y decodificar cadenas.
 */
public class HuffmanAlgorithmOperations {

    /**
     * Construye un HuffmanBinaryTree a partir de un texto, contando las
     * frecuencias de caracteres.
     *
     * @param text texto fuente (puede ser null o vacío)
     * @return árbol de Huffman construido o un árbol vacío si no hay datos
     */
    public static HuffmanBinaryTree buildTreeFromText(String text) {
        if (text == null || text.isEmpty()) {
            return new HuffmanBinaryTree();
        }

        AuxMap<Character, Integer> frequencyMap = countFrequencies(text);
        return buildTreeFromFrequencies(frequencyMap);
    }
    /**
     * Construye un HuffmanBinaryTree a partir de un mapa de frecuencias.
     *
     * @param frequencies mapa de frecuencias (puede ser null o vacío)
     * @return árbol de Huffman resultante
     */
    public static HuffmanBinaryTree buildTreeFromFrequencies(AuxMap<Character, Integer> frequencies) {
        if (frequencies == null || frequencies.isEmpty()) {
            return new HuffmanBinaryTree();
        }

        AuxQueue<HuffmanNode> queue = new AuxPriorityQueue<>(new AuxComparator<HuffmanNode>() {
            @Override
            public int compare(HuffmanNode a, HuffmanNode b) {
                return Integer.compare(a.getFrequency(), b.getFrequency());
            }
        });
        SimpleList<Entry<Character, Integer>> entries = frequencies.entryList();
        for (int i = 0; i < entries.size(); i++) {
            Entry<Character, Integer> en = entries.get(i);
            queue.offer(new HuffmanNode(en.getKey(), en.getValue()));
        }

        while (queue.size() > 1) {
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();
            HuffmanNode parent = new HuffmanNode('\0', left.getFrequency() + right.getFrequency());
            parent.setLeft(left);
            parent.setRight(right);
            queue.offer(parent);
        }

        HuffmanNode root = queue.poll();
        return new HuffmanBinaryTree(root);
    }

    /**
     * Cuenta la frecuencia de aparición de cada carácter en el texto.
     *
     * @param text texto fuente (puede ser null)
     * @return AuxMap con la frecuencia por carácter
     */
    public static AuxMap<Character, Integer> countFrequencies(String text) {
        AuxMap<Character, Integer> frequencies = new AuxMap<>();
        if (text == null) {
            return frequencies;
        }

        for (char c : text.toCharArray()) {
            Integer prev = frequencies.get(c);
            frequencies.put(c, (prev == null ? 0 : prev) + 1);
        }

        return frequencies;
    }

    /**
     * Genera el mapa de códigos Huffman (carácter -> código binario en forma de String)
     * a partir de la raíz del árbol.
     *
     * @param root raíz del árbol de Huffman
     * @return AuxMap con códigos por carácter (vacío si root es null)
     */
    public static AuxMap<Character, String> buildCodes(HuffmanNode root) {
        AuxMap<Character, String> codes = new AuxMap<>();
        if (root == null) {
            return codes;
        }
        buildCodesRecursive(root, "", codes);
        return codes;
    }

    private static void buildCodesRecursive(HuffmanNode node, String code, AuxMap<Character, String> codes) {
        if (node == null) {
            return;
        }

        if (isLeaf(node)) {
            codes.put(node.getCharacter(), code.isEmpty() ? "0" : code);
            return;
        }

        buildCodesRecursive(node.getLeft(), code + '0', codes);
        buildCodesRecursive(node.getRight(), code + '1', codes);
    }

    /**
     * Codifica un texto usando la tabla de códigos proporcionada.
     *
     * @param text  texto a codificar
     * @param codes mapa de códigos generado por buildCodes
     * @return cadena con la representación en bits (como '0' y '1')
     * @throws IllegalArgumentException si un carácter del texto no tiene código
     */
    public static String encode(String text, AuxMap<Character, String> codes) {
        if (text == null || text.isEmpty() || codes == null || codes.isEmpty()) {
            return "";
        }

        StringBuilder encoded = new StringBuilder();
        for (char c : text.toCharArray()) {
            String code = codes.get(c);
            if (code == null) {
                throw new IllegalArgumentException("Character not found in Huffman code table: " + c);
            }
            encoded.append(code);
        }
        return encoded.toString();
    }

    /**
     * Decodifica una cadena de bits usando el árbol de Huffman.
     *
     * @param encodedText cadena de bits (por ejemplo "0101")
     * @param root        raíz del árbol de Huffman usado para decodificar
     * @return texto original decodificado
     * @throws IllegalArgumentException si el texto codificado no es válido
     */
    public static String decode(String encodedText, HuffmanNode root) {
        if (encodedText == null || encodedText.isEmpty() || root == null) {
            return "";
        }

        StringBuilder decoded = new StringBuilder();
        HuffmanNode current = root;
        for (char bit : encodedText.toCharArray()) {
            current = (bit == '0') ? current.getLeft() : current.getRight();
            if (current == null) {
                throw new IllegalArgumentException("Encoded text is not valid for the provided Huffman tree.");
            }
            if (isLeaf(current)) {
                decoded.append(current.getCharacter());
                current = root;
            }
        }

        return decoded.toString();
    }

    /**
     * Indica si un nodo de Huffman es hoja (no tiene hijos).
     *
     * @param node nodo a comprobar
     * @return true si es hoja, false en caso contrario
     */
    public static boolean isLeaf(HuffmanNode node) {
        return node != null && node.getLeft() == null && node.getRight() == null;
    }
}
