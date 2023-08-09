/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.ed.grafo.exception;

/**
 *
 * @author Kevin Sarango y Anderson Ambuludí
 */
public class GrafoSizeExeption extends Exception {

    public GrafoSizeExeption(String msg) {
        super(msg);
    }

    public GrafoSizeExeption() {
        super("Accedio a un tamanio fuera del grafo");
    }
}
