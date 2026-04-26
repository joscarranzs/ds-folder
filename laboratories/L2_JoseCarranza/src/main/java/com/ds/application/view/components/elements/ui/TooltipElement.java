package com.ds.application.view.components.elements.ui;

import javafx.scene.control.Tooltip;

/**
 * Envoltorio para {@link Tooltip} que facilita la creación y aplicación
 * de estilos básicos por estado.
 */
public class TooltipElement {
    private Tooltip tooltip;

    /**
     * Crea un Tooltip con el texto proporcionado.
     *
     * @param text texto del tooltip
     */
    public TooltipElement(String text){
        tooltip = new Tooltip(text);
        setActive(false);
    }

    /**
     * Aplica estilo visual según el estado activo/inactivo.
     *
     * @param active true para estilo activo, false para inactivo
     */
    public void setActive(boolean active){
        if(active){
            tooltip.setStyle(
                "-fx-background-color: #2563eb;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
            );
        } else {
            tooltip.setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-border-color: #dbe1ea;"
            );
        }
    }

    /**
     * Devuelve el nodo {@link Tooltip} asociado.
     *
     * @return Tooltip creado
     */
    public Tooltip getNode(){
        return tooltip;
    }
}
