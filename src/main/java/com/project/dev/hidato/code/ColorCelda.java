/*
 * @fileoverview {FileName} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {FileName} fue realizada el 31/07/2022.
 * @Dev - La primera version de {FileName} fue escrita por Dyson A. Parra T.
 */
package com.project.dev.hidato.code;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * TODO: Definición de {@code ColorCelda}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
public class ColorCelda extends DefaultTableCellRenderer {

    /**
     * FIXME: Definición de {@code getTableCellRendererComponent}. Declara un componente con el
     * CellRenderer de la tabla.
     */
    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row,
            int column) {
        // Crea un componente con el CellRenderer de la tabla.
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Evalúa si el tipo de dato de la celda es entero.
        if (value instanceof Integer) {
            int valor = (int) value;                                            // A valor le lleva lo que tiene la cada una de las celdas.

            // Evalúa si el valor en esa celda es 1 (principio del hidato) ó es cantidad de filas*cantidad de columnas del JTable (fin del hidato).
            if (valor == 1 || valor == table.getRowCount() * table.getColumnCount()) {
                // Colorea el fondo de la celda, y la letra.
                cell.setBackground(Color.red);
                cell.setForeground(Color.yellow);
            } // Evalúa si el valor en esa celda es difente al principio ó el fin del hidato, y y no es cero (es cualquier otra pista).
            else if (valor > 0) {
                // Colorea el fondo de la celda, y la letra.
                cell.setBackground(Color.CYAN);
                cell.setForeground(Color.black);
            }
        }
        // Evalúa si la celda se puede editar (No es ninguna pista).
        if (table.isCellEditable(row, column) == true) {
            // Colorea el fondo de la celda, y la letra.
            cell.setBackground(Color.white);
            cell.setForeground(Color.black);
        }

        // Devuelve la celda.
        return cell;
    }
}
