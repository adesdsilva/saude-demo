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
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controladores.ControladorGuiaAtendimento;
import controladores.ControladorMedico;
import controladores.ControladorPaciente;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.swing.JOptionPane;
import negocio.GuiaAutorizacao;
import repositorios.RepositorioGuiaAtendimento;

/**
 *
 * @author adelino
 */
@ManagedBean(name = "rel")
@SessionScoped
@ViewScoped
public class RelatorioGuiaStatusAutorizado {
    
    private static final Font fonteCabecalho = new Font(Font.FontFamily.TIMES_ROMAN, 8,
            Font.BOLD);
    private static final Font fonteSubtitulo = new Font(Font.FontFamily.TIMES_ROMAN, 8,
            Font.BOLD);

    private static final Font fonteSubtituloExame = new Font(Font.FontFamily.TIMES_ROMAN, 8,
            Font.UNDERLINE);

    private static final Font fontePadrao = new Font(Font.FontFamily.TIMES_ROMAN, 8);
    private static final Font fonteMenor = new Font(Font.FontFamily.TIMES_ROMAN, 8);

    private static final Font fonteAlinhamento = new Font(Font.FontFamily.TIMES_ROMAN, 8);
    private static final Font fonteVermelha = new Font(Font.FontFamily.TIMES_ROMAN,
            8, Font.NORMAL, BaseColor.RED);
    private static final Font negritoPequena = new Font(Font.FontFamily.TIMES_ROMAN,
            8, Font.BOLD);
    private static final Font negritoGrande = new Font(Font.FontFamily.TIMES_ROMAN,
            8, Font.BOLD);
    private static ControladorMedico cm = new ControladorMedico();

    private static ControladorGuiaAtendimento ce = new ControladorGuiaAtendimento();

    private static RepositorioGuiaAtendimento repGuia = null;

    private static ControladorPaciente pa = new ControladorPaciente();
    
