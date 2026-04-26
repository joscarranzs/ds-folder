package com.ds.application.core.trees.operations;

import com.ds.application.core.structures.HuffmanNode;
import com.ds.application.core.trees.HuffmanBinaryTree;

import com.ds.application.core.structures.aux.Entry;
import com.ds.application.core.structures.aux.SimpleList;
import com.ds.application.core.structures.aux.AuxMap;
import com.ds.application.core.structures.aux.AuxPriorityQueue;
import com.ds.application.core.structures.aux.AuxQueue;
import java.util.Comparator;

// Operaciones para construir y usar un árbol Huffman.
public class HuffmanAlgorithmOperations {

    public static HuffmanBinaryTree buildTreeFromText(String text) {
        if (text == null || text.isEmpty()) {
            return new HuffmanBinaryTree();
        }

        AuxMap<Character, Integer> frequencyMap = countFrequencies(text);
        return buildTreeFromFrequencies(frequencyMap);
    }
    public static HuffmanBinaryTree buildTreeFromFrequencies(AuxMap<Character, Integer> frequencies) {
        if (frequencies == null || frequencies.isEmpty()) {
            return new HuffmanBinaryTree();
        }

        AuxQueue<HuffmanNode> queue = new AuxPriorityQueue<>(new Comparator<HuffmanNode>() {
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

    public static boolean isLeaf(HuffmanNode node) {
        return node != null && node.getLeft() == null && node.getRight() == null;
    }
}
