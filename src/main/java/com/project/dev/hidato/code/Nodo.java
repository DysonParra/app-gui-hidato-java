/*
 * @fileoverview    {Nodo}
 *
 * @version         2.0
 *
 * @author          Dyson Arley Parra Tilano <dysontilano@gmail.com>
 *
 * @copyright       Dyson Parra
 * @see             github.com/DysonParra
 *
 * History
 * @version 1.0     Implementation done.
 * @version 2.0     Documentation added.
 */
package com.project.dev.hidato.code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: Definición de {@code Nodo}.
 *
 * @author Dyson Parra
 * @since 11
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Nodo {

    private int fila;                                   // Fila.
    private int columna;                                // Columna.
    private int posibilidades;                          // Posibilidades.
    private Nodo siguiente;                             // Siguiente nodo.

}
