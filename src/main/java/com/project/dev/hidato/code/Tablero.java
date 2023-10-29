/*
 * @fileoverview    {Tablero}
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
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import static javax.swing.JTable.AUTO_RESIZE_OFF;

/**
 * TODO: Description of {@code Tablero}.
 *
 * @author Dyson Parra
 * @since 11
 */
public class Tablero {

    /**
     * FIXME: Description of {@code newMatrix}. Crea una matrix con filas y columnas especificadas
     * como parámetro
     *
     * @param m es la cantidad de filas de la matrix.
     * @param n es la cantidad de columnas de la matrix.
     * @return la matrix de enteros de mxn vacía.
     */
    public int[][] newMatrix(int m, int n) {
        int[][] matrix = new int[m][n];                                         // crea matrix de enteros de mxm.
        return matrix;                                                          // Devuelve la matrix.

    }

    /**
     * FIXME: Description of {@code printMatrix}. Muestra una matrix parámetro por consola (Solo
     * usado para pruebas)
     *
     * @param matrix es la matrix que se mostrará.
     */
    public void printMatrix(int[][] matrix) {
        String print = "";                                                      // Crea un String llamado print.
        for (int i = 0; i < matrix.length; i++) {                                   // Recorre la matrix por filas.
            for (int j = 0; j < matrix[0].length; j++) {                          // recorre la matrix por columnas.
                print += "[";

                // Evalúa valor de i.
                if (i < 10) {
                    print = print + "  " + i;
                } else if (i < 100) {
                    print = print + " " + i;
                } else {
                    print += i;
                }

                // Evalúa valor de j.
                if (j < 10) {
                    print = print + "   " + j;
                } else if (j < 100) {
                    print = print + "  " + j;
                } else {
                    print = print + " " + j;
                }

                // Evalúa valor de matrix[i][j]
                if (matrix[i][j] < 0) {
                    print = print + "  " + matrix[i][j];
                } else if (matrix[i][j] < 10) {
                    print = print + "   " + matrix[i][j];
                } else if (matrix[i][j] < 100) {
                    print = print + "  " + matrix[i][j];
                } else {
                    print = print + " " + matrix[i][j];
                }

                print += "]  ";
            }
            System.out.println(print);                                          // Muestra cada fila con sus respectivas tripletas.
            print = "";                                                         // reinicia print.
        }
        System.out.printf("\n");
    }

    /**
     * FIXME: Description of {@code printMatrixV2}. Muestra una matrix parámetro por consola (Solo
     * usado para pruebas)
     *
     * @param matrix es la matrix que se mostrará.
     */
    public void printMatrixV2(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {                                   // Recorre la matrix por filas.
            int f = i + 1;
            System.out.println("[" + f + " " + matrix[i][0] + " " + matrix[i][1] + "]"); // Muestra cada fila con sus respectivas tripletas.
        }
    }

    /**
     * FIXME: Description of {@code matrixToJTable}. Lleva un matrix parámetro a un JTable, y el
     * JTable a un JScrollPane. Pone las posiciones de la matrix con valor diferente a cero en el
     * Jtable como no editable (pistas). Pone las posiciones de la matrix con valor = ó valor = size
     * de la matrix con fondo rojo. Pone las demás pistas con fondo color Cian. Deja la casillas del
     * JTable con valor = null con fondo blanco.
     *
     * @param matrix       es la matrix que se almacenará en un JTable.
     * @param jScrollPane1 es donde se almacenará el JTable.
     * @param Table        JTable en que se almacenará la matrix
     * @return el JSCrollPane con el JTable almacenado.
     */
    public JScrollPane matrixToJTable(int[][] matrix, JScrollPane jScrollPane1, JTable Table) {
        int m = matrix.length;                                                  // m es la cantidad de filas de la matrix.
        int n = matrix[0].length;                                               // n es la cantidad de columnas de la matrix.
        boolean[][] editable = new boolean[m][n];                               // Matrix que indica si es posible editar la celda.
        String[] id = new String[n];                                            // Matrix titulos de las columnas.
        String[][] matrixStr = new String[m][n];                                // Matrix de Strings.

        // Llena la matrix que indica si la posición i,j es editable (No es una pista)
        for (int i = 0; i < m; i++) {                                               // Recorre la matrix por filas.
            for (int j = 0; j < n; j++) {                                          // Recorre la matrix por columnas.
                if (matrix[i][j] == 0) {                                         // Evalúa si la matrix parámetro en la posición i,j es un cero.
                    editable[i][j] = true;                                      // Si es un cero pone la posición como editable.
                }
            }
        }
        ////////////////////////////////////////////////////////////////////////////

        // Llena la matrix de titulos con "." //////////////////////////////////////
        for (int i = 0; i < n; i++) {                                               // Recorre la matrix.
            id[i] = ".";                                                        // Pone un punto en cada posición de la matrix.
        }
        ////////////////////////////////////////////////////////////////////////////

        // Asigna formato al JTable ////////////////////////////////////////////////
        jScrollPane1.setBounds(10, 10, 0, 0);                                      // A jScrollPane1 le asigna dimensiones de 0,0 y que deje una margen de 10 px en la ventana.
        //Table = new JTable();                                                 // Inicializa el JTable en caso de que no lo esté.
        Table.setModel(new javax.swing.table.DefaultTableModel(m, n));          // A Table le asigna la cantidad de filas y de columnas de la matrix parámetro.
        Table.setModel(new javax.swing.table.DefaultTableModel(matrixStr, id) {  // Inicia Table con "" en todas sus celdas, y con "." en cada uno de los titulos de las columnas.
            Class[] types = new Class[]{java.lang.Integer.class};               // Crea un vector de clases con una única posición (Integer.class).

            @Override
            public Class getColumnClass(int columnIndex) {                      // Asigna el tipo de Datos que permite cada celda.
                return types[0];                                                // A cada celda le asigna como tipo de dato permitido types[0], es decir Integer.
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {      // Asignar a cada celda si es o nó editable.
                return editable[rowIndex][columnIndex];                         // A cada celda le asigna el valor almacenado en el vector editable[i][j],
                // si la matrix parámetro en dicha posición tiene un valor diferente a cero, se pone la celda como no editable.
            }
        });
        ////////////////////////////////////////////////////////////////////////////

        // Escribe lo valores de la matrix parámeto en el JTable --------------------
        for (int i = 0; i < m; i++) {                                           // Recorre matrix parámetro por filas.
            for (int j = 0; j < n; j++) {                                       // Recorre matrix parámetro por columnas.
                if (matrix[i][j] == 0) {                                         // Si la matrix parámetro en i,j tiene un cero.
                    Table.setValueAt("", i, j);                                 // Se inicializa como null. Sin este if las posiciones del hidato vacías iniciarían en cero.
                } else {                                                          // Si no es cero (es una pista) se escrbe el mismo valor de la matrix en el JTable.
                    Table.setValueAt(matrix[i][j], i, j);                       // Almacena el valor de la matrix en la osición del JTable.

                }
            }
        }
        ////////////////////////////////////////////////////////////////////////////

        Table.setColumnSelectionAllowed(true);                                  // Indica que al dar click en una celda solo se seleccone esta y no toda la fila.
        Table.setRowHeight(52);                                                 // Asigna a todas las filas un tamaño de 52px.
        jScrollPane1.setBounds(10, 10, Table.getColumnCount() * 52 + 3, Table.getRowCount() * 52 + 22); // Hace que el JscrollPane tenga las mismas dimensiones del JTale más unos cuantos pixeles de margen.
        jScrollPane1.setViewportView(Table);                                    // Agrega el JTable al jScrollPane.

        // Asigna dimensiones de 52px a cada columna.
        for (int i = 0; i < n; i++) {
            Table.getColumnModel().getColumn(i).setMinWidth(52);                // Pone 52px como ancho mínimo.
            Table.getColumnModel().getColumn(i).setPreferredWidth(52);          // Pone 52px como ancho preferido.
            Table.getColumnModel().getColumn(i).setMaxWidth(52);                // Pone 52px como ancho maximo.
        }

        Table.setDefaultRenderer(Integer.class, new ColorCelda());              // Colorea cada celda según como está especifícado en ColorCelda.
        return jScrollPane1;                                                    // Devuelve el jScrollPane parámetro con el JTable y todas sus modificaciones dentro de este.
    }

    /**
     * FIXME: Description of {@code duplicarMatrix}. Devuelve una copia de una matrix parámetro
     *
     * @param matrix es la matrix que se coipiará.
     * @return una nueva matrix exactamente igual a la ingresada por parámetro.
     */
    public int[][] duplicarMatrix(int[][] matrix) {
        int[][] matrixAux = new int[matrix.length][matrix[0].length];           // Crea una matrixAux con las mismas filas y columnas de la matrix parámetro.
        for (int i = 0; i < matrix.length; i++) {                                    // recorre matrix parámetro or filas.
            System.arraycopy(matrix[i], 0, matrixAux[i], 0, matrix[0].length); // recore matrix parámetro por columnas.
            // A matrixAux le asigana e mismo valor de matrix parámetro en i,j.
        }
        return matrixAux;                                                       // Devuelve matrixAux.
    }

    /**
     * FIXME: Description of {@code calcularPosNoVacias}. Recibe una matrix parámetro, y mira cuantas
     * de sus posiciones tienen el valor cero. Se usa para que antes de escribir el .txt se evalúe
     * cuantas posiciones del Hidato en pantalla no tienen valores. El valor de retorno es >= a la
     * cantidad de pistas del hidato.
     *
     * @param matrix es la matrix a recorrer.
     * @return un entero con la cantidad de posiciones con valor cero.
     */
    public int calcularPosNoVacias(int[][] matrix) {
        int k = 0;
        for (int[] matrix1 : matrix)
            for (int j : matrix1)
                if (j != 0)
                    k++;
        return k;
    }

    /**
     * FIXME: Description of {@code asignarLevel}. Recibe una matrix y un entero entre 1 y 3, y pone
     * n-posiciones de la matrix en cero dependiendo del entero.
     *
     * @param matrix la matrix que se le pondrán algunas posiciones en cero.
     * @param level  el entero que indica cuantas casillas se pondrán en cero.
     * @return la matrix parámetro con n-posiciones en cero.
     */
    public int[][] asignarLevel(int[][] matrix, int level) {
        Application.cantPistas = matrix.length * matrix[0].length;                     // Pone que la cantidad de pista del Application es igual a su size, ya que el Application entra lleno.
        double i = 0;                                                           // crea un double que indicará cuantas casillas se pondrán en cero.

        switch (level) {                                                         // Evalúa el nivel ingreado.
            case (1):
                i = 0.2;                                                        // Si es nivel 1 se pone i = 20%.
                break;
            case (2):
                i = 0.4;                                                        // Si es nivel 2 se pone i = 40%.
                break;
            case (3):
                i = 0.5;                                                        // Si es nivel 3 se pone i = 50%.
                break;
        }

        // Asigna a j la cantidad de casillas a poner en cero dependiendo de i y el nivel.
        // j probablemente no dará un número entero, entonces se redondea usando el método científico.
        int j = (int) (i * matrix.length * matrix[0].length * 10);                     // A j se le lleva la cantidad de casillas a borrar*10 para conservar un decimal.
        int jv = ((int) (i * matrix.length * matrix[0].length) * 10);                  // A jv se le lleva la parte entera de la cantidad de casillas*10 para no conservar ningún decimal.
        if (j - jv > 4) {                                                          // Evalúa si la resta entre las casillas con decimal menos las casillas sin decimal es mayor a 4.
            j = (j + 10) / 10;                                                      // Se aumenta la cantidad de casillas a poner en cero en 1.
        } else {                                                                  // Si es menor o igual a 4.
            j /= 10;                                                           // Se dejan las casillas sin decimales.
        }

        int k = 0;                                                              // Crea un k iniciado en cero indicando que aún no de han puesto casillas en cero.

        while (k != j) {                                                           // Mientras no haya puesto en cero la cantidad de casilas que dice j.
            int Aleat1 = (int) (Math.random() * matrix.length + 1) - 1;               // Cree un número aleatorio para las filas.
            int Aleat2 = (int) (Math.random() * matrix[0].length + 1) - 1;            // Cree un número aleatorio para las columnas.
            // Evalúa el los números generados.
            // Si los números corresponden a la primera casilla del hidato, la última, ó esa casilla ya está en cero no entra al if.
            if (matrix[Aleat1][Aleat2] != matrix.length * matrix[0].length && matrix[Aleat1][Aleat2] != 1 && matrix[Aleat1][Aleat2] != 0) {
                matrix[Aleat1][Aleat2] = 0;                                      // Pone la posición en cero.
                k++;                                                             // Aumenta la cantidad de casillas con cero.
                Application.cantPistas--;                                             // Disminuye el número de pistas.
            }
        }
        return matrix;                                                          // Devuelve la matrix con n-casillas en cero.
    }

    /**
     * FIXME: Description of {@code jTableToMatrix}. Recibe un JTable y lo almacena en una matrix
     *
     * @param Table es el JTable a almacenar
     * @return matrix con el JTable.
     */
    public int[][] jTableToMatrix(JTable Table) {
        int m = Application.cantRows;                                                // A m le llva la cantidad de filas del hidato.
        int n = Application.cantCols;                                                // A n le lleva la cantidad de columnas dl hidato.
        int[][] matrix = new int[m][n];                                         // Crea una matrix de enteros con cantidad de filas y columnas del hidato.
        String valueStr;                                                        // Crea un String para almacenar el valor del JTable en cada casilla.
        int valueInt;                                                           // Crea un int para almacenar el valor del JTable en cada casilla.
        for (int i = 0; i < m; i++) {                                              // recorre la matrix por filas.
            for (int j = 0; j < n; j++) {                                            // recorre la matrix por columnas.
                valueStr = Table.getValueAt(i, j).toString();                   // A valueStr le lleva lo que tiene el JTable en i,j.
                if ("".equals(valueStr)) {                                       // Evalúa si en la casilla se tiene el valor "", El if es necesario puesto que una matrix de enteros no reconoce el valor null.
                    matrix[i][j] = 0;                                           // En dicho caso se pone 0 en la matrix.
                } else {                                                          // Si hay algún valor en la casilla.
                    valueInt = (int) Table.getValueAt(i, j);                     // A valueInt le lleva el valor de la casilla en entero.
                    matrix[i][j] = valueInt;                                   // En la matrix almacena el valor del JTable en i,j en entero.
                }
            }
        }
        if (Application.debug) {
            System.out.printf("Tracks matrix is\n:");
            printMatrix(Application.tracks);
        }
        return matrix;                                                          // Devuelve la matrix con el Jtable.
    }

    /**
     * FIXME: Description of {@code validarPos}. Verifica que dado una matrix que representa un
     * Application, un i, y un j, la posición[i][j] no se salga de la matrix
     *
     * @param matrix es la matrix que se evaluará una posición.
     * @param i      es la fila que se evaluará.
     * @param j      es la columna que se evaluará.
     * @return true o false dependiendo de si se sale o no del tablero.
     */
    public boolean validarPos(int[][] matrix, int i, int j) {
        boolean valida = true;                                                  // Declara un boolean como true.
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) {         // Válida que la posición i,j no se slga del tablero por ninguno de los cuatro lados.
            valida = false;                                                     // Si la posición está por fuera del tablero, asigna false al retorno.
        }
        return valida;                                                          // Devuelve si es o no valida la posición.
    }

