/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Frames.Principal;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alan Huerta Cortes
 */
public class Mobizen {
     public void OpenMobizen(){
        Runtime runTime = Runtime.getRuntime();
        //String executablePath = "src//Ejecutable//EyesMovement.exe";
        //String executablePath = "C:\\Users\\Alan Huerta Cortés\\OneDrive\\Escritorio\\TTAlan\\TT2\\Interfaz\\EyeTec\\src\\Ejecutable\\EyesMovement.exe";
        String executablePath = "D:\\Mobizen\\Mobizen\\Mobizen.exe";
        try {
            Process process = runTime.exec(executablePath);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        //this.dispose();
    }
}
