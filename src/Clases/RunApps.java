/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Alan Huerta Cortes
 */
public class RunApps {
    public void OpenCatVenge(){
        Runtime runTime = Runtime.getRuntime();
        String executablePath = "C:\\Users\\Alan Huerta Cortés\\OneDrive\\Escritorio\\TTAlan\\TT2\\EjecutaCatvenge.bat";
        //String executablePath = System.getProperty("user.dir") + "/EjecutaCatvenge.bat";
        try {
            Process process = runTime.exec(executablePath);
        } catch (IOException ex) { }
    }
    public void OpenSpaceHero(){
        Runtime runTime = Runtime.getRuntime();
        String executablePath = "C:\\Users\\Alan Huerta Cortés\\OneDrive\\Escritorio\\TTAlan\\TT2\\EjecutaSpaceHero.bat";
        //String executablePath = System.getProperty("user.dir") + "/EjecutaSpaceHero.bat";
        try {
            Process process = runTime.exec(executablePath);
        } catch (IOException ex) { }
    }
    public void OpenCalentamiento(){
        Runtime runTime = Runtime.getRuntime();
        String executablePath = "C:\\Users\\Alan Huerta Cortés\\OneDrive\\Escritorio\\TTAlan\\TT2\\EjecutaCalentamiento.bat";
        //String executablePath = System.getProperty("user.dir") + "/EjecutaCalentamiento.bat";
        try {
            Process process = runTime.exec(executablePath);
        } catch (IOException ex) { }
    }
    
    public void OpenRelajacion(){
        Runtime runTime = Runtime.getRuntime();
        String executablePath = "C:\\Users\\Alan Huerta Cortés\\OneDrive\\Escritorio\\TTAlan\\TT2\\EjecutaRelajacion.bat";
        //String executablePath = System.getProperty("user.dir") + "/EjecutaRelajacion.bat";
        try {
            Process process = runTime.exec(executablePath);
        } catch (IOException ex) { }
    }
}
