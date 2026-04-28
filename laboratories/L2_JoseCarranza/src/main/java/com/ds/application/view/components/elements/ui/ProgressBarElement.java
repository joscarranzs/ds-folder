// Elemento de UI usado para construir componentes como header, sidebar y controles de formulario.
package com.ds.application.view.components.elements.ui;

import javafx.scene.control.ProgressBar;

public class ProgressBarElement {
    // TODO: implement progress bar element for JavaFX
    private ProgressBar progressBar;

    public ProgressBarElement(double progress){
        progressBar = new ProgressBar(progress);
        applyStyles();
    }

    public ProgressBarElement(){
        progressBar = new ProgressBar();
        applyStyles();
    }

    public void applyStyles(){
        //Clase CSS
        progressBar.getStylesheets();
    }

    public ProgressBar getNode(){
        return progressBar;
    }
}