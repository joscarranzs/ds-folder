package com.ds.application.core.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;

public class InspectorPanel {

    private final VBox root;
    private final Label lblKeyValue;
    private final Label lblDegree;
    private final Label lblLevel;
    private final Label lblChildNodes;

    private final Button btnDelete;

    public InspectorPanel() {
        root = new VBox(0);
        root.getStyleClass().add("right-panel");
        root.setPrefWidth(300);

        HBox header = new HBox(8);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(20, 20, 16, 20));

        Label title = new Label("NODE INSPECTOR");
        title.getStyleClass().add("inspector-title");

        Label info = new Label("ℹ");
        info.getStyleClass().add("info-icon");
        header.getChildren().addAll(title, info);

        root.getChildren().addAll(header, createDivider());

        lblKeyValue = inspectorValueLabel("—");
        lblDegree = inspectorValueLabel("—");
        lblLevel = inspectorValueLabel("—");
        lblChildNodes = inspectorChipLabel("—");

        root.getChildren().add(inspectorRow("KEY VALUE", lblKeyValue));
        root.getChildren().add(createDivider());
        root.getChildren().add(inspectorRow("DEGREE", lblDegree));
        root.getChildren().add(createDivider());
        root.getChildren().add(inspectorRow("LEVEL", lblLevel));
        root.getChildren().add(createDivider());
        root.getChildren().add(inspectorRow("CHILD NODES", lblChildNodes));
        root.getChildren().add(createDivider());

        btnDelete = new Button("DELETE NODE");
        btnDelete.setGraphic(createIcon("fas-times-circle", Color.web("#e11d48")));
        btnDelete.setGraphicTextGap(12);
        btnDelete.setContentDisplay(ContentDisplay.LEFT);
        btnDelete.getStyleClass().add("sidebar-btn-delete");
        btnDelete.setMaxWidth(Double.MAX_VALUE);
        btnDelete.setAlignment(Pos.CENTER_LEFT);

        root.getChildren().add(btnDelete);
        Region filler = new Region();
        VBox.setVgrow(filler, Priority.ALWAYS);
        root.getChildren().add(filler);
    }

    public void setDeleteAction(javafx.event.EventHandler<javafx.event.ActionEvent> action) {
        btnDelete.setOnAction(action);
    }

    public VBox getRoot() {
        return root;
    }

    public void setInspector(String key, String degree, String level, String childNodes) {
        lblKeyValue.setText(key);
        lblDegree.setText(degree);
        lblLevel.setText(level);
        lblChildNodes.setText(childNodes);
    }

    private HBox inspectorRow(String title, Label value) {
        HBox row = new HBox();
        row.getStyleClass().add("insp-row");
        row.setPadding(new Insets(14, 20, 14, 20));
        row.setAlignment(Pos.CENTER_LEFT);

        Label label = new Label(title);
        label.getStyleClass().add("insp-label");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        row.getChildren().addAll(label, spacer, value);
        return row;
    }

    private Label inspectorValueLabel(String value) {
        Label label = new Label(value);
        label.getStyleClass().add("insp-value");
        return label;
    }

    private Label inspectorChipLabel(String value) {
        Label label = new Label(value);
        label.getStyleClass().add("insp-chip");
        return label;
    }

    private FontIcon createIcon(String literal, Color color) {
        FontIcon icon = new FontIcon(literal);
        icon.setIconSize(20);
        icon.setIconColor(color);
        icon.getStyleClass().add("button-icon");
        return icon;
    }

    private Region createDivider() {
        Region divider = new Region();
        divider.getStyleClass().add("sidebar-divider");
        divider.setPrefHeight(1);
        return divider;
    }
}