    /**
     * FIXME: Description of {@code calcularCantPosAdy}. Calcula a cuantas posiciones posibles
     * adyacentes a una posición i,j dada puede desplazarse. Cuantifica las posibilidades que tiene
     * para ir desde una casilla dada mientras llena el hidato.
     *
     * Se modificó para la práctica 2 agregandole una segunda matrix. Esta segunda matrix vendría
     * siendo la matrix con un hidato cargado.
     *
     * Pero esto deja de funcionar a la hora de resolver un hidato puesto que también es necesario
     * válidar si esa casilla no posee una pista en el hidato cargado para definir si es posible
     * desplazarse a esta.
     *
     * @param matrix es la matrix a la que se le evaluará su posición.
     * @param i      es la fila de la casilla a evaluar.
     * @param j      es la columna de la casilla a evaluar.
     * @param hidato Es la matrix con un hidato cargado (para resolver hidatos), ó una exactamente
     *               igual que matrix (para crear hidatos).
     * @return un entero con la cantidad de casillas a las cuales se puede desplazar desde la
     *         actual.
     */
    public int calcularCantPosAdy(int[][] matrix, int i, int j, int hidato[][]) {
        // Entero que tendrá la cantidad de posibles casillas a las cuales se puede desplazar.
        int cantPosAdy = 0;

        // Evalúa si la posición ubicada en la diagonal superior izquierda no se sale de la matrix.
        if (validarPos(matrix, i - 1, j - 1)) {
            // Evalúa si la casilla no ha sido visitada ó no tiene una pista en el hidato cargado.
            if (matrix[i - 1][j - 1] == 0 && hidato[i - 1][j - 1] == 0) {   // "diagUpIzq"
                // Aumenta la cantidad de casillas posibles.
                cantPosAdy++;
            }
        }
        // Evalúa si la posición ubicada en la diagonal superior derecha no se sale de la matrix.
        if (validarPos(matrix, i - 1, j + 1)) {
            // Evalúa si la casilla no ha sido visitada ó no tiene una pista en el hidato cargado.
            if (matrix[i - 1][j + 1] == 0 && hidato[i - 1][j + 1] == 0) {   //"diagUpDer"
                // Aumenta la cantidad de casillas posibles.
                cantPosAdy++;
            }
        }

        // Evalúa si la posición ubicada en la diagonal inferior izquierda no se sale de la matrix.
        if (validarPos(matrix, i + 1, j - 1)) {
            // Evalúa si la casilla no ha sido visitada ó no tiene una pista en el hidato cargado.
            if (matrix[i + 1][j - 1] == 0 && hidato[i + 1][j - 1] == 0) {   //"diagDownIzq"
                // Aumenta la cantidad de casillas posibles.
                cantPosAdy++;
            }
        }

        // Evalúa si la posición ubicada en la diagonal inferior derecha no se sale de la matrix.
        if (validarPos(matrix, i + 1, j + 1)) {
            // Evalúa si la casilla no ha sido visitada ó no tiene una pista en el hidato cargado.
            if (matrix[i + 1][j + 1] == 0 && hidato[i + 1][j + 1] == 0) {   //"diagDownDer"
                // Aumenta la cantidad de casillas posibles.
                cantPosAdy++;
            }
        }

        // Evalúa si la posición ubicada en la izquierda no se sale de la matrix.
        if (validarPos(matrix, i, j - 1)) {
            // Evalúa si la casilla no ha sido visitada ó no tiene una pista en el hidato cargado.
            if (matrix[i][j - 1] == 0 && hidato[i][j - 1] == 0) {   //"izq"
                // Aumenta la cantidad de casillas posibles.
                cantPosAdy++;
            }
        }

        // Evalúa si la posición ubicada en la derecha no se sale de la matrix.
        if (validarPos(matrix, i, j + 1)) {
            // Evalúa si la casilla no ha sido visitada ó no tiene una pista en el hidato cargado.
            if (matrix[i][j + 1] == 0 && hidato[i][j + 1] == 0) {   //"der"
                // Aumenta la cantidad de casillas posibles.
                cantPosAdy++;
            }
        }

        // Evalúa si la posición ubicada encima no se sale de la matrix.
        if (validarPos(matrix, i - 1, j)) {
            // Evalúa si la casilla no ha sido visitada ó no tiene una pista en el hidato cargado.
            if (matrix[i - 1][j] == 0 && hidato[i - 1][j] == 0) {   //"up"
                // Aumenta la cantidad de casillas posibles.
                cantPosAdy++;
            }
        }

        // Evalúa si la posición ubicada debajo no se sale de la matrix.
        if (validarPos(matrix, i + 1, j)) {
            // Evalúa si la casilla no ha sido visitada ó no tiene una pista en el hidato cargado.
            if (matrix[i + 1][j] == 0 && hidato[i + 1][j] == 0) {
                //"down"
                // Aumenta la cantidad de casillas posibles.
                cantPosAdy++;
            }
        }

        // Devuelve la cantidad de casillas posibles.
        return cantPosAdy;
    }

