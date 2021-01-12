/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validaciones;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author Alan Huerta Cortes
 */
public class Validaciones {
    Border redline;
    Border grayline;
    public Validaciones() {
        redline = BorderFactory.createLineBorder(Color.red);
        grayline = BorderFactory.createLineBorder(Color.gray);
        
    }
    
    public void validarCampoSoloLetras(JTextField campo, String nombreCampo){
        
        if(campo.getText().isEmpty()){
            campo.setBorder(redline);
        }
        else if(!campo.getText().matches("[A-Za-zÀ-ÿ\u00f1\u00d1 ]+")){
            JOptionPane.showMessageDialog(null,"Debe ingresar sólo letras en el campo " + nombreCampo);
            campo.setText("");            
        }else{
            campo.setBorder(new JTextField().getBorder()); 
        } 
    }
    
    public void validarCampoSoloNumeros(JTextField campo, String nombreCampo){
        if(campo.getText().isEmpty()){
            campo.setBorder(redline);
        }
        else if(!campo.getText().matches("[0-9]+")){
            JOptionPane.showMessageDialog(null,"Debe ingresar sólo números en el campo " + nombreCampo);
            campo.setText("");            
        }else{
            campo.setBorder(new JTextField().getBorder()); 
        }
    }
    
    public void validarCampoEmail(JTextField campo){
        if(campo.getText().isEmpty()){
            campo.setBorder(redline);
        }else
        if(!campo.getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
          JOptionPane.showMessageDialog(null,"Debe ingresar el formato correcto en el campo Email");
          campo.setText("");            
        }else{
            campo.setBorder(new JTextField().getBorder());
        }
    }
    
    public void validarCampoAlfanumerico(JTextField campo, String nombreCampo){
        if(campo.getText().isEmpty()){            
            campo.setBorder(redline);
        }
        else
        if(!campo.getText().matches("[A-Za-z0-9À-ÿ\u00f1\u00d1 ]+")){
            JOptionPane.showMessageDialog(null,"Debe ingresar sólo números o letras en el campo " + nombreCampo);
            campo.setText("");            
        }else{
            campo.setBorder(new JTextField().getBorder());
        }
    }
    
    public void validarCampoContrasena(JTextField campo){
        if(campo.getText().isEmpty()){
            campo.setBorder(redline);
        }
        else
        if (!campo.getText().matches("^[a-zA-Z0-9À-ÿ\u00f1\u00d1]+$")){   
            JOptionPane.showMessageDialog(null,"Debe ingresar letras y números sin caracteres especiales o espacios");
            campo.setText("");
        }else{
            campo.setBorder(new JTextField().getBorder());
        }
    }
    
    public void validarSimilitudContrasenas(JTextField campo1, JTextField campo2){
        if(!campo1.getText().equals(campo2.getText())){           
            JOptionPane.showMessageDialog(null, "La contraseña no coincide, favor de verificarla");
            campo1.setText("");
            campo2.setText(""); 
            campo1.setBorder(redline);
            campo2.setBorder(redline);
        }else{
            campo1.setBorder(new JTextField().getBorder());
            campo2.setBorder(new JTextField().getBorder());
        }
    }
}
