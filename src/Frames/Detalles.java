/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import Clases.GenerarPDF;
import DAO.Diagnostico;
import DAO.Especialista;
import DAO.Prediagnostico;
import DAO.Tratamiento;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Alan Huerta Cortes
 */
public class Detalles extends javax.swing.JFrame {
    /*Clases*/
    Especialista especialistaUnico;
    DAO.Paciente paciente;
    ArrayList<Diagnostico> diagnostico;
    ArrayList<Prediagnostico> prediagnostico;
    ArrayList<Tratamiento> tratamiento;
    ArrayList<Especialista> especialista;
   
    public Detalles(Especialista especialistaUnico,DAO.Paciente paciente, ArrayList<Especialista> especialista, ArrayList<Diagnostico> diagnostico, ArrayList<Tratamiento> tratamiento, ArrayList<Prediagnostico> prediagnostico){
        this.especialistaUnico = especialistaUnico;
        this.paciente = paciente;
        this.especialista = especialista;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.prediagnostico = prediagnostico;
        initComponents();        
        /*Detalles prediagnostico*/
        detallesPrediagnostico();
        /*Tratamiento*/
        detallesTratamiento();
        setTitle("Detalles");
        labelModificar.setVisible(false);
    }
       
    private JTextArea[] detallesPrediagnostico(){
        /*Nombre del Paciente*/
        labelName.setText( this.paciente.getNombre() + " " + this.paciente.getApellidoPaterno() + " " + this.paciente.getApellidoMaterno());
        /*Prediagnostico*/
        JPanel panelPre = new JPanel();        
        int TamanoPre =  this.prediagnostico.size();
        JLabel textLabelPre[] = new JLabel[TamanoPre+1];
        JTextArea textAreaPre[] = new JTextArea[TamanoPre+1];
        JScrollPane jScrollPanePre[] = new JScrollPane[TamanoPre+1];
        
        try{            
            
            for(int i=0; i<TamanoPre; i++){
                jScrollPanePre[i] = new javax.swing.JScrollPane();
                textLabelPre[i] = new JLabel();
                textLabelPre[i].setText("Atendido por: " + this.especialista.get(i).getNombre() + " " + this.especialista.get(i).getApellidoPaterno() + " " + this.especialista.get(i).getApellidoMaterno());
                textLabelPre[i].setPreferredSize(new Dimension(25, 25));

                textAreaPre[i] = new JTextArea();
                textAreaPre[i].setEditable(false);
                textAreaPre[i].setText(">Tipo de estrabismo: " + this.prediagnostico.get(i).getTipoEstrabismo() + "\n" +
                                        ">Desviación del Ojo Derecho: "+ this.prediagnostico.get(i).getDesviacionDerecha() + " º" +"\n"+
                                        ">Desviación del Ojo Izquierdo: "+ this.prediagnostico.get(i).getDesviacionIzquierda() + " º" + "\n" +
                                        ">Dioptrías prismáticas: "+ this.prediagnostico.get(i).getDioptriasPrismaticas() + "\n"+
                                        ">Fecha de Realización: "+ this.prediagnostico.get(i).getFecha() + "\n");
                
                jScrollPanePre[i].setViewportView(textAreaPre[i]);
                javax.swing.GroupLayout panelPreLayout = new javax.swing.GroupLayout(panelPre);
                panelPre.setLayout(new BoxLayout(panelPre,BoxLayout.Y_AXIS));    
                panelPre.add(textLabelPre[i]);
                panelPre.add(jScrollPanePre[i]); 
            } 
            jScrollPane1.setViewportView(panelPre);
        }catch(Exception e){ 
            //JOptionPane.showMessageDialog(null, "Vacio");
            System.out.println("Sin prediagnostico" + e);
        }
        /*int TamanoPre =  this.prediagnostico.size();
        for(int i=0; i<TamanoPre; i++){
            System.err.println(this.prediagnostico.get(i).getTipoEstrabismo());
        }*/
        return textAreaPre;
        
    }
    
