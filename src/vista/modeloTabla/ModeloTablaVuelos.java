/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.modeloTabla;

import Modelo.Vuelo;
import controlador.ed.lista.ListaEnlazada;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Kevin Sarango y Anderson Ambuludí
 */
public class ModeloTablaVuelos extends AbstractTableModel {

    private ListaEnlazada<Vuelo> lista = new ListaEnlazada<>();

    public ListaEnlazada<Vuelo> getLista() {
        return lista;
    }

    public void setLista(ListaEnlazada<Vuelo> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Vuelo c = null;
        try {
            c = lista.get(i);
        } catch (Exception e) {
        }
        switch (i1) {
//            case 0: return c.getCliente();
//            case 1: return c.getNro_cuenta();
//            case 2: return c.getSaldo();
//            case 3: return (c.getEstado())?"ACTIVO":"NO ACTIVO";
            default:
                return null;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "IDAEROORIGEN";
            case 2:
                return "CODAERODESTINO";
            case 3:
                return "DISTANCIA";
            default:
                return null;
        }
//return super.getColumnName(column); //To change body of generated methods, choose Tools | Templates.
    }

}

