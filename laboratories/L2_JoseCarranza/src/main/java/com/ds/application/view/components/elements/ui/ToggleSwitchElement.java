// Elemento de UI usado para construir componentes como header, sidebar y controles de formulario.

package com.ds.application.view.components.elements.ui;
import javafx.scene.control.ToggleButton;
    /*
    *Componente reutilizable para interruptores de toggle en JavaFX,
    *con estilos dinámicos para estados activos e inactivos.
    */
public class ToggleSwitchElement {
          /*
    instancia interna del interruptor de toggle de JavaFX */
    private ToggleButton toggleButton;
            /*
    *Constructor que inicializa el interruptor con un texto y 
    *aplica estilos predeterminados para el estado inactivo.
    */
    public ToggleSwitchElement(String label){
        toggleButton = new ToggleButton(label);
        setActive(false);
    }
    /*Cambia el estado visual del interruptor entre activo e inactivo
    * aplicando estilos CSS dinámicamente.
    */
   public void setActive(boolean active){
    if(active){
        /*cuando esta activo estara azul */
        toggleButton.setStyle(
            "-fx-background-color: #2563eb;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );
    } else {
        /*cuando este inactivo tendra fondo blanco y borde gris */
        toggleButton.setStyle(
            "-fx-background-color: #ffffff;" +
            "-fx-border-color: #dbe1ea;"
        );
    }
}
/*devuelve el nodo JavaFX del interruptor */
    public ToggleButton getNode(){
        return toggleButton;
    }
}
