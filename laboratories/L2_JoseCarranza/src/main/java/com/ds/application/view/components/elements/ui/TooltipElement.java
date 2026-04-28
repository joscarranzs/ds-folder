// Elemento de UI usado para construir componentes como header, sidebar y controles de formulario.

package com.ds.application.view.components.elements.ui;

import javafx.scene.control.Tooltip;

public class TooltipElement {
    // TODO: implement tooltip element for JavaFX
    private Tooltip tooltip;

    public TooltipElement(String text){
        tooltip = new Tooltip(text);
        applyStyles();
    }

    private void applyStyles(){
        tooltip.getStyle();
    }

    public Tooltip getNode(){
        return tooltip;
    }
}