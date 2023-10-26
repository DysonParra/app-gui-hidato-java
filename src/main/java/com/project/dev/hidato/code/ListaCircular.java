/*
 * @fileoverview    {ListaCircular}
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

/**
 * TODO: Definición de {@code ListaCircular}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
public class ListaCircular {

    public Nodo inicio = null;                  // Referencia que indica el inicio de la lista.
    public Nodo ultimo = null;                  // Referencia que indica el final de la lista o el ultimo nodo.
    public int size = 0;                        // size tendrá la cantidad de elementos de la lista.

    /**
     * FIXME: Definición de {@code agregarElemento}. Crea un nuevo nodo en una lista ligadaCircular.
     *
     * @param fila          es lo que llevará el nuevo nodo en el campo fila.
     * @param columna       es lo que llevará el nuevo nodo en el campo columna.
     * @param posibilidades es lo que llevará el nuevo nodo en el campo posibilidades.
     * @param start         Define si el elemento se agrega al inicio de la lista. (false = agregar
     *                      al final).
     */
    public void agregarElemento(int fila, int columna, int posibilidades, boolean start) {
        Nodo nuevo = Nodo.builder()
                .fila(fila)
                .columna(columna)
                .posibilidades(posibilidades)
                .build();

        // Evalúa si la lista está vacia.
        if (size == 0) {
            inicio = nuevo;                             // Inicializa la lista.
            ultimo = nuevo;                             // El ultimo nodo sera el nuevo.
            inicio.setSiguiente(ultimo);                // Apuntamos con el nodo inicio al ultimo nodo de la lista.
            ultimo.setSiguiente(inicio);                // El puntero del ultimo debe apuntar al nodo inicio.
        } else {                                        // Si el nodo no es el primer nodo de la lista.
            ultimo.setSiguiente(nuevo);                 // A ultimo le pone como siguiente el nuevo nodo.
            nuevo.setSiguiente(inicio);                 // Enlaza el nuevo nodo con el primer nodo de la lista.

            // Evalúa si se debe agregar al inicio de la lista.
            if (start) {
                inicio = nuevo;                         // El nuevo nodo es ahora el primero.
            } else {
                ultimo = nuevo;                         // El nuevo nodo es ahora el ultimo.
            }
        }

        // Actualiza la cantidad de elementos de la lista.
        size++;
    }

    /**
     * FIXME: Definición de {@code eliminarElemento}. Quita un nodo de una lista ligadaCircular.
     *
     * @param first Define si se elimina el primer nodo de la lista. (false = eliminar el ultimo).
     */
    public void eliminarElemento(boolean first) {
        // Evalúa si la lista no está vacia.
        if (size != 0 && size != 1) {
            Nodo aux = inicio.getSiguiente();
            // Evalúa si se debe agregar al inicio de la lista.
            if (first) {
                // Desliga ultimo y inicio, y liga último con segundo..
                inicio.setSiguiente(null);
                ultimo.setSiguiente(aux);
                this.inicio = aux;
            } else {
                // Avanza hasta el penúltimo nodo.
                while (aux.getSiguiente() != ultimo) {
                    aux = aux.getSiguiente();
                }
                // Desliga ultimo y inicio, y liga penúltimo con inicio.
                ultimo.setSiguiente(null);
                aux.setSiguiente(inicio);
                this.ultimo = aux;
            }
        } else if (size == 1) {
            inicio = null;
            ultimo = null;
        }

        // Actualiza la cantidad de elementos de la lista.
        size--;
    }

    /**
     * FIXME: Definición de {@code listar}. Muestra en pantalla los elementos de la lista. Solo
     * usado para debug, no necesitado para el programa.
     */
    public void listar() {
        System.out.printf("size is %d", size);
        Nodo aux = inicio;                                                      // aux apunta al primer nodo de la lista.
        int i = 0;                                                              // Posicion de los elementos de la lista.
        System.out.print("\n" + aux.getFila() + " " + aux.getColumna() + " " + aux.getPosibilidades());
        aux = aux.getSiguiente();                                           // Avanza al siguiente nodo.
        i++;                                                                // Incrementa el contador de la posión.
        while (aux != inicio) {                                                  // Recorre la lista hasta llegar al incio de la lista.
            System.out.print("\n" + aux.getFila() + " " + aux.getColumna() + " " + aux.getPosibilidades());
            aux = aux.getSiguiente();                                           // Avanza al siguiente nodo.
            i++;                                                                // Incrementa el contador de la posión.
        }
        System.out.printf("\n\n");
    }

    /**
     * FIXME: Definición de {@code quickSortList}. Ordena los nodos de menor a mayor valor de campo
     * posibilidades usando quickSort.
     *
     * @param lista Es la lista que se ordenará.
     * @return la lista con los elementos de menor a mayor campo de posibilidades.
     */
    public ListaCircular quickSortList(ListaCircular lista) {
        // Crea un nuevo Nodo con el inicio de la lista.
        Nodo aux = lista.inicio;

        // Crea una lista que tendrá los nodos menores (en el campo posibilidades) a aux, y otra con los mayores.
        ListaCircular menores = new ListaCircular();
        ListaCircular mayores = new ListaCircular();

        // Crea un nuevo nodo con una copia del inicio de la lista (igual a aux).
        Nodo medio = Nodo.builder()
                .fila(aux.getFila())
                .columna(aux.getColumna())
                .posibilidades(aux.getPosibilidades())
                .build();
        medio.setFila(aux.getFila());
        medio.setColumna(aux.getColumna());
        medio.setPosibilidades(aux.getPosibilidades());

        // Recorre la lista desde el segundo elemento.
        for (int i = 1; i < lista.size; i++) {
            //System.out.printf("i is %d\n", i);

            // Avanza aux.
            aux = aux.getSiguiente();

            // Si el nodo es menor o igual al primer nodo de la lista (en el campo posibilidades).
            if (aux.getPosibilidades() <= medio.getPosibilidades()) {
                // Agrega el nodo a la lista de menores.
                menores.agregarElemento(aux.getFila(), aux.getColumna(), aux.getPosibilidades(), false);
            } // Si el nodo es mayor al primer nodo de la lista (en el campo posibilidades).
            else {
                // Agrega el nodo a la lista de mayores.
                mayores.agregarElemento(aux.getFila(), aux.getColumna(), aux.getPosibilidades(), false);
            }
        }

        // Evalúa si todos los nodos pasaron a la lista de menores (medio tiene el número mayor de posibilidades).
        // Y además evalúa que la lista tenga más de un elemento.
        if (menores.size == lista.size - 1 && menores.size > 1) {
            //System.out.printf("Quick again size is %d\n",menores.size);
            // Procede a ordenar la lista de menores.
            menores = quickSortList(menores);
        }
        // Agrega el medio como último elemento de la lista de menores.
        menores.agregarElemento(medio.getFila(), medio.getColumna(), medio.getPosibilidades(), false);

        // Imprime los valores hasta el momento de ambas listas.Mostrar.
        // if (menores.inicio!=null) {
        //     System.out.printf("menores is\n");
        //     menores.listar();
        // }
        // if (mayores.inicio!=null) {
        //     System.out.printf("mayores is\n");
        //     mayores.listar();
        // }
        // Llega acá si ya terminó de ordenar la lista de menores.
        if (menores.size == lista.size) {
            //System.out.printf("menores ordenado\n\n\n");
            return menores;
        } // Si la lista de menores no está vacía.
        else if (menores.inicio != null) {
            // Ordena la lista de menores.
            menores = quickSortList(menores);
        }

        // Si hay elementos en la lista de mayores.
        if (mayores.inicio != null) {
            // Ordena la lista de mayores.
            mayores = quickSortList(mayores);
        }

        // Concatena la lista de mayores y la de menores.
        Nodo nodoAux = mayores.inicio;
        //System.out.printf("mayores is %d inicio is %d\n", mayores.size,nodoAux.getPosibilidades());
        for (int i = 0; i < mayores.size; i++) {
            menores.agregarElemento(nodoAux.getFila(), nodoAux.getColumna(), nodoAux.getPosibilidades(), false);
            nodoAux = nodoAux.getSiguiente();
        }
        //menores.listar();

        // Devuelve la lista completamente ordenada.
        return menores;
    }

}
