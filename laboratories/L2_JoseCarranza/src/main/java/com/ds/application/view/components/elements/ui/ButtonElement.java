<<<<<<< HEAD
// Elemento de UI usado para construir componentes como header, sidebar y controles de formulario.
=======
>>>>>>> origin/main
package com.ds.application.view.components.elements.ui;

import javafx.scene.control.Button;

<<<<<<< HEAD
/*
 * Componente reutilizable para botones en JavaFX,
 * con estilos dinámicos para estados activos e inactivos.
 */
public class ButtonElement {

    private Button button;

    public ButtonElement(String text) {
=======
/**
 * Componente envoltorio para javafx.scene.control.Button.
 * Proporciona una forma sencilla de crear un botón con estilos predeterminados
 * y alternar su apariencia entre estados "activo" e "inactivo".
 * <p>
 * Las instancias devuelven el nodo JavaFX mediante {@link #getNode()} para
 * ser insertadas en la escena. Los estilos aplicados son inline; se
 * recomienda usar una hoja de estilos externa para proyectos mayores.
 */
/**
 * Crea y estiliza un {@link javafx.scene.control.Button} con estados visuales.
 */
public class ButtonElement {
    private Button button;

    /**
     * Crea un ButtonElement con el texto visible en el botón.
     * El estado inicial es inactivo.
     *
     * @param text texto que se mostrará en el botón
     */
    public ButtonElement(String text){
>>>>>>> origin/main
        button = new Button(text);
        setActive(false);
    }

<<<<<<< HEAD
    public void setActive(boolean active) {
        if (active) {
            button.setStyle(
                    "-fx-background-color: #2563eb;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 13px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 10;" +
                    "-fx-padding: 8 14;" +
                    "-fx-cursor: hand;" +
                    "-fx-effect: dropshadow(gaussian, rgba(37,99,235,0.3), 8, 0.3, 0, 2);"
            );
        } else {
            button.setStyle(
                    "-fx-background-color: #f8fafc;" +
                    "-fx-text-fill: #1e293b;" +
                    "-fx-font-size: 13px;" +
                    "-fx-background-radius: 10;" +
                    "-fx-border-color: #e2e8f0;" +
                    "-fx-border-radius: 10;" +
                    "-fx-padding: 8 14;" +
                    "-fx-cursor: hand;"
=======
    /**
     * Actualiza la apariencia visual del botón para indicar su estado.
     *
     * @param active true para aplicar el estilo "activo", false para "inactivo"
     */
    public void setActive(boolean active){
        if(active){
            button.setStyle(
                "-fx-background-color: #2563eb;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
            );
        } else {
            button.setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-border-color: #dbe1ea;"
>>>>>>> origin/main
            );
        }
    }

<<<<<<< HEAD
    public Button getNode() {
        return button;
    }
}
=======
    /**
     * Devuelve el nodo JavaFX asociado a este componente.
     *
     * @return instancia de {@link Button} que representa este elemento
     */
    public Button getNode(){
        return button;
    }
}
>>>>>>> origin/main
