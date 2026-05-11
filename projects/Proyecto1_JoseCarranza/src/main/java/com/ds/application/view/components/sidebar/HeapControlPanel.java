package com.ds.application.view.components.sidebar;

import com.ds.application.controller.button.heap.HeapController;
import com.ds.application.view.components.elements.ui.ButtonElement;
import com.ds.application.view.components.elements.ui.LabelElement;
import com.ds.application.view.components.notifications.ToastNotification;
import com.ds.application.view.components.visualizers.HeapVisualizer;
import javafx.geometry.Insets;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;

public class HeapControlPanel extends VBox {

    // Controlador que maneja la lógica del montón
    private final HeapController controller;

    // Notificación flotante global
    private final ToastNotification toast;

    // Indica si estoy en montón mínimo o máximo
    private final boolean minHeapMode;

    // Botones principales del panel
    private ButtonElement insertBtn;
    private ButtonElement deleteBtn;
    private ButtonElement clearBtn;

    public HeapControlPanel(HeapVisualizer visualizer, boolean minHeapMode, ToastNotification toast) {

        this.toast = toast;
        this.minHeapMode = minHeapMode;

        // Creo el controlador indicando si es montón mínimo o máximo
        controller = new HeapController(visualizer, minHeapMode);

        // Configuración visual general del panel lateral
        setPadding(new Insets(18));
        setSpacing(12);
        setPrefWidth(260);
        setStyle(
                "-fx-background-color: #BFCFBB;" +
                "-fx-border-color: #8EA58C;" +
                "-fx-background-radius: 14;" +
                "-fx-border-radius: 14;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 18, 0.25, 0, 6);"
        );

        // Títulos del panel
        LabelElement title = new LabelElement("PANEL DE CONTROL");
        title.setTitle();

        LabelElement subtitle = new LabelElement(minHeapMode ? "MONTON MINIMO" : "MONTON MAXIMO");
        subtitle.setSubtitle();

        LabelElement operations = new LabelElement("OPERACIONES");
        operations.setMuted();

        // Botones de operaciones del montón
        insertBtn = new ButtonElement("+", "Insertar");
        deleteBtn = new ButtonElement("−", "Eliminar raiz");
        clearBtn = new ButtonElement("↻", "Limpiar");

        // Estado inicial
        selectButton(insertBtn);

        // Evento para insertar valor
        insertBtn.getNode().setOnAction(e -> {
            selectButton(insertBtn);

            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Insertar valor");
            dialog.setHeaderText(null);
            dialog.setContentText("Valor:");

            dialog.showAndWait().ifPresent(text -> {
                try {
                    int value = Integer.parseInt(text.trim());

                    controller.insert(value);

                    toast.showSuccess(
                            (minHeapMode ? "Montón mínimo" : "Montón máximo") +
                            ": valor " + value + " insertado."
                    );

                } catch (NumberFormatException ex) {
                    toast.showError("Valor inválido. Ingrese solo números enteros.");
                }
            });
        });

        // Evento para eliminar la raíz del montón
        deleteBtn.getNode().setOnAction(e -> {
            selectButton(deleteBtn);

            boolean deleted = controller.deleteRoot();

            if (deleted) {
                toast.showSuccess("Raíz eliminada correctamente.");
            } else {
                toast.showWarning("No se puede eliminar. El montón está vacío.");
            }
        });

        // Evento para limpiar el montón completo
        clearBtn.getNode().setOnAction(e -> {
            selectButton(clearBtn);

            controller.clear();
            toast.showInfo("Montón limpiado correctamente.");
        });

        // Agrego todos los elementos al panel en orden
        getChildren().addAll(
                title.getNode(),
                subtitle.getNode(),
                operations.getNode(),
                insertBtn.getNode(),
                deleteBtn.getNode(),
                clearBtn.getNode()
        );
    }

    // Cambia visualmente cuál botón está activo
    private void selectButton(ButtonElement selected) {
        insertBtn.setActive(false);
        deleteBtn.setActive(false);
        clearBtn.setActive(false);

        selected.setActive(true);
    }
}