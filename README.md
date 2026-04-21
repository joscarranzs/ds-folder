# ds-folder

## Descripción

Este repositorio se utiliza para organizar y subir todos los proyectos, tareas y laboratorios de la materia de Estructura de Datos.

## Flujo de trabajo

- Clonar el repositorio:
  ```bash
  git clone https://github.com/joscarranzs/ds-folder.git
  ```
- Cambiar a la rama `dev`:
  ```bash
  git checkout dev
  ```
  Si la rama no existe localmente, crearla desde la rama principal:
  ```bash
  git checkout -b dev origin/main
  ```
- Verificar cambios en remoto y sincronizar todo:
  ```bash
  git fetch
  git status
  git pull origin dev
  ```
- Usar ramas temporales con convenciones locales para trabajar en cambios aislados.
- Crear una rama de convención con un nombre claro según el tipo de tarea:
  ```bash
  git checkout -b feat/nombre-descriptivo
  git checkout -b fix/nombre-descriptivo
  git checkout -b docs/nombre-descriptivo
  git checkout -b chore/nombre-descriptivo
  ```
  Tipos de convenciones de rama:
  - `feat/` para nuevas funcionalidades.
  - `fix/` para correcciones de errores.
  - `docs/` para cambios en documentación.
  - `chore/` para tareas de mantenimiento y configuración.
- Al terminar la tarea, agregar los cambios y crear un commit usando las convenciones:
  ```bash
  git add .
  git commit -m "feat: agregar nuevo algoritmo de búsqueda"
  ```
  Convenciones de commit:
  - `feat:` para nuevas funcionalidades.
  - `fix:` para correcciones de errores.
  - `docs:` para documentación.
  - `chore:` para tareas generales o de mantenimiento.
  - `refactor:` para cambios en el código sin nueva funcionalidad.
- Fusionar los cambios a la rama `dev` cuando termines la tarea:
  ```bash
  git checkout dev
  git merge --no-ff feat/nombre-descriptivo
  ```
- Eliminar la rama temporal en el local después de fusionar:
  ```bash
  git branch -d feat/nombre-descriptivo
  ```

## Funcionamiento del código del laboratorio L1_JoseCarranza

Este repositorio contiene un proyecto Maven en `laboratories/L1_JoseCarranza` que implementa un visualizador de árbol binario con una interfaz JavaFX.

### Estructura principal

- `src/main/java/com/ds/App.java`
  - Clase de entrada (`main`).
  - Inicia la aplicación JavaFX delegando en `BinaryTree`.
- `src/main/java/com/ds/application/core/view/BinaryTree.java`
  - Construye la interfaz de usuario completa.
  - Crea la barra lateral, el lienzo central, el panel inspector y la barra inferior de estadísticas.
  - Dibuja dos representaciones:
    - `drawTree(...)`: muestra el árbol binario con nodos y conexiones.
    - `drawLinkedList(...)`: muestra una vista en tabla enlazada con columnas `LEFT`, `INFO` y `RIGHT`.
  - Controla la presentación activa entre árbol y tabla.
- `src/main/java/com/ds/application/core/controller/TreeController.java`
  - Controlador MVC que maneja la interacción entre modelo y vista.
  - Inserta nuevos nodos, actualiza el inspector, y refresca las métricas.
  - Cambia la representación activa según la selección del usuario.
- `src/main/java/com/ds/application/core/model/BinarySearchTree.java`
  - Implementa el árbol binario de búsqueda.
  - Provee inserción, búsqueda y cálculo de métricas.
  - Calcula:
    - profundidad del árbol (`depth()`)
    - longitud de camino interno (`internalPathLength()`)
    - longitud de camino interno medio (`meanInternalPathLength()`)
    - nodos padres y hojas.
- `src/main/java/com/ds/application/core/model/TreeNode.java`
  - Representa cada nodo del árbol.
  - Guarda valor y referencias a hijos izquierdo y derecho.

### Flujo de ejecución

1. `App.main(...)` arranca JavaFX.
2. `BinaryTree.start(...)` construye la ventana y crea el controlador.
3. `TreeController` opera sobre el modelo `BinarySearchTree` y actualiza la vista.
4. Al insertar un valor, el árbol se redibuja y se recalculan las métricas.
5. Al cambiar a `Linked List Table`, la vista muestra una tabla ordenada en preorden con índices 1-based para los nodos.

### Cómo exponerlo

- Explica la separación MVC:
  - La vista contiene todos los elementos gráficos.
  - El controlador gestiona eventos y recibe datos del modelo.
  - El modelo mantiene la estructura de datos y los cálculos.
- Muestra que la aplicación soporta dos vistas del mismo árbol:
  - visualización gráfica en árbol
  - visualización tipo tabla enlazada
- Describe cómo se calculan las métricas de árbol internas y cómo se actualizan automáticamente.

### Cómo exponerlo

- Explica la separación MVC:
  - La vista contiene todos los elementos gráficos.
  - El controlador gestiona eventos y recibe datos del modelo.
  - El modelo mantiene la estructura de datos y los cálculos.
- Muestra que la aplicación soporta dos vistas del mismo árbol:
  - visualización gráfica en árbol
  - visualización tipo tabla enlazada
- Describe cómo se calculan las métricas de árbol internas y cómo se actualizan automáticamente.

### Cómo ejecutar el proyecto

Para ejecutar el laboratorio desde Maven y que JavaFX se cargue correctamente:

```bash
cd laboratories/L1_JoseCarranza
mvn javafx:run
```

Esta configuración detecta el sistema operativo y carga la dependencia de JavaFX correcta. Si usas otro sistema operativo y aparece un error, puedes forzar la plataforma con:

```bash
mvn javafx:run -Djavafx.platform=linux
```
```bash
mvn javafx:run -Djavafx.platform=mac
```
```bash
mvn javafx:run -Djavafx.platform=win32
```
