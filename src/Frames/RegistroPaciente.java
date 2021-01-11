/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import Clases.Conexion;
import DAO.Especialista;
import Validaciones.Validaciones;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Alan Huerta Cortes
 */
public class RegistroPaciente extends javax.swing.JFrame {

    PreparedStatement ps;
    ResultSet rs;
    Conexion cone = new Conexion();
    String idPaciente;
    String idEspecialista;
    Especialista especialista;
    DAO.Paciente paciente;
    Validaciones validaciones;
    String tipoConsulta;

    public RegistroPaciente(){
        initComponents();
        setTitle("Registro pacientes");
        setResizable(false);
    }

/*Constructor para modificar paciente*/    
    public RegistroPaciente(Especialista especialista,DAO.Paciente paciente, String tipoConsulta){
        this.especialista = especialista;
        this.paciente = paciente;
        initComponents();
        setTitle("Registro pacientes");
        setResizable(false);
        validaciones = new Validaciones();      
        btnGuardar.setText(tipoConsulta);     
        llenarModificacion();
    }
    
    public RegistroPaciente(Especialista especialista, String tipoConsulta) {
        this.especialista = especialista;
        this.idEspecialista = String.valueOf(especialista.getIdEspecialista());// idEspecialista;
        this.tipoConsulta = tipoConsulta;
        
        initComponents();
        setTitle("Registro pacientes");
        setResizable(false);   
        validaciones = new Validaciones();      
        btnGuardar.setText(tipoConsulta);
        
    }
    
    public boolean existePaciente(String nombre, String Paterno, String Materno){
        Connection con = null;
        boolean existe = false;
        /*Calendar cal = dateChooserNacimiento.getCalendar();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd"); 
        String date = sdf.format(cal.getTime());*/
        try{
            con = cone.getConexion(); 
            /*Validar si existe un especialista con el mismo nombre*/
            ps = con.prepareStatement("SELECT * FROM paciente where  nombre = ? AND ap_paterno =? AND ap_materno = ? ");
            //ps.setInt(1, this.paciente.getIdPaciente());
            ps.setString(1, nombre);
            ps.setString(2, Paterno);
            ps.setString(3, Materno);
            rs = ps.executeQuery(); // guarda el resutado de la consulta en res
            
            if(rs.next()){ // para verificar si trae los datos de la consulta
                existe = true;
                paciente = new DAO.Paciente(rs.getInt("idPaciente"), rs.getString("nombre"), rs.getString("ap_paterno"), rs.getString("ap_materno"), rs.getString("sexo"), rs.getString("fecha_nacimiento"), rs.getString("email"));
                //JOptionPane.showMessageDialog(null, "Este paciente ya se encuentra registrado"); 
            }else{
                existe = false;               
            }
            ps.close();               
            con.close(); // cerrar la conexion
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectar Haciendo la consulta" + e.getMessage());
        }      
        return existe;
    }
    
