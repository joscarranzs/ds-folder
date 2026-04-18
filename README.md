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