    /**
     * FIXME: Description of {@code calcularPosibPos}. Almacena en una lista ligada circular las
     * casillas a las cuales se pude dirigir desde la casilla parámetro. Se definió de tal modo que
     * la lista almacena primero las diagonales, y luego las laterales.
     *
     * @param matrix es la matrix a la que se le evaluará su posición.
     * @param vect   tiene en la posición 1 la casilla a la cual le desea calcular las casillas
     *               posibles a laa cuales dirigirse.
     * @param hidato es la matrix con un hidato cargado desde un txt.
     * @return una lista ligada con las casillas a las cuales se puede desplazar desde la casilla
     *         indicada por vect.
     */
    public ListaCircular calcularPosibPos(int[][] matrix, int[] vect, int hidato[][]) {
        int i = vect[0];                                                        // A i le asigna la fila actual.
        int j = vect[1];                                                        // A j le asigna la columna actual.
        ListaCircular lista = new ListaCircular();                              // Crea una lista circular.
        lista.agregarElemento(i - 1, j - 1, 0, false);                        // Añada a la lista circular la casilla como primer elemento.
        lista.agregarElemento(i - 1, j + 1, 0, false);                        // Añada a la lista circular la casilla como segundo elemento.
        lista.agregarElemento(i + 1, j - 1, 0, false);                        // Añada a la lista circular la casilla como tercer elemento.
        lista.agregarElemento(i + 1, j + 1, 0, false);                        // Añada a la lista circular la casilla como cuarto elemento.
        lista.agregarElemento(i, j - 1, 0, false);                        // Añada a la lista circular la casilla como quinto elemento.
        lista.agregarElemento(i, j + 1, 0, false);                        // Añada a la lista circular la casilla como sexto elemento.
        lista.agregarElemento(i - 1, j, 0, false);                        // Añada a la lista circular la casilla como séptimo elemento.
        lista.agregarElemento(i + 1, j, 0, false);                        // Añada a la lista circular la casilla como octavo elemento.
        Nodo nodoAux = lista.inicio;                                            // Crea un nodo que contine el principio de la lista.
        int fila, columna, posib;                                               // Crea enteros para asignarles los valores de cada nodo de la lista.
        for (int k = 0; k < 8; k++) {                                               // recorre la lista.
            fila = nodoAux.getFila();                                           // A fila le asigna el nodo actual en su campo fila.
            columna = nodoAux.getColumna();                                     // A columna le asigna el nodo actual en su campo columna.
            posib = calcularCantPosAdy(matrix, fila, columna, hidato);            // A posib le asigna el nodo actual en su campo posib.
            if (validarPos(matrix, fila, columna) && matrix[fila][columna] == 0) {  // Valida si la matrix en la posición indicada por el nodo no se sale del tablero, ni tiene un valor diferente a cero.
                nodoAux.setPosibilidades(posib);                                // Al nodo actual en el campo posibilidades le asigna las posibilidades de la casilla.
            } else {                                                              // Si no puede dirigirse a esa casilla.
                nodoAux.setPosibilidades(-1);                                   // Si cantidad de posibilades = -1, la casilla se sale del tablero ó tiene un valor distionto a cero.
            }
            nodoAux = nodoAux.getSiguiente();                                   // Avanza al siguiente nodo.
        }
        return lista;                                                           // Devuelve la lista con las casillas posibles y su cantidad de posibilidades.
    }

    /**
     * FIXME: Description of {@code calcularPosToGo}. Define a cual de todas las casillas posibles va
     * desde la casilla actual. Define que desde la casilla actual, irá a aquella que en caso de
     * desplazarse a ella, esta tiene menos casillas posibles a las cuales desplazarse.
     *
     * @param matrix matrix en la cual se decidirá a donde desplazarse.
     * @param vect   Contine en la pos 0 la fila actual, en la 1 la columna actual, y en la 2 el
     *               valor que se va a escribir en la casilla del hidato
     * @param inicio Define desde cual casilla empezará a evalúar las posibilidades, tomando como
     *               prioritarias las primeras que aparezcan en caso de haber casillas con igual
     *               cantidad de posibilidades, inicio puede tomar valores entre cero y siete, por
     *               tanto hay ocho formas de recorrer la lista, entonces ocho posibilidades de
     *               encontrar una solución a un hidato empezando desde una casilla arbitraria y
     *               teniendo presente que primero están almacenadas las diagonales y luego las
     *               laterales
     * @param hidato Es la matrix con un hidato cargado desde un txt.
     * @return vector de tres posiciones, en las cuales: pos 0 = fila de la casilla la cual se
     *         desplazará. pos 1= columna de la casilla la cual se desplazará, pos 2 = cantidad de
     *         posibles posiciones para desplazarse desde la casilla a la cual se desplazará.
     */
    public int[] calcularPosToGo(int[][] matrix, int[] vect, int inicio, int[][] hidato) {
        ListaCircular lista = calcularPosibPos(matrix, vect, hidato);
        int cantPosib = 10;                                                     // Iniciliza la cantidad de posibilidades en 10.
        Nodo nodoAux = lista.inicio;                                            // Crea un nuevo nodo que empezará desde el inicio de la lista.
        int posibAux;                                                           // Entero que tendrá la cantidad de posibilidades de cada nodo.
        for (int i = 0; i < inicio; i++) {                                         // Marca el punto de inicio desde donde se recorrerá la lista, si inicio = 0, empieza desde el primer nodo hasta el octavo, inicio = 1, empieza del segundo al primero....
            nodoAux = nodoAux.getSiguiente();                                   // Asigna a nodoAux el siguiente nodo.
        }
        for (int k = 0; k < 8; k++) {                                              // Recorre la lista que contiene las posible casillas a las cuales desplazarse.
            posibAux = nodoAux.getPosibilidades();                              // A posibAux le lleva el nodo actual en su campo pisibilidades.
            if (posibAux < cantPosib && posibAux != -1) {                          // Evalúa si las posibilidades del nodo actual son menores al valor actual de cantPosib.
                vect[0] = nodoAux.getFila();                                    // A vect en la posición cero le lleva la fila de la casilla.
                vect[1] = nodoAux.getColumna();                                 // A vect en la posición 1 le lleva la columna de la casilla.
                cantPosib = nodoAux.getPosibilidades();                         // A cantPosib le lleva las posibilidades del nodo actual.
            }
            nodoAux = nodoAux.getSiguiente();                                   // Avanza en la lista.
        }

        if (cantPosib == 10) {                                                   // Solo pasa por aquí si recorriendo la lista iniciando desde el lugar parámetro no lo llevó a una solución.
            vect[3] = 1;                                                        // Indica que no halló solución recorriendo la lista desde ese nodo.
        }
        return vect;                                                            // devuelve el vector con la fila, columna, y el valor que se ecribira en la casilla.
    }

    /**
     * FIXME: Description of {@code markPos}. Pone el valor de la posición 2 de un vector en la fila
     * que indica el vector[0] y en la columna que indica eel vector[1] en una matrix parámetro.
     *
     * @param matrix es la matrix que se le cambiará el valor de una posición.
     * @param vect   es el vector con la información de la fila, columna y valor que se pondrá en la
     *               matrix.
     * @return matrix con el JTable.
     */
    public int[][] markPos(int[][] matrix, int vect[]) {
        matrix[vect[0]][vect[1]] = vect[2];                                     // A matrix le asigna el valor de vect[2] en la fila[vect[0] columna[vect[1].
        return matrix;                                                          // Devuelve la matrix con un valor editado.
    }

