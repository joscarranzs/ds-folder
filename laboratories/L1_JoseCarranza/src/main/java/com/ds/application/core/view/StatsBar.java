package com.ds.application.core.view;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 * Renderiza la barra de estadísticas inferior para las métricas del árbol actual.
 *
 * <p>La barra de estadísticas muestra la profundidad, la longitud del camino, los nodos padres y los nodos hoja.</p>
 */
public class StatsBar {

    private final HBox root;
    private final Label lblDepthBar;
    private final Label lblLCIBar;
    private final Label lblLCIMBar;
    private final Label lblParentsBar;
    private final Label lblLeavesBar;

    public StatsBar() {
        root = new HBox(20);
        root.getStyleClass().add("bottom-bar");
        root.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        root.setPadding(new javafx.geometry.Insets(10, 24, 10, 24));

        root.getChildren().add(legendItem("INTERNAL NODE", "#0f172a"));
        root.getChildren().add(legendItem("LEAF NODE", "#e2e8f0"));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        root.getChildren().add(spacer);

        lblDepthBar = statLabel("0");
        lblLCIBar = statLabel("—");
        lblLCIMBar = statLabel("—");
        lblParentsBar = statLabel("—");
        lblLeavesBar = statLabel("—");

        root.getChildren().add(statItem("TREE DEPTH:", lblDepthBar));
        root.getChildren().add(statItem("LCI:", lblLCIBar));
        root.getChildren().add(statItem("LCIM:", lblLCIMBar));
        root.getChildren().add(statItem("PARENT NODES:", lblParentsBar));
        root.getChildren().add(statItem("LEAF NODES:", lblLeavesBar));
    }

    /**
     * Devuelve el componente raíz de UI para la barra de estadísticas.
     *
     * @return el nodo raíz usado por el diseño padre
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Actualiza los valores estadísticos mostrados en la barra de estadísticas.
     *
     * @param depth la profundidad actual del árbol
     * @param lci la longitud de camino interno formateada
     * @param lcim la longitud interna media formateada
     * @param parents los valores de nodos padres formateados
     * @param leaves los valores de nodos hoja formateados
     */
    public void setStats(int depth, String lci, String lcim, String parents, String leaves) {
        lblDepthBar.setText(String.valueOf(depth));
        lblLCIBar.setText(lci);
        lblLCIMBar.setText(lcim);
        lblParentsBar.setText(parents);
        lblLeavesBar.setText(leaves);
    }

    private HBox legendItem(String text, String color) {
        HBox item = new HBox(8);
        item.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

        javafx.scene.shape.Circle dot = new javafx.scene.shape.Circle(7);
        dot.setFill(javafx.scene.paint.Color.web(color));
        dot.setStroke(javafx.scene.paint.Color.web("#cbd5e1"));
        dot.setStrokeWidth(1.5);

        Label label = new Label(text);
        label.getStyleClass().add("legend-label");

        item.getChildren().addAll(dot, label);
        return item;
    }

    private Label statLabel(String value) {
        Label label = new Label(value);
        label.getStyleClass().add("stat-bar-value");
        return label;
    }

    private HBox statItem(String title, Label valueLabel) {
        HBox item = new HBox(4);
        item.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("stat-bar-title");
        item.getChildren().addAll(titleLabel, valueLabel);

        return item;
    }
}
