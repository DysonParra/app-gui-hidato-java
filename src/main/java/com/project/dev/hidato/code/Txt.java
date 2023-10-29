/*
 * @fileoverview    {Txt}
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

import com.project.dev.Application;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JOptionPane;

/**
 * TODO: Description of {@code Txt}.
 *
 * @author Dyson Parra
 * @since 11
 */
public class Txt {

    private long tiempoInicio = System.currentTimeMillis();                     // calcula el tiempo de inicio para generar el txt.

    /**
     * FIXME: Description of {@code saveHidatoToTxt}. Guarda una matrix ingresada en un archivo de
     * texto en una ruta específicada.
     *
     * @param ruta   Indica donde se intentará guardar el .txt
     * @param matrix Contiene el Application que se almacenará en el .txt
     * @param slash  esun booleano que indica si la ruta ingresada tiene algún slash.
     */
    public void saveHidatoToTxt(String ruta, int[][] matrix, boolean slash) {
        Validator val = new Validator();                                        // Crea una instancia de validator.
        try {
            // Llama a validarTxt pa veríficar si se pudo abrir el txt.
            // Se pone como parámetro "read" para que solo verífique si el Application existe, y que no intente cargarlo.
            val.validarTxt(ruta, "read");
            JOptionPane.showMessageDialog(null, "No se pudo guardar el archivo txt\nporque el archivo ya existe\nIntente con otro nombre");        // Indica que no fue posible guardar el ".txt".
        } // Si no se pudo abrir el txt (la ruta no es váĺida, o el hidato no existe en esa ruta).
        catch (Exception ex) {
            Tablero tablero = new Tablero();                                        // Crea instancia de Tablero.
            int k = tablero.calcularPosNoVacias(matrix);                            // A k le asigna la cantidad de posiciones no vacías mostaradas en el Application de Main_Frame.
            try {
                File archivo = new File(ruta);                                      // Se crea un objeto File que se encarga de crear o abrir acceso a un archivo.
                FileWriter hidato = new FileWriter(archivo, true);                  // Crear objeto FileWriter que ayuda a escribir sobre el archivo.
                // "\r\n" Indica un salto de línea en el .txt.
                hidato.write(Application.cantRows + " " + Application.cantCols + " " + Application.level + " " + k + "\r\n");      // En la primera línea del .txt almacena la cantidad de filas, cantidad de columnas, nivel, y cantidad de pistas.
                for (int i = 0; i < matrix.length; i++) {                           // Recorre la matrix con el Application por filas.
                    for (int j = 0; j < matrix[0].length; j++) {                    // Recorre la matrix con el Application por columnas.
                        if (matrix[i][j] != 0) {                                      // Verifica si matrix[i][j] tiene una posición no vacía.
                            hidato.write(i + " " + j + " " + matrix[i][j] + "\r\n");// Escribe en el .txt la tripleta con la fila, la columna y el valor.
                        }
                    }
                }
                long totalTiempo = System.currentTimeMillis() - tiempoInicio;
                hidato.write("Total time generating the txt is " + totalTiempo / 1000.0 + " seconds");        // Calcula el tiempo trancurrido entre el tiempo de inicio y el final de la escritura del .txt.
                hidato.close();                                                     // Se guarda lo escrito.
                if (slash == true) {                                                 // Verifica si la ruta tiene algún slash.
                    JOptionPane.showMessageDialog(null, "Se ha exportado como texto en" + "\n" + ruta); // Si la ruta es válida indica que se guardo exitosamente.
                } else {                                                              // Si la ruta no tiene algún slash se guarda por defecto en la carpeta del proyecto.
                    JOptionPane.showMessageDialog(null, "Se ha exportado como texto en la carpeta del proyecto" + "\n" + "con nombre " + ruta);    // Indica que se guardo en la carpet del proyecto.
                }

            }//Si existe un problema al escribir entra aquí//Si existe un problema al escribir entra aquí
            catch (Exception e) {                                                   // Si la ruta tiene algún slash, pero no es una ruta válida.
                JOptionPane.showMessageDialog(null, "Escriba una ruta válida");     // Indica que no fue posible guardar el ".txt".
            }
        }
    }
}