    /**
     * FIXME: Description of {@code fillMatrix}. Recibe una matrix, y en ella almacena un hidato.
     *
     * @param matrix es la matrix en la cual se almacenará un hidato.
     * @return matrix con un hidato.
     */
    public int[][] fillMatrix(int matrix[][]) {
        // genera dos números aleatorios para que el hidato comience desde la fila y columna indicadas por estos.
        int Aleat1 = (int) (Math.random() * matrix.length + 1) - 1;                   // Crea un número aleatorio que indica la fila desde la cual se empezará a llenar el hidato.
        int Aleat2 = (int) (Math.random() * matrix[0].length + 1) - 1;                // Crea un número aleatorio que indica la columna desde la cual se empezará a llenar el hidato.
        //int Aleat1 = 5;
        //int Aleat2 = 5;
        int index = 1;                                                          // Es el valor desde el cual iniciará el hidato.
        int vect[] = new int[4];                                                // Crea vector que tendrá la fila, columna, y valor actual a escribir del hidato.
        vect[0] = Aleat1;                                                       // A vect en la posición cero le asigna la fila aleatoria.
        vect[1] = Aleat2;                                                       // A vect en la posición 1 le asigna la columna aleatoria.
        vect[2] = index;                                                        // A vect en la posición 2 le asigna el valor inicial desde el cual se iniciará la primera casilla del hidato.
        int inicio;                                                             // Desde que nodo recorre las listas.
        inicio = (int) (Math.random() * 8 + 1) - 1;                             // A inicio le lleva un número aleatorio que indica desde que nodo deben recorrerse las listas.
        while (vect[2] != (matrix.length * matrix[0].length) + 1) {             // Mientras no haya recorrido todas las casillas del hidato.
            matrix = markPos(matrix, vect);                                     // Actualiza posición de la matrix.
            vect = calcularPosToGo(matrix, vect, inicio, matrix);               // Calcula la próxima posición a la cual se desplazará.
            vect[2] += 1;                                                       // Actualiza el valor que se escribirá en dicha casilla.

            if (vect[3] == 1 && vect[2] != (matrix.length * matrix[0].length) + 1) {// En caso de que no encuentre una solución recorriendo la lista desde esa posición.
                matrix = new int[matrix.length][matrix[0].length];
                index = 1;                                                      // Reinicia el índice.
                vect[0] = Aleat1;                                               // A vect en la posición cero le asigna la fila aleatoria.
                vect[1] = Aleat2;                                               // A vect en la posición 1 le asigna la columna aleatoria.
                vect[2] = index;                                                // A vect en la posición 2 le asigna el valor inicial desde el cual se iniciará la primera casilla del hidato.
                vect[3] = 0;                                                    // Reinicia la posición que indica si se halló solución.
                inicio = (int) (Math.random() * 8 + 1) - 1;                     // Asigna a inicio un nuevo valor para que comience a recorrer la lista denuevo, pero iniciando en este.
            }
        }
        if (Application.debug)
            System.out.printf("Hidato generated with inicio iqual to %d\n", inicio);
        return matrix;                                                          // Devuelve la matrix con un hidato.
    }

    /**
     * FIXME: Description of {@code resizeHidato}. Redimensiona la ventana Main_Frame, ajustandola al
     * tamaño de un hidato creado, cargado, reiniciado o solucionado antes de su invocación. También
     * se usa para mostrar en el menú ayuda el nivel de adyacencia.
     *
     * @param str Define si el Application fue creado, cargado, reiniciado ó solucionado y
     *            dependiendo de eso escribe el mensaje correspondiente.
     */
    public void resizeHidato(String str) {
        String message;                                                         // Mensaje a escribir.
        // Evalúa el valor del string parámetro.
        switch (str) {
            // Si el parámetro es igual a new, significa que el hidato fué creado.
            case "new":
                message = "Se ha creado el hidato";                             // A message le lleva un mensaje indicando que se creó el hidato.
                break;
            // Si el parámetro es load, significa que el hidato fué cargado.
            case "load":
                message = "Se ha cargado el hidato";                           // A message le lleva un mensaje indicando que se cargó el hidato.
                break;
            // Si el parámetro es restart, significa que el hidato fué reiniciado.
            case "restart":
                message = "Se ha reiniciado el hidato";                         // A message le lleva un mensaje indicando que se reinició el hidato.
                break;
            // Si no se ha creado, cargado ni reiniciado.
            default:
                message = "Se ha solucionado el hidato";                        // A message le lleva un mensaje indicando que se ha mostrado la solución del hidato.
                break;
        }

        // Agrega el jsCrollPane con el Application a Main_Frame.
        Application.Main_Frame.add(Application.jScrollPane1);

        // Evalúa el tamaño del jSCrollPane1.
        // Si su ancho es menor a 320px solo redimensiona el alto de Main_Frame.
        if ((Application.jScrollPane1.getWidth() + 20) < 320) {
            Application.Main_Frame.resize(320, Application.jScrollPane1.getHeight() + 80);
        } // Si el ancho es mayor a 320px.
        else {
            // Redimensiona Main_Frame con las dimensiones de jScrollPane1 más algunos píxeles de margen.
            Application.Main_Frame.resize(Application.jScrollPane1.getWidth() + 20, Application.jScrollPane1.getHeight() + 80);
        }

        // Hace que la tabla no se redimensione automáticamente.
        Application.Table.setAutoResizeMode(AUTO_RESIZE_OFF);

        // En caso de que el alto sea mayor ó igual a 720px y el ancho menor a 1280px, se redimensiona de tal modo que aparezca un jscrollbar vertical.
        if (Application.Main_Frame.getWidth() < 1280 && Application.Main_Frame.getHeight() >= 720) {
            // Redimensiona Main_Frame para que tenga un alto de 720px, y un ancho de unos 35px más, en los cuales quedará el jscrollbar vertical.
            Application.Main_Frame.resize(Application.jScrollPane1.getWidth() + 35, 720);

            // Se redimensiona el jscrollbar de tal modo que conserve un espacio entre este y el Main_Frame.
            Application.jScrollPane1.resize(Application.jScrollPane1.getWidth() + 15, 640);
        } // En caso de que el alto sea menor a 720px y el ancho mayor ó igual a 1280px, se redimensiona de tal modo que aparezca un jscrollbar Horizontal.
        else if (Application.Main_Frame.getWidth() >= 1280 && Application.Main_Frame.getHeight() < 720) {
            // Redimensiona Main_Frame para que tenga un alto de 1280px, y un ancho de unos 100px más, en los cuales quedará el jscrollbar vertical.
            Application.Main_Frame.resize(1280, Application.jScrollPane1.getHeight() + 100);

            // Se redimensiona el jscrollbar de tal modo que conserve un espacio entre este y el Main_Frame.
            Application.jScrollPane1.resize(1260, Application.jScrollPane1.getHeight() + 20);

        } // En caso de que el alto sea mayor ó igual a 720px y el ancho mayor ó igual a 1280px, se redimensiona de tal modo que aparezca un jscrollbar vertical y uno horizontal.
        else if (Application.Main_Frame.getWidth() >= 1280 && Application.Main_Frame.getHeight() >= 720) {
            // Redimensiona Main_Frame para que tenga un alto de 720px, y un ancho de 1280px.
            Application.Main_Frame.resize(1280, 720);

            // Se redimensiona el jscrollbar de tal modo que conserve un espacio entre este y el Main_Frame.
            Application.jScrollPane1.resize(1260, 640);
        }

        // Escribe que se ha creado, cargado el hidato, o mostrado la solución.
        JOptionPane.showMessageDialog(null, message);

        // Hace que Main_Frame no se pueda redimensionar.
        Application.Main_Frame.setResizable(false);
    }

