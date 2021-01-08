/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import Clases.Conexion;
import Clases.Mobizen;
import Clases.RunApps;
import Clases.Unity;
import static Frames.BuscarPaciente.datosPaciente;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

/**
 *
 * @author Alan Huerta Cortes
 */
public class Paciente extends javax.swing.JFrame {

    PreparedStatement ps;
    ResultSet rs;
    Conexion cone = new Conexion();
    /*Datos Prediagnostico*/
    public static ArrayList<String> tipoStrabismo;
    public static ArrayList<String> nombreEspecialista;
    public static ArrayList<String> paternoEspecialista;
    public static ArrayList<String> maternoEspecialista;
    public static ArrayList<String> desviacionIzq;
    public static ArrayList<String> desviacionDer;
    public static ArrayList<String> dioptrias;
    public static ArrayList<String> fecha;
    
    /*Datos Tratamiento*/
    public static ArrayList<String> nombreEspecialistaT;
    public static ArrayList<String> paternoEspecialistaT;
    public static ArrayList<String> maternoEspecialistaT;
    public static ArrayList<String> tipoTratamiento;
    public static ArrayList<String> puntuacion;
    public static ArrayList<String> duracionTotal;
    public static ArrayList<String> fechaT;
    
    /*Nombre de la terapia*/
    //public static String nombreTerapia;
    
    public Paciente() {
        initComponents();
        textNombre.setText(BuscarPaciente.datosPaciente[1] + " " + BuscarPaciente.datosPaciente[2] + " " + BuscarPaciente.datosPaciente[3]);
        btnTerapia.setVisible(false);
        btnDetails.setVisible(false);
        comboTerapia.setVisible(false);
        
        textSeleccion.setVisible(false);
        spinnerMinutos.setVisible(false);
        
        inicio();
        comboTerapia.getModel();
    }
    
    public void inicio(){
        Connection con = null;
        con = cone.getConexion(); //trae la conexion
        try{
            ps = con.prepareStatement("SELECT * FROM diagnostico where Paciente_idPaciente = ?");
            ps.setString(1, BuscarPaciente.datosPaciente[0]);
  
            rs = ps.executeQuery(); // guarda el resutado de la consulta en res
            
            if(rs.next()){ // para verificar si trae los datos de la consulta
              textIsDiagnostic.setText("Este paciente ya se ha realizado un prediagnóstico");
              btnTerapia.setVisible(true);
              btnDetails.setVisible(true);
              comboTerapia.setVisible(true);
 
            }else{
              textIsDiagnostic.setText("Este paciente no se ha realizado un prediagnóstico");
              textIsDiagnostic.setForeground(Color.red);
            }
            ps.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectar Haciendo la consulta");
        }
    }
    
    public void insertTiempoApi(int tiempo){
        Connection con = null;
        con = cone.getConexion(); //trae la conexion
        try{
            ps = con.prepareStatement("UPDATE api SET tiempo=? WHERE id=? "); // para insertar valores a mi tabla
            ps.setInt(1, tiempo);
            ps.setString(2,"1");

            int res = ps.executeUpdate(); // nos dara el resultado si se hizo bien 
            if(res > 0){
                System.out.println("Se modifico correctamente los datos de la API Tiempo");

            }else{
                JOptionPane.showMessageDialog(null,"No se pudieron modifiar los datos de la API Tiempo");
            } 
        }catch(Exception e){
            
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textIsDiagnostic = new javax.swing.JLabel();
        btnDiagnostico = new javax.swing.JButton();
        btnTerapia = new javax.swing.JButton();
        textNombre = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        btnDetails = new javax.swing.JButton();
        comboTerapia = new javax.swing.JComboBox<>();
        spinnerMinutos = new javax.swing.JSpinner();
        textSeleccion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Paciente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 51, 255))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Paciente");

        textIsDiagnostic.setText("Este paciente no se ha realizado un prediagnóstico");

