/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.Dao;

import Modelo.Aeropuerto;
import java.io.IOException;

/**
 *
 * @author Kevin Sarango y Anderson Ambuludí
 */
public class AeropuertoDao extends AdaptadorDAO<Aeropuerto> {

    private Aeropuerto aero;

    public AeropuertoDao() {
        super(Aeropuerto.class);
    }

    public Aeropuerto getAero() {
        if (aero == null) {
            aero = new Aeropuerto();
        }
        return aero;
    }

    public void setAero(Aeropuerto aero) {
        this.aero = aero;
    }

    public void guardar() throws IOException {
        aero.setId(generarId());
        this.guardar(aero);
    }

    public Integer generateId() {
        return listar().size() + 1;
    }

}
