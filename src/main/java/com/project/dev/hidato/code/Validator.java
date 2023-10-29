/*
 * @fileoverview    {Validator}
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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * TODO: Definición de {@code Validator}.
 *
 * @author Dyson Parra
 * @since 11
 */
public class Validator {

    /**
     * FIXME: Definición de {@code esUnNumero}. Se usa en conjunto con validar número para verificar
     * que el hidato a crear tenga en nivel, ancho y alto solo números.
     *
     * @param character Valida si un caracter ingresado es un número.
     * @return true si cumple.
     */
    public boolean esUnNumero(String character) {
        boolean valor;
        switch (character) {
            case "1":
                valor = true;
                break;
            case "2":
                valor = true;
                break;
            case "3":
                valor = true;
                break;
            case "4":
                valor = true;
                break;
            case "5":
                valor = true;
                break;
            case "6":
                valor = true;
                break;
            case "7":
                valor = true;
                break;
            case "8":
                valor = true;
                break;
            case "9":
                valor = true;
                break;
            case "0":
                valor = true;
                break;
            default:
                valor = false;
                break;
        }
        return valor;
    }

    /**
     * FIXME: Definición de {@code validarNumero}. Valida si el String ingresado es un número de
     * tipo integer. Se usa cuando se escribe el nivel, ancho y alto de un hidato a ser creado, para
     * validar que se hayan escrito números.
     *
     * @param text Es el String que se evaluará.
     * @return true si es número, y false en caso contrario.
     */
    public boolean validarNumero(String text) {
        boolean valido = true;
        String charActStr;                                                      // charActStr se usará para almacenar cada uno de los caracteres del texto en String.
        char charActChar;                                                       // charActChar se usará para almacenar cada uno de los caracteres del texto en Char.

        for (int i = 0; i <= text.length() - 1; i++) {                                 // Recorre cada uno de los caracteres del texto parámetro.
            charActChar = text.charAt(i);                                   // A charActChar le asigna el caracter actual en char.
            charActStr = String.valueOf(charActChar);                       // A charActStr le asigna el caracter actual en String.

            if (!esUnNumero(charActStr)) {                                   // Evalúa si el caracter actual no es un número.
                valido = false;                                             // A valido le asigna false.
                return valido;                                              // Devuelve valido.
            }
        }
        return valido;                                                          // Devuelve valido como true si al terminar de recorrer el testo no hay cracteres que no sean números.
    }

    /**
     * FIXME: Definición de {@code validarSlash}. Valida si el String ingresado contiene algún Slash
     * "/". Se usa al intentar guardar el txt o la imagen, puesto que si se pone un slash en la ruta
     * trata de guardarlo en la ruta especificada, y en caso de que no, por defecto java guarda las
     * imagenes o txs en la carpeta del proyecto.
     *
     * @param text Es el String que se evaluará.
     * @return true si encontro algún Slash "/".
     */
    public boolean validarSlash(String text) {
        boolean found = false;                                                  // Inicializa found como false indicando que no se han encontrado slash.
        String charActStr;                                                      // charActStr se usará para almacenar cada uno de los caracteres del texto en String.
        char charActChar;                                                       // charActChar se usará para almacenar cada uno de los caracteres del texto en Char.

        for (int i = 0; i <= text.length() - 1; i++) {                                 // Recorre cada uno de los caracteres del texto parámetro.
            charActChar = text.charAt(i);                                   // A charActChar le asigna el caracter actual en char.
            charActStr = String.valueOf(charActChar);                       // A charActStr le asigna el caracter actual en String.
            if ("/".equals(charActStr)) {                                    // Evalúa si el caracter actual es un Slash.
                found = true;                                               // A found le asigna true;
                return found;                                               // Devuelve found.
            }
        }
        return found;                                                           // Devuelve found como false si no encontro algún Slash "/".
    }

    /**
     * FIXME: Definición de {@code validarTxt}. Intenta abrir un txt indicado en una ruta.
     *
     * @param ruta       Es la ruta del txt.
     * @param readOrLoad Si solo se intentará abrir el txt, o si se abrira y además se intentará
     *                   cargarlo en una matrix. La diferencia es que si solo se intenta abrir, es
     *                   para ver si el archivo existe antes de guardar un txt, la otra es para
     *                   cargar el hidato.
     * @throws java.io.FileNotFoundException
     */
    public void validarTxt(String ruta, String readOrLoad) throws FileNotFoundException, IOException {
        FileReader f = new FileReader(ruta);                                    // Crea un fileReader de la ruta.
        BufferedReader txt = new BufferedReader(f);                             // crea un Buffer Reader de la ruta.

        if ("load".equals(readOrLoad)) {                                         // Valida si se indicó que se intentará además de validar si el txt existe, pasarlo a matrix.
            Tablero tab = new Tablero();                                        // crea una instancia de tablero.
            tab.txtToMatrix(txt);                                               // Pasa el txt a matrix.
        }
    }
}
