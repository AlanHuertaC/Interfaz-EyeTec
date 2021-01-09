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
public class Especialista extends Persona{
    private int idEspecialista;
    private String cedula;
    private String usuario;
    private String contrasena;
    
    public Especialista(){
        super();
    }

    public Especialista(String nombre, String apellidoPaterno, String apellidoMaterno) {
        super(nombre, apellidoPaterno, apellidoMaterno);
    }

    
    public Especialista(int idEspecialista, String nombre, String apellidoPaterno, String apellidoMaterno, String cedula, String sexo, int edad, String usuario, String contrasena) {
        super(nombre, apellidoPaterno, apellidoMaterno, sexo, edad);
        this.idEspecialista = idEspecialista;
        this.cedula = cedula;
        this.usuario = usuario;
        this.contrasena = contrasena;
        
    }

    public int getIdEspecialista() {
        return idEspecialista;
    }

    public void setIdEspecialista(int idEspecialista) {
        this.idEspecialista = idEspecialista;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
