/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Kevin Sarango y Anderson Ambuludí
 */
public class Aeropuerto {

    private Integer id;
    private String nombre;
    private String codAeropuerto;

    public Aeropuerto() {
    }

    public Aeropuerto(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodAeropuerto() {
        return codAeropuerto;
    }

    public void setCodAeropuerto(String codAeropuerto) {
        this.codAeropuerto = codAeropuerto;
    }

    @Override
    public String toString() {
        return nombre + "(" + codAeropuerto + ")";
    }

}
