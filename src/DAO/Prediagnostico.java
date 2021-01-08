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
public class Prediagnostico {
    private int idPrediagnostico;
    private Date fecha;
    private int duracion;
    
    public Prediagnostico(){}
    
    public Prediagnostico(int idPrediagnostico, Date fecha, int duracion) {
        this.idPrediagnostico = idPrediagnostico;
        this.fecha = fecha;
        this.duracion = duracion;
    }

    public int getIdPrediagnostico() {
        return idPrediagnostico;
    }

    public void setIdPrediagnostico(int idPrediagnostico) {
        this.idPrediagnostico = idPrediagnostico;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    
}
