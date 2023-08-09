/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.Dao;

import controlador.Grafo.GrafoEtiquetadoD;
import Modelo.Aeropuerto;
import Modelo.Vuelo;
import controlador.ed.lista.ListaEnlazada;
import controlador.ed.lista.exception.PosicionException;
import controlador.ed.lista.exception.VacioException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Kevin Sarango y Anderson Ambuludí
 */
public class AeropuertoGrafo {

    private GrafoEtiquetadoD<Aeropuerto> grafo;
    private ListaEnlazada<Aeropuerto> lista = new ListaEnlazada<>();

    public AeropuertoGrafo() {
        AeropuertoDao aeroDao = new AeropuertoDao();
        lista = aeroDao.listar();
        grafo = new GrafoEtiquetadoD<>(lista.size());
        try {
            for (int i = 0; i < lista.size(); i++) {
                grafo.etiquetarVertice(i + 1, lista.get(i));
                System.out.println(lista.get(i));
            }
            System.out.println("<------------------->");
            llenarGrafo();
        } catch (Exception e) {
        }
    }

    public GrafoEtiquetadoD<Aeropuerto> getGrafo() {
        return grafo;
    }

    public void setGrafo(GrafoEtiquetadoD<Aeropuerto> grafo) {
        this.grafo = grafo;
    }

    public ListaEnlazada<Aeropuerto> getLista() {
        return lista;
    }

    public void setLista(ListaEnlazada<Aeropuerto> lista) {
        this.lista = lista;
    }

    private void llenarGrafo() {
        try {
            for (int i = 0; i < lista.size(); i++) {
                Aeropuerto aero = lista.get(i);
                HashMap<String, Double> mapa = new HashMap<>();
                System.out.println("Aeropuerto " + aero.getNombre());
                ListaEnlazada<Vuelo> listaV = new VueloDao().listaParaVuelo(aero.getId());
                for (int j = 0; j < listaV.size(); j++) {
                    Vuelo vue = listaV.get(j);
                    if (mapa.get(vue.getCodAeroDestino()) != null) {
                        Double suma = mapa.get(vue.getCodAeroDestino());
                        suma += vue.getDistancia();
                        mapa.put(vue.getCodAeroDestino(), suma);
                    } else {
                        mapa.put(vue.getCodAeroDestino(), vue.getDistancia());
                    }
                }
                for (Map.Entry<String, Double> entry : mapa.entrySet()) {
                    Aeropuerto aux = getAero(entry.getKey());
                    if (aux != null) {
                        System.out.println(aux);
                        grafo.insertarAristaE(aero, aux, entry.getValue());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error de llenado: " + e);
            e.printStackTrace();
        }
    }

    private Aeropuerto getAero(String vuelo) throws VacioException, PosicionException {
        Aeropuerto aux = null;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCodAeropuerto().equals(vuelo)) {
                aux = lista.get(i);
                break;
            }
        }
        return aux;
    }

}
