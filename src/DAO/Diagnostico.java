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
public class Diagnostico {
    private int idDiagnostico;
    private String tipoEstrabismo;
    private String caracteristicas;
    private String observaciones;
    
    public  Diagnostico(){
        
    }
    public Diagnostico(int idDiagnostico, String tipoEstrabismo, String caracteristicas, String observaciones) {
        this.idDiagnostico = idDiagnostico;
        this.tipoEstrabismo = tipoEstrabismo;
        this.caracteristicas = caracteristicas;
        this.observaciones = observaciones;
    }

    public int getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(int idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public String getTipoEstrabismo() {
        return tipoEstrabismo;
    }

    public void setTipoEstrabismo(String tipoEstrabismo) {
        this.tipoEstrabismo = tipoEstrabismo;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
