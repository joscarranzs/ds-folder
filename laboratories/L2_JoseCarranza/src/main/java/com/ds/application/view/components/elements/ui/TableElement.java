// Elemento de UI usado para construir componentes como header, sidebar y controles de formulario.

package com.ds.application.view.components.elements.ui;
import javafx.scene.control.TableView;
    /*
    *Componente reutilizable para tablas en JavaFX,
    *con estilos dinámicos para estados activos e inactivos.
    */
public class TableElement {
          /*
    instancia interna de la tabla de JavaFX */
    private TableView<String> tableView;
            /*
    *Constructor que inicializa la tabla con un texto y 
    *aplica estilos predeterminados para el estado inactivo.
    */
    public TableElement(){
        tableView = new TableView<>();
        setActive(false);
    }
       /*
    Cambia el estado visual de la tabla entre activo e inactivo
    * aplicando estilos CSS dinámicamente.
    */
    public void setActive(boolean active){
    if(active){
        /*cuando esta activo estara azul */
        tableView.setStyle(
            "-fx-background-color: #2563eb;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );
        /*cuando este inactivo tendra fondo blanco y borde gris */
    } else {
        tableView.setStyle(
            "-fx-background-color: #ffffff;" +
            "-fx-border-color: #dbe1ea;"
        );
    }
}
/*devuelve el nodo JavaFX de la tabla */
    public TableView<String> getNode(){
        return tableView;
    }
}
