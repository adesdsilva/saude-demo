/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package relatorios;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controladores.ControladorGuiaAtendimento;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import negocio.GuiaAtendimento;

/**
 *
 * @author Adelino
 */
@ManagedBean
@SessionScoped
@ViewScoped
public class Test {
    
    
    public void imprimir() throws DocumentException{
        Document documentoPDF = new Document(PageSize.LETTER);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try{
            PdfWriter.getInstance(documentoPDF, baos);
            
            PdfWriter.getInstance(documentoPDF, new FileOutputStream("D:\\PDF_PROGRAMINHAS.pdf"));

            
            ControladorGuiaAtendimento ga = new ControladorGuiaAtendimento();
            List<GuiaAtendimento> lista = new ArrayList<>();
            GuiaAtendimento gatend = ga.recuperargat(ga.getSelectedGuiaAtendimento().getCodigoguiaatendimento());
            lista.add(gatend);
            
            documentoPDF.open();
            documentoPDF.add(new Paragraph("Relátório Guia de Atendimento \n"));
            
//Após incluir novos Paragraph para chamar o cabeçalho do PDF
            String cod = ""+ gatend.getCodigoguiaatendimento();
            
            documentoPDF.add(new Paragraph("GUIA DE ATENDIMENTO Nº: 0000"+cod));
            
            PdfPTable pdfTable = new PdfPTable(4);
            pdfTable.setTotalWidth(new float[]{50, 120, 50, 100});
            pdfTable.setLockedWidth(true);
            
            PdfPCell cell = new PdfPCell(new Paragraph("Informações do Paciente", FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GRAY);
            cell.setColspan(4);
            cell = new PdfPCell(new Paragraph("ID", FontFactory.getFont("arial",12,Font.BOLD,BaseColor.BLACK)));
            
            pdfTable.addCell("PACIENTE");
            
            pdfTable.addCell("CPF");
            
            pdfTable.addCell("IDENTIDADE");
            
            pdfTable.addCell("ENDEREÇO");
            
            for (int i = 0; i < lista.size(); i++) {
                GuiaAtendimento guia = lista.get(i);
                pdfTable.addCell(guia.toString());
                cell.setBackgroundColor(BaseColor.BLACK);
                pdfTable.addCell(guia.getPaciente().getNome());
                pdfTable.addCell(guia.getPaciente().getCpf());
                pdfTable.addCell(guia.getPaciente().getRg());
                pdfTable.addCell(guia.getPaciente().getLogradouro());
            }
            documentoPDF.add(pdfTable);
        }catch(Exception ex){
            ex.getMessage();
        }
        //documentoPDF.close();
        FacesContext context = FacesContext.getCurrentInstance();
        Object response = context.getExternalContext().getResponse();
        if(response instanceof HttpServletResponse){
            HttpServletResponse hsr = (HttpServletResponse) response;
            hsr.setContentType("pdfGuia/pdf");
            hsr.setHeader("Content-disposition", "attachment");
            hsr.setContentLength(baos.size());
            try{
                ServletOutputStream out = hsr.getOutputStream();
                baos.writeTo(out);
                out.flush();                
            }catch(IOException io){
                io.getMessage();
            }
            context.responseComplete();
        }
    }
}
