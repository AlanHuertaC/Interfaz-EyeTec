/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import Clases.Conexion;
import DAO.Especialista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alan Huerta Cortes
 */
public class BuscarPaciente extends javax.swing.JFrame {

    PreparedStatement ps,ps2;
    ResultSet rs,rs2;
    Connection con = null;    
    Conexion cone = new Conexion();
    int Tamano = 0;
    String column[] = new String[Tamano];
    ResultSetMetaData rsMeta;
    int indiceFilaSeleccionada;
    
    Especialista especialista;
    DAO.Paciente paciente;
    
    public BuscarPaciente(Especialista especialista) {
        initComponents();
        con = cone.getConexion(); //trae la conexion
        this.especialista = especialista;
        textNombre.setText(this.especialista.getNombre() + " " + especialista.getApellidoPaterno() + " " + especialista.getApellidoMaterno());
        llenarTablaInicio();
        seleccionFilas();
    }
        
    public void llenarTablaInicio(){
        try{            
            Statement st = con.createStatement(); 
            String sql = "SELECT idPaciente as 'Nº Paciente', nombre as Nombre, ap_paterno as Paterno, ap_materno as Materno, Sexo, fecha_nacimiento as 'Fecha de nacimiento', email  FROM paciente ";
               
            rs = st.executeQuery(sql);
            
            rsMeta = rs.getMetaData();
            int columna = rsMeta.getColumnCount();
            Tamano = columna+3;
            DefaultTableModel modelo = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int i, int i1) { 
                return false; //To change body of generated methods, choose Tools | Templates. 
                } 
            };
            String col[] = new String[columna];
                      
            for (int i =1 ; i <= columna; i++) {
                modelo.addColumn(rsMeta.getColumnLabel(i));
            }
            modelo.addColumn("Prediagnostico");
            modelo.addColumn("Tratamiento");
            modelo.addColumn("Especialista");
            String filas[] = new String[columna+3];
            
            ps = con.prepareStatement("SELECT DISTINCT Paciente_idPaciente FROM Prediagnostico");
            // ps.setString(1, rs.getString(j + 1));
            rs2 = ps.executeQuery(); // guarda el resutado de la consulta en res
            
