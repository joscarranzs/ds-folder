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

Con estas herramientas podrás abrir el proyecto, compilar el código y ejecutar las pruebas de forma sencilla en un entorno Windows.

## Instrucciones de git

- Preconfigurar git antes de clonar:
  - `git config --global user.name "Tu Nombre"`
  - `git config --global user.email "tu.email@dominio.com"`

- Clonar el repositorio desde la terminal:
  - `git clone https://github.com/joscarranzs/ds-folder.git`
  - `cd ds-folder`

- Crear y cambiarse a la rama temporal asignada en local:
  - José: `git checkout -b jose`
  - Arena: `git checkout -b arena`
  - Xavi: `git checkout -b xavi`

<<<<<<< HEAD
- Guardar cambios en la rama temporal y crear el commit en inglés usando convenciones:
  - `git add .`
  - `git commit -m "feat: add git instructions"`
  - Ejemplos de tipos de convenciones:
    - `feat: ` para nuevas funcionalidades
    - `fix: ` para correcciones de errores
    - `docs: ` para cambios en documentación
    - `style: ` para formato o estilo sin cambios funcionales
    - `refactor: ` para reestructurar código sin comportamiento nuevo
    - `test: ` para agregar o actualizar pruebas
    - `chore: ` para tareas de mantenimiento y configuración

- Subir los cambios a `dev` desde la rama temporal local:
  - `git checkout dev`
  - `git pull origin dev`
  - `git merge --no-ff <rama-asignada>`
  - `git push origin dev`
  - `git checkout <rama-asignada>`

<<<<<<< HEAD
- Repetir el mismo flujo para seguir trabajando en su rama temporal:
  - trabajar en la rama temporal local
  - `git add .`
  - `git commit -m "<tipo>: <descripción en inglés>"
  - `git checkout dev`
  - `git pull origin dev`
  - `git merge --no-ff <rama-asignada>`
  - `git push origin dev`
  - `git checkout <rama-asignada>`

- Las ramas `dev` y `main` son las principales. Las ramas `jose`, `arena` y `xavi` son temporales y locales.
- Nota: No tocar la rama `main`. Solo el administrador puede crear el PR y fusionar `dev` en `main`.

