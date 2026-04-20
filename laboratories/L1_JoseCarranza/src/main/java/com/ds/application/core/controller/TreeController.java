package com.ds.application.core.controller;

import com.ds.application.core.model.BinarySearchTree;
import com.ds.application.core.model.TreeNode;
import com.ds.application.core.view.BinaryTree;
import javafx.scene.control.TextInputDialog;

import java.util.List;

/**
 * Controlador principal de la aplicación de árbol binario.
 *
 * <p>Separa la lógica de interacción de usuario (vista) de la lógica de datos
 * del árbol binario. Gestiona eventos, solicita datos al modelo y actualiza
 * la vista con los resultados.</p>
 */
public class TreeController {

    private final BinaryTree view;
    private final BinarySearchTree model;

    /**
     * Crea un controlador asociado a una vista específica.
     *
     * @param view vista principal que presentará el árbol y los indicadores
     */
    public TreeController(BinaryTree view) {
        this.view = view;
        this.model = new BinarySearchTree();

        view.setInspector("—", "—", "—", "—");
        view.setStats(0, "—", "—", "—", "—");
    }

    /**
     * Muestra un diálogo para insertar un valor entero y actualiza la vista.
     *
     * <p>Si el valor es válido, el método agrega el nodo al árbol,
     * redibuja la estructura y refresca los datos del inspector.</p>
     */
    public void handleInsertNode() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Insert Node");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter integer value:");

        dialog.showAndWait().ifPresent(input -> {
            try {
                int value = Integer.parseInt(input.trim());
                model.insert(value);
                view.drawCurrentRepresentation(model.getRoot());
                updateInspector(model.search(value));
                updateStats();
            } catch (NumberFormatException ignored) {
            }
        });
    }

    /**
     * Busca un nodo por su valor y actualiza el panel inspector.
     *
     * <p>Si el nodo se encuentra, muestra los detalles del nodo; de lo
     * contrario, informa que no se encontró.</p>
     */
    public void handleSearch() {
        if (model.isEmpty()) {
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Search Node");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter node value:");

        dialog.showAndWait().ifPresent(input -> {
            try {
                int value = Integer.parseInt(input.trim());
                TreeNode node = model.search(value);
                if (node != null) {
                    updateInspector(node);
                } else {
                    view.setInspector("Not found", "—", "—", "—");
                }
            } catch (NumberFormatException ignored) {
            }
        });
    }

    /**
     * Reinicia el árbol y restablece la vista a su estado original.
     */
    public void handleNewTree() {
        model.reset();
        view.resetView();
    }

    /**
     * Actualiza las estadísticas del árbol cuando se selecciona identificar
     * los nodos padres.
     */
    public void handleParentNodes() {
        updateStats();
    }

    /**
     * Actualiza las estadísticas del árbol cuando se selecciona identificar
     * los nodos hoja.
     */
    public void handleLeafNodes() {
        updateStats();
    }

    /**
     * Actualiza las estadísticas del árbol cuando se selecciona calcular LCI.
     */
    public void handleLCI() {
        updateStats();
    }

    /**
     * Actualiza las estadísticas del árbol cuando se selecciona calcular LCIM.
     */
    public void handleLCIM() {
        updateStats();
    }

    /**
     * Cambia la representación a la vista de árbol binario.
     */
    public void handleSequential() {
        view.setRepresentationActive(true);
        view.drawCurrentRepresentation(model.getRoot());
        updateStats();
    }

    /**
     * Cambia la representación a la vista de tabla enlazada.
     */
    public void handleLinkedList() {
        view.setRepresentationActive(false);
        view.drawCurrentRepresentation(model.getRoot());
        updateStats();
    }

    /**
     * Maneja el evento de clic sobre un nodo dibujado en pantalla.
     *
     * @param node nodo seleccionado por el usuario
     */
    public void handleNodeClick(TreeNode node) {
        updateInspector(node);
    }

    /**
     * Actualiza los valores de métricas que se muestran en la vista.
     *
     * <p>Si el árbol está vacío, restablece los indicadores a un valor neutro.</p>
     */
    private void updateStats() {
        if (model.isEmpty()) {
            view.setStats(0, "—", "—", "—", "—");
            return;
        }

        int depth = model.depth();
        int lci = model.internalPathLength();
        double lcim = model.meanInternalPathLength();
        List<Integer> parents = model.parentValues();
        List<Integer> leaves = model.leafValues();

        view.setStats(
                depth,
                String.valueOf(lci),
                String.format("%.2f", lcim),
                parents.toString(),
                leaves.toString()
        );
    }

    /**
     * Actualiza el panel de inspección con los datos del nodo seleccionado.
     *
     * @param node nodo cuyos datos se deben mostrar; puede ser {@code null}
     */
    private void updateInspector(TreeNode node) {
        if (node == null) {
            view.setInspector("—", "—", "—", "—");
            return;
        }

        int degree = (node.getLeft() != null ? 1 : 0) + (node.getRight() != null ? 1 : 0);
        int level = model.level(node);
        String levelText = level == -1 ? "—" : String.valueOf(level);

        String childNodes = modelChildrenText(node);

        view.setInspector(
                String.valueOf(node.getValue()),
                String.valueOf(degree),
                levelText,
                childNodes
        );
    }

    /**
     * Construye el texto que describe los hijos directos de un nodo.
     *
     * @param node nodo actual
     * @return cadena con los valores de los hijos o "none" si no tiene
     */
    private String modelChildrenText(TreeNode node) {
        StringBuilder builder = new StringBuilder();
        if (node.getLeft() != null) {
            builder.append(node.getLeft().getValue());
        }
        if (node.getRight() != null) {
            if (builder.length() > 0) {
                builder.append(", ");
            }
            builder.append(node.getRight().getValue());
        }
        return builder.length() == 0 ? "none" : builder.toString();
    }
}
