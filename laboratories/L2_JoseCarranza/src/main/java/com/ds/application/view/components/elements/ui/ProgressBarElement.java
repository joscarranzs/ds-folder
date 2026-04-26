package com.ds.application.view.components.elements.ui;

import javafx.scene.control.ProgressBar;

/**
 * Envoltorio para {@link ProgressBar} con estilos básicos y acceso al nodo.
 */
public class ProgressBarElement {
    private ProgressBar progressBar;

    /**
     * Crea una ProgressBar y aplica el estilo inicial (inactivo).
     */
    public ProgressBarElement(){
        progressBar = new ProgressBar();
        setActive(false);
    }

    /**
     * Alterna el estilo visual de la barra de progreso.
     *
     * @param active true para estilo activo, false para inactivo
     */
    public void setActive(boolean active){
        if(active){
            progressBar.setStyle(
                "-fx-background-color: #2563eb;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
            );
        } else {
            progressBar.setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-border-color: #dbe1ea;"
            );
        }
    }

    /**
     * Devuelve el nodo {@link ProgressBar} asociado.
     *
     * @return ProgressBar creada
     */
    public ProgressBar getNode(){
        return progressBar;
    }
}
