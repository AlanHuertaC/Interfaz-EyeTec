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
public class Paciente extends Persona{
    private int idPaciente;
    private String email;
    
    public Paciente(){}

    public Paciente(int idPaciente, String nombre, String apellidoPaterno, String apellidoMaterno, String sexo, String fechaNacimiento, String email) {
        super(nombre,apellidoPaterno,apellidoMaterno,sexo,fechaNacimiento);
        this.idPaciente = idPaciente;
        this.email = email;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
