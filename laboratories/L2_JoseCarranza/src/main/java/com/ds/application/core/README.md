++README.md
README - Paquete core
=====================

Este README contiene ejemplos de uso cortos para las clases del paquete
core que se consumen desde otros paquetes del proyecto. Solo se documentan
las clases previstas para uso externo.

1) BinarySearchTree
-------------------
Árbol binario de búsqueda (BST) simple que almacena enteros. Se usan los
métodos insert/delete/getRoot para manipular el árbol.

Ejemplo:

```java
BinarySearchTree bst = new BinarySearchTree();
bst.insert(10);
bst.insert(5);
bst.insert(15);
// obtener la raíz para visualizadores u otras utilidades
BinaryTreeNode<Integer> root = bst.getRoot();
```

2) BinaryTreeOperations
-----------------------
Clase de utilidades para realizar búsquedas, recorridos y métricas simples
sobre un árbol binario. Se construye con la raíz (BinaryTreeNode).

Ejemplo:

```java
BinaryTreeOperations ops = new BinaryTreeOperations(root);
BinaryTreeNode<Integer> found = ops.search(5);
boolean has = ops.contains(7);
int size = ops.size();
int height = ops.height();
String inOrder = ops.inOrderString();
```

3) HuffmanBinaryTree y HuffmanNode
----------------------------------
Contenedor ligero para un árbol de Huffman y sus nodos. Para construir y
usar el árbol, emplea HuffmanAlgorithmOperations.

Ejemplo:

```java
// construir árbol desde texto
HuffmanBinaryTree tree = HuffmanAlgorithmOperations.buildTreeFromText("aaabbc");
HuffmanNode root = tree.getRoot();
boolean empty = tree.isEmpty();
```

4) HuffmanAlgorithmOperations
-----------------------------
Contiene funciones estáticas para el algoritmo de Huffman: conteo de
frecuencias, construcción del árbol, generación de códigos, codificar y
decodificar cadenas.

Ejemplo:

```java
AuxMap<Character, Integer> freqs = HuffmanAlgorithmOperations.countFrequencies("aabccc");
HuffmanBinaryTree tree = HuffmanAlgorithmOperations.buildTreeFromFrequencies(freqs);
AuxMap<Character, String> codes = HuffmanAlgorithmOperations.buildCodes(tree.getRoot());
String encoded = HuffmanAlgorithmOperations.encode("aab", codes);
String decoded = HuffmanAlgorithmOperations.decode(encoded, tree.getRoot());
```

5) Estructuras auxiliares (usadas desde core)
--------------------------------------------
El paquete core usa reemplazos ligeros de colecciones de java.util para
mantener el núcleo libre de esas dependencias. Son estructuras simples,
diseñadas para uso interno y por otros paquetes.

Tipos principales:
- SimpleList<T>: lista dinámica con add/get/size
- AuxMap<K,V>: mapa sencillo respaldado por una lista de Entry<K,V>
- Entry<K,V>: par clave/valor usado por AuxMap
- AuxQueue<T>: interfaz de cola
- AuxPriorityQueue<T>: implementación de cola de prioridad que requiere un AuxComparator
- AuxComparator<T>: interfaz comparadora

Ejemplo de uso (SimpleList & AuxMap):

```java
SimpleList<Integer> list = new SimpleList<>();
list.add(1);
list.add(2);
int v = list.get(0);

AuxMap<Character, Integer> map = new AuxMap<>();
map.put('a', 2);
Integer count = map.get('a');
```

Ejemplo de cola de prioridad (usado por el constructor de Huffman):

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

Notas
-----
- Los ejemplos presuponen las importaciones adecuadas desde
  com.ds.application.core.*
- Estas clases son intencionalmente pequeñas; consulta su Javadoc para
  detalles más precisos sobre contratos (manejo de null, valores devueltos,
  excepciones).
