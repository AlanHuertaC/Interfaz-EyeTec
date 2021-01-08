/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.Date;

/**
 *
 * @author Alan Huerta Cortes
 */
public class Tratamiento {
    private int idTratamiento;
    private String tipoTratamiento;
    private String puntuacion;
    private String duracion;
    private Date fecha;
    
    private String nivelMejora;
    
    public  Tratamiento(){
        
    }

    public Tratamiento(int idTratamiento, String tipoTratamiento, String puntuacion, String duracion, Date fecha, String nivelMejora) {
        this.idTratamiento = idTratamiento;
        this.tipoTratamiento = tipoTratamiento;
        this.puntuacion = puntuacion;
        this.duracion = duracion;
        this.fecha = fecha;
        this.nivelMejora = nivelMejora;
    }

    public int getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(int idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public String getTipoTratamiento() {
        return tipoTratamiento;
    }

    public void setTipoTratamiento(String tipoTratamiento) {
        this.tipoTratamiento = tipoTratamiento;
    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNivelMejora() {
        return nivelMejora;
    }

    public void setNivelMejora(String nivelMejora) {
        this.nivelMejora = nivelMejora;
    }
    
}