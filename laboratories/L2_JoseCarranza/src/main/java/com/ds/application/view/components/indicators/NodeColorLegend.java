package com.ds.application.view.components.indicators;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NodeColorLegend extends VBox {
  /**
   * Leyenda que explica los colores usados para los nodos del arbol.
   * Es un componente visual puro usado en el panel lateral.
   */
  public NodeColorLegend() {
    setPadding(new Insets(6, 0, 0, 0));
    setSpacing(8);

    Label title = new Label("TIPOS DE NODO");
    title.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #9ca3af;");

    HBox legend = new HBox(10);
    legend.setAlignment(Pos.CENTER_LEFT);
    legend.setPadding(new Insets(8, 10, 8, 10));
    legend.setStyle(
        "-fx-background-color: #ffffff;" +
            "-fx-background-radius: 18;" +
            "-fx-border-color: #e2e8f0;" +
            "-fx-border-radius: 18;");

    legend.getChildren().addAll(
        createLegendItem("●", "RAIZ", "#0f172a"),
        createLegendItem("●", "INTERNO", "#2563eb"),
        createLegendItem("○", "HOJA", "#2563eb"));

    getChildren().addAll(title, legend);
  }

  /**
   * Crea un item de leyenda con un icono, texto y color específico.
   *
   * @param icon  El símbolo que representa el tipo de nodo (● para raíz/interno,
   *              ○ para hoja).
   * @param text  La etiqueta descriptiva del tipo de nodo.
   * @param color El color del símbolo para diferenciar los tipos de nodo.
   * @return Un HBox que contiene el ícono y la etiqueta formateados.
   */
  private HBox createLegendItem(String icon, String text, String color) {
    HBox item = new HBox(5);
    item.setAlignment(Pos.CENTER);

    Label dot = new Label(icon);
    dot.setStyle("-fx-text-fill: " + color + "; -fx-font-size: 12px;");

    Label label = new Label(text);
    label.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #334155;");

    item.getChildren().addAll(dot, label);
    return item;
  }
}