    /**
     * FIXME: Description of {@code txtToMatrix}. Recibe un buffered de tipo txt con un hidato,
     * analiza el txt y crea una matrix con los datos del txt.
     *
     * @param txt es el Buffereded de tipo txt que se analizará.
     * @throws java.io.FileNotFoundException
     */
    public void txtToMatrix(BufferedReader txt) throws FileNotFoundException, IOException {
        String line;                                                            // Crea un String que contendrá cada uno de los renglones del txt.
        line = txt.readLine();                                                  // A line le asigna la primera línea del hidato en txt.
        int row = -1, col = -1, value = -1, pistas = -1;                        // Filas, columnas y valores que hay en cada una de la líneas del hidato. (pistas solo en la primera).
        String aux = "";                                                        // Crea un string que tendrá cada uno de los valores en cada línea del hidato.
        char charActChar;                                                       // Crea un char que tendrá cada uno de los caracteres en cada línea (incluyendo los espacios), en char.
        String charActStr;                                                      // Crea un String que tendrá cada uno de los caracteres en cada línea (incluyendo los espacios), en String.

        // Recorre la primera línea del txt.
        for (int i = 0; i < line.length(); i++) {
            charActChar = line.charAt(i);                                       // A charActChar le asigna el caracter en la línea uno en la posición i.
            charActStr = String.valueOf(charActChar);                           // A charActStr le asigna el caracter en la línea uno en la posición i.
            if (" ".equals(charActStr) || i == line.length() - 1) {             // Evalúa si está ubicado en el último caracter de la línea, o el caracter actual es un espacio.
                if (row == -1) {                                                // Evalúa si ya ha asignado valor a row.
                    row = Integer.parseInt(aux);                                // Si no se le ha asignado valor le lleva el valor de aux.
                } else if (col == -1) {                                         // Evalúa si ya ha asignado valor a col.
                    col = Integer.parseInt(aux);                                // Si no se le ha asignado valor le lleva el valor de aux.
                } else if (value == -1) {                                       // Evalúa si ya ha asignado valor a value.
                    value = Integer.parseInt(aux);                              // Si no se le ha asignado valor le lleva el valor de aux.
                } else {                                                        // Si ya asigno valor a row, col y value solo faltan las pistas.
                    aux = aux.concat(charActStr);                               // Llega aquí si se encuentra en el último caracter, entonces lo concatena con el valor de aux.
                    pistas = Integer.parseInt(aux);                             // Lleva el valor de aux a pistas.
                }
                aux = "";                                                       // reinicia aux cuanpone valor arow, col, value ó pistas.
            } else {                                                            // Si el caracter actual no es un espacio ni es el último.
                aux = aux.concat(charActStr);                                   // A aux le lleva lo que tiene concatenado con el caracter actual.
            }
        }

        // Llegado acá ya se tienen las filas, columnas, nivel, y cantidad de pistas del hidato.
        // Ahora se le asignan a los atributos globales declaradas en Application.
        Application.cantRows = row;
        Application.cantCols = col;
        Application.level = value;
        Application.cantPistas = pistas;

        // A matrix_Compete le asigna una matrix vacía con cantidad de filas y de columnas indicadas en la primera línea del txt.
        Application.matrix_With_Level = newMatrix(Application.cantRows, Application.cantCols);
        Application.matrix_Complete = newMatrix(Application.cantRows, Application.cantCols);

        // Recorre las líneas desde la segunda hasta la la cantidad de pistas indicadas en la línea uno +1.
        // (Si el hidato es un hidato válido recorre hasta la penúltima línea).
        for (int j = 0; j < Application.cantPistas; j++) {
            // Reinicia los valores de fila, columna y valor cada vez que pase a una nueva línea.
            row = -1;                                                           // A row le asigna -1.
            col = -1;                                                           // A col le asigna -1.
            value = -1;                                                         // A value le asigna -1.
            line = txt.readLine();                                              // Pasa a una nueva línea.

            for (int i = 0; i < line.length(); i++) {
                charActChar = line.charAt(i);                                   // A charActChar le asigna el caracter de la línea actual en la posición i.
                charActStr = String.valueOf(charActChar);                       // A charActChar le asigna charActChar convertido a String.
                if (" ".equals(charActStr) || i == line.length() - 1) {            // Evalua si está en el último caracter o el caracter actual es un espacio.
                    if (row == -1) {                                             // Pasa si no le ha asignado valor a row.
                        row = Integer.parseInt(aux);                            // A row le lleva el valor de aux.
                    } else if (col == -1) {                                        // Pasa si ya le asigno valor a row, pero no a col.
                        col = Integer.parseInt(aux);                            // A col le lleva el valor de aux.
                    } else {                                                      // Pasa si row y col ya tienen valores y está en el último caracter de la línea.
                        aux = aux.concat(charActStr);                           // A aux le lleva lo que tiene concatenado con el último caracter.
                        value = Integer.parseInt(aux);                          // A value le lleva el valor de aux.
                        if (value == 1) {                                        // Evalúa si el valor encontrado es 1.
                            // A firstPos le lleva la fila y a columna donde se encuentra almacenado el inicio del hidato.
                            Application.firstPos[0] = row;                           // A Application.firstPos le lleva row.
                            Application.firstPos[1] = col;                           // A Application.firstPos le lleva col.
                        }
                    }
                    aux = "";                                                   // Reinicia aux cada vez que asine valor a row, col y value.
                } else {                                                          // Si el caracter actual no es un espacio, y no se encuentra en el último caracter de la línea.
                    aux = aux.concat(charActStr);                               // A aux le lleva lo que tiene concatenado con el caracer actual.
                }
            }
            // Al Application creado segun las filas y columnas de la línea uno le lleva el valor value en la fila row, y columna col.
            Application.matrix_With_Level[row][col] = value;
        }

        // A jsCrollPane1 le lleva matrix_With_level almacenada en un JTable y pintada, usando matrixToJTable.
        Application.jScrollPane1 = matrixToJTable(Application.matrix_With_Level, Application.jScrollPane1, Application.Table);

        Application.loadOrNew = 2;                                                   // Indica que el hidato fué cargado, para proceder con la opción 2 al dar mostrar solución.
        txt.close();                                                            // Cierra el txt abierto.
    }

