/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Connection;
import java.sql.DriverManager;


public class Conexion {
    //private final String base = "tienda";
    private final String user = "root";
    private final String password = "root";
    private final String url = "jdbc:mysql://localhost:3306/eyetecdb"; // despues de local host puede ir la url si esta en internet
    private Connection con = null;
    
    public Connection getConexion(){
        
        try{
            Class.forName("com.mysql.jdbc.Driver"); // Controlador para realzar la conexion
            
            con =(Connection) DriverManager.getConnection(this.url,this.user,this.password); // para realizar la conexion
            System.out.println("Conexion realizada con exito");
        }catch(Exception e){
            System.out.println("No se pudo Conectar");
        }
        
         return con;
    }
    
}
