/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
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
import javax.swing.JTextArea;
/**
 *
 * @author Alan Huerta Cortes
 */
public class GenerarPDF {
    
    JTextArea prediagnostico[];

    public GenerarPDF(JTextArea prediagnostico[]) {
        this.prediagnostico = prediagnostico;
    }
    
    /*public static void main(String[] args) 
    {
       try {
           writePDF();
       } catch (IOException ex) {
           Logger.getLogger(GererarPDF.class.getName()).log(Level.SEVERE, null, ex);
       }
    }*/
    
    public void writePDF() throws FileNotFoundException, IOException {
 
        Document document = new Document();
 
        try {
            String path = new File(".").getCanonicalPath();
            String FILE_NAME = path + "/reporte.pdf";
        	
            PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME))); 
            document.open();
            
            Image foto = Image.getInstance(System.getProperty("user.dir") + "/logo.jpg");
            foto.scaleToFit(200, 200);
            foto.setAlignment(Chunk.ALIGN_MIDDLE);
            document.add(foto);
 
           
            /**/
            SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' 'enero' 'de' yyyy", new Locale("es_ES"));
            Date fechaDate = new Date();
            String fecha = formateador.format(fechaDate);
            /**/
            
            Font fontFecha = new Font();
            //fontFecha.setFamily(FontFamily.COURIER.name());
            fontFecha.setStyle(Font.BOLDITALIC);
            fontFecha.setSize(8);
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
            Font fontDoctor = new Font();
            //fontFecha.setFamily(FontFamily.COURIER.name());
            fontDoctor.setStyle(Font.NORMAL);
            fontDoctor.setSize(12);
            Paragraph paragraphDoctor = new Paragraph();
            paragraphDoctor.setFont(fontDoctor);
            paragraphDoctor.add("Dr. " + "\n\n");
            document.add(paragraphDoctor);
            
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
            
            Font fontDatos = new Font();
            //fontFecha.setFamily(FontFamily.COURIER.name());
            fontDatos.setStyle(Font.NORMAL);
            fontDatos.setSize(10);
            Paragraph paragraphDatos = new Paragraph();
            paragraphDatos.setFont(fontDatos);
            int TamanoPre = this.prediagnostico.length - 1;
            
            for(int i=0; i<TamanoPre; i++){
                paragraphDatos.add(this.prediagnostico[i].getText());
                document.add(paragraphDatos);                
            }
           
            
            /*Tratamiento*/            
            
            Font fontTitulo2 = new Font();
            //fontFecha.setFamily(FontFamily.COURIER.name());
            fontTitulo2.setStyle(Font.BOLD);
            fontTitulo2.setSize(12);
            Paragraph paragraphTitulo2 = new Paragraph();
            paragraphTitulo2.setFont(fontTitulo);
            paragraphTitulo2.add("\n\nResultados del tratamiento" + "\n\n\n\n");
            document.add(paragraphTitulo2);
            
            
           
            
            document.close();
 
        } catch (DocumentException e) {
            e.printStackTrace();
        }
 
    }
}
