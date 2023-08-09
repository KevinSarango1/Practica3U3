/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.Grafo;

import Modelo.Aeropuerto;
import Modelo.Vuelo;
import controlador.Dao.AeropuertoDao;
import controlador.Dao.VueloDao;
import controlador.ed.grafo.exception.GrafoSizeExeption;
import controlador.ed.lista.ListaEnlazada;
import controlador.ed.lista.exception.PosicionException;
import controlador.ed.lista.exception.VacioException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Kevin Sarango y Anderson Ambuludí
 */
public class GrafoAeropuerto {

    private GrafoEtiquetadoD<Aeropuerto> grafo;
    private ListaEnlazada<Aeropuerto> lista = new ListaEnlazada<>();

    public GrafoAeropuerto() {
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

    public ListaEnlazada<Aeropuerto> bellmanFord(Aeropuerto origen, Aeropuerto destino) throws GrafoSizeExeption, VacioException, PosicionException {
        ListaEnlazada<Aeropuerto> caminoLista = new ListaEnlazada<>();

        Double[] distancias = new Double[grafo.numV + 1];
        Integer[] padres = new Integer[grafo.numV + 1];

        for (int i = 0; i <= grafo.numV; i++) {
            distancias[i] = Double.POSITIVE_INFINITY;
        }

        Integer origenNum = grafo.getVerticeNum(origen);
        distancias[origenNum] = 0.0;

        for (int i = 1; i <= grafo.numV - 1; i++) {
            for (int j = 1; j <= grafo.numV; j++) {
                ListaEnlazada<Adycencia> lista = grafo.adyacentesGE(grafo.getEtiqueta(j));
                for (int k = 0; k < lista.size(); k++) {
                    try {
                        Adycencia adyacencia = lista.get(k);
                        Double pesoArista = adyacencia.getPeso();
                        Integer destinoNum = adyacencia.getDestino();
                        if (distancias[j] + pesoArista < distancias[destinoNum]) {
                            distancias[destinoNum] = distancias[j] + pesoArista;
                            padres[destinoNum] = j; // Actualiza el padre
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }

        // Reconstruir el camino más corto
        ListaEnlazada<Aeropuerto> camino = reconstruirCamino(origen, destino, padres, caminoLista);

    if (camino.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No existe camino", "Información", JOptionPane.INFORMATION_MESSAGE);
    } else {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Camino más corto desde ").append(origen.getNombre()).append(" a ").append(destino.getNombre()).append(":\n");
        
        for (int i = 0; i < camino.size(); i++) {
            mensaje.append(camino.get(i));
            if (i < camino.size() - 1) {
                mensaje.append(" -> ");
            }
        }
        
        JOptionPane.showMessageDialog(null, mensaje.toString(), "Camino más corto", JOptionPane.INFORMATION_MESSAGE);
    }

    return camino;
}

    public ListaEnlazada<Aeropuerto> reconstruirCamino(Aeropuerto origen, Aeropuerto destino, Integer[] padres, ListaEnlazada<Aeropuerto> camino) throws VacioException, PosicionException {
    Integer dest = grafo.getVerticeNum(destino); // Convierte el destino en su número de vértice
    Integer act = grafo.getVerticeNum(origen); // Convierte el origen en su número de vértice

    while (dest != null && act != 0) {
        camino.insertarInicio(grafo.getEtiqueta(dest)); // Agrega el aeropuerto al inicio del camino
        dest = padres[dest];
    }
    if (act == 0) {
        camino.deleteAll(); // No se encontró un camino válido
    }
    return camino;
}


    public ListaEnlazada<Aeropuerto> Floyd(Aeropuerto origen, Aeropuerto destino) throws GrafoSizeExeption, VacioException, PosicionException {
        ListaEnlazada<Aeropuerto> caminoLista = new ListaEnlazada<>();

        Double[][] distancias = new Double[grafo.numV + 1][grafo.numV + 1];
        Integer[][] padres = new Integer[grafo.numV + 1][grafo.numV + 1];

        for (int i = 1; i <= grafo.numV; i++) {
            for (int j = 1; j <= grafo.numV; j++) {
                if (i == j) {
                    distancias[i][j] = 0.0;
                } else {
                    distancias[i][j] = Double.POSITIVE_INFINITY;
                }
                padres[i][j] = -1; // Inicialización de los padres
            }
        }

        // Llenar la matriz de distancias con los pesos de las aristas existentes
        for (int i = 1; i <= grafo.numV; i++) {
            ListaEnlazada<Adycencia> lista = grafo.adyacentesGE(grafo.getEtiqueta(i));
            for (int j = 0; j < lista.size(); j++) {
                try {
                    Adycencia aux = lista.get(j);
                    distancias[i][aux.getDestino()] = aux.getPeso();
                    padres[i][aux.getDestino()] = i;
                } catch (Exception e) {
                }
            }
        }

        // Calcular las rutas más cortas usando el algoritmo de Floyd-Warshall
        for (int k = 1; k <= grafo.numV; k++) {
            for (int i = 1; i <= grafo.numV; i++) {
                for (int j = 1; j <= grafo.numV; j++) {
                    if (distancias[i][k] + distancias[k][j] < distancias[i][j]) {
                        distancias[i][j] = distancias[i][k] + distancias[k][j];
                        padres[i][j] = padres[k][j];
                    }
                }
            }
        }

        Integer origenNum = grafo.getVerticeNum(origen);
        Integer destinoNum = grafo.getVerticeNum(destino);

        // Reconstruir el camino más corto como lista de aeropuertos
        ListaEnlazada<Aeropuerto> camino = reconstruirFloyd(origenNum, destinoNum, padres, caminoLista);

    if (camino.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No existe camino", "Información", JOptionPane.INFORMATION_MESSAGE);
    } else {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Camino más corto desde ").append(origen.getNombre()).append(" a ").append(destino.getNombre()).append(":\n");
        
        for (int i = 0; i < camino.size(); i++) {
            mensaje.append(camino.get(i));
            if (i < camino.size() - 1) {
                mensaje.append(" -> ");
            }
        }
        
        JOptionPane.showMessageDialog(null, mensaje.toString(), "Camino más corto", JOptionPane.INFORMATION_MESSAGE);
    }

    return camino;
}


    private ListaEnlazada<Aeropuerto> reconstruirFloyd(int origen, int destino, Integer[][] padres, ListaEnlazada<Aeropuerto> camino) throws VacioException, PosicionException {

        if (padres[origen][destino] == -1) {
            return camino; // No hay camino
        }

        int actual = destino;
        while (actual != origen) {
            camino.insertarInicio(grafo.getEtiqueta(actual)); // Agrega el aeropuerto al inicio del camino
            actual = padres[origen][actual];
        }
        camino.insertarInicio(grafo.getEtiqueta(origen));

        return camino;
    }
}
