/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Alan Huerta Cortes
 */
public class Ojo {
    private float desviacionDerecha;
    private float desviacionIzquierda;
    private String caracteristicasDerecha;
    private String caracteristicasIzquierda;
    private float dioptriasPrismaticas;
    
    public  Ojo(){
        
    }

    public Ojo(float desviacionDerecha, float desviacionIzquierda, float dioptriasPrismaticas) {
        this.desviacionDerecha = desviacionDerecha;
        this.desviacionIzquierda = desviacionIzquierda;
        this.dioptriasPrismaticas = dioptriasPrismaticas;
    }
    
    public Ojo(float desviacionDerecha, float desviacionIzquierda, String caracteristicasDerecha, String caracteristicasIzquierda, float dioptriasPrismaticas) {
        this.desviacionDerecha = desviacionDerecha;
        this.desviacionIzquierda = desviacionIzquierda;
        this.caracteristicasDerecha = caracteristicasDerecha;
        this.caracteristicasIzquierda = caracteristicasIzquierda;
        this.dioptriasPrismaticas = dioptriasPrismaticas;
    }

    public float getDesviacionDerecha() {
        return desviacionDerecha;
    }

    public void setDesviacionDerecha(float desviacionDerecha) {
        this.desviacionDerecha = desviacionDerecha;
    }

    public float getDesviacionIzquierda() {
        return desviacionIzquierda;
    }

    public void setDesviacionIzquierda(float desviacionIzquierda) {
        this.desviacionIzquierda = desviacionIzquierda;
    }

    public String getCaracteristicasDerecha() {
        return caracteristicasDerecha;
    }

    public void setCaracteristicasDerecha(String caracteristicasDerecha) {
        this.caracteristicasDerecha = caracteristicasDerecha;
    }

    public String getCaracteristicasIzquierda() {
        return caracteristicasIzquierda;
    }

    public void setCaracteristicasIzquierda(String caracteristicasIzquierda) {
        this.caracteristicasIzquierda = caracteristicasIzquierda;
    }

    public float getDioptriasPrismaticas() {
        return dioptriasPrismaticas;
    }

    public void setDioptriasPrismaticas(float dioptriasPrismaticas) {
        this.dioptriasPrismaticas = dioptriasPrismaticas;
    }
    
}
