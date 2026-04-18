package com.ds.application.ui.Resources;

import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * ArbolController.java
 * Maneja toda la lógica del árbol binario
 * y actualiza la UI automáticamente.
 */
public class TreeController {

    private final BinaryTree ui;
    private NodoArbol raiz;

    public TreeController(BinaryTree ui) {
        this.ui = ui;
        this.raiz = null;

        ui.setInspector("—", "—", "—", "—");
        ui.setStats(0, "—", "—", "—", "—");
    }

    // =====================================================
    // NODO INTERNO
    // =====================================================

    private static class NodoArbol {
        int valor;
        NodoArbol izquierdo;
        NodoArbol derecho;

        NodoArbol(int valor) {
            this.valor = valor;
        }
    }

    // =====================================================
    // BOTONES SIDEBAR
    // =====================================================

    public void handleInsertNode() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Insert Node");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter integer value:");

        dialog.showAndWait().ifPresent(input -> {
            try {
                int valor = Integer.parseInt(input.trim());

                raiz = insertarBST(raiz, valor);

                dibujarArbol();

                NodoArbol n = buscar(raiz, valor);
                if (n != null) {
                    actualizarInspector(n);
                }

                actualizarStats();

            } catch (NumberFormatException ignored) {
            }
        });
    }

    public void handleSearch() {
        if (raiz == null) return;

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Search Node");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter node value:");

        dialog.showAndWait().ifPresent(input -> {
            try {
                int valor = Integer.parseInt(input.trim());

                NodoArbol n = buscar(raiz, valor);

                if (n != null) {
                    actualizarInspector(n);
                } else {
                    ui.setInspector("Not found", "—", "—", "—");
                }

            } catch (NumberFormatException ignored) {
            }
        });
    }

    public void handleNewTree() {
        raiz = null;

        ui.panelArbol.getChildren().clear();
        ui.lblVacio.setVisible(true);
        ui.panelArbol.getChildren().add(ui.lblVacio);

        ui.setInspector("—", "—", "—", "—");
        ui.setStats(0, "—", "—", "—", "—");
    }

    public void handleParentNodes() {
        actualizarStats();
    }

    public void handleLeafNodes() {
        actualizarStats();
    }

    public void handleLCI() {
        actualizarStats();
    }

    public void handleLCIM() {
        actualizarStats();
    }

    public void handleSequential() {
        ui.setRepresentationActive(true);
        actualizarStats();
    }

    public void handleLinkedList() {
        ui.setRepresentationActive(false);
        actualizarStats();
    }

    public void handleParentheses() {
        actualizarStats();
    }

    public void handleNodeDegree() {
        if (raiz == null) return;

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Node Degree");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter node value:");

        dialog.showAndWait().ifPresent(input -> {
            try {
                int valor = Integer.parseInt(input.trim());

                NodoArbol n = buscar(raiz, valor);

                if (n != null) {
                    actualizarInspector(n);
                } else {
                    ui.setInspector("Not found", "—", "—", "—");
                }

            } catch (NumberFormatException ignored) {
            }
        });
    }

    public void handleDepth() {
        actualizarStats();
    }

    // =====================================================
    // LÓGICA BST
    // =====================================================

    private NodoArbol insertarBST(NodoArbol nodo, int valor) {
        if (nodo == null) return new NodoArbol(valor);

        if (valor < nodo.valor) {
            nodo.izquierdo = insertarBST(nodo.izquierdo, valor);
        } else if (valor > nodo.valor) {
            nodo.derecho = insertarBST(nodo.derecho, valor);
        }

        return nodo;
    }

    private NodoArbol buscar(NodoArbol nodo, int valor) {
        if (nodo == null) return null;

        if (valor == nodo.valor) return nodo;

        if (valor < nodo.valor) {
            return buscar(nodo.izquierdo, valor);
        }

        return buscar(nodo.derecho, valor);
    }

    private int altura(NodoArbol nodo) {
        if (nodo == null) return -1;

        return 1 + Math.max(
                altura(nodo.izquierdo),
                altura(nodo.derecho)
        );
    }

    private int contarNodos(NodoArbol nodo) {
        if (nodo == null) return 0;

        return 1 +
                contarNodos(nodo.izquierdo) +
                contarNodos(nodo.derecho);
    }

    private int nivel(NodoArbol actual, NodoArbol buscado, int nivel) {
        if (actual == null) return -1;

        if (actual == buscado) return nivel;

        int izq = nivel(actual.izquierdo, buscado, nivel + 1);

        if (izq != -1) return izq;

        return nivel(actual.derecho, buscado, nivel + 1);
    }

    private int sumaLCI(NodoArbol nodo) {
        if (nodo == null) return 0;

        return nodo.valor +
                sumaLCI(nodo.izquierdo) +
                sumaLCI(nodo.derecho);
    }

    private double promedioLCIM(NodoArbol nodo) {
        int total = contarNodos(nodo);

        if (total == 0) return 0;

        return (double) sumaLCI(nodo) / total;
    }

    private void recolectarPadres(NodoArbol nodo, List<Integer> lista) {
        if (nodo == null) return;

        if (nodo.izquierdo != null || nodo.derecho != null) {
            lista.add(nodo.valor);
        }

        recolectarPadres(nodo.izquierdo, lista);
        recolectarPadres(nodo.derecho, lista);
    }

    private void recolectarHojas(NodoArbol nodo, List<Integer> lista) {
        if (nodo == null) return;

        if (nodo.izquierdo == null && nodo.derecho == null) {
            lista.add(nodo.valor);
        }

        recolectarHojas(nodo.izquierdo, lista);
        recolectarHojas(nodo.derecho, lista);
    }

    // =====================================================
    // UI
    // =====================================================

    private void actualizarStats() {
        if (raiz == null) {
            ui.setStats(0, "—", "—", "—", "—");
            return;
        }

        int depth = altura(raiz);
        int lci = sumaLCI(raiz);
        double lcim = promedioLCIM(raiz);

        List<Integer> padres = new ArrayList<>();
        List<Integer> hojas = new ArrayList<>();

        recolectarPadres(raiz, padres);
        recolectarHojas(raiz, hojas);

        ui.setStats(
                depth,
                String.valueOf(lci),
                String.format("%.2f", lcim),
                padres.toString(),
                hojas.toString()
        );
    }

    private void actualizarInspector(NodoArbol nodo) {

        int grado =
                (nodo.izquierdo != null ? 1 : 0) +
                (nodo.derecho != null ? 1 : 0);

        int lvl = nivel(raiz, nodo, 0);

        List<Integer> hijos = new ArrayList<>();

        if (nodo.izquierdo != null) hijos.add(nodo.izquierdo.valor);
        if (nodo.derecho != null) hijos.add(nodo.derecho.valor);

        String textoHijos =
                hijos.isEmpty() ? "none" : hijos.toString();

        String textoNivel =
                lvl == 0 ? "0 (Root)" : String.valueOf(lvl);

        ui.setInspector(
                String.valueOf(nodo.valor),
                String.valueOf(grado),
                textoNivel,
                textoHijos
        );
    }

    // =====================================================
    // DIBUJAR ÁRBOL
    // =====================================================

    public void dibujarArbol() {

        ui.panelArbol.getChildren().clear();

        if (raiz == null) {
            ui.panelArbol.getChildren().add(ui.lblVacio);
            return;
        }

        ui.lblVacio.setVisible(false);

        double ancho = ui.panelArbol.getWidth();

        if (ancho < 100) ancho = 900;

        dibujarNodo(
                raiz,
                ancho / 2,
                70,
                ancho / 4,
                100,
                true
        );
    }

    private void dibujarNodo(
            NodoArbol nodo,
            double x,
            double y,
            double offset,
            double gapY,
            boolean root
    ) {

        if (nodo == null) return;

        boolean hoja =
                nodo.izquierdo == null &&
                nodo.derecho == null;

        double size = 58;

        if (nodo.izquierdo != null) {
            ui.panelArbol.getChildren().add(
                    linea(
                            x,
                            y + size / 2,
                            x - offset,
                            y + gapY - size / 2
                    )
            );

            dibujarNodo(
                    nodo.izquierdo,
                    x - offset,
                    y + gapY,
                    offset / 2,
                    gapY,
                    false
            );
        }

        if (nodo.derecho != null) {
            ui.panelArbol.getChildren().add(
                    linea(
                            x,
                            y + size / 2,
                            x + offset,
                            y + gapY - size / 2
                    )
            );

            dibujarNodo(
                    nodo.derecho,
                    x + offset,
                    y + gapY,
                    offset / 2,
                    gapY,
                    false
            );
        }

        if (root) {
            Label tag = new Label("ROOT");
            tag.getStyleClass().add("node-tag-root");
            tag.setLayoutX(x - size / 2);
            tag.setLayoutY(y - 22);
            ui.panelArbol.getChildren().add(tag);
        }

        if (hoja && !root) {
            Label tag = new Label("LEAF");
            tag.getStyleClass().add("node-tag-leaf");
            tag.setLayoutX(x - size / 2);
            tag.setLayoutY(y + size + 4);
            ui.panelArbol.getChildren().add(tag);
        }

        StackPane pane = new StackPane();

        pane.setPrefSize(size, size);
        pane.setLayoutX(x - size / 2);
        pane.setLayoutY(y - size / 2);

        if (root) {
            pane.getStyleClass().add("node-root");
        } else if (hoja) {
            pane.getStyleClass().add("node-leaf");
        } else {
            pane.getStyleClass().add("node-internal");
        }

        Text txt = new Text(String.valueOf(nodo.valor));
        txt.setFont(Font.font("Segoe UI", FontWeight.BOLD, root ? 18 : 16));
        txt.setFill(
                root
                        ? Color.WHITE
                        : hoja
                        ? Color.web("#475569")
                        : Color.web("#0f172a")
        );

        pane.getChildren().add(txt);

        NodoArbol ref = nodo;
        pane.setOnMouseClicked(e -> actualizarInspector(ref));
        pane.setStyle("-fx-cursor: hand;");

        ui.panelArbol.getChildren().add(pane);
    }

    private Line linea(double x1, double y1, double x2, double y2) {
        Line l = new Line(x1, y1, x2, y2);
        l.setStroke(Color.web("#cbd5e1"));
        l.setStrokeWidth(1.8);
        return l;
    }
}