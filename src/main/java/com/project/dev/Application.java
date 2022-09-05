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
package com.project.dev;

import com.project.dev.hidato.frames.HelpFrame;
import com.project.dev.hidato.frames.MainFrame;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * TODO: Definición de {@code Application}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
public class Application {

    public static String defaultLocation
            = System.getProperty("user.home")
            + "/Desktop/hidato-output/Hidato";                                  // Ruta por defecto que aparece al exportar imagen,texto, o al cargar un hidato.
    public static String ruta = null;                                           // Ruta donde se intenta guardar el txt,la imagen o se intentará cargar el hidato, para hacer validaciones.
    public static JFrame Main_Frame;                                            // Declara una instancia global de MainFrame.
    public static JScrollPane jScrollPane1;                                     // Crea un JSCrollPane global para agregarlo a MainFrame más adelante (Se crea global para que pueda ser editado por newFrame).
    public static JTable Table;                                                 // Crea un JTable global para agregarlo a MainFrame más adelante (Se crea global para que pueda ser editado por newFrame).
    public static JFrame helpFrame;                                             // Declara una instancia global de HelpFrame.
    public static JScrollPane jScrollPaneHelp;                                  // Crea un JSCrollPane global para agregarlo a HelpFrame y mostrar en él las tablas del menú ayuda.
    public static JTable TableHelp;                                             // Crea un JTable global para agregarlo a HelpFrame con las matrices del menú ayuda.
    public static int[][] matrix_Complete;                                      // matrix_Complete almacenará cada uno de los hidatos creados (la solución), y la solución de cada hidato cargado.
    public static int[][] matrix_With_Level;                                    // Hidato con algunas casillas borradas dependiendo de la dificultad, o un Hidato cargado.
    public static int[][] matrix_Help;                                          // Matrices del menú ayuda.
    public static int cantRows = 0;                                             // Cantidad de filas de cada uno de los Hidatos.
    public static int cantCols = 0;                                             // Cantidad de columnas de cada uno de los Hidatos.
    public static int level = 0;                                                // Dificultad de cada uno de los Hidatos.
    public static int cantPistas = 0;                                           // Cantidad de casillas no vacías (pistas) de cada uno de los Hidatos.
    public static int loadOrNew = 0;                                            // Si el hidato en pantalla fué creado ó cargado, y dependiendo de eso decidir que hacer a la hora de dar en mostrar solución.
    public static int[] firstPos = new int[2];                                  // Fila y columna donde se encuentra el inicio (valor 1) de un hidato cargado.
    public static int[][] tracks;                                               // Posición (filay columna) en la cual se encuentrá cada una de las pistas de un hidato cargado desde txt.

    /*
     * Define si se mostrará en consola el proceso que se está realizando para resolver el hidato.
     */
    public static boolean debug = false;

    /**
     * Entrada principal del sistema.
     *
     * @param args argumentos de la linea de comandos.
     */
    public static void main(String[] args) {
        Main_Frame = new MainFrame();                                           // Inicializa instancia de MainFrame.
        Main_Frame.setVisible(true);                                            // Pone visible el MainFrame.
        jScrollPane1 = new JScrollPane();                                       // Inicializa jScrollPane1.
        Table = new JTable();                                                   // Inicializa Table.

        helpFrame = new HelpFrame();                                            // Inicializa instancia de HelpFrame.
        jScrollPaneHelp = new JScrollPane();                                    // Inicializa jScrollPaneHelp.
        TableHelp = new JTable();                                               // Inicializa TableHelp.
    }
}
