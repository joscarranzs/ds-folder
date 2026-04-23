# ds-folder

## Descripción del proyecto

Este repositorio agrupa proyectos, tareas y laboratorios de la materia de Estructuras de Datos. El ejercicio principal aquí es el laboratorio `L1_JoseCarranza`, que implementa un visualizador interactivo de árbol binario utilizando JavaFX.

## Estructura del repositorio

- `README.md`: documentación general del proyecto.
- `laboratories/L1_JoseCarranza`: proyecto Maven principal del laboratorio.
- `laboratories/L1_JoseCarranza/pom.xml`: configuración de Maven, dependencias y plugins.
- `laboratories/L1_JoseCarranza/src/main/java`: código fuente Java de la aplicación.
- `laboratories/L1_JoseCarranza/src/main/resources`: recursos JavaFX, hojas de estilo y recursos gráficos.

## Estructura del proyecto `L1_JoseCarranza`

### Paquetes principales

- `com.ds`: clase `App` que arranca la aplicación.
- `com.ds.application.core.controller`: lógica de control y presentación.
- `com.ds.application.core.model`: implementación de la estructura de datos y cálculos de métricas.
- `com.ds.application.core.view`: componentes gráficos y visualización de la interfaz.

## Archivos clave y su propósito

### `laboratories/L1_JoseCarranza/src/main/java/com/ds/App.java`

- Clase principal que extiende `javafx.application.Application`.
- Método `main(String[] args)` ejecuta `launch(args)` para iniciar JavaFX.
- Método `start(Stage stage)` crea y muestra la pantalla de bienvenida con `WelcomeScreen`.

### `laboratories/L1_JoseCarranza/src/main/java/com/ds/application/core/view/WelcomeScreen.java`

- Construye la pantalla inicial de bienvenida.
- Muestra información del laboratorio, integrantes y botón de inicio.
- Al pulsar "Inicio", cierra la ventana de bienvenida y abre la vista principal `BinaryTree`.

### `laboratories/L1_JoseCarranza/src/main/java/com/ds/application/core/view/BinaryTree.java`

- Implementa la interfaz principal del visualizador.
- Crea la barra superior, la barra lateral de acciones, el canvas central, el panel inspector y la barra de estadísticas.
- Mantiene el estado de la vista y el controlador `TreeController`.
- Métodos principales:
  - `start(Stage stage)`: monta la ventana principal y todos los componentes.
  - `resetView()`: restablece la interfaz a estado inicial vacío.
  - `drawCurrentRepresentation(TreeNode root)`: dibuja el árbol según el modo activo.
  - `drawNodesByLevel(TreeNode root)`: dibuja la vista de nodos por nivel.
  - `animateSearch(List<TreeNode> path, boolean found)`: anima el recorrido de búsqueda.
  - `setInspector(...)` y `setStats(...)`: actualizan datos en el panel derecho y en la barra inferior.

### `laboratories/L1_JoseCarranza/src/main/java/com/ds/application/core/view/TreeCanvas.java`

- Motor gráfico que dibuja el árbol y las representaciones complementarias.
- Métodos principales:
  - `getView()`: devuelve el contenedor visual principal.
  - `resetView()`: limpia el canvas y muestra el mensaje de árbol vacío.
  - `drawTree(TreeNode root)`: dibuja la estructura jerárquica del árbol.
  - `drawLinkedList(TreeNode root)`: representa el árbol como una lista enlazada lineal.
  - `drawNodesByLevel(TreeNode root)`: muestra cada nivel de nodos en filas horizontales.
  - `animateSearch(...)`: destaca nodos visitados durante una búsqueda.

### `laboratories/L1_JoseCarranza/src/main/java/com/ds/application/core/view/InspectorPanel.java`

- Panel lateral para inspeccionar el nodo seleccionado.
- Muestra clave, grado, nivel y nodos hijos.
- Permite asignar la acción de eliminar con `setDeleteAction(...)`.
- Actualiza los valores con `setInspector(...)`.

### `laboratories/L1_JoseCarranza/src/main/java/com/ds/application/core/view/StatsBar.java`

- Barra inferior que muestra métricas del árbol.
- Muestra:
  - profundidad del árbol
  - longitud de camino interno (LCI)
  - longitud interna media (LCIM)
  - lista de valores de nodos padres
  - lista de valores de nodos hoja
- Método principal: `setStats(...)` para actualizar la información visual.

### `laboratories/L1_JoseCarranza/src/main/java/com/ds/application/core/controller/TreeController.java`

- Conecta la vista con el modelo (controlador en patrón MVC).
- Administra las acciones del usuario:
  - insertar nodos
  - buscar valores
  - eliminar nodos
  - cambiar representación entre árbol, lista y niveles
