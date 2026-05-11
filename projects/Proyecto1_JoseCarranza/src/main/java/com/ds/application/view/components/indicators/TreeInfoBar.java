package com.ds.application.view.components.indicators;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class TreeInfoBar extends HBox {
  /**
   * Barra inferior que resume informacion del arbol: recorrido, nodos,
   * profundidad, grado, LCI, LCIM y estructura en parentesis. Es un componente
   * visual que se actualiza desde controllers.
   */
  private Label traversalValue;
  private Label parentNodesValue;
  private Label leafNodesValue;
  private Label depthValue;
  private Label degreeValue;
  private Label lciValue;
  private Label lcimValue;
  private Label structureValue;

  /**
   * Construye la barra inferior con su estilo y componentes iniciales.
   * Cada tarjeta de informacion se crea con un metodo auxiliar para
   * mantener el constructor limpio.
   */
  public TreeInfoBar() {
    setSpacing(10);
    setPadding(new Insets(12));
    setAlignment(Pos.CENTER_LEFT);
    setStyle(
        "-fx-background-color: #BFCFBB;" +
            "-fx-border-color: #8EA58C;" +
            "-fx-background-radius: 14;" +
            "-fx-border-radius: 14;" +
            "-fx-effect: dropshadow(gaussian, rgba(52,76,61,0.08), 14, 0.20, 0, 4);");

    traversalValue = new Label("-");
    parentNodesValue = new Label("-");
    leafNodesValue = new Label("-");
    depthValue = new Label("0");
    degreeValue = new Label("0");
    lciValue = new Label("0");
    lcimValue = new Label("0.00");
    structureValue = new Label("-");

    // Creo las tarjetas por separado para poder ajustar mejor el espacio.
    // Las tarjetas pequeñas no deben crecer demasiado para evitar ocultar
    // informacion cuando el recorrido o la estructura tengan muchos caracteres.
    VBox traversalCard = createInfoCard("Recorrido", traversalValue);
    VBox parentsCard = createInfoCard("Nodos padre", parentNodesValue);
    VBox leavesCard = createInfoCard("Nodos hoja", leafNodesValue);
    VBox depthCard = createSmallInfoCard("Profundidad", depthValue);
    VBox degreeCard = createSmallInfoCard("Grado", degreeValue);
    VBox lciCard = createSmallInfoCard("LCI", lciValue);
    VBox lcimCard = createSmallInfoCard("LCIM", lcimValue);
    VBox structureCard = createInfoCard("Paréntesis", structureValue);

    traversalCard.setMinWidth(130);
    traversalCard.setPrefWidth(150);

    parentsCard.setMinWidth(125);
    parentsCard.setPrefWidth(145);

    leavesCard.setMinWidth(125);
    leavesCard.setPrefWidth(145);

    structureCard.setMinWidth(190);
    HBox.setHgrow(structureCard, Priority.ALWAYS);

    getChildren().addAll(
        traversalCard,
        parentsCard,
        leavesCard,
        depthCard,
        degreeCard,
        lciCard,
        lcimCard,
        structureCard
    );
  }

  /**
   * Crea una tarjeta de informacion con un titulo y un valor. Es un metodo
   * auxiliar para mantener el constructor limpio y evitar repeticion de codigo.
   *
   * @param titleText  El texto del titulo de la tarjeta.
   * @param valueLabel La etiqueta que mostrara el valor dinamico.
   * @return Un VBox formateado como tarjeta de informacion.
   */
  private VBox createInfoCard(String titleText, Label valueLabel) {
    VBox card = new VBox(4);
    card.setPadding(new Insets(10, 12, 10, 12));
    card.setStyle(
        "-fx-background-color: #DBE8D9;" +
            "-fx-background-radius: 10;" +
            "-fx-border-radius: 10;" +
            "-fx-border-color: #8EA58C;");

    Label title = new Label(titleText);
    title.setStyle(
        "-fx-font-size: 11px;" +
            "-fx-text-fill: #738A6E;" +
            "-fx-font-weight: bold;");

    valueLabel.setWrapText(true);
    valueLabel.setMaxWidth(Double.MAX_VALUE);
    valueLabel.setStyle(
        "-fx-font-size: 13px;" +
            "-fx-text-fill: #344C3D;" +
            "-fx-font-weight: bold;");

    card.getChildren().addAll(title, valueLabel);
    return card;
  }

  /**
   * Crea una tarjeta compacta para valores cortos como profundidad, grado,
   * LCI y LCIM. Esto evita que estas tarjetas ocupen demasiado espacio.
   *
   * @param titleText  El texto del titulo de la tarjeta.
   * @param valueLabel La etiqueta que mostrara el valor dinamico.
   * @return Un VBox formateado como tarjeta compacta.
   */
  private VBox createSmallInfoCard(String titleText, Label valueLabel) {
    VBox card = createInfoCard(titleText, valueLabel);
    card.setMinWidth(86);
    card.setPrefWidth(92);
    card.setMaxWidth(100);
    return card;
  }

  /**
   * Metodos publicos para actualizar la informacion mostrada en la barra.
   * Estos metodos seran llamados desde controllers para reflejar cambios en el
   * arbol.
   */
  public void updateTreeData(String parents, String leaves, int depth, int degree, String structure) {
    updateTreeData(parents, leaves, depth, degree, 0, 0.0, structure);
  }

  public void updateTreeData(
      String parents,
      String leaves,
      int depth,
      int degree,
      long lci,
      double lcim,
      String structure
  ) {
    parentNodesValue.setText(parents == null || parents.isEmpty() ? "-" : parents);
    leafNodesValue.setText(leaves == null || leaves.isEmpty() ? "-" : leaves);
    depthValue.setText(String.valueOf(depth));
    degreeValue.setText(String.valueOf(degree));
    lciValue.setText(String.valueOf(lci));
    lcimValue.setText(String.format("%.2f", lcim));
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
    lciValue.setText("0");
    lcimValue.setText("0.00");
    structureValue.setText("-");
  }
}