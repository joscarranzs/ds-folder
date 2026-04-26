++README.md
Core package usage
==================

This README gives short usage examples for the core classes that other
packages in the project depend on. Only classes that are intended to be
consumed by other packages are documented here.

1) BinarySearchTree
-------------------
Simple BST storing integers. Use insert/delete/getRoot to manipulate the tree.

Example:

```java
BinarySearchTree bst = new BinarySearchTree();
bst.insert(10);
bst.insert(5);
bst.insert(15);
// root available for visualizers or other utilities
BinaryTreeNode<Integer> root = bst.getRoot();
```

2) BinaryTreeOperations
-----------------------
Utility class for performing searches, traversals and simple metrics on a
binary tree. Construct it with a BinaryTreeNode root.

Example:

```java
BinaryTreeOperations ops = new BinaryTreeOperations(root);
BinaryTreeNode<Integer> found = ops.search(5);
boolean has = ops.contains(7);
int size = ops.size();
int height = ops.height();
String inOrder = ops.inOrderString();
```

3) HuffmanBinaryTree and HuffmanNode
------------------------------------
Lightweight container for a Huffman tree and its nodes. Use
HuffmanAlgorithmOperations to build and use the tree.

Example:

```java
// build tree from text
HuffmanBinaryTree tree = HuffmanAlgorithmOperations.buildTreeFromText("aaabbc");
HuffmanNode root = tree.getRoot();
boolean empty = tree.isEmpty();
```

4) HuffmanAlgorithmOperations
-----------------------------
Contains static helpers for Huffman coding: counting frequencies, building
the tree, generating codes, encoding and decoding strings.

Example:

```java
AuxMap<Character, Integer> freqs = HuffmanAlgorithmOperations.countFrequencies("aabccc");
HuffmanBinaryTree tree = HuffmanAlgorithmOperations.buildTreeFromFrequencies(freqs);
AuxMap<Character, String> codes = HuffmanAlgorithmOperations.buildCodes(tree.getRoot());
String encoded = HuffmanAlgorithmOperations.encode("aab", codes);
String decoded = HuffmanAlgorithmOperations.decode(encoded, tree.getRoot());
```

5) Auxiliary data structures (used across core)
---------------------------------------------
The core package uses small replacements for java.util collections to avoid
bringing java.util into core code. These are lightweight and designed to be
used internally and by other packages.

Main types:
- SimpleList<T>: dynamic list with add/get/size
- AuxMap<K,V>: simple map backed by Entry<K,V> list
- Entry<K,V>: key/value pair used by AuxMap
- AuxQueue<T>: queue interface
- AuxPriorityQueue<T>: priority queue implementation that needs an AuxComparator
- AuxComparator<T>: comparator functional interface

Example usage (SimpleList & AuxMap):

```java
SimpleList<Integer> list = new SimpleList<>();
list.add(1);
list.add(2);
int v = list.get(0);

AuxMap<Character, Integer> map = new AuxMap<>();
map.put('a', 2);
Integer count = map.get('a');
```

Priority queue example (used by Huffman builder):

```java
AuxQueue<HuffmanNode> pq = new AuxPriorityQueue<>(new AuxComparator<HuffmanNode>() {
    @Override
    public int compare(HuffmanNode a, HuffmanNode b) {
        return Integer.compare(a.getFrequency(), b.getFrequency());
    }
});
pq.offer(new HuffmanNode('a', 3));
HuffmanNode n = pq.poll();
```

Notes
-----
- All examples assume appropriate imports from com.ds.application.core.*
- These classes are intentionally small; consult their Javadoc for more
  precise contract details (null handling, return values, exceptions).
