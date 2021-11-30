/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.faces.bean.ManagedBean;
import negocio.GuiaAtendimento;
import negocio.GuiaAutorizacao;

/**
 *
 * @author adelino
 */
@ManagedBean
public class TestePDF {

    public void gerarRel(GuiaAutorizacao g) throws FileNotFoundException, DocumentException, IOException {
        Document doc = null;
        OutputStream os = null;

        try {
            //cria o documento tamanho A4, margens de 2,54cm
            doc = new Document(PageSize.A4, 72, 72, 72, 72);

            //cria a stream de saída
            os = new FileOutputStream("C:\\Users\\José\\Documents\\relatorios\\GuiaAtendimentoRelatorio.pdf");

            //associa a stream de saída ao 
            PdfWriter.getInstance(doc, os);

            //abre o documento
            doc.open();

            //adiciona o texto/img ao PDF
            //adicionar logo da prefeitura com o caminho específico
            Image img = Image.getInstance("C:\\Users\\José\\Desktop\\SecSaude3\\web\\img\\angelim.fw.jpg");
            img.setAlignment(Element.ALIGN_CENTER);
            doc.add(img);

            Font f = new Font(FontFamily.TIMES_ROMAN, 15, Font.BOLD);
            Paragraph p1 = new Paragraph("FUNDO MUNICIPAL DE SAÚDE DE ANGELIM", f);
            p1.setAlignment(Element.ALIGN_CENTER);
            doc.add(p1);

            Font f1 = new Font(FontFamily.TIMES_ROMAN, 15, Font.BOLD);
            Paragraph p2 = new Paragraph("RUA BELA VISTA, 12 - CENTRO", f1);
            p2.setAlignment(Element.ALIGN_CENTER);
            doc.add(p2);

            Font f2 = new Font(FontFamily.TIMES_ROMAN, 15, Font.BOLD);
            Paragraph p3 = new Paragraph("CEP: 55430-000 ANGELIM - PE", f2);
            p3.setAlignment(Element.ALIGN_CENTER);
            doc.add(p3);

            Font f3 = new Font(FontFamily.TIMES_ROMAN, 15, Font.BOLD);
            Paragraph p4 = new Paragraph("Inscrição do CNPJ:                         Fone:", f3);
            p4.setAlignment(Element.ALIGN_CENTER);
            doc.add(p4);
            p4.setSpacingAfter(20);

            Font guia = new Font(FontFamily.TIMES_ROMAN, 20, Font.BOLD);
            Paragraph numGuia = new Paragraph("GUIA DE AUTORIZAÇÃO Nº:00000" + g.getCodigoguiaautorizacao());
            doc.add(numGuia);

            int[] larguras = new int[]{20, 80};
            Font font = new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD);
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage((float) 100.0);
            //table.setWidths(larguras);
            PdfPCell header = new PdfPCell(new Paragraph("Informações do Paciente"));
            header.setColspan(4);
            table.addCell(header);
            table.addCell(new Paragraph("PACIENTE:", font));
            table.addCell("" + g.getPaciente().getNome());
            table.addCell(new Paragraph("CPF:", font));
            table.addCell("" + g.getPaciente().getCpf());
            table.addCell(new Paragraph("ENDEREÇO:", font));
            table.addCell("" + g.getPaciente().getLogradouro());
            table.addCell(new Paragraph("IDENTIDADE:", font));
            table.addCell("" + g.getPaciente().getRg());
            table.addCell(new Paragraph("CEP:", font));
            table.addCell("" + g.getPaciente().getCep());
            table.addCell(new Paragraph("CIDADE:", font));
            table.addCell("" + g.getPaciente().getCidade());
            table.addCell(new Paragraph("ESTADO:", font));
            table.addCell("" + g.getPaciente().getEstado());
            table.addCell(new Paragraph("AG. SAÚDE:", font));
            table.addCell("" + g.getPaciente().getAgentesaude());
            doc.add(table);

            int[] largura = new int[]{25, 75};
            PdfPTable table1 = new PdfPTable(largura.length);
            table1.setWidthPercentage((float) 100.0);
            table1.setWidths(largura);
            table1.addCell(new Paragraph("Atendido Por:", font));
            table1.addCell(new Paragraph("" + g.getPaciente().getAgentesaude()));
            String statusVerifica = "";
            if (g.isStatus() == true) {
                statusVerifica = "AUTORIZADO";
            } else {
                statusVerifica = "PENDENTE";
            }
            Font fonteStatus = new Font(FontFamily.TIMES_ROMAN, 13, Font.BOLD);
            table1.addCell(new Paragraph("STATUS", fonteStatus));
            table1.addCell(new Paragraph("" + statusVerifica));
            doc.add(table1);

            PdfPTable table2 = new PdfPTable(new float[]{0.2f, 0.4f, 0.7f, 0.2f});
            table2.setWidthPercentage((float) 100.0);
            table2.addCell(new Paragraph("Código", font));
            table2.addCell(new Paragraph("Procedimento Solicitado", font));
            table2.addCell(new Paragraph("Médico", font));
            table2.addCell(new Paragraph("CRM", font));
            table2.addCell("" + g.getProcedimento().getCodigoprocedimento());
            table2.addCell("" + g.getProcedimento().getNome());
            table2.addCell("" + g.getMedico().getNome());
            table2.addCell("" + g.getMedico().getCrm());
            doc.add(table2);

            PdfPTable table3 = new PdfPTable(4);
            PdfPCell header1 = new PdfPCell(new Paragraph("Dados do Atendimento", font));
            table3.setWidthPercentage((float) 100.0);
            header1.setColspan(4);
            table3.addCell(header);
            table3.addCell(new Paragraph("Data Solicitação", font));
            table3.addCell("" + g.getDatasolicitanteDate());
            table3.addCell(new Paragraph("Atendimento Prioritário", font));
            String prioritario = "";
            if(g.isAtendimentoprioritario() == true){
                prioritario = "SIM";
            }else{
                prioritario = "NÃO";
            }
            table3.addCell("" + prioritario);
            
            doc.add(table3);
            
            PdfPTable table4 = new PdfPTable(new float[]{0.2f, 0.8f, 0.2f, 0.8f});
            PdfPCell header2 = new PdfPCell(new Paragraph("Dados da Unidade Solicitante", font));
            table4.setWidthPercentage((float) 100.00);
            header2.setColspan(4);
            table4.addCell(header2);
            table4.addCell(new Paragraph("Unidade", font));
            table4.addCell("" + g.getUnidadesolicitante().getNome());
            table4.addCell(new Paragraph("Endereço", font));
            table4.addCell("" + g.getUnidadesolicitante().getLogradouro());
            table4.addCell(new Paragraph("Observação", font));
            table4.addCell("" + g.getUnidadesolicitante().getObservacao());
            table4.addCell(new Paragraph("Atenção", font));
            table4.addCell("" + g.getUnidadesolicitante().getAtencao());
            doc.add(table4);
            
//            Font f4 = new Font(FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
//            Paragraph linhaAss = new Paragraph("____________________________________________________________________________", f);
//            linhaAss.setAlignment(Element.ALIGN_CENTER);
//            doc.add(linhaAss);
//            
//            Paragraph via = new Paragraph("PACIENTE", f);
//            linhaAss.setAlignment(Element.ALIGN_CENTER);
//            doc.add(linhaAss);
            
            Font f5 = new Font(FontFamily.TIMES_ROMAN, 8, Font.ITALIC);
            Paragraph p5 = new Paragraph("via da Secretaria Municipal de Saúde", f5);
            p5.setAlignment(Element.ALIGN_RIGHT);
            doc.add(p5);
            
            String linha = "______________________________________________________________________________________";
            Paragraph p6 = new Paragraph(linha, f5);
            Paragraph p7 = new Paragraph("ASSINATURA DO PACIENTE", f5);
            p7.setAlignment(Element.ALIGN_CENTER);
            doc.add(p6);
            doc.add(p7);
            
            java.awt.Desktop.getDesktop().open(new File("C:\\Users\\José\\Documents\\relatorios\\GuiaAtendimentoRelatorio.pdf"));
            

        } finally {
            if (doc != null) {
                //fechamento do documento
                doc.close();
            }
            if (os != null) {
                //fechamento da stream de saída
                os.close();
            }
        }
    }
}
