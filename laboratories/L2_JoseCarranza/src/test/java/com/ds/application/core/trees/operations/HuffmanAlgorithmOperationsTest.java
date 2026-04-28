package com.ds.application.core.trees.operations;

import com.ds.application.core.trees.HuffmanBinaryTree;
import com.ds.application.core.structures.HuffmanNode;
import junit.framework.TestCase;

import java.util.Map;

public class HuffmanAlgorithmOperationsTest extends TestCase {

    public void testBuildHuffmanTreeAndEncodeDecode() {
        String text = "aaabbc";

        HuffmanBinaryTree tree = HuffmanAlgorithmOperations.buildTreeFromText(text);
        assertNotNull(tree);
        assertNotNull(tree.getRoot());

        Map<Character, String> codes = HuffmanAlgorithmOperations.buildCodes(tree.getRoot());
        assertNotNull(codes);
        assertFalse(codes.isEmpty());
        assertTrue(codes.containsKey('a'));
        assertTrue(codes.containsKey('b'));
        assertTrue(codes.containsKey('c'));

        String encoded = HuffmanAlgorithmOperations.encode(text, codes);
        assertNotNull(encoded);
        assertFalse(encoded.isEmpty());

        String decoded = HuffmanAlgorithmOperations.decode(encoded, tree.getRoot());
        assertEquals(text, decoded);
    }

    public void testCountFrequencies() {
        String text = "abac";
        Map<Character, Integer> frequencyMap = HuffmanAlgorithmOperations.countFrequencies(text);

        assertEquals(3, frequencyMap.size());
        assertEquals(Integer.valueOf(2), frequencyMap.get('a'));
        assertEquals(Integer.valueOf(1), frequencyMap.get('b'));
        assertEquals(Integer.valueOf(1), frequencyMap.get('c'));
    }
}
