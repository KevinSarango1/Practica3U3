/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.ed.lista.ListaEnlazada;
import controlador.ed.lista.exception.PosicionException;
import controlador.ed.lista.exception.VacioException;
import javax.swing.JComboBox;
import Modelo.Aeropuerto;

/**
 *
 * @author Kevin Sarango y Anderson Ambuludí
 */
public class UtilidadesVistaGrafo {

    public static void cargarCombo(ListaEnlazada<Aeropuerto> lista, JComboBox cbx) 
            throws VacioException, PosicionException{
        cbx.removeAllItems();
        
            for (int i = 0; i < lista.size(); i++) {
                cbx.addItem(lista.get(i));
            }
        
    }
    
    public static Aeropuerto obtenerCombo(ListaEnlazada<Aeropuerto> lista, JComboBox cbx) 
            throws VacioException, PosicionException {
        return lista.get(cbx.getSelectedIndex());
    }
    
}