    public void emitir(GuiaAutorizacao ga) {

        //Variáveis para adicionar formatação 
        int numeroPage = 0;
        String a = "                                          ";
        String s = "                                                ";
        String b = "                                                                                   ";
        String h = "                                                             ";
        String linha = "______________________________________________________________________________________";
        String linhaTracejada = "------------------------------------------------------------"
                + "---------------------------------------------------";
        String quebraLinha = "";

        String statusVerifica = "";
        float borda = 0.0000000000000000000000000000000000000000001f;
        String verificaPrioritario = "";
        Document document = new Document();
        try {
            Document documento = new Document(PageSize.A4, 72, 72, 72, 72);

            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\São Luiz\\Documents\\relatorios\\GuiaAutorizacaoRelatorio.pdf"));
            document.open();

            // adicionando um parágrafo no documento
            // document.add(new Paragraph("Prefeitura municipal de Agelim"));
            Image imagem = Image.getInstance("C:\\Users\\São Luiz\\Desktop\\apache-tomcat-7.0.67\\webapps\\SecSaude3\\img\\Angelim.fw.jpg");
            //Inserir imagem do Recibo
            int ab = 26;
            //imagem.setAlignment(Element.ALIGN_UNDEFINED); 

            imagem.scaleAbsoluteWidth(65);
//            imagem.setAlignment(ab);
            imagem.scaleAbsoluteHeight(65);
            
            

            // Paragraph titulo = new Paragraph("RUA BELA VISTA, 12 /N CENTRO /N 55430-00 ANGELIM - PE /N Inscrição do CNPJ.:",fontePadrao );
//            Paragraph subtitulo = new Paragraph("LABORATÓRIO DE ANÁLISES CLÍNICAS", fonteSubtitulo);
//            Paragraph l1 = new Paragraph("Rua Hermenegildo Sena S/N                          "
//                    + "                                                       Fone: (87) 3788-1156", negritoPequena);
//            subtitulo.setAlignment(Element.ALIGN_LEFT);
            //titulo.setAlignment(Element.ALIGN_LEFT);
            PdfPCell branc = new PdfPCell(new Paragraph("", fontePadrao));
            PdfPCell brancD = new PdfPCell(new Paragraph("", fontePadrao));
            document.add(imagem);
            PdfPCell fundo = new PdfPCell(new Paragraph("FUNDO MUNICIPAL DE SAÚDE DE ANGELIM ", negritoPequena));
            PdfPCell rua = new PdfPCell(new Paragraph("RUA BELA VISTA, Nº12", fontePadrao));
            PdfPCell centro = new PdfPCell(new Paragraph("CENTRO", fontePadrao));
            PdfPCell cep = new PdfPCell(new Paragraph("55430-000 ANGELIM - PE", fontePadrao));
            PdfPCell insc = new PdfPCell(new Paragraph("Inscrição do CNPJ.:", fontePadrao));
            PdfPCell fone = new PdfPCell(new Paragraph("Fone: ", fontePadrao));

            fundo.setBorder(0);

            rua.setBorder(0);
            brancD.setBorder(0);
            branc.setBorder(0);

            rua.setBorder(0);
            centro.setBorder(0);
            cep.setBorder(0);
            insc.setBorder(0);

            fone.setBorder(0);

            PdfPTable GUIAAUT = new PdfPTable(new float[]{0.2f, 0.4f, 0.2f, 0.2f});
            //setColspan(4);

            GUIAAUT.addCell(branc);
            GUIAAUT.addCell(fundo);
            GUIAAUT.addCell(branc);
            GUIAAUT.addCell(brancD);

            GUIAAUT.addCell(branc);
            GUIAAUT.addCell(rua);
            GUIAAUT.addCell(brancD);
            GUIAAUT.addCell(branc);
            GUIAAUT.addCell(branc);
            GUIAAUT.addCell(centro);
            GUIAAUT.addCell(branc);
            GUIAAUT.addCell(branc);
            GUIAAUT.addCell(branc);
            GUIAAUT.addCell(cep);
            GUIAAUT.addCell(branc);
            GUIAAUT.addCell(branc);
            GUIAAUT.addCell(branc);
            GUIAAUT.addCell(insc);
            GUIAAUT.addCell(fone);
            GUIAAUT.addCell(branc);
            //  GUIAAUT.addCell(guia);

            GUIAAUT.setWidthPercentage(100.0f);
            GUIAAUT.setHorizontalAlignment(Element.ALIGN_LEFT);
            document.add(GUIAAUT);

            Paragraph guia = new Paragraph("GUIA DE AUTORIZAÇÃO Nº: 00000" + ga.getCodigoguiaautorizacao()+ " ", fonteSubtituloExame);
            Paragraph BR = new Paragraph(" ", negritoGrande);
            guia.setAlignment(Element.ALIGN_CENTER);

            document.add(guia);
            document.add(BR);

            PdfPCell branc1 = new PdfPCell(new Paragraph("", negritoGrande));
            PdfPCell paciente = new PdfPCell(new Paragraph("PACIENTE..:", negritoGrande));
            PdfPCell endereco = new PdfPCell(new Paragraph("ENDEREÇO:", negritoGrande));
            PdfPCell bairro = new PdfPCell(new Paragraph("BAIRRO......: ", negritoGrande));
            PdfPCell cidade = new PdfPCell(new Paragraph("CIDADE......:", negritoGrande));
            PdfPCell ags = new PdfPCell(new Paragraph("AGS..............:", negritoGrande));
            PdfPCell CEP = new PdfPCell(new Paragraph("CEP............:", negritoGrande));
            PdfPCell ESTADO = new PdfPCell(new Paragraph("ESTADO...:", negritoGrande));
            PdfPCell CPF = new PdfPCell(new Paragraph("CPF...........:", negritoGrande));
            PdfPCell RG = new PdfPCell(new Paragraph("RG.............:", negritoGrande));
            PdfPCell TITULO_ELEITOR = new PdfPCell(new Paragraph("TITULO....:", negritoGrande));
            PdfPCell CARTAO_SUS = new PdfPCell(new Paragraph("CARD-SUS:", negritoGrande));

            branc1.setBorder(0);
            paciente.setBorder(0);
            endereco.setBorder(0);
            bairro.setBorder(0);
            cidade.setBorder(0);
            ags.setBorder(0);
            CEP.setBorder(0);
            ESTADO.setBorder(0);
            CPF.setBorder(0);
            RG.setBorder(0);
            TITULO_ELEITOR.setBorder(0);
            CARTAO_SUS.setBorder(0);

            PdfPCell branc1V = new PdfPCell(new Paragraph("", fontePadrao));
            PdfPCell pacienteV = new PdfPCell(new Paragraph(ga.getPaciente().getNome(), fontePadrao));

            PdfPCell enderecoV = new PdfPCell(new Paragraph(ga.getPaciente().getLogradouro(), fontePadrao));
            PdfPCell bairroV = new PdfPCell(new Paragraph(ga.getPaciente().getBairro(), fontePadrao));
            PdfPCell cidadeV = new PdfPCell(new Paragraph(ga.getPaciente().getCidade(), fontePadrao));
            PdfPCell agsV = new PdfPCell(new Paragraph(ga.getPaciente().getAgentesaude(), fontePadrao));
            PdfPCell CEPV = new PdfPCell(new Paragraph(ga.getPaciente().getCep(), fontePadrao));
            PdfPCell ESTADOV = new PdfPCell(new Paragraph(ga.getPaciente().getEstado(), fontePadrao));
            PdfPCell CPFV = new PdfPCell(new Paragraph(ga.getPaciente().getCpf(), fontePadrao));
            PdfPCell RGV = new PdfPCell(new Paragraph(ga.getPaciente().getRg(), fontePadrao));
            PdfPCell TITULO_ELEITORV = new PdfPCell(new Paragraph(ga.getPaciente().getTitulozona(), fontePadrao));
            PdfPCell CARTAO_SUSV = new PdfPCell(new Paragraph(ga.getPaciente().getCartaosus(), fontePadrao));

            branc1V.setBorder(0);
            pacienteV.setBorder(0);
            enderecoV.setBorder(0);
            bairroV.setBorder(0);
            cidadeV.setBorder(0);
            agsV.setBorder(0);
            CEPV.setBorder(0);
            ESTADOV.setBorder(0);
            CPFV.setBorder(0);
            RGV.setBorder(0);
            TITULO_ELEITORV.setBorder(0);
            CARTAO_SUSV.setBorder(0);

            PdfPTable dadosAutorizacao = new PdfPTable(new float[]{0.1f, 0.3f, 0.1f, 0.2f, 0.1f, 0.2f});

            dadosAutorizacao.addCell(paciente);
            dadosAutorizacao.addCell(pacienteV);
            dadosAutorizacao.addCell(branc1);
            dadosAutorizacao.addCell(branc1);
            dadosAutorizacao.addCell(CPF);
            dadosAutorizacao.addCell(CPFV);
            dadosAutorizacao.addCell(endereco);
            dadosAutorizacao.addCell(enderecoV);
            dadosAutorizacao.addCell(branc1);
            dadosAutorizacao.addCell(branc1);
            dadosAutorizacao.addCell(RG);
            dadosAutorizacao.addCell(RGV);
            dadosAutorizacao.addCell(bairro);
            dadosAutorizacao.addCell(bairroV);
            dadosAutorizacao.addCell(branc1);
            dadosAutorizacao.addCell(branc1);
            dadosAutorizacao.addCell(CEP);
            dadosAutorizacao.addCell(CEPV);
            dadosAutorizacao.addCell(cidade);
            dadosAutorizacao.addCell(cidadeV);
            dadosAutorizacao.addCell(branc1);
            dadosAutorizacao.addCell(branc1);
            dadosAutorizacao.addCell(ESTADO);
            dadosAutorizacao.addCell(ESTADOV);
            dadosAutorizacao.addCell(ags);
            dadosAutorizacao.addCell(agsV);
            dadosAutorizacao.addCell(branc1);
            dadosAutorizacao.addCell(branc1);
            dadosAutorizacao.addCell(branc1);
            dadosAutorizacao.addCell(branc1);

            dadosAutorizacao.setWidthPercentage(100.0f);
            dadosAutorizacao.setHorizontalAlignment(Element.ALIGN_LEFT);
            document.add(dadosAutorizacao);

            //Editar o procedimento solicitado
            if (ga.isStatus() == true) {
                statusVerifica = "AUTORIZADO";
            } else {
                statusVerifica = "PENDENTE";
            }

            //JOptionPane.showMessageDialog(null, ce.getSelectedGuiaAtendimento().getPaciente().getNome());
            PdfPCell branco2 = new PdfPCell(new Paragraph("", fontePadrao));
            PdfPCell aten = new PdfPCell(new Paragraph("ATENDIDO POR \n" + ga.getUsuario().getNome(), fontePadrao));
            PdfPCell pro = new PdfPCell(new Paragraph(":::PROCEDIMENTO SOLICITADO:::", fonteAlinhamento));
            PdfPCell status = new PdfPCell(new Paragraph("STATUS", fontePadrao));

            branco2.setBorder(0);
            aten.setBorder(0);
            pro.setBorder(0);
            status.setBorder(0);

            PdfPCell statusV = new PdfPCell(new Paragraph(statusVerifica, fontePadrao));
            statusV.setBorder(0);

            PdfPTable procedimento = new PdfPTable(new float[]{0.2f, 0.5f, 0.1f, 0.2f});

            procedimento.addCell(aten);
            procedimento.addCell(pro);
            procedimento.addCell(status);
            procedimento.addCell(statusV);

            procedimento.setWidthPercentage(100.0f);
            procedimento.setHorizontalAlignment(Element.ALIGN_LEFT);
            document.add(procedimento);

            //Tabela para Descrição do procedimento solicitado
            PdfPCell branco3 = new PdfPCell(new Paragraph("", fontePadrao));
            PdfPCell codigo = new PdfPCell(new Paragraph("CÓDIGO", negritoGrande));
            PdfPCell descricao = new PdfPCell(new Paragraph("DESCRIÇÃO DO PROCEDIMENTO SOLICITADO", negritoGrande));

            PdfPCell codigoV = new PdfPCell(new Paragraph(" " + ga.getProcedimento().getCodigoprocedimento(), fontePadrao));
            PdfPCell descricaoV = new PdfPCell(new Paragraph(ga.getProcedimento().getNome(), fontePadrao));

            branco3.setBorder(0);
            codigo.setBorder(0);
            descricao.setBorder(0);
            codigoV.setBorder(0);
            descricaoV.setBorder(0);

            PdfPTable DESC = new PdfPTable(new float[]{0.2f, 0.8f});

            DESC.addCell(codigo);
            DESC.addCell(descricao);
            DESC.addCell(codigoV);
            DESC.addCell(descricaoV);

            DESC.setWidthPercentage(100.0f);
            DESC.setHorizontalAlignment(Element.ALIGN_LEFT);
            document.add(DESC);

            document.add(new Paragraph(" ", fontePadrao));
            //Tabela para informações

            PdfPCell branco4 = new PdfPCell(new Paragraph("", fonteMenor));
            PdfPCell brancol = new PdfPCell(new Paragraph("", fonteMenor));
            PdfPCell brancoNormal = new PdfPCell(new Paragraph("", fonteMenor));
            PdfPCell unidS = new PdfPCell(new Paragraph("UNIDADE SOLICITANTE", fonteMenor));
            PdfPCell medicoS = new PdfPCell(new Paragraph("MÉDICO SOLICITANTE", fonteMenor));
            PdfPCell crm = new PdfPCell(new Paragraph("CRM", fonteMenor));
            PdfPCell dataS = new PdfPCell(new Paragraph("DATA SOLICITAÇÃO", fonteMenor));
            PdfPCell dataAut = new PdfPCell(new Paragraph("DATA AUTORIZAÇÃO", fonteMenor));
            PdfPCell dataAg = new PdfPCell(new Paragraph("DATA AGENDADA", fonteMenor));
            PdfPCell horaAg = new PdfPCell(new Paragraph("HORA AGENDADA", fonteMenor));
            PdfPCell senhaAten = new PdfPCell(new Paragraph("SENHA DE ATENDIMENTO", fonteMenor));
            PdfPCell atenPri = new PdfPCell(new Paragraph("ATENDIMENTO PRIORITÁRIO", fonteMenor));
            PdfPCell unidAtend = new PdfPCell(new Paragraph("UNIDADE PARA ATENDIMENTO", fonteMenor));
            PdfPCell obs = new PdfPCell(new Paragraph("OBSERVAÇÃO", fonteMenor));
            PdfPCell enderecoAten = new PdfPCell(new Paragraph("ENDERECO", fonteMenor));
            PdfPCell atenA = new PdfPCell(new Paragraph("ATENÇÃO", fonteMenor));
            PdfPCell viaS = new PdfPCell(new Paragraph("VIA DA SECRETARIA MUNICIPAL DE SAÚDE", fonteMenor));
            PdfPCell pacienteS = new PdfPCell(new Paragraph("                                                             (ASSINATURA DO PACIENTE)", fonteMenor));

            //Está faltando o valores vindo do banco de dados
            crm.setBorder(0);
            brancoNormal.setBorder(0);
            brancol.setBorder(2);
            branco4.setBorder(0);
            atenPri.setBorder(0);
            unidS.setBorder(0);
            medicoS.setBorder(0);
            dataS.setBorder(0);
            dataAut.setBorder(0);
            dataAg.setBorder(0);
            horaAg.setBorder(0);
            senhaAten.setBorder(0);
            unidAtend.setBorder(0);
            obs.setBorder(0);
            enderecoAten.setBorder(0);
            atenA.setBorder(0);
            viaS.setBorder(0);
            pacienteS.setBorder(0);

            if (ce.getSelectedGuiaAtendimento().isAtendimentoprioritario() == true) {
                verificaPrioritario = "SIM";
            } else {
                verificaPrioritario = "NÃO";
            }

            //Falta usar os dados do banco    
            PdfPCell unidSV = new PdfPCell(new Paragraph(ga.getUnidadesolicitante().getNome(), fonteMenor));
            PdfPCell medicoSV = new PdfPCell(new Paragraph(ga.getMedico().getNome(), fonteMenor));
            PdfPCell crmV = new PdfPCell(new Paragraph(ga.getMedico().getCrm(), fonteMenor));
            PdfPCell dataSV = new PdfPCell(new Paragraph(ga.getDatasolicitanteDate(), fonteMenor));
            PdfPCell dataAutV = new PdfPCell(new Paragraph(ga.getDataautorizacaoDate(), fonteMenor));
            PdfPCell dataAgV = new PdfPCell(new Paragraph(ga.getDataagendadaDate(), fonteMenor));
            PdfPCell horaAgV = new PdfPCell(new Paragraph(ga.getHoraagendada(), fonteMenor));
            PdfPCell senhaAtenV = new PdfPCell(new Paragraph(" ", fonteMenor));
            PdfPCell atenPriV = new PdfPCell(new Paragraph(verificaPrioritario, fonteMenor));
            PdfPCell unidAtendV = new PdfPCell(new Paragraph(ga.getUnidadeatendimento().getNome(), fonteMenor));
            PdfPCell obsV = new PdfPCell(new Paragraph(ga.getObservacao(), fonteMenor));
            PdfPCell enderecoAtenV = new PdfPCell(new Paragraph(ga.getUnidadeatendimento().getLogradouro(), fonteMenor));
            PdfPCell atenAV = new PdfPCell(new Paragraph(ga.getAtencao(), fonteMenor));

            unidSV.setBorder(0);
            medicoSV.setBorder(0);
            crmV.setBorder(0);
            dataSV.setBorder(0);
            dataAutV.setBorder(0);
            dataAgV.setBorder(0);
            horaAgV.setBorder(0);
            senhaAtenV.setBorder(0);
            unidAtendV.setBorder(0);
            obsV.setBorder(0);
            enderecoAtenV.setBorder(0);
            atenAV.setBorder(0);
            atenPriV.setBorder(0);

            atenPriV.setBorderColorBottom(BaseColor.RED);

          // antes de usar esta parte do código termine o cadastro no banco  
            PdfPTable informaAdicionais = new PdfPTable(new float[]{0.16f, 0.16f, 0.16f, 0.16f, 0.16f, 0.2f});
            unidS.setColspan(3);
            unidSV.setColspan(3);

            medicoS.setColspan(2);
            medicoSV.setColspan(2);

            unidAtend.setColspan(3);
            unidAtendV.setColspan(3);

            enderecoAten.setColspan(3);
            enderecoAtenV.setColspan(3);

            obs.setColspan(3);
            obsV.setColspan(3);

            atenA.setColspan(3);
            atenAV.setColspan(3);

            branco4.setColspan(6);
            pacienteS.setColspan(4);
            brancol.setColspan(4);
            viaS.setColspan(4);
            brancoNormal.setColspan(2);

            informaAdicionais.addCell(unidS);
            informaAdicionais.addCell(medicoS);
            informaAdicionais.addCell(crm);
            informaAdicionais.addCell(unidSV);
            informaAdicionais.addCell(medicoSV);
            informaAdicionais.addCell(crmV);

            informaAdicionais.addCell(dataS);
            informaAdicionais.addCell(dataAut);
            informaAdicionais.addCell(dataAg);
            informaAdicionais.addCell(horaAg);
            informaAdicionais.addCell(senhaAten);
            informaAdicionais.addCell(atenPri);

            informaAdicionais.addCell(dataSV);
            informaAdicionais.addCell(dataAutV);
            informaAdicionais.addCell(dataAgV);
            informaAdicionais.addCell(horaAgV);
            informaAdicionais.addCell(senhaAtenV);
            informaAdicionais.addCell(atenPriV);

            informaAdicionais.addCell(unidAtend);
            informaAdicionais.addCell(enderecoAten);

            informaAdicionais.addCell(unidAtendV);
            informaAdicionais.addCell(enderecoAtenV);

            informaAdicionais.addCell(obs);
            informaAdicionais.addCell(atenA);

            informaAdicionais.addCell(obsV);
            informaAdicionais.addCell(atenAV);

            informaAdicionais.addCell(branco4);
            informaAdicionais.addCell(branco4);
            informaAdicionais.addCell(branco4);
            informaAdicionais.addCell(brancol);
            informaAdicionais.addCell(brancoNormal);
            informaAdicionais.addCell(pacienteS);
            informaAdicionais.addCell(viaS);

            informaAdicionais.setWidthPercentage(100.0f);
            informaAdicionais.setHorizontalAlignment(Element.ALIGN_LEFT);
            document.add(informaAdicionais);

            Paragraph subtitulo = new Paragraph(linhaTracejada, fonteCabecalho);
            document.add(subtitulo);

            // document.add(new Paragraph(linhaTracejada, negritoPequena));
            
            PdfPCell branc11 = new PdfPCell(new Paragraph("", fontePadrao));
            PdfPCell brancD1 = new PdfPCell(new Paragraph("", fontePadrao));
            PdfPCell fundo1 = new PdfPCell(new Paragraph("FUNDO MUNICIPAL DE SAÚDE DE ANGELIM ", negritoPequena));
            PdfPCell rua1 = new PdfPCell(new Paragraph("RUA BELA VISTA, Nº12", fontePadrao));
            PdfPCell centro1 = new PdfPCell(new Paragraph("CENTRO", fontePadrao));
            PdfPCell cep1 = new PdfPCell(new Paragraph("55430-000 ANGELIM - PE", fontePadrao));
            PdfPCell insc1 = new PdfPCell(new Paragraph("Inscrição do CNPJ.:", fontePadrao));
            PdfPCell fone1 = new PdfPCell(new Paragraph("Fone: ", fontePadrao));

            fundo1.setBorder(0);

            rua1.setBorder(0);
            brancD1.setBorder(0);
            branc11.setBorder(0);

            rua1.setBorder(0);
            centro1.setBorder(0);
            cep1.setBorder(0);
            insc1.setBorder(0);

            fone1.setBorder(0);

            PdfPTable GUIAAUTa = new PdfPTable(new float[]{0.2f, 0.4f, 0.2f, 0.2f});
            //setColspan(4);

            GUIAAUT.addCell(branc11);
            GUIAAUT.addCell(fundo1);
            GUIAAUT.addCell(branc11);
            GUIAAUT.addCell(brancD1);

            GUIAAUT.addCell(branc11);
            GUIAAUT.addCell(rua1);
            GUIAAUT.addCell(brancD1);
            GUIAAUT.addCell(branc11);
            GUIAAUT.addCell(branc11);
            GUIAAUT.addCell(centro1);
            GUIAAUT.addCell(branc11);
            GUIAAUT.addCell(branc11);
            GUIAAUT.addCell(branc11);
            GUIAAUT.addCell(cep1);
            GUIAAUT.addCell(branc11);
            GUIAAUT.addCell(branc11);
            GUIAAUT.addCell(branc11);
            GUIAAUT.addCell(insc1);
            GUIAAUT.addCell(fone1);
            GUIAAUT.addCell(branc1);
            //  GUIAAUT.addCell(guia);

            GUIAAUT.setWidthPercentage(100.0f);
            GUIAAUT.setHorizontalAlignment(Element.ALIGN_LEFT);
            document.add(GUIAAUTa);
            
            PdfPCell branc2via = new PdfPCell(new Paragraph("", fontePadrao));
            PdfPCell brancD2via = new PdfPCell(new Paragraph("", fontePadrao));
            //document.add(imagem);
            PdfPCell fundo2via = new PdfPCell(new Paragraph("FUNDO MUNICIPAL DE SAÚDE DE ANGELIM ", negritoPequena));
            PdfPCell rua2via = new PdfPCell(new Paragraph("RUA BELA VISTA, Nº12", fontePadrao));
            PdfPCell centro2via = new PdfPCell(new Paragraph("CENTRO", fontePadrao));
            PdfPCell cep2via = new PdfPCell(new Paragraph("55430-000 ANGELIM - PE", fontePadrao));
            PdfPCell insc2via = new PdfPCell(new Paragraph("Inscrição do CNPJ.:", fontePadrao));
            PdfPCell fone2via = new PdfPCell(new Paragraph("Fone: ", fontePadrao));

            
            
            fundo2via.setBorder(0);

            rua2via.setBorder(0);
            //brancD.setBorder(0);
            branc2via.setBorder(0);

            rua2via.setBorder(0);
            centro2via.setBorder(0);
            cep2via.setBorder(0);
            insc2via.setBorder(0);

            fone2via.setBorder(0);

            PdfPTable GUIAAUT2via = new PdfPTable(new float[]{0.2f, 0.4f, 0.2f, 0.2f});
            //setColspan(4);

            GUIAAUT2via.addCell(branc);
            GUIAAUT2via.addCell(fundo);
            GUIAAUT2via.addCell(branc);
            GUIAAUT2via.addCell(brancD);

            GUIAAUT2via.addCell(branc);
            GUIAAUT2via.addCell(rua);
            GUIAAUT2via.addCell(brancD);
            GUIAAUT2via.addCell(branc);
            GUIAAUT2via.addCell(branc);
            GUIAAUT2via.addCell(centro);
            GUIAAUT2via.addCell(branc);
            GUIAAUT2via.addCell(branc);
            GUIAAUT2via.addCell(branc);
            GUIAAUT2via.addCell(cep);
            GUIAAUT2via.addCell(branc);
            GUIAAUT2via.addCell(branc);
            GUIAAUT2via.addCell(branc);
            GUIAAUT2via.addCell(insc);
            GUIAAUT2via.addCell(fone);
            GUIAAUT2via.addCell(branc);
            //  GUIAAUT.addCell(guia);

            GUIAAUT2via.setWidthPercentage(100.0f);
            GUIAAUT2via.setHorizontalAlignment(Element.ALIGN_LEFT);
            document.add(GUIAAUT2via);

            Paragraph guia2via = new Paragraph("GUIA DE AUTORIZAÇÃO Nº: 00000" + ga.getCodigoguiaautorizacao() + " ", fonteSubtituloExame);
            Paragraph BR2via = new Paragraph(" ", negritoGrande);
            guia2via.setAlignment(Element.ALIGN_CENTER);

            document.add(guia2via);
            document.add(BR2via);

            PdfPCell branc12via = new PdfPCell(new Paragraph("", negritoGrande));
            PdfPCell paciente2via = new PdfPCell(new Paragraph("PACIENTE..:", negritoGrande));
            PdfPCell endereco2via = new PdfPCell(new Paragraph("ENDEREÇO:", negritoGrande));
            PdfPCell bairro2via = new PdfPCell(new Paragraph("BAIRRO......: ", negritoGrande));
            PdfPCell cidade2via = new PdfPCell(new Paragraph("CIDADE......:", negritoGrande));
            PdfPCell ags2via = new PdfPCell(new Paragraph("AGS..............:", negritoGrande));
            PdfPCell CEP2via = new PdfPCell(new Paragraph("CEP............:", negritoGrande));
            PdfPCell ESTADO2via = new PdfPCell(new Paragraph("ESTADO...:", negritoGrande));
            PdfPCell CPF2via = new PdfPCell(new Paragraph("CPF...........:", negritoGrande));
            PdfPCell RG2via = new PdfPCell(new Paragraph("RG.............:", negritoGrande));
            PdfPCell TITULO_ELEITOR2via = new PdfPCell(new Paragraph("TITULO....:", negritoGrande));
            PdfPCell CARTAO_SUS2via = new PdfPCell(new Paragraph("CARD-SUS:", negritoGrande));

            branc12via.setBorder(0);
            paciente2via.setBorder(0);
            endereco2via.setBorder(0);
            bairro2via.setBorder(0);
            cidade2via.setBorder(0);
            ags2via.setBorder(0);
            CEP2via.setBorder(0);
            ESTADO2via.setBorder(0);
            CPF2via.setBorder(0);
            RG2via.setBorder(0);
            TITULO_ELEITOR2via.setBorder(0);
            CARTAO_SUS2via.setBorder(0);

            PdfPCell branc1V2via = new PdfPCell(new Paragraph("", fontePadrao));
            PdfPCell pacienteV2via = new PdfPCell(new Paragraph(ga.getPaciente().getNome(), fontePadrao));

            PdfPCell enderecoV2via = new PdfPCell(new Paragraph(ga.getPaciente().getLogradouro(), fontePadrao));
            PdfPCell bairroV2via = new PdfPCell(new Paragraph(ga.getPaciente().getBairro(), fontePadrao));
            PdfPCell cidadeV2via = new PdfPCell(new Paragraph(ga.getPaciente().getCidade(), fontePadrao));
            PdfPCell agsV2via = new PdfPCell(new Paragraph(ga.getPaciente().getAgentesaude(), fontePadrao));
            PdfPCell CEPV2via = new PdfPCell(new Paragraph(ga.getPaciente().getCep(), fontePadrao));
            PdfPCell ESTADOV2via = new PdfPCell(new Paragraph(ga.getPaciente().getEstado(), fontePadrao));
            PdfPCell CPFV2via = new PdfPCell(new Paragraph(ga.getPaciente().getCpf(), fontePadrao));
            PdfPCell RGV2via = new PdfPCell(new Paragraph(ga.getPaciente().getRg(), fontePadrao));
            PdfPCell TITULO_ELEITORV2via = new PdfPCell(new Paragraph(ga.getPaciente().getTitulozona(), fontePadrao));
            PdfPCell CARTAO_SUSV2via = new PdfPCell(new Paragraph(ga.getPaciente().getCartaosus(), fontePadrao));

            branc1V2via.setBorder(0);
            pacienteV2via.setBorder(0);
            enderecoV2via.setBorder(0);
            bairroV2via.setBorder(0);
            cidadeV2via.setBorder(0);
            agsV2via.setBorder(0);
            CEPV2via.setBorder(0);
            ESTADOV2via.setBorder(0);
            CPFV2via.setBorder(0);
            RGV2via.setBorder(0);
            TITULO_ELEITORV2via.setBorder(0);
            CARTAO_SUSV2via.setBorder(0);

            PdfPTable dadosAutorizacao2via = new PdfPTable(new float[]{0.1f, 0.3f, 0.1f, 0.2f, 0.1f, 0.2f});

            dadosAutorizacao2via.addCell(paciente2via);
            dadosAutorizacao2via.addCell(pacienteV2via);
            dadosAutorizacao2via.addCell(branc12via);
            dadosAutorizacao2via.addCell(branc12via);
            dadosAutorizacao2via.addCell(CPF2via);
            dadosAutorizacao2via.addCell(CPFV2via);
            dadosAutorizacao2via.addCell(endereco2via);
            dadosAutorizacao2via.addCell(enderecoV2via);
            dadosAutorizacao2via.addCell(branc12via);
            dadosAutorizacao2via.addCell(branc12via);
            dadosAutorizacao2via.addCell(RG2via);
            dadosAutorizacao2via.addCell(RGV2via);
            dadosAutorizacao2via.addCell(bairro2via);
            dadosAutorizacao2via.addCell(bairroV2via);
            dadosAutorizacao2via.addCell(branc12via);
            dadosAutorizacao2via.addCell(branc12via);
            dadosAutorizacao2via.addCell(CEP2via);
            dadosAutorizacao2via.addCell(CEPV2via);
            dadosAutorizacao2via.addCell(cidade2via);
            dadosAutorizacao2via.addCell(cidadeV2via);
            dadosAutorizacao2via.addCell(branc12via);
            dadosAutorizacao2via.addCell(branc12via);
            dadosAutorizacao2via.addCell(ESTADO2via);
            dadosAutorizacao2via.addCell(ESTADOV2via);
            dadosAutorizacao2via.addCell(ags2via);
            dadosAutorizacao2via.addCell(agsV2via);
            dadosAutorizacao2via.addCell(branc12via);
            dadosAutorizacao2via.addCell(branc12via);
            dadosAutorizacao2via.addCell(branc12via);
            dadosAutorizacao2via.addCell(branc12via);

            dadosAutorizacao2via.setWidthPercentage(100.0f);
            dadosAutorizacao2via.setHorizontalAlignment(Element.ALIGN_LEFT);
            document.add(dadosAutorizacao2via);

            //Editar o procedimento solicitado
            
            if (ga.isStatus() == true) {
                statusVerifica = "AUTORIZADO";
            } else {
                statusVerifica = "PENDENTE";
            }

            //JOptionPane.showMessageDialog(null, ce.getSelectedGuiaAtendimento().getPaciente().getNome());
            PdfPCell branco22via = new PdfPCell(new Paragraph("", fontePadrao));
            PdfPCell aten2via = new PdfPCell(new Paragraph("ATENDIDO POR \n" + ga.getUsuario().getNome(), fontePadrao));
            PdfPCell pro2via = new PdfPCell(new Paragraph(":::PROCEDIMENTO SOLICITADO:::", fonteAlinhamento));
            PdfPCell status2via = new PdfPCell(new Paragraph("STATUS", fontePadrao));

            branco22via.setBorder(0);
            aten2via.setBorder(0);
            pro2via.setBorder(0);
            status2via.setBorder(0);

            PdfPCell statusV2via = new PdfPCell(new Paragraph(statusVerifica, fontePadrao));
            statusV2via.setBorder(0);

            PdfPTable procedimento2via = new PdfPTable(new float[]{0.2f, 0.5f, 0.1f, 0.2f});

            procedimento2via.addCell(aten2via);
            procedimento2via.addCell(pro2via);
            procedimento2via.addCell(status2via);
            procedimento2via.addCell(statusV2via);

            procedimento2via.setWidthPercentage(100.0f);
            procedimento2via.setHorizontalAlignment(Element.ALIGN_LEFT);
            document.add(procedimento2via);

            //Tabela para Descrição do procedimento solicitado
            PdfPCell branco32via = new PdfPCell(new Paragraph("", fontePadrao));
            PdfPCell codigo2via = new PdfPCell(new Paragraph("CÓDIGO", negritoGrande));
            PdfPCell descricao2via = new PdfPCell(new Paragraph("DESCRIÇÃO DO PROCEDIMENTO SOLICITADO", negritoGrande));

            PdfPCell codigoV2via = new PdfPCell(new Paragraph(" " + ga.getProcedimento().getCodigoprocedimento(), fontePadrao));
            PdfPCell descricaoV2via = new PdfPCell(new Paragraph(ga.getProcedimento().getNome(), fontePadrao));

            branco32via.setBorder(0);
            codigo2via.setBorder(0);
            descricao2via.setBorder(0);
            codigoV2via.setBorder(0);
            descricaoV2via.setBorder(0);

            PdfPTable DESC2via = new PdfPTable(new float[]{0.2f, 0.8f});

            DESC2via.addCell(codigo2via);
            DESC2via.addCell(descricao2via);
            DESC2via.addCell(codigoV2via);
            DESC2via.addCell(descricaoV2via);

            DESC2via.setWidthPercentage(100.0f);
            DESC2via.setHorizontalAlignment(Element.ALIGN_LEFT);
            document.add(DESC2via);

            document.add(new Paragraph(" ", fontePadrao));
            //Tabela para informações

            PdfPCell branco42via = new PdfPCell(new Paragraph("", fonteMenor));
            PdfPCell brancol2via = new PdfPCell(new Paragraph("", fonteMenor));
            PdfPCell brancoNormal2via = new PdfPCell(new Paragraph("", fonteMenor));
            PdfPCell unidS2via = new PdfPCell(new Paragraph("UNIDADE SOLICITANTE", fonteMenor));
            PdfPCell medicoS2via = new PdfPCell(new Paragraph("MÉDICO SOLICITANTE", fonteMenor));
            PdfPCell crm2via = new PdfPCell(new Paragraph("CRM", fonteMenor));
            PdfPCell dataS2via = new PdfPCell(new Paragraph("DATA SOLICITAÇÃO", fonteMenor));
            PdfPCell dataAut2via = new PdfPCell(new Paragraph("DATA AUTORIZAÇÃO", fonteMenor));
            PdfPCell dataAg2via = new PdfPCell(new Paragraph("DATA AGENDADA", fonteMenor));
            PdfPCell horaAg2via = new PdfPCell(new Paragraph("HORA AGENDADA", fonteMenor));
            PdfPCell senhaAten2via = new PdfPCell(new Paragraph("SENHA DE ATENDIMENTO", fonteMenor));
            PdfPCell atenPri2via = new PdfPCell(new Paragraph("ATENDIMENTO PRIORITÁRIO", fonteMenor));
            PdfPCell unidAtend2via = new PdfPCell(new Paragraph("UNIDADE PARA ATENDIMENTO", fonteMenor));
            PdfPCell obs2via = new PdfPCell(new Paragraph("OBSERVAÇÃO", fonteMenor));
            PdfPCell enderecoAten2via = new PdfPCell(new Paragraph("ENDERECO", fonteMenor));
            PdfPCell atenA2via = new PdfPCell(new Paragraph("ATENÇÃO", fonteMenor));
            PdfPCell viaS2via = new PdfPCell(new Paragraph("VIA DA SECRETARIA MUNICIPAL DE SAÚDE", fonteMenor));
            PdfPCell pacienteS2via = new PdfPCell(new Paragraph("                                                             (ASSINATURA DO SERVIDOR)", fonteMenor));

            //Está faltando o valores vindo do banco de dados
            crm2via.setBorder(0);
            brancoNormal2via.setBorder(0);
            brancol2via.setBorder(2);
            branco42via.setBorder(0);
            atenPri2via.setBorder(0);
            unidS2via.setBorder(0);
            medicoS2via.setBorder(0);
            dataS2via.setBorder(0);
            dataAut2via.setBorder(0);
            dataAg2via.setBorder(0);
            horaAg2via.setBorder(0);
            senhaAten2via.setBorder(0);
            unidAtend2via.setBorder(0);
            obs2via.setBorder(0);
            enderecoAten2via.setBorder(0);
            atenA2via.setBorder(0);
            viaS2via.setBorder(0);
            pacienteS2via.setBorder(0);

            if (ce.getSelectedGuiaAtendimento().isAtendimentoprioritario() == true) {
                verificaPrioritario = "SIM";
            } else {
                verificaPrioritario = "NÃO";
            }

            //Falta usar os dados do banco    
            PdfPCell unidSV2via= new PdfPCell(new Paragraph(ga.getUnidadesolicitante().getNome(), fonteMenor));
            PdfPCell medicoSV2via = new PdfPCell(new Paragraph(ga.getMedico().getNome(), fonteMenor));
            PdfPCell crmV2via = new PdfPCell(new Paragraph(ga.getMedico().getCrm(), fonteMenor));
            PdfPCell dataSV2via = new PdfPCell(new Paragraph(ga.getDatasolicitanteDate(), fonteMenor));
            PdfPCell dataAutV2via = new PdfPCell(new Paragraph(ga.getDataautorizacaoDate(), fonteMenor));
            PdfPCell dataAgV2via = new PdfPCell(new Paragraph(ga.getDataagendadaDate(), fonteMenor));
            PdfPCell horaAgV2via = new PdfPCell(new Paragraph(ga.getHoraagendada(), fonteMenor));
            PdfPCell senhaAtenV2via = new PdfPCell(new Paragraph(" ", fonteMenor));
            PdfPCell atenPriV2via = new PdfPCell(new Paragraph(verificaPrioritario, fonteMenor));
            PdfPCell unidAtendV2via = new PdfPCell(new Paragraph(ga.getUnidadeatendimento().getNome(), fonteMenor));
            PdfPCell obsV2via = new PdfPCell(new Paragraph(ga.getObservacao(), fonteMenor));
            PdfPCell enderecoAtenV2via = new PdfPCell(new Paragraph(ga.getUnidadeatendimento().getLogradouro(), fonteMenor));
            PdfPCell atenAV2via = new PdfPCell(new Paragraph(ga.getAtencao(), fonteMenor));

            unidSV2via.setBorder(0);
            medicoSV2via.setBorder(0);
            crmV2via.setBorder(0);
            dataSV2via.setBorder(0);
            dataAutV2via.setBorder(0);
            dataAgV2via.setBorder(0);
            horaAgV2via.setBorder(0);
            senhaAtenV2via.setBorder(0);
            unidAtendV2via.setBorder(0);
            obsV2via.setBorder(0);
            enderecoAtenV2via.setBorder(0);
            atenAV2via.setBorder(0);
            atenPriV.setBorder(0);

            atenPriV2via.setBorderColorBottom(BaseColor.RED);

          // antes de usar esta parte do código termine o cadastro no banco  
            PdfPTable informaAdicionais2via = new PdfPTable(new float[]{0.16f, 0.16f, 0.16f, 0.16f, 0.16f, 0.2f});
            unidS2via.setColspan(3);
            unidSV2via.setColspan(3);

            medicoS2via.setColspan(2);
            medicoSV2via.setColspan(2);

            unidAtend2via.setColspan(3);
            unidAtendV2via.setColspan(3);

            enderecoAten2via.setColspan(3);
            enderecoAtenV2via.setColspan(3);

            obs2via.setColspan(3);
            obsV2via.setColspan(3);

            atenA2via.setColspan(3);
            atenAV2via.setColspan(3);

            branco42via.setColspan(6);
            pacienteS2via.setColspan(4);
            brancol2via.setColspan(4);
            viaS2via.setColspan(4);
            brancoNormal2via.setColspan(2);

            informaAdicionais2via.addCell(unidS2via);
            informaAdicionais2via.addCell(medicoS2via);
            informaAdicionais2via.addCell(crm2via);
            informaAdicionais2via.addCell(unidSV2via);
            informaAdicionais2via.addCell(medicoSV2via);
            informaAdicionais2via.addCell(crmV2via);

            informaAdicionais2via.addCell(dataS2via);
            informaAdicionais2via.addCell(dataAut2via);
            informaAdicionais2via.addCell(dataAg2via);
            informaAdicionais2via.addCell(horaAg2via);
            informaAdicionais2via.addCell(senhaAten2via);
            informaAdicionais2via.addCell(atenPri2via);

            informaAdicionais2via.addCell(dataSV2via);
            informaAdicionais2via.addCell(dataAutV2via);
            informaAdicionais2via.addCell(dataAgV2via);
            informaAdicionais2via.addCell(horaAgV2via);
            informaAdicionais2via.addCell(senhaAtenV2via);
            informaAdicionais2via.addCell(atenPriV2via);

            informaAdicionais2via.addCell(unidAtend2via);
            informaAdicionais2via.addCell(enderecoAten2via);

            informaAdicionais2via.addCell(unidAtendV2via);
            informaAdicionais2via.addCell(enderecoAtenV2via);

            informaAdicionais2via.addCell(obs2via);
            informaAdicionais2via.addCell(atenA2via);

            informaAdicionais2via.addCell(obsV2via);
            informaAdicionais2via.addCell(atenAV2via);

            informaAdicionais2via.addCell(branco42via);
            informaAdicionais2via.addCell(branco42via);
            informaAdicionais2via.addCell(branco42via);
            informaAdicionais2via.addCell(brancol2via);
            informaAdicionais2via.addCell(brancoNormal2via);
            informaAdicionais2via.addCell(pacienteS2via);
            informaAdicionais2via.addCell(viaS2via);

            informaAdicionais2via.setWidthPercentage(100.0f);
            informaAdicionais2via.setHorizontalAlignment(Element.ALIGN_LEFT);
            document.add(informaAdicionais2via);

            Paragraph subtitulo2via = new Paragraph(linhaTracejada, fonteCabecalho);
            document.add(subtitulo2via);

            // document.add(new Paragraph(linhaTracejada, negritoPequena));
            
            PdfPCell branc112via = new PdfPCell(new Paragraph("", fontePadrao));
            PdfPCell brancD12via = new PdfPCell(new Paragraph("", fontePadrao));
            PdfPCell fundo12via = new PdfPCell(new Paragraph("FUNDO MUNICIPAL DE SAÚDE DE ANGELIM ", negritoPequena));
            PdfPCell rua12via = new PdfPCell(new Paragraph("RUA BELA VISTA, Nº12", fontePadrao));
            PdfPCell centro12via = new PdfPCell(new Paragraph("CENTRO", fontePadrao));
            PdfPCell cep12via = new PdfPCell(new Paragraph("55430-000 ANGELIM - PE", fontePadrao));
            PdfPCell insc12via = new PdfPCell(new Paragraph("Inscrição do CNPJ.:", fontePadrao));
            PdfPCell fone12via = new PdfPCell(new Paragraph("Fone: ", fontePadrao));

            fundo12via.setBorder(0);

            rua12via.setBorder(0);
            brancD12via.setBorder(0);
            branc112via.setBorder(0);

            rua12via.setBorder(0);
            centro12via.setBorder(0);
            cep12via.setBorder(0);
            insc12via.setBorder(0);

            fone12via.setBorder(0);

            PdfPTable GUIAAUTa2via = new PdfPTable(new float[]{0.2f, 0.4f, 0.2f, 0.2f});
            //setColspan(4);

            GUIAAUT2via.addCell(branc112via);
            GUIAAUT2via.addCell(fundo12via);
            GUIAAUT2via.addCell(branc112via);
            GUIAAUT2via.addCell(brancD12via);

            GUIAAUT2via.addCell(branc112via);
            GUIAAUT2via.addCell(rua12via);
            GUIAAUT2via.addCell(brancD12via);
            GUIAAUT2via.addCell(branc112via);
            GUIAAUT2via.addCell(branc112via);
            GUIAAUT2via.addCell(centro12via);
            GUIAAUT2via.addCell(branc112via);
            GUIAAUT2via.addCell(branc112via);
            GUIAAUT2via.addCell(branc112via);
            GUIAAUT2via.addCell(cep12via);
            GUIAAUT2via.addCell(branc112via);
            GUIAAUT2via.addCell(branc112via);
            GUIAAUT2via.addCell(branc112via);
            GUIAAUT2via.addCell(insc12via);
            GUIAAUT2via.addCell(fone12via);
            GUIAAUT2via.addCell(branc12via);
            //  GUIAAUT.addCell(guia);

            GUIAAUT2via.setWidthPercentage(100.0f);
            GUIAAUT2via.setHorizontalAlignment(Element.ALIGN_LEFT);
            document.add(GUIAAUTa2via);



            java.awt.Desktop.getDesktop().open(new File("C:\\Users\\São Luiz\\Documents\\relatorios\\GuiaAutorizacaoRelatorio.pdf"));

        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        document.close();

    }
}
