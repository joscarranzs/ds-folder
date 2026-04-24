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
   - Para Java en VS Code, instala la extensión "Extension Pack for Java" desde el marketplace.
   - También puedes instalar la extensión "Language Support for Java(TM) by Red Hat" si deseas soporte adicional.

Con estas herramientas podrás abrir el proyecto, compilar el código y ejecutar las pruebas de forma sencilla en un entorno Windows.

## Instrucciones de git

- Clonar repositorio:
  - `git clone <url-del-repositorio>`
  - `cd <nombre-del-repositorio>`

- Moverse a la rama correspondiente y asignada:
  - José: `git checkout jose`
  - Arena: `git checkout arena`
  - Xavi: `git checkout xavi`

- Las ramas `dev` y `main` son las principales. Las demás ramas (`jose`, `arena`, `xavi`) son temporales.
- Nota: No se debe tocar la rama `dev` ni `main` directamente. Solo subir y actualizar cambios desde sus ramas personales.

- Para actualizar su rama desde `dev` antes de subir cambios:
  - `git checkout <rama-asignada>`
  - `git pull origin dev`

- Para subir cambios desde su rama temporal a `dev`:
  - `git add .`
  - `git commit -m "Descripción de los cambios"`
  - `git push origin <rama-asignada>`

- Después de que la rama temporal haya sido revisada y aprobada, hacer merge a `dev` según la política del equipo.