        btnDiagnostico.setText("Realizar Prediganóstico");
        btnDiagnostico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiagnosticoActionPerformed(evt);
            }
        });

        btnTerapia.setText("Iniciar Terapia visual");
        btnTerapia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerapiaActionPerformed(evt);
            }
        });

        textNombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        textNombre.setText("Nombre");

        btnBack.setText("Volver");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnDetails.setText("Detalles");
        btnDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailsActionPerformed(evt);
            }
        });

        comboTerapia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione la terapia", "Calentamiento", "Manejo de contraste", "Oclución de objetos", "Relajación" }));
        comboTerapia.setBorder(null);
        comboTerapia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboTerapiaItemStateChanged(evt);
            }
        });

        textSeleccion.setText("Seleccione los minutos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(textIsDiagnostic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addComponent(btnDetails))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textNombre))
                            .addComponent(btnDiagnostico))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBack, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(textSeleccion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spinnerMinutos, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnTerapia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboTerapia, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBack)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(textNombre))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDetails)
                    .addComponent(textIsDiagnostic))
                .addGap(18, 18, 18)
                .addComponent(comboTerapia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerMinutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textSeleccion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDiagnostico)
                    .addComponent(btnTerapia))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDiagnosticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiagnosticoActionPerformed
        Connection con = null;
        Unity unity = new Unity();
        try{
            con = cone.getConexion(); 
            /*Validar si ya se hizo un prediagnostico*/
            ps = con.prepareStatement("SELECT * FROM diagnostico where Paciente_idPaciente = ?");
            ps.setString(1, BuscarPaciente.datosPaciente[0]);
  
            rs = ps.executeQuery(); // guarda el resutado de la consulta en res
            
            if(rs.next()){ // para verificar si trae los datos de la consulta
            int confirmacion = JOptionPane.showConfirmDialog(null, "Este paciente ya se ha realizado un prediagnostico, ¿Desea realizar otro?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            
                if(confirmacion==0){
                    /*Abrir Unity*/
                    unity.OpenUnity();
                }            
            }else{
                /*Abrir Unity*/
                unity.OpenUnity();
            }
            ps.close();               
            con.close(); // cerrar la conexion
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectar Haciendo la consulta");
        }        
       
        //dispose();
    }//GEN-LAST:event_btnDiagnosticoActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        BuscarPaciente buscarPaciente = new BuscarPaciente();
        buscarPaciente.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnTerapiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerapiaActionPerformed
        Border redline = BorderFactory.createLineBorder(Color.red);
        Border whiteline = BorderFactory.createLineBorder(Color.white);
        
        boolean flag = false;
        
        if(comboTerapia.getSelectedIndex() == 1){
            flag = true;
            
            if(flag && !spinnerMinutos.getValue().toString().equalsIgnoreCase("0")){
                insertTiempoApi((int) spinnerMinutos.getValue()); // Insertar el tiempo en la BD
                RunApps runapps = new RunApps();
                runapps.OpenCalentamiento();
                Mobizen mobizen = new Mobizen();
                mobizen.OpenMobizen();
                comboTerapia.setBorder(whiteline);
            }else{
                JOptionPane.showMessageDialog(null, "Debe seleccionar un tiempo mayor a 0 para iniciar la terapia");
            }           
        }else if(comboTerapia.getSelectedIndex() == 2){
            RunApps runapps = new RunApps();
            runapps.OpenCatVenge();
            Mobizen mobizen = new Mobizen();
            comboTerapia.setBorder(whiteline);
            mobizen.OpenMobizen();
            flag = false;
            
            
        }else if(comboTerapia.getSelectedIndex() == 3){
            RunApps runapps = new RunApps();
            runapps.OpenSpaceHero();
            Mobizen mobizen = new Mobizen();
            mobizen.OpenMobizen();
            comboTerapia.setBorder(whiteline);
            flag = false;
            
            
        }else if(comboTerapia.getSelectedIndex() == 4){
            flag = true;
            
            if(flag && !spinnerMinutos.getValue().toString().equalsIgnoreCase("0")){
                insertTiempoApi((int) spinnerMinutos.getValue());// Insertar el tiempo en la BD
                RunApps runapps = new RunApps();
                runapps.OpenRelajacion();
                Mobizen mobizen = new Mobizen();
                mobizen.OpenMobizen();
                comboTerapia.setBorder(whiteline);
            }else{
                JOptionPane.showMessageDialog(null, "Debe seleccionar un tiempo mayor a 0 para iniciar la terapia");
            }        
      
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar una terapia");
            comboTerapia.setBorder(redline);
        }
        //nombreTerapia = comboTerapia.getSelectedItem().toString();
        //System.out.println("Se ha seleccionado: " + nombreTerapia);
       //dispose();
    }//GEN-LAST:event_btnTerapiaActionPerformed

    private void btnDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailsActionPerformed
        Connection con = null;
        con = cone.getConexion(); //trae la conexion
        /*Datos del prediagnostico*/
        tipoStrabismo = new ArrayList<String>();
        nombreEspecialista = new ArrayList<String>();
        paternoEspecialista = new ArrayList<String>();
        maternoEspecialista = new ArrayList<String>();
        desviacionIzq = new ArrayList<String>();
        desviacionDer = new ArrayList<String>();
        dioptrias = new ArrayList<String>();
        fecha = new ArrayList<String>();
        
        boolean flag = false;
        
        try{
            ps = con.prepareStatement("SELECT DISTINCT tipo_estrabismo, nombre, ap_paterno, ap_materno FROM Diagnostico d, Especialista e WHERE d.Paciente_idPaciente =? and d.Especialista_idEspecialista = e.idEspecialista");
            ps.setString(1, BuscarPaciente.datosPaciente[0]);            
  
            rs = ps.executeQuery(); // guarda el resutado de la consulta en res
            
            while(rs.next()){ // para verificar si trae los datos de la consulta
                tipoStrabismo.add(rs.getString("tipo_estrabismo"));     
                nombreEspecialista.add(rs.getString("nombre"));
                paternoEspecialista.add(rs.getString("ap_paterno"));
                maternoEspecialista.add(rs.getString("ap_materno"));
                
            }
            ps = con.prepareStatement("SELECT DISTINCT o.desviacion_der, o.desviacion_izq, o.dioptrias_prismaticas FROM ojo o WHERE o.Paciente_idPaciente =?  ");
            ps.setString(1, BuscarPaciente.datosPaciente[0]); 
            rs = ps.executeQuery();
            while(rs.next()){
                desviacionIzq.add(rs.getString("desviacion_izq"));
                desviacionDer.add(rs.getString("desviacion_der"));
                dioptrias.add(rs.getString("dioptrias_prismaticas"));
            }
            ps = con.prepareStatement("SELECT DISTINCT pre.fecha FROM prediagnostico pre WHERE pre.Paciente_idPaciente = ?");
            ps.setString(1, BuscarPaciente.datosPaciente[0]); 
            rs = ps.executeQuery();
            while(rs.next()){
                fecha.add(rs.getString("fecha"));                
            }           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectar Haciendo la consulta");
            System.err.println(e);
        }
        
        /*Datos del tratamiento*/
        
        tipoTratamiento = new ArrayList<String>();
        puntuacion = new ArrayList<String>();
        duracionTotal = new ArrayList<String>();
        fechaT = new ArrayList<String>();
        nombreEspecialistaT = new ArrayList<String>();
        paternoEspecialistaT = new ArrayList<String>();
        maternoEspecialistaT = new ArrayList<String>();
        try{
            ps = con.prepareStatement("SELECT DISTINCT TipoDeTratamiento, puntuacion, duraciontotal, fecha, nombre, ap_paterno, ap_materno FROM Tratamiento t, Especialista e WHERE t.Paciente_idPaciente =? and t.Especialista_idEspecialista = e.idEspecialista");
            ps.setString(1, BuscarPaciente.datosPaciente[0]);            

            rs = ps.executeQuery(); // guarda el resutado de la consulta en res

            while(rs.next()){ // para verificar si trae los datos de la consulta
                tipoTratamiento.add(rs.getString("TipoDeTratamiento"));     
                puntuacion.add(rs.getString("puntuacion"));
                duracionTotal.add(rs.getString("duraciontotal"));
                fechaT.add(rs.getString("fecha"));
                nombreEspecialistaT.add(rs.getString("nombre"));
                paternoEspecialistaT.add(rs.getString("ap_paterno"));
                maternoEspecialistaT.add(rs.getString("ap_materno"));
            } 
            flag = true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectar Haciendo la consulta");
            System.err.println("Erro en tratamiento detalles" + e);
        }
        if(flag){
            Detalles detalles = new Detalles();
            detalles.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_btnDetailsActionPerformed

    private void comboTerapiaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboTerapiaItemStateChanged
        Border redline = BorderFactory.createLineBorder(Color.red);
        Border whiteline = BorderFactory.createLineBorder(Color.white);
        if(comboTerapia.getSelectedIndex() == 1){
            //flag = true;
            textSeleccion.setVisible(true);
            spinnerMinutos.setVisible(true);
            comboTerapia.setBorder(whiteline);
              
        }else if(comboTerapia.getSelectedIndex() == 2){
            
            textSeleccion.setVisible(false);
            spinnerMinutos.setVisible(false);
            comboTerapia.setBorder(whiteline);
            
        }else if(comboTerapia.getSelectedIndex() == 3){
            
            textSeleccion.setVisible(false);
            spinnerMinutos.setVisible(false);
            comboTerapia.setBorder(whiteline);
            
        }else if(comboTerapia.getSelectedIndex() == 4){
           
            textSeleccion.setVisible(true);
            spinnerMinutos.setVisible(true);
            comboTerapia.setBorder(whiteline);
      
        }else{
            //JOptionPane.showMessageDialog(null, "Debe seleccionar una terapia");
            comboTerapia.setBorder(redline);
        }
    }//GEN-LAST:event_comboTerapiaItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Paciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Paciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Paciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Paciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Paciente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDetails;
    private javax.swing.JButton btnDiagnostico;
    private javax.swing.JButton btnTerapia;
    private javax.swing.JComboBox<String> comboTerapia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner spinnerMinutos;
    private javax.swing.JLabel textIsDiagnostic;
    private javax.swing.JLabel textNombre;
    private javax.swing.JLabel textSeleccion;
    // End of variables declaration//GEN-END:variables
}