    private JTextArea[] detallesTratamiento(){
            JPanel panelTra = new JPanel();        
            int TamanoTra = this.tratamiento.size();//Paciente.tipoTratamiento.size();
            
            JLabel textLabelTra[] = new JLabel[TamanoTra+1];
            JTextArea textAreaTra[] = new JTextArea[TamanoTra+1];
            JScrollPane jScrollPaneTra[] = new JScrollPane[TamanoTra+1];
        try{
                        
            for(int i=0; i<TamanoTra; i++){
                jScrollPaneTra[i] = new javax.swing.JScrollPane();
                textLabelTra[i] = new JLabel();
                textLabelTra[i].setText("Atendido por: " + this.especialista.get(i).getNombre() + " " + this.especialista.get(i).getApellidoPaterno() + " " + this.especialista.get(i).getApellidoMaterno());
                textLabelTra[i].setPreferredSize(new Dimension(25, 25));
                
                textAreaTra[i] = new JTextArea();
                textAreaTra[i].setEditable(false);
                String nombreTerapia  =""; //TODO: cambiar nombre dinamicamente
                
                if(this.tratamiento.get(i).getTipoTratamiento().equals("Manejo de Contrastes")){
                    nombreTerapia = "Juego CatVenge";
                }else if(this.tratamiento.get(i).getTipoTratamiento().equals("Oclusión de objetos")){
                    nombreTerapia = "Juego SpaceHero";
                }else if(this.tratamiento.get(i).getTipoTratamiento().equals("Calentamiento")){
                    nombreTerapia = "Espacio Atmósferico";
                }else if(this.tratamiento.get(i).getTipoTratamiento().equals("Relajación")){
                    nombreTerapia = "Lazy Sphere 2077";
                }
                
                String unidadesTiempo="minutos";
                if(this.tratamiento.get(i).getDuracion().substring(0, 2).equalsIgnoreCase("00"))
                    unidadesTiempo = "segundos";
                
                textAreaTra[i].setText(">Tipo de Tratamiento: " + this.tratamiento.get(i).getTipoTratamiento() + "\n"  +
                                        ">Nombre de la terapia visual: "+ nombreTerapia + "\n" +
                                        ">Puntuación Obtenida: "+ this.tratamiento.get(i).getPuntuacion() + "\n" +
                                        ">Duración de la terapia: "+ this.tratamiento.get(i).getDuracion() + " " + unidadesTiempo + "\n" +                                        
                                        ">Fecha de Realización: "+ this.tratamiento.get(i).getFecha() + "\n");
                
                jScrollPaneTra[i].setViewportView(textAreaTra[i]);
                javax.swing.GroupLayout panelPreLayout = new javax.swing.GroupLayout(panelTra);
                panelTra.setLayout(new BoxLayout(panelTra,BoxLayout.Y_AXIS));    
                panelTra.add(textLabelTra[i]);
                panelTra.add(jScrollPaneTra[i]);
            }
            jScrollPane2.setViewportView(panelTra);
        }catch(Exception e){
            System.out.println("Sin Tratamiento" + e);
        }    
        
        return textAreaTra;
    }
    
    private void volverPaciente(){
        Paciente paciente = new Paciente(this.especialistaUnico, this.paciente);
        paciente.setVisible(true);
        dispose();
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
        btnVolver = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        labelName = new javax.swing.JLabel();
        labelModificar = new javax.swing.JLabel();
        btnPDF = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Detalles del paciente-");

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Prediagnóstico", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(0, 0, 255))); // NOI18N

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tratamiento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(0, 0, 255))); // NOI18N

        labelName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelName.setText("Nombre");

        labelModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/modificar2.jpg"))); // NOI18N
        labelModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelModificarMouseClicked(evt);
            }
        });

        btnPDF.setText("Generar PDF");
        btnPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPDF))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVolver)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(btnVolver)
                        .addComponent(labelName))
                    .addComponent(labelModificar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPDF)))
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

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        volverPaciente();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void labelModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelModificarMouseClicked
       Paciente paciente = new Paciente();
       paciente.modificarNombre(this.paciente);
    }//GEN-LAST:event_labelModificarMouseClicked

    private void btnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFActionPerformed
        GenerarPDF pdf = new GenerarPDF(especialistaUnico, especialista , paciente, detallesPrediagnostico(),detallesTratamiento());
        try {
            pdf.writePDF();
        } catch (IOException ex) {
            Logger.getLogger(Detalles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPDFActionPerformed

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
            java.util.logging.Logger.getLogger(Detalles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Detalles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Detalles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Detalles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Especialista especialistaU = new Especialista();
                DAO.Paciente paciente = new DAO.Paciente();
                ArrayList<Especialista> especialista = new ArrayList<Especialista>();
                ArrayList<Diagnostico> diagnostico = new ArrayList<Diagnostico>();
                ArrayList<Tratamiento> tratamiento = new ArrayList<Tratamiento>();
                ArrayList<Prediagnostico> prediagnostico = new ArrayList<Prediagnostico>();
                new Detalles(especialistaU,paciente,especialista,diagnostico,tratamiento,prediagnostico).setVisible(true);
            }
        });
    }
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPDF;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelModificar;
    private javax.swing.JLabel labelName;
    // End of variables declaration//GEN-END:variables
}
