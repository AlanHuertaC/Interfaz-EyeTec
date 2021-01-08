/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import DAO.Diagnostico;
import DAO.Especialista;
import DAO.Ojo;
import DAO.Prediagnostico;
import DAO.Tratamiento;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Alan Huerta Cortes
 */
public class Detalles extends javax.swing.JFrame {
    /*Clases*/
    Especialista especialista;
    DAO.Paciente paciente;
    Ojo ojo; 
    Diagnostico diagnostico;
    Tratamiento tratamiento;
    Prediagnostico prediagnostico;
    
    /*variables prediagnostico*/
    JPanel panelPre;
    JLabel textLabelPre[];
    JTextArea textAreaPre[];
    JScrollPane jScrollPanePre[];
    /*variables Tratamiento*/
    JPanel panelTra;
    JLabel textLabelTra[];
    JTextArea textAreaTra[];
    JScrollPane jScrollPaneTra[];
   
    public Detalles(Especialista especialista, DAO.Paciente paciente, Ojo ojo, Diagnostico diagnostico, Tratamiento tratamiento, Prediagnostico prediagnostico){
        this.especialista = especialista;
        this.paciente = paciente;
        this.ojo = ojo;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.prediagnostico = prediagnostico;
        initComponents();
        
        /*Nombre del Paciente*/
        labelName.setText( this.paciente.getNombre() + " " + this.paciente.getApellidoPaterno() + " " + this.paciente.getApellidoMaterno());
        /*Prediagnostico*/
        try{
            panelPre = new JPanel();        
            int TamanoPre = Paciente.tipoStrabismo.size();

            textLabelPre = new JLabel[TamanoPre+1];
            textAreaPre = new JTextArea[TamanoPre+1];
            jScrollPanePre = new JScrollPane[TamanoPre+1];

            for(int i=0; i<TamanoPre; i++){
                jScrollPanePre[i] = new javax.swing.JScrollPane();
                textLabelPre[i] = new JLabel();
                textLabelPre[i].setText("Atendido por: " + Paciente.nombreEspecialista.get(i) + " " + Paciente.paternoEspecialista.get(i) + " " + Paciente.maternoEspecialista.get(i));
                textLabelPre[i].setPreferredSize(new Dimension(25, 25));

                textAreaPre[i] = new JTextArea();
                textAreaPre[i].setEditable(false);
                textAreaPre[i].setText("Tipo de estrabismo: " + Paciente.tipoStrabismo.get(i) + "\n" +"\n" +
                                        "Desviación del Ojo Derecho: "+ Paciente.desviacionDer.get(i) + " º" +"\n"+ "\n"+
                                        "Desviación del Ojo Izquierdo: "+ Paciente.desviacionIzq.get(i)+ " º" + "\n"+ "\n" +
                                        "Dioptrías prismáticas: "+ Paciente.dioptrias.get(i) + "\n"+"\n"+
                                        "Fecha de Realización: "+ Paciente.fecha.get(i) + "\n");

                jScrollPanePre[i].setViewportView(textAreaPre[i]);
                javax.swing.GroupLayout panelPreLayout = new javax.swing.GroupLayout(panelPre);
                panelPre.setLayout(new BoxLayout(panelPre,BoxLayout.Y_AXIS));    
                panelPre.add(textLabelPre[i]);
                panelPre.add(jScrollPanePre[i]);
            }

            jScrollPane1.setViewportView(panelPre);
        }catch(Exception e){ 
            //JOptionPane.showMessageDialog(null, "Vacio");
            System.out.println("Sin prediagnostico");
        }
        /*Tratamiento*/
        try{
            panelTra = new JPanel();        
            int TamanoTra = Paciente.tipoTratamiento.size();
            
            textLabelTra = new JLabel[TamanoTra+1];
            textAreaTra = new JTextArea[TamanoTra+1];
            jScrollPaneTra = new JScrollPane[TamanoTra+1];
            
            for(int i=0; i<TamanoTra; i++){
                jScrollPaneTra[i] = new javax.swing.JScrollPane();
                textLabelTra[i] = new JLabel();
                textLabelTra[i].setText("Atendido por: " + Paciente.nombreEspecialistaT.get(i) + " " + Paciente.paternoEspecialistaT.get(i) + " " + Paciente.maternoEspecialistaT.get(i));
                textLabelTra[i].setPreferredSize(new Dimension(25, 25));
                
                textAreaTra[i] = new JTextArea();
                textAreaTra[i].setEditable(false);
                String nombreTerapia  =""; //TODO: cambiar nombre dinamicamente
                
                if(Paciente.tipoTratamiento.get(i).equals("Manejo de Contrastes")){
                    nombreTerapia = "Juego CatVenge";
                }else if(Paciente.tipoTratamiento.get(i).equals("Oclusión de objetos")){
                    nombreTerapia = "Juego SpaceHero";
                }else if(Paciente.tipoTratamiento.get(i).equals("Calentamiento")){
                    nombreTerapia = "Espacio Atmósferico";
                }else if(Paciente.tipoTratamiento.get(i).equals("Relajación")){
                    nombreTerapia = "Lazy Sphere 2077";
                }
                
                textAreaTra[i].setText("Tipo de Tratamiento: " + Paciente.tipoTratamiento.get(i) + "\n" +"\n" +
                                        "Nombre de la terapia visual: "+ nombreTerapia + "\n" + "\n"+
                                        "Puntuación Obtenida: "+  Paciente.puntuacion.get(i) + "\n" + "\n"+
                                        "Duración de la terapia: "+ Paciente.duracionTotal.get(i) + " min" +"\n"+ "\n"+                                        
                                        "Fecha de Realización: "+ Paciente.fechaT.get(i) + "\n");
                
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
    }
    
    /*public void inicio(){ 
        //textAreaPre = new JTextArea();
        textAreaPre[0].setText("Tipo de estrabismo: " + Paciente.tipoStrabismo + "\n" +"\n" +
                            "Desviación del Ojo Derecho: "+ Paciente.desviacionDer + " º" +"\n"+ "\n"+
                            "Desviación del Ojo Izquierdo: "+ Paciente.desviacionIzq+ " º" + "\n"+ "\n" +
                            "Dioptrías prismáticas: "+ Paciente.dioptrias + "\n"+"\n"+
                            "Fecha de Realización: "+ Paciente.fecha + "\n");    
    }*/
    
    public void set(){
        
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
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        labelName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Detalles del paciente");

        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Prediagnóstico", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(0, 0, 255))); // NOI18N

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tratamiento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(0, 0, 255))); // NOI18N

        labelName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelName.setText("Nombre");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1)
                    .addComponent(labelName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Paciente paciente = new Paciente();
        paciente.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
                Especialista especialista = new Especialista();
                DAO.Paciente paciente = new DAO.Paciente();
                Ojo ojo = new Ojo();
                Diagnostico diagnostico = new Diagnostico();
                Tratamiento tratamiento = new Tratamiento();
                 Prediagnostico prediagnostico = new Prediagnostico();
                new Detalles(especialista,paciente,ojo,diagnostico,tratamiento,prediagnostico).setVisible(true);
            }
        });
    }
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelName;
    // End of variables declaration//GEN-END:variables
}