            while (rs.next()) {                
                for (int j = 0; j < columna; j++) {
                    filas[j] = rs.getString(j + 1);
                    /*col[j] = modelo.getColumnName(j);
                    column[j] = col[j];*/ 
                    /*Validar si ya se realizó un prediagnostico*/
                    if(j== 6){            
                        ps = con.prepareStatement("SELECT DISTINCT Paciente_idPaciente FROM Prediagnostico Where Paciente_idPaciente=?");
                        ps.setString(1, rs.getString(1));
                        rs2 = ps.executeQuery(); // guarda el resutado de la consulta en res
                        if(rs2.next()){
                            if(rs.getString(1).equals(rs2.getString(1))){
                                filas[7] = "Realizado";}
                            else{
                                filas[7] = "Sin realizar";
                            }
                        }
                        else{
                            filas[7] = "Sin realizar";
                        }
                        
                        /*Validar si se realizó una terapia*/
                        ps = con.prepareStatement("SELECT DISTINCT Paciente_idPaciente FROM Tratamiento Where Paciente_idPaciente=?");
                        ps.setString(1, rs.getString(1));
                        rs2 = ps.executeQuery(); // guarda el resutado de la consulta en res
                        if(rs2.next()){
                            if(rs.getString(1).equals(rs2.getString(1))){
                                filas[8] = "Realizado";}
                            else{
                                filas[8] = "Sin realizar";
                            }
                        }
                        else{
                            filas[8] = "Sin realizar";
                        }
                        
                        /*Validar que especialista lo atendio con anterioridad*/
                        ps = con.prepareStatement("SELECT DISTINCT pr.Paciente_idPaciente, es.nombre, es.ap_paterno  FROM prediagnostico pr, especialista es WHERE pr.Paciente_idPaciente=? AND pr.Especialista_idEspecialista=es.idEspecialista");
                        ps.setString(1, rs.getString(1));
                        rs2 = ps.executeQuery(); // guarda el resutado de la consulta en res
                        if(rs2.next()){
                            if(rs.getString(1).equals(rs2.getString(1))){
                                filas[9] = rs2.getString(2)+" "+rs2.getString(3);
                            }
                            else{
                                filas[9] = "Sin realizar";
                            }
                        }
                        else{
                            filas[9] = "Sin asignar";
                        }
                    }
                }
                modelo.addRow(filas);
            }          
            tablaPacientes.setModel(modelo);
            
        }catch(Exception e){
            
        }
    }
    
    public void seleccionFilas(){
        tablaPacientes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
               //Obtener el número de filas seleccionadas 
                int cuentaFilasSeleccionadas = tablaPacientes.getSelectedRowCount(); 
                //System.out.println("Hay seleccionadas: " + cuentaFilasSeleccionadas + " filas");
                
                if (cuentaFilasSeleccionadas == 1) { 
                    //Sólo hay una fila seleccionada 
                    indiceFilaSeleccionada = tablaPacientes.getSelectedRow(); 
                    //System.out.println("Fila seleccionada: " + indiceFilaSeleccionada);
                } else { 
                    //Hay varias filas seleccionadas 
                    int[] indicesfilasSeleccionadas = tablaPacientes.getSelectedRows(); 
                    //System.out.println("Filas seleccionadas: "); 
                    for (int indice : indicesfilasSeleccionadas) { 
                        //System.out.print(indice + " "); 
                    } 
                    //System.out.println(); 
                }
            }
        });
    }
    
    private void seleccionarPaciente(){
         Paciente paciente = new Paciente(this.especialista, this.paciente);
        paciente.setVisible(true);
        dispose();
        //System.out.println("El id es " + Login.idEspecialista);
        /*Modificar API*/
         /*Validar si hay datos en la tabla API*/
        try{ 
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
                    ps.setInt(1, this.paciente.getIdPaciente());
                    ps.setInt(2, especialista.getIdEspecialista());
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
                ps.setInt(1, this.paciente.getIdPaciente());
                ps.setInt(2, especialista.getIdEspecialista());
                ps.setString(3,"1");

                int res = ps.executeUpdate(); // nos dara el resultado si se hizo bien 
                if(res > 0){
                    System.out.println("Se modifico correctamente los datos de la API");

                }else{
                    JOptionPane.showMessageDialog(null,"No se pudieron modifiar los datos de la API");
                } 
            }
        }catch(Exception e){JOptionPane.showMessageDialog(null, "Error al modificar la API");}
    }
    
    private void registrarPaciente(){
        RegistroPaciente registroPaciente = new RegistroPaciente(especialista);
        registroPaciente.setVisible(true);
        dispose();
    }
    
    private void elminarPaciente(){
        int option = JOptionPane.showConfirmDialog(null, "¿Desea eliminar este registro de manera permanente?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if(option == 0){
            try{
                if(textSearch.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Debe seleccionar al paciente a eliminar");
                }else{
                    /*Eliminar paciente*/
                    //System.out.println(datosPaciente[1]+ " " + datosPaciente[2] + " " + datosPaciente[3] );
                    try{
                        ps = con.prepareStatement("DELETE FROM Paciente WHERE nombre=? AND ap_paterno=? AND ap_materno=?");
                        ps.setString(1, this.paciente.getNombre());
                        ps.setString(2, this.paciente.getApellidoPaterno());
                        ps.setString(3, this.paciente.getApellidoMaterno());
                        int res = ps.executeUpdate();

                        if(res > 0){
                            System.out.println("Se Eliminó correctamente el paciente");
                            textSearch.setText("");
                            llenarTablaInicio();
                        }else{
                            JOptionPane.showMessageDialog(null,"No se pudo eliminar el Paciente");
                        } 
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null, "Error al querer eliminar al paciente, favor de intentar mas tarde");
                    }
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Debe seleccionar al paciente a eliminar");
            }  
        }   
    }
    
    private void filtrarPaciente(){
        //System.out.println("Se esta dando click");
        try{            
            Statement st = con.createStatement(); 
            String sql = "SELECT idPaciente as 'Nº Paciente', nombre as Nombre, ap_paterno as Paterno, ap_materno as Materno, Sexo, fecha_nacimiento as 'Fecha de nacimiento', email FROM paciente WHERE nombre LIKE '%" + textSearch.getText() + "%'";
               
            rs = st.executeQuery(sql);
            
            rsMeta = rs.getMetaData();
            int columna = rsMeta.getColumnCount();
            Tamano = columna;
             DefaultTableModel modelo = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int i, int i1) { 
                return false; //To change body of generated methods, choose Tools | Templates. 
                } 
            };
            String col[] = new String[columna];
                      
            for (int i =1 ; i <= columna; i++) {
                modelo.addColumn(rsMeta.getColumnLabel(i));
            }
            modelo.addColumn("Prediagnostico");
            modelo.addColumn("Tratamiento");
            modelo.addColumn("Especialista");
            String filas[] = new String[columna+3];
            
            ps = con.prepareStatement("SELECT DISTINCT Paciente_idPaciente FROM Prediagnostico");
            // ps.setString(1, rs.getString(j + 1));
            rs2 = ps.executeQuery(); // guarda el resutado de la consulta en res
            
            while (rs.next()) {                
                for (int j = 0; j < columna; j++) {
                    filas[j] = rs.getString(j + 1);
                    /*col[j] = modelo.getColumnName(j);
                    column[j] = col[j];*/ 
                    /*Validar si ya se realizó un prediagnostico*/
                    if(j== 6){            
                        ps = con.prepareStatement("SELECT DISTINCT Paciente_idPaciente FROM Prediagnostico Where Paciente_idPaciente=?");
                        ps.setString(1, rs.getString(1));
                        rs2 = ps.executeQuery(); // guarda el resutado de la consulta en res
                        if(rs2.next()){
                            if(rs.getString(1).equals(rs2.getString(1))){
                                filas[7] = "Realizado";}
                            else{
                                filas[7] = "Sin realizar";
                            }
                        }
                        else{
                            filas[7] = "Sin realizar";
                        }
                        
                        /*Validar si se realizó una terapia*/
                        ps = con.prepareStatement("SELECT DISTINCT Paciente_idPaciente FROM Tratamiento Where Paciente_idPaciente=?");
                        ps.setString(1, rs.getString(1));
                        rs2 = ps.executeQuery(); // guarda el resutado de la consulta en res
                        if(rs2.next()){
                            if(rs.getString(1).equals(rs2.getString(1))){
                                filas[8] = "Realizado";}
                            else{
                                filas[8] = "Sin realizar";
                            }
                        }
                        else{
                            filas[8] = "Sin realizar";
                        }
                        
                        /*Validar que especialista lo atendio con anterioridad*/
                        ps = con.prepareStatement("SELECT DISTINCT pr.Paciente_idPaciente, es.nombre, es.ap_paterno  FROM prediagnostico pr, especialista es WHERE pr.Paciente_idPaciente=? AND pr.Especialista_idEspecialista=es.idEspecialista");
                        ps.setString(1, rs.getString(1));
                        rs2 = ps.executeQuery(); // guarda el resutado de la consulta en res
                        if(rs2.next()){
                            if(rs.getString(1).equals(rs2.getString(1))){
                                filas[9] = rs2.getString(2)+" "+rs2.getString(3);
                            }
                            else{
                                filas[9] = "Sin realizar";
                            }
                        }
                        else{
                            filas[9] = "Sin asignar";
                        }
                    }
                }
                modelo.addRow(filas);
            }          
            tablaPacientes.setModel(modelo);
            
        }catch(Exception e){
            
        }
    }   
    
    private void seleccionarPacienteTabla(){
        String datosPaciente[] = new String[Tamano];
        for(int i=0; i<Tamano; i++){
            datosPaciente[i] = tablaPacientes.getModel().getValueAt(indiceFilaSeleccionada, i).toString();
        }
        textSearch.setText(datosPaciente[1]+ " " + datosPaciente[2] + " " + datosPaciente[3] );
        /*Eliminar fila no seleccioanda*/
        DefaultTableModel dtm = (DefaultTableModel) tablaPacientes.getModel(); //TableProducto es el nombre de mi tabla ;)
        
        while(dtm.getRowCount()>0){
            dtm.removeRow(0);
        }       
        dtm.addRow(datosPaciente);                 
        tablaPacientes.setModel(dtm);
        
        this.paciente = new DAO.Paciente(Integer.parseInt(datosPaciente[0]),datosPaciente[1],datosPaciente[2],datosPaciente[3],datosPaciente[4],datosPaciente[5],datosPaciente[6]);
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
        textNombre = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPacientes = new javax.swing.JTable();
        textSearch = new javax.swing.JTextField();
        btnSeleccionar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel1.setText("Bienvenido ");

        textNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textNombre.setText("Nombre");

        jLabel3.setText("Busqueda de pacientes:");

        tablaPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaPacientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPacientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaPacientes);

        textSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textSearchKeyTyped(evt);
            }
        });

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        btnRegistrar.setText("Registrar un nuevo Paciente");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar Paciente");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(textNombre)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnRegistrar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEliminar)))
                        .addGap(0, 194, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(textNombre)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeleccionar)
                    .addComponent(btnRegistrar)
                    .addComponent(btnEliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
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

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
      seleccionarPaciente();
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void textSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textSearchKeyTyped
        filtrarPaciente();
    }//GEN-LAST:event_textSearchKeyTyped

    private void tablaPacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPacientesMouseClicked
        seleccionarPacienteTabla();
    }//GEN-LAST:event_tablaPacientesMouseClicked

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        registrarPaciente();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        elminarPaciente();
    }//GEN-LAST:event_btnEliminarActionPerformed

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
            java.util.logging.Logger.getLogger(BuscarPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuscarPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuscarPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuscarPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Especialista especialista = new Especialista();
                new BuscarPaciente(especialista).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaPacientes;
    private javax.swing.JLabel textNombre;
    private javax.swing.JTextField textSearch;
    // End of variables declaration//GEN-END:variables
}
