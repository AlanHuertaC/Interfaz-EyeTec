/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
import DAO.Especialista;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.Desktop;
import java.awt.FileDialog;
import java.awt.Frame;
import java.net.URL;
import javax.swing.JTextArea;
/**
 *
 * @author Alan Huerta Cortes
 */
public class GenerarPDF {
    
    Especialista especialista;
    ArrayList<Especialista> especialistasPrediagnosticos;
    ArrayList<Especialista> especialistasTratamiento;
    DAO.Paciente paciente;
    JTextArea prediagnostico[];
    JTextArea tratamiento[];

    public GenerarPDF(Especialista especialista, ArrayList<Especialista> especialistasPrediagnosticos, ArrayList<Especialista> especialistasTratamiento, DAO.Paciente paciente,JTextArea prediagnostico[], JTextArea tratamiento[]) {
        this.especialista = especialista;
        this.especialistasPrediagnosticos = especialistasPrediagnosticos;
        this.especialistasTratamiento = especialistasTratamiento;
        this.paciente = paciente;
        this.prediagnostico = prediagnostico;
        this.tratamiento = tratamiento;
    }
    
    public void writePDF() throws FileNotFoundException, IOException {
        
        FileDialog dialogoArchivo;
        Frame f  =new Frame();
        dialogoArchivo = new FileDialog(f,"Guardar reporte", FileDialog.SAVE);
        dialogoArchivo.show();
        
        Document document = new Document();
        String FILE_NAME = dialogoArchivo.getDirectory() + System.getProperty("file.separator") + dialogoArchivo.getFile() + ".pdf";
        	
        try {
            /*String path = new File(".").getCanonicalPath();
            String FILE_NAME = path + "/reporte.pdf";*/
           
            PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME))); 
            document.open();
            
            Image foto = Image.getInstance(getClass().getResource("/Images/logo.jpg"));            
            foto.scaleToFit(200, 200);
            foto.setAlignment(Chunk.ALIGN_MIDDLE);
            document.add(foto);
 
           
            /*Establecer fecha*/
            SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' 'enero' 'de' yyyy", new Locale("es_ES"));
            Date fechaDate = new Date();
            String fecha = formateador.format(fechaDate);
            /*Fecha*/
            
            Font fontFecha = new Font();
            //fontFecha.setFamily(FontFamily.COURIER.name());
            fontFecha.setStyle(Font.BOLDITALIC);
            fontFecha.setSize(10);
            Paragraph paragraphFecha = new Paragraph();
            paragraphFecha.setFont(fontFecha);
            paragraphFecha.add("Toluca, México a " + fecha + "\n\n\n\n");
            paragraphFecha.setAlignment(Element.ALIGN_RIGHT);
 
            document.add(paragraphFecha);
 
            /*Paragraph paragraphLorem = new Paragraph();
            paragraphLorem.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit."
            		+ "Maecenas finibus fringilla turpis, vitae fringilla justo."
            		+ "Sed imperdiet purus quis tellus molestie, et finibus risus placerat."
            		+ "Donec convallis eget felis vitae interdum. Praesent varius risus et dictum hendrerit."
            		+ "Aenean eu semper nunc. Aenean posuere viverra orci in hendrerit. Aenean dui purus, eleifend nec tellus vitae,"
            		+ " pretium dignissim ex. Aliquam erat volutpat. ");
            
            java.util.List<Element> paragraphList = new ArrayList<>();
            
            paragraphList = paragraphLorem.breakUp();
 
            Font f = new Font();
            f.setFamily(FontFamily.COURIER.name());
            f.setStyle(Font.BOLDITALIC);
            f.setSize(8);
            
            Paragraph p3 = new Paragraph();
            p3.setFont(f);
            p3.addAll(paragraphList);
            p3.add("TEST LOREM IPSUM DOLOR SIT AMET CONSECTETUR ADIPISCING ELIT!");
 
            document.add(paragraphLorem);
            document.add(p3);*/
            /*Doctor*/
            /*Font fontDoctor = new Font();            
            fontDoctor.setStyle(Font.ITALIC);
            fontDoctor.setSize(12);
            Paragraph paragraphDoctor = new Paragraph();
            paragraphDoctor.setFont(fontDoctor);
            paragraphDoctor.add("Dr. " + especialista.getNombre() + " " + especialista.getApellidoPaterno() + " " + especialista.getApellidoMaterno() + "\n\n");
            document.add(paragraphDoctor);*/
            
            /*Texto Paciente*/
            Font fontTituloPaciente = new Font();            
            fontTituloPaciente.setStyle(Font.UNDERLINE);
            fontTituloPaciente.setSize(14);
            Paragraph paragraphTituloPaciente = new Paragraph();
            paragraphTituloPaciente.setFont(fontTituloPaciente);
            paragraphTituloPaciente.setAlignment(Element.ALIGN_CENTER);
            paragraphTituloPaciente.add("DATOS DEL PACIENTE \n\n");
            document.add(paragraphTituloPaciente);
            
            /*Datos del paciente*/
            Font fontDatosPaciente = new Font();            
            fontDatosPaciente.setStyle(Font.NORMAL);
            fontDatosPaciente.setSize(12);
            Paragraph paragraphDatosPaciente = new Paragraph();
            paragraphDatosPaciente.setFont(fontDatosPaciente);            
            paragraphDatosPaciente.add("Nombre completo: " + paciente.getNombre() + " " + paciente.getApellidoPaterno() + " " + paciente.getApellidoMaterno() +"\n"+
                                       "Sexo: " + paciente.getSexo() + "\n" +
                                       "Fecha de nacimiento: " + paciente.getFechaNacimiento() + "\n\n");
            document.add(paragraphDatosPaciente);
            
            
            /*Prediagnostico*/
            Font fontTitulo = new Font();
            //fontFecha.setFamily(FontFamily.COURIER.name());
            fontTitulo.setStyle(Font.BOLD);
            fontTitulo.setSize(12);
            Paragraph paragraphTitulo = new Paragraph();
            paragraphTitulo.setFont(fontTitulo);
            paragraphTitulo.add("Resultados del prediagnóstico" + "\n\n");
            paragraphTitulo.setAlignment(Element.ALIGN_LEFT);
            document.add(paragraphTitulo);
            
            /*Datos prediagnostico*/
            Font fontDatos = new Font();            
            fontDatos.setStyle(Font.NORMAL);
            fontDatos.setSize(12);
            Paragraph paragraphDatos = new Paragraph();
            paragraphDatos.setFont(fontDatos);
            int TamanoPre = this.prediagnostico.length - 1;
            
            /*Lineas naranjas*/
            Image orangelines = Image.getInstance(getClass().getResource("/Images/Orange-lines.png"));
            orangelines.scaleToFit(300, 40);
            orangelines.setAlignment(Chunk.ALIGN_MIDDLE);
            
            /*Quien atendió*/
            Paragraph paragraphEspecialistas = new Paragraph();
            Font fontEspecialiastas = new Font();            
            fontEspecialiastas.setStyle(Font.BOLD);
            fontEspecialiastas.setSize(12);
            paragraphEspecialistas.setFont(fontEspecialiastas);
            
            for(int i=0; i<TamanoPre; i++){
                if(i!= 0)
                    document.add(orangelines);
                paragraphEspecialistas.add("Atendido por: " + this.especialistasPrediagnosticos.get(i).getNombre() + " " + this.especialistasPrediagnosticos.get(i).getApellidoPaterno() + " " + this.especialistasPrediagnosticos.get(i).getApellidoMaterno() + "\n");
                document.add(paragraphEspecialistas);
                paragraphEspecialistas = null;
                paragraphEspecialistas = new Paragraph();
                paragraphEspecialistas.setFont(fontEspecialiastas);
                
                paragraphDatos.add(this.prediagnostico[i].getText() + "\n");
                document.add(paragraphDatos);   
                paragraphDatos = null;
                paragraphDatos = new Paragraph();
                paragraphDatos.setFont(fontDatos);                
            }
           
            /*Separador azul*/
            Image bluelines = Image.getInstance(getClass().getResource("/Images/Blue-lines.png"));
            bluelines.scaleToFit(500, 40);
            bluelines.setAlignment(Chunk.ALIGN_MIDDLE);
            document.add(bluelines);
            
            /*Tratamiento*/            
            
            Font fontTitulo2 = new Font();
            //fontFecha.setFamily(FontFamily.COURIER.name());
            fontTitulo2.setStyle(Font.BOLD);
            fontTitulo2.setSize(12);
            Paragraph paragraphTitulo2 = new Paragraph();
            paragraphTitulo2.setFont(fontTitulo);
            paragraphTitulo2.add("\n\nResultados del tratamiento" + "\n\n");
            document.add(paragraphTitulo2);
            
            
            /*T*/          
            
            /*Datos tratamiento*/
            Font fontDatosT = new Font();            
            fontDatosT.setStyle(Font.NORMAL);
            fontDatosT.setSize(12);
            Paragraph paragraphDatosT = new Paragraph();
            paragraphDatosT.setFont(fontDatosT);
            int TamanoTra = this.tratamiento.length -1;
            
            /*Lineas naranjas*/
            /*Image orangelines = Image.getInstance(System.getProperty("user.dir") + "/Orange-lines.png");
            orangelines.scaleToFit(300, 40);
            orangelines.setAlignment(Chunk.ALIGN_MIDDLE);*/
            
            /*Quien atendió*/
            Paragraph paragraphEspecialistasT = new Paragraph();
            Font fontEspecialiastasT = new Font();            
            fontEspecialiastasT.setStyle(Font.BOLD);
            fontEspecialiastasT.setSize(12);
            paragraphEspecialistasT.setFont(fontEspecialiastasT);
            
            for(int i=0; i<TamanoTra; i++){
                if(i!= 0)
                   document.add(orangelines);
                paragraphEspecialistasT.add("Atendido por: " + this.especialistasTratamiento.get(i).getNombre() + " " + this.especialistasTratamiento.get(i).getApellidoPaterno() + " " + this.especialistasTratamiento.get(i).getApellidoMaterno() + "\n");
                document.add(paragraphEspecialistasT);
                paragraphEspecialistasT = null;
                paragraphEspecialistasT = new Paragraph();
                paragraphEspecialistasT.setFont(fontEspecialiastasT);
                
                paragraphDatosT.add(this.tratamiento[i].getText() + "\n");
                document.add(paragraphDatosT);   
                paragraphDatosT = null;
                paragraphDatosT = new Paragraph();
                paragraphDatosT.setFont(fontDatos);                
            }
            document.close(); 
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        
        try {
            File path = new File (FILE_NAME);
            Desktop.getDesktop().open(path);
        }catch (IOException ex) {
            ex.printStackTrace();
        }
 
    }
}
