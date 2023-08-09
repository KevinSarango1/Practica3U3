/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.modeloTabla;

import Modelo.Aeropuerto;
import controlador.ed.lista.ListaEnlazada;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Kevin Sarango y Anderson Ambuludí
 */
public class ModeloTablaAeropuertos extends AbstractTableModel {

    ListaEnlazada<Aeropuerto> lista = new ListaEnlazada<>();

    public ListaEnlazada<Aeropuerto> getLista() {
        return lista;
    }

    public void setLista(ListaEnlazada<Aeropuerto> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Aeropuerto aero = null;
        try {
            aero = lista.get(rowIndex);
        } catch (Exception e) {
        }
        switch (columnIndex) {
            case 0:
                return aero.getId();
            case 1:
                return aero.getNombre();
            case 2:
                return aero.getCodAeropuerto();

            default:
                return null;
        }
    }

    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "NOMBRE";
            case 2:
                return "CODIGO";

            default:
                return null;
        }
    }

}
