/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Kevin Sarango y Anderson Ambuludí
 */
public class Vuelo {

    private Integer id;
    private Integer idAeroOrigen;
    private String codAeroDestino;
    private Double distancia;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdAeroOrigen() {
        return idAeroOrigen;
    }

    public void setIdAeroOrigen(Integer idAeroOrigen) {
        this.idAeroOrigen = idAeroOrigen;
    }

    public String getCodAeroDestino() {
        return codAeroDestino;
    }

    public void setCodAeroDestino(String codAeroDestino) {
        this.codAeroDestino = codAeroDestino;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

}
