package com.ds.application.view.components.indicators;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TreeInfoBar extends HBox {
  /**
   * Barra inferior que resume informacion del arbol: recorrido, nodos,
   * profundidad y grado. Es un componente visual que se actualiza desde
   * controllers.
   */
  private Label traversalValue;
  private Label parentNodesValue;
  private Label leafNodesValue;
  private Label depthValue;
  private Label degreeValue;
  private Label structureValue;

  /**
   * Construye la barra inferior con su estilo y componentes iniciales.
   * Cada tarjeta de informacion se crea con un metodo auxiliar para
   * mantener el constructor limpio.
   */
  public TreeInfoBar() {
    setSpacing(12);
    setPadding(new Insets(12));
    setAlignment(Pos.CENTER_LEFT);
    setStyle(
        "-fx-background-color: #ffffff;" +
            "-fx-border-color: #e5e7eb;" +
            "-fx-background-radius: 14;" +
            "-fx-border-radius: 14;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.06), 14, 0.20, 0, 4);");

    traversalValue = new Label("-");
    parentNodesValue = new Label("-");
    leafNodesValue = new Label("-");
    depthValue = new Label("0");
    degreeValue = new Label("0");
    structureValue = new Label("-");

    // Creo las tarjetas por separado para poder ajustar el tamaño de la tarjeta
    // de estructura (la representación en paréntesis puede ser larga y debe
    // permitir salto de línea).
    VBox traversalCard = createInfoCard("Recorrido", traversalValue);
    VBox parentsCard = createInfoCard("Nodos padre", parentNodesValue);
    VBox leavesCard = createInfoCard("Nodos hoja", leafNodesValue);
    VBox depthCard = createInfoCard("Profundidad", depthValue);
    VBox degreeCard = createInfoCard("Grado", degreeValue);
    VBox structureCard = createInfoCard("Paréntesis", structureValue);
    structureCard.setMinWidth(260);

    getChildren().addAll(traversalCard, parentsCard, leavesCard, depthCard, degreeCard, structureCard);
  }

  /**
   * Crea una tarjeta de informacion con un titulo y un valor. Es un metodo
   * auxiliar
   * para mantener el constructor limpio y evitar repeticion de codigo.
   *
   * @param titleText  El texto del titulo de la tarjeta (ej: "Nodos padre").
   * @param valueLabel La etiqueta que mostrara el valor dinamico (ej: "5").
   * @return Un VBox formateado como tarjeta de informacion.
   */
  private VBox createInfoCard(String titleText, Label valueLabel) {
    VBox card = new VBox(4);
    card.setMinWidth(150);
    card.setPadding(new Insets(10, 12, 10, 12));
    card.setStyle(
        "-fx-background-color: #f1f5f9;" +
            "-fx-background-radius: 10;" +
            "-fx-border-radius: 10;");

    Label title = new Label(titleText);
    title.setStyle("-fx-font-size: 11px; -fx-text-fill: #64748b; -fx-font-weight: bold;");

    valueLabel.setWrapText(true);
    valueLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #111827; -fx-font-weight: bold;");

    card.getChildren().addAll(title, valueLabel);
    return card;
  }

  /**
   * Metodos publicos para actualizar la informacion mostrada en la barra.
   * Estos metodos seran llamados desde controllers para reflejar cambios en el
   * arbol.
   */
  public void updateTreeData(String parents, String leaves, int depth, int degree, String structure) {
    parentNodesValue.setText(parents == null || parents.isEmpty() ? "-" : parents);
    leafNodesValue.setText(leaves == null || leaves.isEmpty() ? "-" : leaves);
    depthValue.setText(String.valueOf(depth));
    degreeValue.setText(String.valueOf(degree));
    structureValue.setText(structure == null || structure.isEmpty() ? "-" : structure);
  }

  public void updateTraversal(String name, String result) {
    traversalValue.setText(name + ": " + result);
  }

  public void clearTraversal() {
    traversalValue.setText("-");
  }

  public void clear() {
    traversalValue.setText("-");
    parentNodesValue.setText("-");
    leafNodesValue.setText("-");
    depthValue.setText("0");
    degreeValue.setText("0");
    structureValue.setText("-");
  }
}