    /**
     * FIXME: Description of {@code createTracksMatrix}. Se llama cuando se carga un hidato desde
     * txt. Analiza el hidato cargado y almacena en una matrix la fila y columna de cada pista.
     * Almacena en la matrix de retorno esto: Dada una fila n, almacena en la columna 0 en que fila
     * (en el hidato cargado) está la pista n, en la columna 1 en que columna (en el hidato cargado)
     * está la pista n. En la columna 2 cuanto falta para la próxima pista (por ejemplo pone 3 si la
     * pista actual es 2 y la próxima pista es 5). En la columna 3 la fila (en el hidato cargado)
     * donde está la próxima pista, y en la columna 4 la columna (en el hidato cargado) donde está
     * la próxima pista.
     *
     * @param matrix Es el hidato cargado de un txt.
     * @return la matrix con las posiciones de cada una de las pistas.
     */
    public int[][] createTracksMatrix(int[][] matrix) {
        // Crea una nueva matrix con cantidad de fila igual a la cantidad de posiciones del hidato más uno.
        int[][] matrixAux = new int[matrix.length * matrix[0].length + 1][5];

        // Llena matrixAux con -1 indicando que las pistas no están en el hidato.
        for (int i = 1; i < matrixAux.length; i++) {
            matrixAux[i][0] = -1;
            matrixAux[i][1] = -1;
            matrixAux[i][2] = -1;
            matrixAux[i][3] = -1;
            matrixAux[i][4] = -1;
        }
        // Recorre el hidato.
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                // Evalúa que el valor del hidato en la posición i,j no sea cero (sea una pista).
                if (matrix[i][j] != 0) {
                    // A matrixAux en la fila que diga la pista y columna 0 le lleva la fila de la pista.
                    // A matrixAux en la fila que diga la pista y columna 1 le lleva la columna de la pista.
                    matrixAux[matrix[i][j]][0] = i;
                    matrixAux[matrix[i][j]][1] = j;

                    // En la posición 0,0 de la matrix almacena la cantidad de casillas que son pistas.
                    matrixAux[0][0]++;
                }
            }
        }
        // En la posición 0,1 de la matrix almacena la cantidad de casillas que no son pistas.
        matrixAux[0][1] = matrixAux.length - matrixAux[0][0] - 1;

        // Crea un entero que almacenará las distancias entre pistas.
        int k = 0;
        // Recorre el vector retorno que ya tiene almacenado fila y columna de cada pista desde la penúltima fila hacia atrás.
        for (int i = matrixAux.length - 2; i > 0; i--) {
            // Evalúa si encontró una pista.
            if (matrixAux[i][0] != -1) {
                // Aumenta k en uno.
                k++;

                // Almacena en la fila de la pista y columna 2 de la matrix retorno la distancia entre la última pista que apareció (hacia adelante) y la actual.
                matrixAux[i][2] = k;

                // Almacena en la columna 3 y 4 de la fila indicada por la pista la fila y columna de la pista en la fila y columna de la prómia pista (hacia adelante).
                matrixAux[i][3] = matrixAux[i + k][0];
                matrixAux[i][4] = matrixAux[i + k][1];

                // Reinicia el contador de distancias.
                k = 0;
            } // En caso de que no halla encontrado una pista simplemente aumenta la distancia.
            else {
                k++;
                // Almacena en la fila de la pista y columna 2 de la matrix retorno la distancia entre la casilla actual y la última pista que apareció (hacia adelante) y la actual.
                matrixAux[i][2] = k;

                // Almacena en la columna 3 y 4 de la fila indicada por la pista la fila y columna de la pista en la fila y columna de la próxima pista (hacia adelante).
                matrixAux[i][3] = matrixAux[i + k][0];
                matrixAux[i][4] = matrixAux[i + k][1];
            }
        }

        // Devuelve la matrix con pista (fila), fila de la pista, distancia hasta la próxima pista, fila de la próxima pista y columna de la próxima pista.
        return matrixAux;
    }

    /**
     * FIXME: Description of {@code fastSolveHidato}. Se invoca cuando se tiene en
     * Hidato.matrix_With_Level un hidato cargado desde un txt. Busca la solución al Hidato de
     * Hidato.matrix_With_Level y la almacena en Hidato.matrix_Complete. Así a la hora de dar en el
     * Main_Frame en restart, se podrá volver al Hidato cargado ya que este está en
     * Hidato.matrix_With_Level. Y a la hora de dar en mostrar solución una segunda vez, no se
     * tendrá que recalcular ya que esta quedará almacenada en Hidato.matrix_Complete.
     *
     * fillMatrix genera los hidatos con una heurística, en la cual la cantidad de hidatos completos
     * posibles es igual a la cantidad de casillas del hidato multiplicado por ocho. Y dado uno de
     * esos hidatos completos, se le asigna nivel al hidato eliminando cierta cantidad de casillas
     * del hidato según el nivel especifícado.
     *
     * Por ejemplo un hidato de 5x5 tiene entonces 200 hidatos completos posibles, y asignar nivel 1
     * consiste en borrar el 20% de las casillas del hidato (en este caso 10). Entonces al final hay
     * 200x(cantidad de formas de escoger 5 casillas del hidato (sin importar el orden) y
     * borrarlas).
     *
     * Viendolo a grandes rasgos es demasiado poco probable que al generar dos veces un hidato de
     * 5x5 (uno pequeño) salga la misma posición inicial (entre 1 y 25), luego el mismo número entre
     * 0 y 7 (que define dada la posición inicial cual de los 8 hidatos se generará), y además que
     * escoja las mismas cinco casillas a borrar.
     *
     * Pero esta heurística define que un hidato se identifica por dos números. Primero la posición
     * inicial, y luego el número entre cero y siete que define cual de los ocho hidatos es. Y
     * asignar nivel al hidato solo consiste en borrar casillas, pero a la hora de resolverlos no
     * importa ni cuantas casillas se hallan borrado, el hidato completo (solución) viene dado solo
     * por posInicial y núm entre 0 y 7.
     *
     * Partiendo de estas consideracsiones entonces dado un txt (que obligatoriamente tiene la
     * posición inicial) sin importar sus dimensiones solo hay que comparar entre ocho hidatos
     * posibles. Por tanto cualquier hidato que halla sido generado con este programa, o que por
     * casualidades de la vida se halla hecho con otro programa y internamente sea uno de esos ocho
     * hidatos posibles dado su posición inicial será resuelto en demasiado poco tiempo (en general
     * menos de un segundo). Caso de que no este incluido entre ese conjunto de hidatos que resulve
     * este programa rápidamente, tocará entonces hacer una solución lenta.
     *
     * @param matrix Es la matrix en la que se almacenará la solución.
     * @return la matrix con la solución.
     */
    public int[][] fastSolveHidato(int matrix[][]) {
        boolean notSolution = false;                                            // Si fue posible hallar solución rápida.
        if (Application.debug)
            System.out.printf("Start position is %d %d\n", Application.firstPos[0], Application.firstPos[1]);
        int vect[] = new int[4];                                                // Crea vector que tendrá la fila, columna, y valor actual a escribir del hidato.
        vect[0] = Application.firstPos[0];                                      // A vect en la posición cero le asigna la fila aleatoria.
        vect[1] = Application.firstPos[1];                                      // A vect en la posición 1 le asigna la columna aleatoria.
        vect[2] = 1;                                                            // A vect en la posición 2 le asigna el valor inicial desde el cual se iniciará la primera casilla del hidato.
        int inicio;                                                             // Desde que nodo recorrerá las listas.
        inicio = 0;                                                             // A inicio le lleva 0, indicando que tratara de hallar la solución recorriendo las listas desde 0 según se especifíca en calcularPosToGo.
        if (Application.debug)
            System.out.println("With inico iqual to " + inicio);                // Muestra con cual valor de inicio intentará hallar una solución.
        while (vect[2] != (matrix.length * matrix[0].length) + 1) {             // Mientras no haya recorrido todas las casillas del hidato.
            matrix = markPos(matrix, vect);                                     // Actualiza posición de la matrix.
            vect = calcularPosToGo(matrix, vect, inicio, matrix);               // Calcula la próxima posición a la cual se desplazará.
            vect[2] += 1;                                                       // Actualiza el valor que se escribirá en dicha casilla.

            /*
             * Evalúa si mientras está llenando la matrix solución según el valor de inicio, se
             * encuentra que la casilla actual en el hidato cargado es una pista, y el valor puesto
             * en esta no es igual al especificado por la pista, y además que no se encuentre en el
             * final del hidato.
             */
            if (vect[2] != Application.matrix_With_Level[vect[0]][vect[1]] && Application.matrix_With_Level[vect[0]][vect[1]] != 0 && vect[2] != (matrix.length * matrix[0].length) + 1) {
                if (Application.debug)
                    System.out.println("Error in pos " + vect[0] + "," + vect[1] + " " + Application.matrix_With_Level[vect[0]][vect[1]] + " != " + vect[2]);
                matrix = new int[matrix.length][matrix[0].length];              // Reinicia la matrix solución.
                vect[0] = Application.firstPos[0];                              // A vect en la posición cero le asigna la fila aleatoria.
                vect[1] = Application.firstPos[1];                              // A vect en la posición 1 le asigna la columna aleatoria.
                vect[2] = 1;                                                    // A vect en la posición 2 le asigna el valor inicial desde el cual se iniciará la primera casilla del hidato.
                inicio++;                                                       // Aumenta valor de inicio puesto que el anterior no fue el usado para generar el hidato.
                if (Application.debug)
                    System.out.println("With inico iqual to " + inicio);
            }

            // Si recorrer la lista de las 8 formas no dió solución.
            if (inicio == 8) {
                notSolution = true;                                             // Indica que no hay solución.
                break;
            }
        }

        // Si halló solución.
        if (notSolution == false) {
            Application.loadOrNew = 1;                                               // Pone loadOrNew como uno para que al dar en mostrar solución no la recalcule.
        } // Si no halló solución.
        else {
            Application.loadOrNew = 3;                                               // Pone loadOrNew como 3 indicando que no hay solución.
        }
        return matrix;                                                          // Devuelve la matrix con un hidato.
    }

    /**
     * FIXME: Description of {@code slowSolveHidato}. Invocado cuando no se pudo encontrar una
     * solución rápida y el usuario decidió encontrar una solución (aunque se demore). Busca la
     * solución al Hidato de Hidato.matrix_With_Level y la almacena en Hidato.matrix_Complete. Así a
     * la hora de dar en el Main_Frame en restart, se podrá volver al Hidato cargado ya que este
     * está en Hidato.matrix_With_Level. Y a la hora de dar en mostrar solución una segunda vez, no
     * se tendrá que recalcular ya que esta quedará almacenada en Hidato.matrix_Complete.
     *
     * @param matrix Es la matrix en la que se almacenará la solución.
     * @param vect   Define en que casilla va el hidato solución (fila, columna, valor).
     * @return la matrix con la solución.
     */
    public int[][] slowSolveHidato(int matrix[][], int[] vect) {
        // vectAux tendrá una copia de vect.
        int[] vectAux = new int[4];
        System.arraycopy(vect, 0, vectAux, 0, 4);

        while (vect[2] != (matrix.length * matrix[0].length) + 1) {                  // Mientras no haya recorrido todas las casillas del hidato.
            matrix = markPos(matrix, vect);                                      // Actualiza posición de la matrix.
            if (Application.debug)
                printMatrix(matrix);

            // Evalúa si el valor de la casilla acabada de marcar corresponde al valor de la última casilla (encontró solución)
            if (vect[2] == matrix.length * matrix[0].length) {
                Application.loadOrNew = 1;
                return matrix;
            }

            // Calcula las posibilidades de la casilla actual.
            ListaCircular lista = calcularPosibPos(matrix, vect, Application.matrix_With_Level);
            int posibAux;                                                       // Entero que tendrá la cantidad de posibilidades de cada nodo.
            Nodo nodoAux = lista.inicio;                                        // Crea un nuevo nodo que empezará desde el inicio de la lista.
            int posibTiles = 8;                                                 // posibTiles define desde la casilla actual a cuantas puede dirigirse.

            // Se recorre la lista de posibilidades para saber cuantas de las casillas posibles son no válidas (se salen del tablero ó ya pasó por estas mientras llenaba el hidato solución) ó son pistas.
            for (int k = 0; k < 8; k++) {
                posibAux = nodoAux.getPosibilidades();
                if (Application.debug)
                    System.out.printf("k is %d and list is %d %d %d\n", k, nodoAux.getFila(), nodoAux.getColumna(), nodoAux.getPosibilidades());

                // Evalúa si la casilla posible está fuera del tablero, ha pasado por ahí mientras llena el hidato solución ó es una pista en el hidato cargado.
                // En fin, evalúa que a esa casilla no se puede avanzar.
                if (posibAux == -1 || Application.matrix_With_Level[nodoAux.getFila()][nodoAux.getColumna()] != 0) {
                    // Disminuye las casillas posibles.
                    posibTiles--;

                    // Pone -1 en el campo posibilidades indicando que no es posible desplazarse a esa casilla.
                    nodoAux.setPosibilidades(-1);
                }
                // Avanza en la lista.
                nodoAux = nodoAux.getSiguiente();
            }
            if (Application.debug)
                System.out.printf("Cant posib in %d %d %d is %d\n\n", vect[0], vect[1], vect[2], posibTiles);

            // Si la cantidad de posibilidades no es cero (lista vacía) la muestra.
            if (posibTiles != 0) {
                if (Application.debug) {
                    System.out.printf("Original list is:\n");
                    lista.listar();
                }
                // Ordena la lista de casillas posibles de menor a mayo cantidad de posibilidades.
                lista = lista.quickSortList(lista);

                if (Application.debug) {
                    System.out.printf("Ordened list is:\n");
                    lista.listar();
                }

                // Borra de la lista las casillas a las cuales no es posible desplazarse.
                for (int i = 0; i < 8 - posibTiles; i++) {
                    lista.eliminarElemento(true);
                }

                if (Application.debug) {
                    System.out.printf("Clean list is:\n");
                    lista.listar();
                }
            }

            /*
             * Se define para esta práctica el concepto de punto muerto como dadas dos casila m y n,
             * la única forma posible de llegar a n es desde m, y en caso de moverse desde m hasta
             * n, el hidato se queda sin movimientos, partiendo de este hecho, se entiende que el
             * valor de m no va en dicha casilla. Ir a ayuda punto muerto para entender mejor.
             */
            // Se define el costo de ir de una casilla m a una n como:
            // |fila de N - fila de M|+|columna de N - columna de M|
            // El costo entonces de ir de una casilla a otra adyacente es:
            // 1 si es adyacente lateralmente, y 2 si es adyacente diagonalmente.
            // Para verlo gráficamente ir a ayuda y luego a Costos.
            // crea un entero cost que definirá costo entre la casilla actual y la próxima pista,
            // uno que definirá diferencia entre las filas (row), otro definirá diferencia entre las columnas (col).
            // Y otro que tendrá la distancia entre la casilla actual y la próxima pista (dist).
            int cost, row, col, dist;

            // A dist le asigna lo que falta para la próxima pista.
            dist = Application.tracks[vect[2]][2];

            // Evalúa si falta 1 para llegar a la próxima pista, es decir para llegar de la casilla actual a la próxima pista es necesario que sean adyacente.
            if (dist == 1) {
                // A row le lleva la resta de la fila de la casilla actual con la próxima pista.
                // A col le lleva la resta de la columna de la casilla actual con la próxima pista.
                row = Math.abs(vect[0] - Application.tracks[vect[2] + 1][0]);
                col = Math.abs(vect[1] - Application.tracks[vect[2] + 1][1]);

                // A cost le asigna el valor absoluto de la suma de la variación entre filas y columnas.
                cost = row + col;

                // Evalúa si la casilla actual y la pista no son adyacentes.
                // En cuyo caso se entiende que por ese camino no es.
                if (cost != 1 && cost != 2) {
                    if (Application.debug)
                        System.out.printf("!Error! next box is a track and position %d %d %d is not adyacent to %d %d %d\n", vect[0], vect[1], vect[2], Application.tracks[vect[2] + 1][0], Application.tracks[vect[2] + 1][1], vect[2] + 1);

                    // Backtracking.
                    matrix[vect[0]][vect[1]] = 0;
                    vect[2]--;
                    if (Application.debug)
                        printMatrix(matrix);
                    return matrix;
                } // Si el costo es dos puede pasar que sea adyacente diagonalmete, o que se llegue a esta avanzando dos casillas lateralmente.
                else if (cost == 2) {
                    // Es adayacente diagonalmente solo si la variación en filas es 1 y en columnas es 1.
                    // Solo entra aquí si eso no ocurre.
                    if (col == 2 || row == 2) {
                        if (Application.debug)
                            System.out.printf("!Error! next box is a track and position %d %d %d is not adyacent to %d %d %d\n", vect[0], vect[1], vect[2], Application.tracks[vect[2] + 1][0], Application.tracks[vect[2] + 1][1], vect[2] + 1);

                        // Backtracking.
                        matrix[vect[0]][vect[1]] = 0;
                        vect[2]--;
                        if (Application.debug)
                            printMatrix(matrix);
                        return matrix;
                    }
                }
                // Caso de que sean adyacentes se desplaza obligatoriamente a la pista.
                vect[0] = Application.tracks[vect[2] + 1][0];
                vect[1] = Application.tracks[vect[2] + 1][1];
                vect[2]++;
                if (Application.debug)
                    System.out.printf("Point obligatory %d %d %d\n", vect[0], vect[1], vect[2]);
                slowSolveHidato(matrix, vect);

                // Evalúa si se devolvió porque ya encontró solución.
                if (Application.loadOrNew == 1) {
                    return matrix;
                }

                // Llega hasta aquí si entrando a esa casilla (obligatoriamente) llegó a un punto en que tuvo que devolverse hasta aquí.
                // Por tanto para que no comience a evaluar posibilidades del for de abajo hay que terminar esta ejecución recursiva.
                if (Application.debug)
                    System.out.printf("¡Error! pointing obligatory in %d %d %d\n", vect[0], vect[1], vect[2] + 1);

                // A vect le lleva el valor de vectAux.
                System.arraycopy(vectAux, 0, vect, 0, 4);

                // Backtracking.
                matrix[vect[0]][vect[1]] = 0;
                vect[2]--;
                if (Application.debug)
                    printMatrix(matrix);
                return matrix;
            } // Si la distancia no es 1 (No es adyacente).
            else {
                // A row le lleva la resta de la fila de la casilla actual con la próxima pista.
                // A col le lleva la resta de la columna de la casilla actual con la próxima pista.
                row = Math.abs(vect[0] - Application.tracks[vect[2] + dist][0]);
                col = Math.abs(vect[1] - Application.tracks[vect[2] + dist][1]);

                // A cost le asigna el valor absoluto de la suma de la variación entre filas y columnas.
                cost = row + col;

                // Evalúa si desde la casilla actual es posible llegar a la próxima pista avanzando tantas casillas como diga dist.
                // Si ni con un valor de dist*2 (dist veces la misma diagonal) se puede llegar se entiende que por ese camino no es.
                // Es decir que no importa cuanto intente, con dist casillas no es posible llegar desde la casilla actual a la próxima pista.
                if (cost > dist * 2) {
                    if (Application.debug)
                        System.out.printf("¡Error! can not go to track %d %d %d from %d %d %d with %d boxes\n", Application.tracks[vect[2] + dist][0], Application.tracks[vect[2] + dist][1], vect[2] + dist, vect[0], vect[1], vect[2], dist);

                    // Backtracking.
                    matrix[vect[0]][vect[1]] = 0;
                    vect[2]--;
                    if (Application.debug)
                        printMatrix(matrix);
                    return matrix;
                } /*
                 * Evalúa si desde la casilla actual hasta la próxima pista se tiene un costo de
                 * dist*2, es decir solo puede llegar desde la casilla actual a la próxima pista
                 * avanzando dist veces por la misma diagonal.
                 *
                 * Por ejemplo, se tiene una casilla actual con valor 5 en 1,1 y la próxima pista es
                 * 8 en 4,4. realmente la única posibilidad de llegar de 5 a 8 es avanzando 3 (dist)
                 * veces por la diagonal principal, pero si la diagonal no está despejada, entonces
                 * definitivamente no es posible llegar de 5 a 8.
                 *
                 * Ahora puede ocurrir que a pesar de que se puede llegar en teoría desde la casilla
                 * actual a la próxima pista usando n-veces la misma diagonal, en realidad para
                 * llegar se necesite el mismo costo, pero usando más casillas y una ruta diferente.
                 * Con el ejemplo anterior por ejemplo ir de 1,1 a 4,4 requiere un costo de 6, y ir
                 * de 1,1 a 3,5 también, pero llegar a 4,4 requiere 3 casillas, y llegar a 3,5
                 * requiere 4, por tanto el costo no tiene suficiente información para decir si
                 * realmente se puede llegar de una casilla a otra. Pero se sabe que están ambas en
                 * la misma diagonal si la variación de filas = variación de columnas, y además que
                 * se puede llegar si row<=dist. En el caso anterior de 1,1 a 4,4 row=3, col=3,
                 * dist=3. y de 1,1 a 3,5 row=2, col=4 dist=3. Con esto último se puede saber
                 * también que no es posible en realidad llegar a 3,5.
                 */ // Evalúa que se pueda llegar a la pista usando alguna diagonal.
                else if (cost == dist * 2 && row == dist && col == dist) {
                    if (Application.debug)
                        System.out.printf("Validate if can go to track %d %d %d from %d %d %d with %d ", Application.tracks[vect[2] + dist][0], Application.tracks[vect[2] + dist][1], vect[2] + dist, vect[0], vect[1], vect[2], dist);
                    // Evalúa si la fila de la casilla actual es mayor que la fila de la próxima pista.
                    // Así sabe que la dianagonal no es algunade las inferiores.
                    if (vect[0] > Application.tracks[vect[2] + dist][0]) {
                        // Evalúa si la columna de la casilla actual es mayor que la de la próxima pista.
                        // Así hay seguridad que se llega a la pista por la diagonal superior izquierda.
                        if (vect[1] > Application.tracks[vect[2] + dist][1]) {
                            if (Application.debug)
                                System.out.printf("left up diagonal boxes\n");
                            // Si solo es posible llegar de la casilla actual a la próxima pista usando dicha diagonal, y la diagonal no está despejada.
                            if (validarDiagonal(vect, 1, dist, Application.matrix_With_Level, matrix) == false) {
                                if (Application.debug)
                                    System.out.printf("Cant not go to next track using %d left up diagonal boxes\n", dist);
                                // Backtracking.
                                matrix[vect[0]][vect[1]] = 0;
                                vect[2]--;
                                if (Application.debug)
                                    printMatrix(matrix);
                                return matrix;
                            }
                        } // Caso contrario se entiende que se llega por la diagonal superior derecha.
                        else {
                            if (Application.debug)
                                System.out.printf("right up diagonal boxes\n");
                            // Si solo es posible llegar de la casilla actual a la próxima pista usando dicha diagonal, y la diagonal no está despejada.
                            if (validarDiagonal(vect, 2, dist, Application.matrix_With_Level, matrix) == false) {
                                if (Application.debug)
                                    System.out.printf("Cant not go to next track using %d right up diagonal boxes\n", dist);
                                // Backtracking.
                                matrix[vect[0]][vect[1]] = 0;
                                vect[2]--;
                                if (Application.debug)
                                    printMatrix(matrix);
                                return matrix;
                            }
                        }
                    } // Caso contrario se entiende que se llega a la próxima pista avanzando por alguna de las diagonales inferiores.
                    else {
                        // Evalúa si la columna de la casilla actual es mayor que la de la próxima pista.
                        // Así hay seguridad que se llega a la pista por la diagonal inferior izquierda.
                        if (vect[1] > Application.tracks[vect[2] + dist][1]) {
                            if (Application.debug)
                                System.out.printf("left down diagonal boxes\n");
                            // Si solo es posible llegar de la casilla actual a la próxima pista usando dicha diagonal, y la diagonal no está despejada.
                            if (validarDiagonal(vect, 3, dist, Application.matrix_With_Level, matrix) == false) {
                                if (Application.debug)
                                    System.out.printf("Cant not go to next track using %d left down diagonal boxes\n", dist);
                                // Backtracking.
                                matrix[vect[0]][vect[1]] = 0;
                                vect[2]--;
                                if (Application.debug)
                                    printMatrix(matrix);
                                return matrix;
                            }
                        } // Caso contrario se entiende que se llega por la diagonal inferior derecha.
                        else {
                            if (Application.debug)
                                System.out.printf("right down diagonal boxes\n");
                            // Si solo es posible llegar de la casilla actual a la próxima pista usando dicha diagonal, y la diagonal no está despejada.
                            if (validarDiagonal(vect, 4, dist, Application.matrix_With_Level, matrix) == false) {
                                if (Application.debug)
                                    System.out.printf("Cant not go to next track using %d down right diagonal boxes\n", dist);
                                // Backtracking.
                                matrix[vect[0]][vect[1]] = 0;
                                vect[2]--;
                                if (Application.debug)
                                    printMatrix(matrix);
                                return matrix;
                            }
                        }
                    }
                    if (Application.debug)
                        System.out.printf("Yes, can go\n");
                }
            }

            /*
             * Arriba se hicieron varias evaluaciones para descartar rápidamente ese camino, pero
             * llegados aquí no queda de otra que probar todas las posibles combinaciones.
             */
            // Si todas las casillas a las cuales se puede desplazar desde la actual son no válidas o son pistas (se quedó sin movimientos).
            if (posibTiles == 0) {
                if (Application.debug)
                    System.out.printf("No more posibs in %d %d %d\n\n\n", vect[0], vect[1], vect[2]);

                // Backtracking.
                matrix[vect[0]][vect[1]] = 0;
                vect[2]--;
                if (Application.debug)
                    printMatrix(matrix);
                return matrix;
            }

            // Reinicia nodoAux.
            nodoAux = lista.inicio;

            // Recorre la lista que contiene las posibles casillas a las cuales desplazarse.
            for (int k = 0; k < lista.size; k++) {
                // A posibAux le lleva el nodo actual en su campo pisibilidades.
                posibAux = nodoAux.getPosibilidades();
                if (Application.debug)
                    System.out.printf("k is %d and posibs is %d\n", k, posibAux);

                // Evalúa si es posible desplazarse a esa casilla (no está fuera del tablero y no ha pasado por ahí mientras llena el hidato solución).
                if (posibAux != -1) {
                    // Evalúa si la casilla no es una pista.
                    if (Application.matrix_With_Level[nodoAux.getFila()][nodoAux.getColumna()] == 0) {
                        vect[0] = nodoAux.getFila();
                        vect[1] = nodoAux.getColumna();
                        vect[2]++;
                        if (Application.debug)
                            System.out.printf("intent to point %d %d %d\n", vect[0], vect[1], vect[2]);
                        slowSolveHidato(matrix, vect);

                        // Evalúa si se devolvió porque ya encontró solución.
                        if (Application.loadOrNew == 1) {
                            return matrix;
                        }
                        matrix[vect[0]][vect[1]] = 0;
                    }
                }
                // Avanza en la lista.
                nodoAux = nodoAux.getSiguiente();
            }

            // Si sale del for anterior significa que dada una casilla n, no se pudo poner en alguna de sus casillas adyacentes el valor siguiente.
            if (Application.debug)
                System.out.printf("¡Error! trying to point value %d with %d %d %d\n", vect[2] + 1, vectAux[0], vectAux[1], vectAux[2]);

            // A vect le lleva el valor de vectAux.
            System.arraycopy(vectAux, 0, vect, 0, 4);

            // Backtracking.
            matrix[vect[0]][vect[1]] = 0;
            vect[2]--;
            if (Application.debug)
                printMatrix(matrix);
            return matrix;
        }

        // Devuelve la matrix con un hidato solución, y en caso de no haber una devuelve un hidato con todas sus casillas en cero.
        return matrix;
    }

    /**
     * FIXME: Description of {@code fillCostMatrix}. Invocado cuando se da en ayuda y luego en
     * Costos. Llena una matrix con los costos de ir de la casilla central a cualquier otra.
     *
     * @param matrix Es la matrix en la que se almacenarán los costos.
     * @param size   Define cual es el tamaño nxn (n impar) de la matrix de costos.
     * @return la matrix con los costos.
     */
    public int[][] fillCostMatrix(int matrix[][], int size) {
        matrix = new int[size][size];
        int k = size;
        for (int i = 0; i < size; i++) {
            if (i <= size / 2) {
                k--;
            } else {
                k++;
            }
            int t = k + 1;
            for (int j = 0; j < size; j++) {
                if (j <= size / 2) {
                    t--;
                } else {
                    t++;
                }
                matrix[i][j] = t;
            }
        }
        return matrix;
    }

    /**
     * FIXME: Description of {@code fillDeadMatrix}. Invocado cuando se da en ayuda y luego en punto
     * muerto. Llena una matrix con ejemplos de puntos muertos.
     *
     * @param matrix  Es la matrix en la que se almacenarán los puntos muertos.
     * @param size    Define cual es el tamaño nxn de la matrix de puntos muertos..
     * @param example define el número del ejemplo
     * @return la matrix con los ejemplos de puntos muertos.
     */
    public int[][] fillDeadMatrix(int matrix[][], int size, int example) {
        matrix = new int[size][size];
        matrix[4][4] = 1;
        matrix[3][3] = 2;
        matrix[4][3] = 3;
        matrix[4][2] = 4;
        matrix[3][2] = 5;
        matrix[4][1] = 6;
        matrix[3][1] = 7;
        matrix[4][0] = 8;
        matrix[3][0] = 9;
        matrix[2][0] = 10;
        matrix[1][0] = 11;
        matrix[0][2] = 18;
        matrix[0][3] = 19;
        matrix[1][4] = 20;
        matrix[3][4] = 25;

        switch (example) {
            case 1:
                matrix[1][1] = 12;
                matrix[1][2] = 13;
                matrix[2][2] = 14;
                break;
            case 2:
                matrix[0][0] = 12;
                matrix[0][1] = 13;
                matrix[1][2] = 14;
                matrix[2][2] = 15;
                matrix[1][1] = 17;
                break;
            default:
                break;
        }

        return matrix;
    }

    /**
     * FIXME: Description of {@code validarDiagonal}. Invocado cuando se está tratando de resolver un
     * hidato, y se encuentra con que solo es posible llegar desde la casilla actual a la próxima
     * pista usando dist veces la misma diagonal.
     *
     * @param position Define desde cual posición se empezará a válidar una diagonal.
     * @param diagonal es un valor entre 1 y 4 que define cual de las diagonales es (1 = sup izq, 2
     *                 = sup der, 3 = inf izq, 4 = inf der)
     * @param dist     define cuantas casillas se debe validar la diagonal.
     * @param hidato   es la matrix con un hidato cargado.
     * @param solution es la matrix con lo que se lleva del hidato solucionado.
     * @return la matrix con los niveles de adyacencia.
     */
    public boolean validarDiagonal(int[] position, int diagonal, int dist, int hidato[][], int solution[][]) {
        int row = position[0];
        int col = position[1];
        // Escoge cual diagonal se va a validar.
        switch (diagonal) {
            // Superior izquierda.
            case 1:
                // Avanza dist veces en fila y columna.
                for (int i = 1; i < dist; i++) {
                    row--;
                    col--;
                    // Si en cierto momento encontró alguna casilla no despejada.
                    if (hidato[row][col] != 0 || solution[row][col] != 0) {
                        if (Application.debug)
                            System.out.printf("Found a value in %d %d %d\n", row, col, solution[row][col]);
                        return false;
                    }
                }
                break;
            // Superior derecha.
            case 2:
                // Avanza dist veces en fila y columna.
                for (int i = 1; i < dist; i++) {
                    row--;
                    col++;
                    // Si en cierto momento encontró alguna casilla no despejada.
                    if (hidato[row][col] != 0 || solution[row][col] != 0) {
                        if (Application.debug)
                            System.out.printf("Found a value in %d %d %d\n", row, col, solution[row][col]);
                        return false;
                    }
                }
                break;
            // Inferior izquierda.
            case 3:
                // Avanza dist veces en fila y columna.
                for (int i = 1; i < dist; i++) {
                    row++;
                    col--;
                    // Si en cierto momento encontró alguna casilla no despejada.
                    if (hidato[row][col] != 0 || solution[row][col] != 0) {
                        if (Application.debug)
                            System.out.printf("Found a value in %d %d %d\n", row, col, solution[row][col]);
                        return false;
                    }
                }
                break;
            // Inferior derecha.
            case 4:
                // Avanza dist veces en fila y columna.
                for (int i = 1; i < dist; i++) {
                    row++;
                    col++;
                    // Si en cierto momento encontró alguna casilla no despejada.
                    if (hidato[row][col] != 0 || solution[row][col] != 0) {
                        if (Application.debug)
                            System.out.printf("Found a value in %d %d %d\n", row, col, solution[row][col]);
                        return false;
                    }
                }
                break;
            default:
                break;
        }
        // Devuelve true si la diagonal está despejada.
        return true;
    }

    /**
     * FIXME: Description of {@code validarAdyacencia}. Invocado cuando se quiere validar si se está
     * en un punto muerto. Recibe 2 casillas (fila y columna) y dice si son o no adyacentes. Para
     * fectos prácticos se dice que una casilla es adyacente consigo misma.
     *
     * @param row1 Fila de la casilla 1.
     * @param col1 Columna de la casilla 1.
     * @param row2 Fila de la casilla 2.
     * @param col2 Columna de la casilla 2.
     * @return la matrix con los niveles de adyacencia.
     */
    public boolean validarAdyacencia(int row1, int col1, int row2, int col2) {
        int row = row1 - row2;
        int col = col1 - col2;
        row = Math.abs(row);
        col = Math.abs(col);

        if (row == 1 || row == 0) {
            if (col == 1 || col == 0) {
                return true;
            }
        }
        return false;
    }

}
