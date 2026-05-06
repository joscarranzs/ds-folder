package com.ds.application.view.components.sidebar;

import com.ds.application.controller.button.binary.DeletionController;
import com.ds.application.controller.button.binary.NodeInspectorController;
import com.ds.application.controller.button.binary.TraversalController;
import com.ds.application.view.components.elements.ui.ButtonElement;
import com.ds.application.view.components.elements.ui.LabelElement;
import com.ds.application.view.components.indicators.TreeInfoBar;
import com.ds.application.view.components.inspector.NodeInspector;
import com.ds.application.view.components.notifications.ToastNotification;
import com.ds.application.view.components.visualizers.BinaryTreeVisualizer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class BinaryTreeControlPanel extends VBox {

    private final ToastNotification toast;

    private ButtonElement insertBtn;
    private ButtonElement searchBtn;
    private ButtonElement clearBtn;
    private ButtonElement preorderBtn;
    private ButtonElement inorderBtn;
    private ButtonElement postorderBtn;

    public BinaryTreeControlPanel(
            BinaryTreeVisualizer visualizer,
            NodeInspector inspector,
            TreeInfoBar infoBar,
            ToastNotification toast
    ) {
        this.toast = toast;

        NodeInspectorController nodeController = new NodeInspectorController(visualizer, inspector);
        TraversalController traversalController = new TraversalController(nodeController, inspector, infoBar);
        DeletionController deletionController = new DeletionController(nodeController);

        inspector.setOnDelete(value -> {
            deletionController.delete(value);
            updateTreeData(nodeController, infoBar);
            infoBar.clearTraversal();
            toast.showSuccess("Nodo " + value + " eliminado correctamente.");
        });

        setPadding(new Insets(18));
        setSpacing(12);
        setPrefWidth(260);
        setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-border-color: #e5e7eb;" +
                "-fx-background-radius: 14;" +
                "-fx-border-radius: 14;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 18, 0.25, 0, 6);"
        );

        LabelElement title = new LabelElement("PANEL DE CONTROL");
        title.setTitle();

        LabelElement subtitle = new LabelElement("ARBOL BINARIO");
        subtitle.setSubtitle();

        LabelElement operations = new LabelElement("OPERACIONES");
        operations.setMuted();

        insertBtn = new ButtonElement("+", "Insertar");
        searchBtn = new ButtonElement("⌕", "Buscar");
        clearBtn = new ButtonElement("↻", "Limpiar");

        LabelElement traversals = new LabelElement("RECORRIDOS");
        traversals.setMuted();

        preorderBtn = new ButtonElement("▦", "Pre-orden");
        inorderBtn = new ButtonElement("☰", "In-orden");
        postorderBtn = new ButtonElement("☷", "Pos-orden");

        selectButton(insertBtn);

        insertBtn.getNode().setOnAction(e -> {
            selectButton(insertBtn);

            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Insertar nodo");
            dialog.setHeaderText(null);
            dialog.setContentText("Valor:");

            dialog.showAndWait().ifPresent(text -> {
                try {
                    int value = Integer.parseInt(text.trim());

                    nodeController.insert(value);
                    updateTreeData(nodeController, infoBar);
                    infoBar.clearTraversal();

                    toast.showSuccess("Nodo " + value + " insertado correctamente.");
                } catch (NumberFormatException ex) {
                    inspector.updateStatus("Valor invalido.");
                    toast.showError("Valor inválido. Ingrese solo números enteros.");
                }
            });
        });

        searchBtn.getNode().setOnAction(e -> {
            selectButton(searchBtn);

            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Buscar nodo");
            dialog.setHeaderText(null);
            dialog.setContentText("Valor:");

            dialog.showAndWait().ifPresent(text -> {
                try {
                    int value = Integer.parseInt(text.trim());

                    boolean exists = nodeController.contains(value);

                    nodeController.search(value);
                    updateTreeData(nodeController, infoBar);
                    infoBar.clearTraversal();

                    if (exists) {
                        toast.showSuccess("Nodo " + value + " encontrado.");
                    } else {
                        toast.showWarning("Nodo " + value + " no encontrado.");
                    }

                } catch (NumberFormatException ex) {
                    inspector.updateStatus("Valor invalido.");
                    toast.showError("Valor inválido. Ingrese solo números enteros.");
                }
            });
        });

        clearBtn.getNode().setOnAction(e -> {
            selectButton(clearBtn);

            if (nodeController.size() == 0) {
                toast.showWarning("El árbol ya está vacío.");
                return;
            }

            nodeController.clear();
            infoBar.clear();
            toast.showInfo("Árbol limpiado correctamente.");
        });

        preorderBtn.getNode().setOnAction(e -> {
            selectButton(preorderBtn);

            if (nodeController.size() == 0) {
                toast.showWarning("No hay nodos para recorrer.");
                return;
            }

            traversalController.preorder();
            toast.showInfo("Recorrido pre-orden ejecutado.");
        });

        inorderBtn.getNode().setOnAction(e -> {
            selectButton(inorderBtn);

            if (nodeController.size() == 0) {
                toast.showWarning("No hay nodos para recorrer.");
                return;
            }

            traversalController.inorder();
            toast.showInfo("Recorrido in-orden ejecutado.");
        });

        postorderBtn.getNode().setOnAction(e -> {
            selectButton(postorderBtn);

            if (nodeController.size() == 0) {
                toast.showWarning("No hay nodos para recorrer.");
                return;
            }

            traversalController.postorder();
            toast.showInfo("Recorrido pos-orden ejecutado.");
        });

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        VBox legendBox = createLegendBox();

        updateTreeData(nodeController, infoBar);

        getChildren().addAll(
                title.getNode(),
                subtitle.getNode(),
                operations.getNode(),
                insertBtn.getNode(),
                searchBtn.getNode(),
                clearBtn.getNode(),
                traversals.getNode(),
                preorderBtn.getNode(),
                inorderBtn.getNode(),
                postorderBtn.getNode(),
                spacer,
                legendBox
        );
    }

    private VBox createLegendBox() {
        VBox box = new VBox(8);
        box.setPadding(new Insets(6, 0, 0, 0));

        Label title = new Label("TIPOS DE NODO");
        title.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #9ca3af;");

        HBox legend = new HBox(10);
        legend.setAlignment(Pos.CENTER_LEFT);
        legend.setPadding(new Insets(8, 10, 8, 10));
        legend.setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: #e2e8f0;" +
                "-fx-border-radius: 18;"
        );

        legend.getChildren().addAll(
                createLegendItem("●", "RAIZ", "#0f172a"),
                createLegendItem("●", "INTERNO", "#2563eb"),
                createLegendItem("○", "HOJA", "#2563eb")
        );

        box.getChildren().addAll(title, legend);
        return box;
    }

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

    private void updateTreeData(NodeInspectorController nodeController, TreeInfoBar infoBar) {
        // Obtengo la representacion en parentesis desde el controlador de
        // inspeccion de nodos y la envío a la barra inferior junto con el resto
        // de metadatos.
        String structure = nodeController.parenthesisString();

        infoBar.updateTreeData(
                nodeController.getParentNodesText(),
                nodeController.getLeafNodesText(),
                nodeController.height(),
                nodeController.degree(),
                structure
        );
    }

    private void selectButton(ButtonElement selected) {
        insertBtn.setActive(false);
        searchBtn.setActive(false);
        clearBtn.setActive(false);
        preorderBtn.setActive(false);
        inorderBtn.setActive(false);
        postorderBtn.setActive(false);

        selected.setActive(true);
    }
}
