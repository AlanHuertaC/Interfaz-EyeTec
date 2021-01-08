/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import Clases.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Alan Huerta Cortes
 */
public class RegistroEspecialista extends javax.swing.JFrame {

    PreparedStatement ps;
    ResultSet rs;
    Conexion cone = new Conexion();
    boolean flag = false;
    public RegistroEspecialista() {
        initComponents();
    }
    
    private void registrarPaciente(){
        Connection con = null;
        try{
            con = cone.getConexion(); //trae la conexion
            ps = con.prepareStatement("Insert INTO Especialista (nombre,ap_paterno,ap_materno,cedula,sexo,edad,usuario,contraseña) VALUES(?,?,?,?,?,?,?,?)"); // para insertar valores a mi tabla
            ps.setString(1,textNombre.getText()); // (indice desde el cual va empezar osea la clave, guarda el texto que esta en el text Field)
            ps.setString(2,textApPaterno.getText());
            ps.setString(3, textApMaterno.getText());
            ps.setString(4, textCedula.getText());
            ps.setString(5, comboSexo.getSelectedItem().toString());            
            ps.setString(6, spinnerEdad.getValue().toString());
            ps.setString(7, textUsuario.getText());
            ps.setString(8, textConfirmPassword.getText());
            
            int res = ps.executeUpdate(); // nos dara el resultado si se hizo bien el insert
            
            if(res > 0){
               System.out.println("Se agrego correctamente el registro Especialista");
               JOptionPane.showMessageDialog(null, "Se agrego correctamente su registro");
               Login login = new Login();
               login.setVisible(true);
               dispose();
            }else{
                JOptionPane.showMessageDialog(null,"No se pudieron agregar los datos del especialista");
            } 
            ps.close();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"No se pudo conectar" + e.toString());
        }
    }
    
    private void validarCamposVacios(){
        if(comboSexo.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Debe seleccionar su sexo");
        }else
        if(textNombre.getText().isEmpty() || textApPaterno.getText().isEmpty() 
           || textApMaterno.getText().isEmpty() || textCedula.getText().isEmpty()
           || textPassword.getText().isEmpty() || textConfirmPassword.getText().isEmpty() ){
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        }else{
            //JOptionPane.showMessageDialog(null, "Se ha registrado de manera exitosa");
            registrarPaciente();
        }
        /*int valorSpinner = (int) spinnerEdad.getValue();
        if(valorSpinner <1){
            JOptionPane.showMessageDialog(null, "No puede tener" + valorSpinner +"esa edad");
        }*/
    }
    
    private void volverLogin(){
        Login login = new Login();
        login.setVisible(true);
        dispose();
    }
    
    private void validarCampoNombre(){
        if(textNombre.getText().isEmpty() && flag == false){
            flag = true;
            JOptionPane.showMessageDialog(null,"Dejó vacio el campo Nombre");
            flag = false;
        }
        else
        if(!textNombre.getText().matches("[A-Za-zÀ-ÿ\u00f1\u00d1 ]+") && flag == false){
            flag = true;
            JOptionPane.showMessageDialog(null,"Debe ingresar solo letras en el campo Nombre");
            textNombre.setText("");            
            flag = false;
        }
    }
    
    private void validarCampoApellidoPaterno(){
        if(textApPaterno.getText().isEmpty() && flag == false){
            flag = true;
            JOptionPane.showMessageDialog(null,"Dejó vacio el campo Apellido paterno");
            flag = false;
        }
        else
        if(!textApPaterno.getText().matches("[A-Za-zÀ-ÿ\u00f1\u00d1 ]+") && flag == false){
            flag = true;
            JOptionPane.showMessageDialog(null,"Debe ingresar solo letras en el campo Apellido paterno");
            textApPaterno.setText("");
            flag = false;
        }
    }
    
    private  void validarCampoApellidoMaterno(){
        if(textApMaterno.getText().isEmpty() && flag == false){
            flag = true;
            JOptionPane.showMessageDialog(null,"Dejó vacio el campo Apellido materno");
            flag = false;
        }
        else
        if(!textApMaterno.getText().matches("[A-Za-zÀ-ÿ\u00f1\u00d1 ]+") && flag == false){
            flag = true;
            JOptionPane.showMessageDialog(null,"Debe ingresar solo letras en el campo Apellido materno");
            textApMaterno.setText("");
            flag = false;
        }
    }
    
    private void validarCampoUsuario(){
        if(textUsuario.getText().isEmpty() && flag == false){
            flag = true;
            JOptionPane.showMessageDialog(null,"Dejó vacio el campo Usuario");
            flag = false;
        }
        else
        if(!textUsuario.getText().matches("[A-Za-z0-9À-ÿ\u00f1\u00d1 ]+") && flag == false){
            flag = true;
            JOptionPane.showMessageDialog(null,"Debe ingresar solo letras en el campo Usuario");
            textUsuario.setText("");            
            flag = false;
        }
    }
    
    private void validarCampoContrasena(){
        if(textPassword.getText().isEmpty() && flag == false){
            flag = true;
            JOptionPane.showMessageDialog(null,"Dejó vacio el campo Contraseña");
            flag = false;
        }
        else
        if (!textPassword.getText().matches("^[a-zA-Z0-9À-ÿ\u00f1\u00d1]+$") && flag == false){   
            flag = true;
            JOptionPane.showMessageDialog(null,"Debe ingresar letras y numeros sin caracteres especiales o espacios");
            textPassword.setText("");
            flag = false;
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        textNombre = new javax.swing.JTextField();
        textApPaterno = new javax.swing.JTextField();
        textApMaterno = new javax.swing.JTextField();
        comboSexo = new javax.swing.JComboBox<>();
        spinnerEdad = new javax.swing.JSpinner();
        btnVolver = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        textUsuario = new javax.swing.JTextField();
        textPassword = new javax.swing.JPasswordField();
        textConfirmPassword = new javax.swing.JPasswordField();
        textCedula = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro Especialista", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 51, 255))); // NOI18N

        jLabel1.setText("Nombre:");

        jLabel2.setText("Apellido paterno:");

        jLabel3.setText("Apellido materno:");

        jLabel4.setText("Cédula:");

        jLabel5.setText("Sexo:");

        jLabel6.setText("Edad:");

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

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
        textApPaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textApPaternoActionPerformed(evt);
            }
        });

        textApMaterno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textApMaternoFocusLost(evt);
            }
        });

        comboSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar sexo", "masculino", "femenino" }));

        spinnerEdad.setModel(new javax.swing.SpinnerNumberModel(1, 1, 99, 1));

        btnVolver.setText("Volver");
        btnVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVolverMouseEntered(evt);
            }
        });
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        jLabel8.setText("Usuario:");

        jLabel9.setText("Contraseña:");

        jLabel10.setText("Confirmar:");

        textUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textUsuarioFocusLost(evt);
            }
        });

        textPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textPasswordFocusLost(evt);
            }
        });

        textConfirmPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textConfirmPasswordFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel1))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textApPaterno)
                                    .addComponent(textNombre)
                                    .addComponent(textApMaterno)
                                    .addComponent(textCedula)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(spinnerEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(textUsuario)
                                    .addComponent(textPassword)
                                    .addComponent(textConfirmPassword)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 28, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegistrar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textApPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textApMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(textCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(comboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(spinnerEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(textUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(textPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(textConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolver)
                    .addComponent(btnRegistrar))
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

    private void textApPaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textApPaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textApPaternoActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        validarCamposVacios();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        volverLogin();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void textNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textNombreFocusLost
        validarCampoNombre();
    }//GEN-LAST:event_textNombreFocusLost

    private void textApPaternoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textApPaternoFocusLost
        validarCampoApellidoPaterno();
    }//GEN-LAST:event_textApPaternoFocusLost

    private void textApMaternoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textApMaternoFocusLost
        validarCampoApellidoMaterno();
    }//GEN-LAST:event_textApMaternoFocusLost

    private void btnVolverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVolverMouseEntered
        flag = true;
    }//GEN-LAST:event_btnVolverMouseEntered

    private void textUsuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textUsuarioFocusLost
        validarCampoUsuario();
    }//GEN-LAST:event_textUsuarioFocusLost

    private void textPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textPasswordFocusLost
        validarCampoContrasena();
    }//GEN-LAST:event_textPasswordFocusLost

    private void textConfirmPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textConfirmPasswordFocusLost
        if(!textPassword.getText().equals(textConfirmPassword.getText()) && flag == false){
            flag = true;
            JOptionPane.showMessageDialog(null, "La contraseña no coincide, favor de verificarla");
            textPassword.setText("");
            textConfirmPassword.setText("");
            flag = false;
        }
    }//GEN-LAST:event_textConfirmPasswordFocusLost

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
            java.util.logging.Logger.getLogger(RegistroEspecialista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroEspecialista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroEspecialista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroEspecialista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroEspecialista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> comboSexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner spinnerEdad;
    private javax.swing.JTextField textApMaterno;
    private javax.swing.JTextField textApPaterno;
    private javax.swing.JTextField textCedula;
    private javax.swing.JPasswordField textConfirmPassword;
    private javax.swing.JTextField textNombre;
    private javax.swing.JPasswordField textPassword;
    private javax.swing.JTextField textUsuario;
    // End of variables declaration//GEN-END:variables
}
