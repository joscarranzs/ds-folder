// Elemento de UI usado para construir componentes como header, sidebar y controles de formulario.

package com.ds.application.view.components.elements.ui;

import javafx.scene.control.Tooltip;
    /*
    *Componente reutilizable para tooltips en JavaFX,
    *con estilos dinámicos para estados activos e inactivos.
    */
public class TooltipElement {
          /*
    instancia interna del tooltip de JavaFX */
    private Tooltip tooltip;

    public TooltipElement(String text){
        tooltip = new Tooltip(text);
        setActive(false);
    }
    /*Cambia el estado visual del tooltip entre activo e inactivo
    * aplicando estilos CSS dinámicamente.
    */
       public void setActive(boolean active){
    if(active){
        /*cuando esta activo estara azul */
        tooltip.setStyle(
            "-fx-background-color: #2563eb;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );
    } else {
        /*cuando este inactivo tendra fondo blanco y borde gris */
        tooltip.setStyle(
            "-fx-background-color: #ffffff;" +
            "-fx-border-color: #dbe1ea;"
        );
    }
}
/*devuelve el nodo JavaFX del tooltip */
    public Tooltip getNode(){
        return tooltip;
    }
}