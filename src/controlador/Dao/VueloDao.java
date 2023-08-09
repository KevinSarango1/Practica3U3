/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.Dao;

import Modelo.Vuelo;
import controlador.ed.lista.ListaEnlazada;
import controlador.ed.lista.exception.PosicionException;
import controlador.ed.lista.exception.VacioException;
import java.io.IOException;

/**
 *
 * @author Kevin Sarango y Anderson Ambuludí
 */
public class VueloDao extends AdaptadorDAO<Vuelo>{
    private Vuelo vue;

    public VueloDao() {
        super(Vuelo.class);
    }

    public Vuelo getVue() {
        if(vue == null){
            vue = new Vuelo();
        }
        return vue;
    }

    public void setVue(Vuelo vue) {
        this.vue = vue;
    }
    
    public void guardar() throws IOException{
        vue.setId(generarId());
        this.guardar(vue);
    }
    public Integer generateId(){
        return listar().size()+1;
    }
    
    public ListaEnlazada<Vuelo> listaParaVuelo(Integer idVuelo) throws VacioException, PosicionException{
        ListaEnlazada<Vuelo> lista = new ListaEnlazada<>();
        ListaEnlazada<Vuelo> listado = listar();
        
        for (int i = 0; i < listado.size(); i++) {
            Vuelo aux = listado.get(i);
            if(aux.getIdAeroOrigen().equals(idVuelo)){
                lista.insertar(aux);
            }
        }
        return lista;
    }
}
