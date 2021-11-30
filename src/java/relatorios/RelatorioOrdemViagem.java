package relatorios;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controladores.ControladorMedico;
import controladores.ControladorOrdemViagem;
import controladores.ControladorPaciente;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import negocio.OrdemViagem;

@ManagedBean(name = "relOrdemViagem")
@SessionScoped
public class RelatorioOrdemViagem {

    private static final Font fonteCabecalho = new Font(Font.FontFamily.TIMES_ROMAN, 13,
            Font.BOLD);
    private static final Font fonteSunblinhada = new Font(Font.FontFamily.TIMES_ROMAN, 9,
            Font.UNDERLINE);

    private static final Font NEGRITOMEDIA = new Font(Font.FontFamily.TIMES_ROMAN, 9,
            Font.BOLD);

    private static final Font fonteSubtitulo = new Font(Font.FontFamily.TIMES_ROMAN, 8,
            Font.BOLD);

    private static final Font fonteSubtituloExame = new Font(Font.FontFamily.TIMES_ROMAN, 9,
            Font.UNDERLINE);

    private static final Font fontePadrao = new Font(Font.FontFamily.TIMES_ROMAN, 9);
    private static final Font fonteMenor = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD);

    private static final Font fonteAlinhamento = new Font(Font.FontFamily.TIMES_ROMAN, 9);
    private static final Font fonteVermelha = new Font(Font.FontFamily.TIMES_ROMAN,
            9, Font.NORMAL, BaseColor.RED);
    private static final Font negritoPequena = new Font(Font.FontFamily.TIMES_ROMAN,
            9, Font.BOLD);
    private static final Font negritoGrande = new Font(Font.FontFamily.TIMES_ROMAN,
            9, Font.BOLD);
    private static final ControladorMedico cm = new ControladorMedico();

    private static final ControladorOrdemViagem ce = new ControladorOrdemViagem();

    private static final ControladorPaciente pa = new ControladorPaciente();
    // private static final ControladorEhExame eh = new ControladorEhExame();

    ArrayList<OrdemViagem> ov = (ArrayList<OrdemViagem>) ce.recuperarTodosOrdemViagens();

    public void emitir(OrdemViagem ov) {

        //Variáveis para adicionar formatação 
        int numeroPage = 0;
        String a = "                                          ";
        String s = "                                                ";
        String b = "                                                                                   ";
        String h = "                                                             ";
        String linha = "_______________________________________________________________________";
        String linhaTracejada = "------------------------------------------------------------"
                + "---------------------------------------------------";
        String quebraLinha = "";
        float borda = 0.0000000000000000000000000000000000000000001f;
        String verificaPrioritario = "";
        String verificaAcomp = "";
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
            Document documento = new Document(PageSize.A4, 72, 72, 72, 72);

            document.setPageSize(PageSize.A4.rotate());
//            document.setPageSize(PageSize.A4.rotate();
            document.setPageCount(numeroPage);
            PdfWriter.getInstance(document, baos);
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\São Luiz\\Documents\\relatorios\\OrdemViagemRelatorio.pdf"));
            
            document.open();

            // adicionando um parágrafo no documento
            // document.add(new Paragraph("Prefeitura municipal de Agelim"));
            Image imagem = Image.getInstance("C:\\Users\\São Luiz\\Desktop\\apache-tomcat-7.0.67\\webapps\\SecSaude3\\img\\Angelim.fw.jpg");
            //Inserir imagem do Recibo
            int ab = 10;
            imagem.setAlignment(Element.ALIGN_UNDEFINED);

            imagem.scaleAbsoluteWidth(45);
            imagem.setAlignment(ab);
            imagem.scaleAbsoluteHeight(45);

            // Paragraph titulo = new Paragraph("RUA BELA VISTA, 12 /N CENTRO /N 55430-00 ANGELIM - PE /N Inscrição do CNPJ.:",fontePadrao );
//            Paragraph subtitulo = new Paragraph("LABORATÓRIO DE ANÁLISES CLÍNICAS", fonteSubtitulo);
//            Paragraph l1 = new Paragraph("Rua Hermenegildo Sena S/N                          "
//                    + "                                                       Fone: (87) 3788-1156", negritoPequena);
//            subtitulo.setAlignment(Element.ALIGN_LEFT);
            //titulo.setAlignment(Element.ALIGN_LEFT);
            Paragraph titulo = new Paragraph("FUNDO MUNICIPAL DE SAÚDE DE ANGELIM", fonteCabecalho);
            titulo.setAlignment(Element.ALIGN_CENTER);
//            titulo.setFirstLineIndent(45);
            // document.add(titulo);

            document.add(imagem);

            PdfPCell branc = new PdfPCell(new Paragraph(" ", fontePadrao));
            PdfPCell fundoDeSaude = new PdfPCell(new Paragraph("  FUNDO MUNICIPAL DE SAÚDE DE ANGELIM", fonteCabecalho));

            branc.setBorder(0);
            fundoDeSaude.setBorder(2);

            PdfPTable inicio = new PdfPTable(new float[]{0.2f, 0.6f, 0.2f});

            inicio.addCell(branc);
            inicio.addCell(fundoDeSaude);
            inicio.addCell(branc);

            inicio.setWidthPercentage(100.0f);
            inicio.setHorizontalAlignment(Element.ALIGN_LEFT);
            document.add(inicio);

            Paragraph subtitulo = new Paragraph("Endereço: Rua Bela Vista, 12, Centro - CEP: 55430-00 - Fone/Fax: (87) 3788-1156 - Ramais: 226/238 \n"
                    + "                CNPJ: 19.908.6600001-29", fontePadrao);

            subtitulo.setAlignment(Element.ALIGN_CENTER);

            document.add(subtitulo);

            Paragraph secretaria = new Paragraph("Secretaria Municipal de Saúde  - Sistema Único de Saúde / SUS", NEGRITOMEDIA);
            secretaria.setAlignment(Element.ALIGN_CENTER);

            document.add(secretaria);

            document.add(new Paragraph(" ", fontePadrao));

            Paragraph declaracao = new Paragraph("DECLARAÇÃO", fonteSunblinhada);
            declaracao.setAlignment(Element.ALIGN_CENTER);
            document.add(declaracao);

            Paragraph dec = new Paragraph("DECLARO", fonteSunblinhada);

            Paragraph declaro = new Paragraph("DECLARO para os devidos fins de regularide de transporte de pacientes para Tratamento Fora de Domicílio - TFD, que este veículo. nesta data. está a serviço da Secretaria Municipal de Saúde do Município de Angelim - PE");
            document.add(declaro);

            document.add(new Paragraph(" ", fontePadrao));

            PdfPCell branc1 = new PdfPCell(new Paragraph(" ", fonteMenor));
            PdfPCell dadosViagem = new PdfPCell(new Paragraph("Dados da viagem", fonteMenor));
            PdfPCell data = new PdfPCell(new Paragraph("Data", fonteMenor));
            PdfPCell destino = new PdfPCell(new Paragraph("Destino", fonteMenor));
            PdfPCell localCompactual = new PdfPCell(new Paragraph("Locadora Compactual", fonteMenor));
            PdfPCell motorista = new PdfPCell(new Paragraph("Motorista", fonteMenor));
            PdfPCell veiculo = new PdfPCell(new Paragraph("Veiculo", fonteMenor));
            PdfPCell placa = new PdfPCell(new Paragraph("Placa", fonteMenor));

            branc1.setBorder(0);
            destino.setBackgroundColor(BaseColor.GRAY);
            data.setBackgroundColor(BaseColor.GRAY);
            motorista.setBackgroundColor(BaseColor.GRAY);
            veiculo.setBackgroundColor(BaseColor.GRAY);
            placa.setBackgroundColor(BaseColor.GRAY);

            PdfPCell dataV = new PdfPCell(new Paragraph(ov.getDataviagemDate(), fonteMenor));
            PdfPCell destinoV = new PdfPCell(new Paragraph(ov.getCidadedestino(), fonteMenor));
            for (int i = 0; i < ov.getPacientes().size(); i++) {
                PdfPCell localCompactualV = new PdfPCell(new Paragraph("" + ov.getPacientes().get(i).getHospitalDestino(), fonteMenor));
            }
            PdfPCell motoristaV = new PdfPCell(new Paragraph(ov.getMotorista().getNome(), fonteMenor));
            PdfPCell veiculoV = new PdfPCell(new Paragraph(ov.getVeiculo().getModelo(), fonteMenor));
            PdfPCell placaV = new PdfPCell(new Paragraph(ov.getVeiculo().getPlaca(), fonteMenor));

            PdfPTable dadosViage = new PdfPTable(new float[]{0.16f, 0.16f, 0.16f, 0.26f, 0.16f, 0.1f});
            branc1.setColspan(2);
            localCompactual.setColspan(3);

            data.setColspan(2);
            dataV.setColspan(2);
            localCompactual.setColspan(3);

            localCompactual.setColspan(3);

            dadosViage.addCell(dadosViagem);
            dadosViage.addCell(branc1);
            dadosViage.addCell(localCompactual);

            dadosViage.addCell(data);
            dadosViage.addCell(destino);

            dadosViage.addCell(motorista);
            dadosViage.addCell(veiculo);
            dadosViage.addCell(placa);

            dadosViage.addCell(dataV);
            dadosViage.addCell(destinoV);
            dadosViage.addCell(motoristaV);
            dadosViage.addCell(veiculoV);
            dadosViage.addCell(placaV);

            // dadosViage.addCell(branc);
            dadosViage.setWidthPercentage(100.0f);
            dadosViage.setHorizontalAlignment(Element.ALIGN_LEFT);
            document.add(dadosViage);

            document.add(new Paragraph(" ", fontePadrao));

            // NOVA TABELA PARA ADICIONAR DADOS GERAIS
            PdfPCell branc2 = new PdfPCell(new Paragraph(" ", fonteMenor));
            PdfPCell paciente = new PdfPCell(new Paragraph("Paciente \n ", fonteMenor));
            PdfPCell acomp = new PdfPCell(new Paragraph("Acompanhante", fonteMenor));
            PdfPCell hospDest = new PdfPCell(new Paragraph("Hopital de destino", fonteMenor));
            PdfPCell horaConsul = new PdfPCell(new Paragraph(" Horário da consulta", fonteMenor));
            PdfPCell local = new PdfPCell(new Paragraph("Local para buscar paciente", fonteMenor));

            paciente.setBackgroundColor(BaseColor.GRAY);
            acomp.setBackgroundColor(BaseColor.GRAY);
            hospDest.setBackgroundColor(BaseColor.GRAY);
            horaConsul.setBackgroundColor(BaseColor.GRAY);
            local.setBackgroundColor(BaseColor.GRAY);

            PdfPTable tabelaFinal = new PdfPTable(new float[]{0.2f, 0.2f, 0.2f, 0.2f, 0.2f});

            tabelaFinal.addCell(paciente);
            tabelaFinal.addCell(acomp);
            tabelaFinal.addCell(hospDest);
            tabelaFinal.addCell(horaConsul);
            tabelaFinal.addCell(local);

            for (int i = 0; i < ov.getPacientes().size(); i++) {
                if (!ov.getPacientes().isEmpty()) {
                    if (ov.getPacientes().get(i).isAcompanhante() == true) {
                        verificaAcomp = "SIM";
                    } else {
                        verificaAcomp = "NÃO";
                    }
                }

//                            if(ov.get(i).getAcompanhantes().get(i) == true){
//                           verificaAcomp = "SIM";
//                           }else{
//                                verificaAcomp = "NÃO";
//                           }
//                            
                PdfPCell pacienteV = new PdfPCell(new Paragraph(ov.getPacientes().get(i).getNome(), fonteMenor));
                PdfPCell branc2V = new PdfPCell(new Paragraph(" ", fonteMenor));
                PdfPCell acompV = new PdfPCell(new Paragraph(verificaAcomp, fonteMenor));
                PdfPCell hospDestV = new PdfPCell(new Paragraph(ov.getPacientes().get(i).getHospitalDestino(), fonteMenor));
                PdfPCell horaConsulta = new PdfPCell();
                horaConsulta = new PdfPCell(new Paragraph("" + ov.getPacientes().get(i).getHorarioConsulta(), fonteMenor));
                //PdfPCell horaConsulV = new PdfPCell(new Paragraph(""+ov.get(i).getHorariosconsultas().get(i).getDataDeViagem(), fonteMenor));
                PdfPCell localV = new PdfPCell(new Paragraph(" Unidade Mista Santa Terezinha", fonteMenor));

                tabelaFinal.addCell(pacienteV);
                tabelaFinal.addCell(acompV);
                tabelaFinal.addCell(hospDestV);
                tabelaFinal.addCell(horaConsulta);
                tabelaFinal.addCell(localV);
                // Terminar o for

            }

            tabelaFinal.setWidthPercentage(100.0f);
            tabelaFinal.setHorizontalAlignment(Element.ALIGN_LEFT);
            document.add(tabelaFinal);

            Paragraph atenciosamente = new Paragraph("Atenciosamente");
            atenciosamente.setAlignment(Element.ALIGN_CENTER);
            document.add(atenciosamente);
            
            Paragraph prefeito = new Paragraph("Vanderluce Pereira Calado", NEGRITOMEDIA);
            prefeito.setAlignment(Element.ALIGN_CENTER);
            document.add(prefeito);
            
            Paragraph sec = new Paragraph("Secretaria de Saúde");
            sec.setAlignment(Element.ALIGN_CENTER);
            document.add(sec);
            
            Paragraph portaria = new Paragraph("PORTARIA ");
            portaria.setAlignment(Element.ALIGN_CENTER);
            document.add(portaria);

//
//            //Para adicionar o nome os dados do paciente no pagina pdf
            //Metodo usando um forech
            // TIPO DE EXAME HEMOGRAMA
            java.awt.Desktop.getDesktop().open(new File("C:\\Users\\São Luiz\\Documents\\relatorios\\OrdemViagemRelatorio.pdf"));

        } catch (DocumentException de) {
            de.getMessage();
        } catch (IOException ioe) {
            ioe.getMessage();
        }       
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
        document.close();
    }

}
