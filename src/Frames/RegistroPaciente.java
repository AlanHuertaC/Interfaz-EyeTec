/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import Clases.Conexion;
import Clases.Unity;
import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    public boolean flag = false;
    
    public RegistroPaciente(String idEspecialista) {
        this.idEspecialista = idEspecialista;
        initComponents();
        setTitle("Registro pacientes");
        setResizable(false);
        System.out.println("El id del especialista ingresado es: " + this.idEspecialista);
    }
    
    public void registroPaciente(){
        Connection con = null;
         
        Calendar cal = dateChooserNacimiento.getCalendar();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd"); 
        String date = sdf.format(cal.getTime());
        /*Guardar el registro del paciente*/
        try{
            con = cone.getConexion(); //trae la conexion
            ps = con.prepareStatement("Insert INTO Paciente (nombre,ap_paterno,ap_materno,sexo,fecha_nacimiento,email) VALUES(?,?,?,?,?,?)"); // para insertar valores a mi tabla
            ps.setString(1,textName.getText()); // (indice desde el cual va empezar osea la clave, guarda el texto que esta en el text Field)
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
        /**/
        /*Seleccion del paciente para obtener su id*/
        try{
            ps = con.prepareStatement("SELECT * FROM paciente where nombre = ? and ap_paterno = ? and ap_materno = ?");
            ps.setString(1, textName.getText());
            ps.setString(2, textApPaterno.getText());
            ps.setString(3,textApMaterno.getText());
            rs = ps.executeQuery(); // guarda el resutado de la consulta en res
            
            if(rs.next()){ // para verificar si trae los datos de la consulta
                System.out.println( rs.getString("idPaciente") + " " +rs.getString("nombre"));
                idPaciente = rs.getString("idPaciente");
                System.out.println("El id del paciente es: "+ idPaciente);
 
            }else{
                JOptionPane.showMessageDialog(null,"No se encuentran los datos");
            }
            ps.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectar Haciendo la consulta");
        }
        
        LimpiarCajas();
        
        /*Validar si hay datos en la tabla API*/
        /*try{
            ps = con.prepareStatement("SELECT * FROM api where id = ?");
            ps.setString(1,"1");
            rs = ps.executeQuery(); // guarda el resutado de la consulta en res
            
            if(!rs.next()){ // para verificar si trae los datos de la consulta
                ps = con.prepareStatement("Insert INTO api (id,idPaciente,idEspecialista) VALUES(?,?,?)"); // para insertar valores a mi tabla
                ps.setString(1, "1");
                ps.setString(2,"0");
                ps.setString(3,"0");
                
                int res = ps.executeUpdate(); // nos dara el resultado si se hizo bien el insert
                //System.out.println(res);
                if(res > 0){
                   System.out.println("Se agrego correctamente el registro api");
                    ps = con.prepareStatement("UPDATE api SET idPaciente=?, idEspecialista=? WHERE id=? "); // para insertar valores a mi tabla
                    ps.setString(1,idPaciente);
                    ps.setString(2, idEspecialista);
                    ps.setString(3,"1");

                    res = ps.executeUpdate(); // nos dara el resultado si se hizo bien 
                    if(res > 0){
                        System.out.println("Se modifico correctamente los datos de la API");

                    }else{
                        JOptionPane.showMessageDialog(null,"No se pudieron modifiar los datos de la API");
                    } 
                   
                }else{
                    JOptionPane.showMessageDialog(null,"No se pudieron agregar los datos api");
            } 
            }else{
                ps = con.prepareStatement("UPDATE api SET idPaciente=?, idEspecialista=? WHERE id=? "); // para insertar valores a mi tabla
                ps.setString(1,idPaciente);
                ps.setString(2, idEspecialista);
                ps.setString(3,"1");

                int res = ps.executeUpdate(); // nos dara el resultado si se hizo bien 
                if(res > 0){
                    System.out.println("Se modifico correctamente los datos de la API");

                }else{
                    JOptionPane.showMessageDialog(null,"No se pudieron modifiar los datos de la API");
                } 
            }
            ps.close();
            con.close(); // cerrar la conexion
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectar Haciendo la consulta");
        }*/        
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
        textName = new javax.swing.JTextField();
        textApPaterno = new javax.swing.JTextField();
        textEmail = new javax.swing.JTextField();
        textApMaterno = new javax.swing.JTextField();
        comboSexo = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
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

        textName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textNameFocusLost(evt);
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

        jButton2.setText("Regresar");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
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
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jButton2)
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
        else if(textName.getText().isEmpty() || textApPaterno.getText().isEmpty() || textApMaterno.getText().isEmpty()
            || textEmail.getText().isEmpty() || dateChooserNacimiento.getCalendar() == null){
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        }else{
        registroPaciente();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        BuscarPaciente buscarPaciente = new BuscarPaciente();
        buscarPaciente.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void textNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textNameFocusLost
        if(textName.getText().isEmpty() && flag == false){
            flag = true;
            JOptionPane.showMessageDialog(null,"Dejó vacio el campo Nombre");
            flag = false;
        }
        else if(!textName.getText().matches("[A-Za-zÀ-ÿ\u00f1\u00d1 ]+") && flag == false){
            flag = true;
            JOptionPane.showMessageDialog(null,"Debe ingresar solo letras en el campo Nombre");
            textName.setText("");            
            flag = false;
        }
    }//GEN-LAST:event_textNameFocusLost

    private void textApPaternoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textApPaternoFocusLost
        if(textApPaterno.getText().isEmpty() && flag == false){
            flag = true;
            JOptionPane.showMessageDialog(null,"Dejó vacio el campo Apellido paterno");
            flag = false;
        }else
        if(!textApPaterno.getText().matches("[A-Za-zÀ-ÿ\u00f1\u00d1 ]+") && flag == false){
          flag = true;
          JOptionPane.showMessageDialog(null,"Debe ingresar solo letras en el campo Apellido paterno");
          textApPaterno.setText("");            
          flag = false;
        }
    }//GEN-LAST:event_textApPaternoFocusLost

    private void textApMaternoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textApMaternoFocusLost
        if(textApMaterno.getText().isEmpty() && flag == false){
            flag = true;
            JOptionPane.showMessageDialog(null,"Dejó vacio el campo Apellido materno");
            flag = false;
        }else
        if(!textApMaterno.getText().matches("[A-Za-zÀ-ÿ\u00f1\u00d1 ]+") && flag == false){
          flag = true;
          JOptionPane.showMessageDialog(null,"Debe ingresar solo letras en el campo Apellido materno");
          textApMaterno.setText("");            
          flag = false;
        }
    }//GEN-LAST:event_textApMaternoFocusLost

    private void textEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textEmailFocusLost
        if(textEmail.getText().isEmpty() && flag == false){
            flag = true;
            JOptionPane.showMessageDialog(null,"Dejó vacio el campo Email");
            flag = false;
        }else
        if(!textEmail.getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") && flag == false){
          flag = true;
          JOptionPane.showMessageDialog(null,"Debe ingresar el formato correcto en el campo Email");
          textEmail.setText("");            
          flag = false;
        }
        
    }//GEN-LAST:event_textEmailFocusLost

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        flag = true;
    }//GEN-LAST:event_jButton2MouseEntered
    
    public void LimpiarCajas(){
        textName.setText("");
        textApPaterno.setText("");
        textApMaterno.setText("");
        comboSexo.setSelectedIndex(0);
        textEmail.setText("");
        dateChooserNacimiento.setCalendar(null);
    }
            
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
                new RegistroPaciente("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> comboSexo;
    private com.toedter.calendar.JDateChooser dateChooserNacimiento;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JTextField textName;
    // End of variables declaration//GEN-END:variables
}
