# Repositorio de estructura de datos

## Descripción

Este repositorio agrupa proyectos, tareas y laboratorios de la materia Estructura de Datos II. Contiene ejercicios en Java, prácticas de algoritmos y estructuras de datos, y desarrollos asociados a los laboratorios de la asignatura, incluyendo el proyecto principal `L1_JoseCarranza` con un visualizador interactivo de árbol binario en JavaFX.

## Herramienta

Para trabajar en los proyectos se recomienda instalar las siguientes herramientas:

- NetBeans: un IDE completo para Java que incluye soporte para proyectos Maven, debugging y edición de código.
- Visual Studio Code: un editor liviano y extensible con soporte para Java mediante extensiones.
- JDK (Java Development Kit): necesario para compilar y ejecutar aplicaciones Java.

### Instalación (Windows)

1. JDK
   - Descarga la versión recomendada de OpenJDK o Oracle JDK desde el sitio oficial.
   - En Windows, descarga el instalador `.msi` o `.exe` y ejecútalo.
   - Asegúrate de seleccionar la opción para agregar Java al `PATH` si el instalador lo pregunta.
   - Verifica la instalación en la consola de Windows con `java -version` y `javac -version`.

2. NetBeans
   - Descarga NetBeans desde https://netbeans.apache.org/
   - Ejecuta el instalador y sigue las instrucciones en pantalla.
   - En Windows, utiliza el instalador `.exe` y acepta las opciones por defecto si no necesitas configuraciones especiales.

3. Visual Studio Code
   - Descarga VS Code desde https://code.visualstudio.com/
   - Ejecuta el instalador `.exe` y sigue los pasos del asistente.
   - Para Java en VS Code, instala el paquete de extensiones "Extension Pack for Java" desde el marketplace.
   - También puedes instalar la extensión "Language Support for Java(TM) by Red Hat" si deseas soporte adicional.

Con estas herramientas podrás abrir el proyecto, compilar el código y ejecutar las pruebas de forma sencilla en un entorno Windows o macOS.

## Instrucciones de git

Esta sección describe las convenciones de commits (los commits deben ir en inglés), cómo crear y usar una rama local nombrada `local/<nombre-colega>`, y el flujo de trabajo recomendado que se debe repetir cada vez que trabajes en la rama local.

1. Convenciones para commits (en inglés)

- Usamos Conventional Commits como guía. Formato en la primera línea:

  ```text
  <type>(<scope>): <short description>
  ```

- Tipos comunes:
  - feat: new features
  - fix: bug fixes
  - docs: documentation changes
  - style: formatting (no code change)
  - refactor: code change without feature or bug fix
  - test: adding or updating tests
  - chore: maintenance tasks
  - perf, ci, build, revert: según convenga

- Recomendaciones:
  - Los commits deben escribirse en inglés.
  - Usa el imperativo en el subject (Add, Fix, Update...).
  - Mantén el subject corto (idealmente <= 50 caracteres) y claro.
  - Añade un body (segunda línea y siguientes) para explicar el "why" y cómo probar los cambios cuando sea necesario.

- Ejemplos:
  - Commit corto (una línea):

    ```bash
    git commit -m "feat(parser): add CSV import support"
    ```

  - Commit con descripción detallada:

    ```bash
    git commit -m "fix(reader): skip empty input lines" -m "Empty lines previously caused NPE in Reader. Add null/empty checks and unit tests. To test: mvn test -Dtest=ReaderTest"
    ```

  - Otros ejemplos:

    ```bash
    git commit -m "docs(readme): update setup instructions"
    git commit -m "refactor(tree): simplify traversal API"
    ```

2. Crear y cambiar a la rama local `local/<nombre-colega>`

- Sustituye `<nombre-colega>` por tu nombre o identificador (ej.: `local/jose`).

- Crear la rama y cambiarse a ella (desde la raíz del repo):

  ```bash
  git fetch origin
  git switch -c local/<nombre-colega>
  # (alternativa antigua) git checkout -b local/<nombre-colega>
  ```

- Si la rama ya existe localmente, simplemente cámbiate a ella:

  ```bash
  git switch local/<nombre-colega>
  # (alternativa) git checkout local/<nombre-colega>
  ```

3. Flujo de trabajo recomendado (repetir este ciclo)

Este proceso es el flujo de trabajo que se debe repetir cada vez que trabajes:

- a) Asegurarse de estar en la rama local.

  ```bash
  git switch local/<nombre-colega>
  ```

- b) Empezar a trabajar en su tarea correspondiente.

  ```bash
  # ejemplo: editar archivos y/o ejecutar pruebas
  mvn test
  ```

- c) Agregar cambios y crear commit.

  ```bash
  git add <ruta/al/archivo>
  # o para todo: git add .
  git commit -m "feat(module): short description" -m "Why this change, how to test."
  ```

- d) Cambiarse a `main` (revisar si hay cambios en remoto antes de fusionar y subir).

  ```bash
  git switch main
  git fetch origin
  git pull origin main
  ```

- e) Fusionar el local con `main`.

  ```bash
  git merge --no-ff local/<nombre-colega>
  ```

- f) Subir los cambios a `main`.

  ```bash
  git push origin main
  ```

- g) Eliminar la rama local y volver a empezar desde el punto 2.

  ```bash
  git branch -d local/<nombre-colega>
  git switch -c local/<nombre-colega>
  ```