    private void registroPaciente(){
        Connection con = null;
         
        Calendar cal = dateChooserNacimiento.getCalendar();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd"); 
        String date = sdf.format(cal.getTime());
        /*Guardar el registro del paciente*/
        try{
            con = cone.getConexion(); //trae la conexion
            ps = con.prepareStatement("Insert INTO Paciente (nombre,ap_paterno,ap_materno,sexo,fecha_nacimiento,email) VALUES(?,?,?,?,?,?)"); // para insertar valores a mi tabla
            ps.setString(1,textNombre.getText()); // (indice desde el cual va empezar osea la clave, guarda el texto que esta en el text Field)
            ps.setString(2,textApPaterno.getText());
            ps.setString(3, textApMaterno.getText());
            ps.setString(4, comboSexo.getSelectedItem().toString());
            ps.setDate(5,Date.valueOf(date));
            ps.setString(6,textEmail.getText());
            
            int res = ps.executeUpdate(); // nos dara el resultado si se hizo bien el insert
            //System.out.println(res);
            if(res > 0){
               System.out.println("Se agrego correctamente el registro paciente");
            }else{
                JOptionPane.showMessageDialog(null,"No se pudieron agregar los datos");
                LimpiarCajas();
            } 
            ps.close();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"No se pudo conectar" + e.toString());
        }        
        LimpiarCajas();
    }
    
    private void llenarModificacion(){
        
    String fechaNacimiento = paciente.getFechaNacimiento();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd");

    java.util.Date date = new java.util.Date();
        try {
            date = formatter.parse(fechaNacimiento);
        } catch (ParseException ex) {
            Logger.getLogger(RegistroPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        existePaciente(paciente.getNombre(), paciente.getApellidoPaterno(), paciente.getApellidoMaterno());
        textNombre.setText(paciente.getNombre());
        textApPaterno.setText(paciente.getApellidoPaterno());
        textApMaterno.setText(paciente.getApellidoMaterno());
        textEmail.setText(paciente.getEmail());
        dateChooserNacimiento.setDate(date);
        comboSexo.setSelectedItem(paciente.getSexo());
        
    }
    
    private void actualizarPaciente(){
        
    }
    
    private void volverBusquedaPaciente(){
        BuscarPaciente buscarPaciente = new BuscarPaciente(especialista);
        buscarPaciente.setVisible(true);
        dispose();
    }
     
    private void LimpiarCajas(){
        textNombre.setText("");
        textApPaterno.setText("");
        textApMaterno.setText("");
        comboSexo.setSelectedIndex(0);
        textEmail.setText("");
        dateChooserNacimiento.setCalendar(null);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        textNombre = new javax.swing.JTextField();
        textApPaterno = new javax.swing.JTextField();
        textEmail = new javax.swing.JTextField();
        textApMaterno = new javax.swing.JTextField();
        comboSexo = new javax.swing.JComboBox<>();
        btnVolver = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        dateChooserNacimiento = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro paciente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 51, 204))); // NOI18N

        jLabel1.setText("Nombre:");

        jLabel2.setText("Apellido Paterno:");

        jLabel3.setText("Apellido Materno:");

        jLabel4.setText("Fecha de nacimiento:");

        jLabel5.setText("Correo:");

        jLabel6.setText("Sexo:");

        textNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textNombreFocusLost(evt);
            }
        });

        textApPaterno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textApPaternoFocusLost(evt);
            }
        });

        textEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textEmailFocusLost(evt);
            }
        });

        textApMaterno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textApMaternoFocusLost(evt);
            }
        });

        comboSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione su sexo", "masculino", "femenino" }));

        btnVolver.setText("Regresar");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar registro");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        dateChooserNacimiento.setDateFormatString("dd/M/yyyy");
        dateChooserNacimiento.setMaxSelectableDate(new java.util.Date(1577862084000L));
        dateChooserNacimiento.setMinSelectableDate(new java.util.Date(-1262278716000L));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textApMaterno))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textApPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(comboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 75, Short.MAX_VALUE))
                                    .addComponent(textEmail)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dateChooserNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel4)
                        .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dateChooserNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(textApPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(textApMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolver)
                    .addComponent(btnGuardar))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(comboSexo.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Debe seleccionar su sexo");
        }
        else if(textNombre.getText().isEmpty() || textApPaterno.getText().isEmpty() || textApMaterno.getText().isEmpty()
            || textEmail.getText().isEmpty() || dateChooserNacimiento.getCalendar() == null){
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        }else{           
            if(existePaciente(textNombre.getText(), textApPaterno.getText(), textApMaterno.getText())){
                JOptionPane.showMessageDialog(null, "Este paciente ya se encuentra registrado");
            }else{    
                if(tipoConsulta.equalsIgnoreCase("Guardar registro")){
                    registroPaciente();
                    JOptionPane.showMessageDialog(null, "Se ha registrado de manera exitosa");
                }else if(tipoConsulta.equalsIgnoreCase("Actualizar registro")){
                    actualizarPaciente();
                    JOptionPane.showMessageDialog(null, "Se actualizó el registro de manera exitosa");
                }               
            }                
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
       volverBusquedaPaciente();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void textNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textNombreFocusLost
       validaciones.validarCampoSoloLetras(textNombre, "Nombre");
    }//GEN-LAST:event_textNombreFocusLost

    private void textApPaternoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textApPaternoFocusLost
        validaciones.validarCampoSoloLetras(textApPaterno, "Apellido paterno");
    }//GEN-LAST:event_textApPaternoFocusLost

    private void textApMaternoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textApMaternoFocusLost
       validaciones.validarCampoSoloLetras(textApMaterno, "Apellido materno");
    }//GEN-LAST:event_textApMaternoFocusLost

    private void textEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textEmailFocusLost
        validaciones.validarCampoEmail(textEmail);
    }//GEN-LAST:event_textEmailFocusLost
           
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
            java.util.logging.Logger.getLogger(RegistroPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Especialista especialista = new Especialista();
                new RegistroPaciente(especialista,"").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> comboSexo;
    private com.toedter.calendar.JDateChooser dateChooserNacimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField textApMaterno;
    private javax.swing.JTextField textApPaterno;
    private javax.swing.JTextField textEmail;
    private javax.swing.JTextField textNombre;
    // End of variables declaration//GEN-END:variables
}