- Métodos principales:
  - `handleInsertNode()`: solicita un valor al usuario y lo inserta en el árbol.
  - `handleSearch()`: solicita un valor, busca el nodo y anima el resultado.
  - `handleDeleteNode()`: elimina el nodo seleccionado.
  - `handleNewTree()`: reinicia el árbol.
  - `handleSequential()`, `handleLinkedList()`, `handleNodesByLevel()`: cambian la vista activa.
  - `handleNodeClick(TreeNode node)`: guarda el nodo seleccionado y actualiza el inspector.

### `laboratories/L1_JoseCarranza/src/main/java/com/ds/application/core/controller/TreeDialogService.java`

- Servicio de diálogos para interactuar con el usuario.
- Métodos principales:
  - `requestInteger(String title, String prompt)`: muestra un diálogo para ingresar un entero.
  - `showError(...)` y `showInformation(...)`: muestran alertas modales.

### `laboratories/L1_JoseCarranza/src/main/java/com/ds/application/core/controller/TreeInspectorPresenter.java`

- Calcula y formatea los datos del nodo seleccionado.
- Determina grado, nivel y valores de hijos.
- Actualiza el panel inspector con `updateInspector(...)`.

### `laboratories/L1_JoseCarranza/src/main/java/com/ds/application/core/controller/TreeStatsPresenter.java`

- Calcula y muestra las métricas generales del árbol.
- Usa `TreeMetrics` para obtener profundidad, LCI, LCIM, padres y hojas.
- Actualiza la vista con `updateStats(...)`.

### `laboratories/L1_JoseCarranza/src/main/java/com/ds/application/core/model/BinarySearchTree.java`

- Implementa la estructura de datos principal.
- Operaciones básicas:
  - `insert(int value)`: inserta un valor siguiendo la propiedad BST.
  - `delete(int value)`: elimina un valor y reorganiza el árbol.
  - `search(int value)`: busca un nodo por valor.
  - `reset()`: borra todos los nodos.
- Estructura interna:
  - `insertNode(...)`, `deleteRecursive(...)`, `findMin(...)`, `searchNode(...)`.

### `laboratories/L1_JoseCarranza/src/main/java/com/ds/application/core/model/TreeNode.java`

- Representa un nodo del árbol con:
  - valor entero
  - referencias a hijo izquierdo y derecho
- Métodos principales:
  - `isLeaf()`: indica si el nodo no tiene hijos.
  - `isInternal()`: indica si tiene al menos un hijo.

### `laboratories/L1_JoseCarranza/src/main/java/com/ds/application/core/model/TreeMetrics.java`

- Calcula métricas del árbol de forma recursiva.
- Métodos más relevantes:
  - `depth(TreeNode root)`: profundidad máxima.
  - `size(TreeNode root)`: número total de nodos.
  - `sumValues(TreeNode root)`: suma de valores.
  - `averageValue(TreeNode root)`: promedio de valores.
  - `internalPathLength(TreeNode root)`: suma de profundidades de nodos internos.
  - `meanInternalPathLength(TreeNode root)`: promedio de la longitud interna.
  - `parentValues(TreeNode root)`: lista de valores de nodos padres.
  - `leafValues(TreeNode root)`: lista de valores de nodos hoja.

### `laboratories/L1_JoseCarranza/src/main/java/com/ds/application/core/model/TreeTraversal.java`

- Provee utilidades de recorrido y búsqueda.
- Métodos principales:
  - `searchWithPath(TreeNode root, int value)`: devuelve el camino recorrido hasta encontrar el valor.
  - `nodesAtLevel(TreeNode root, int level)`: obtiene los nodos de un nivel específico.
  - `level(TreeNode root, TreeNode target)`: calcula la profundidad de un nodo.

## Cómo ejecutar el laboratorio

Desde la carpeta del laboratorio:

```bash
cd laboratories/L1_JoseCarranza
mvn javafx:run
```

Si necesitas forzar la plataforma JavaFX:

```bash
mvn javafx:run -Djavafx.platform=linux
```
```bash
mvn javafx:run -Djavafx.platform=mac
```
```bash
mvn javafx:run -Djavafx.platform=win32
```

## Resumen de responsabilidades

- La vista (`view`) se encarga de mostrar la UI y recolectar eventos.
- El controlador (`controller`) coordina acciones del usuario, actualiza modelo y vista.
- El modelo (`model`) mantiene la estructura de datos y calcula métricas.
- El servicio de diálogo abstrae la entrada/salida de usuario.

## Notas adicionales

- El proyecto sigue una arquitectura similar a MVC para separar responsabilidades.
- La aplicación es adecuada para aprender cómo funciona un árbol binario de búsqueda y visualizar el árbol en diferentes formas.
