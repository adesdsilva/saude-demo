/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.swing.JOptionPane;
import negocio.GuiaAtendimento;
import negocio.GuiaAutorizacao;
import negocio.Unidade;
import negocio.Usuario;
import org.apache.commons.codec.digest.DigestUtils;
import repositorios.RepositorioGuiaAutorizacao;
import repositorios.RepositorioUsuario;
import util.JSFUtil;

/**
 *
 * @author Adelino
 */
@ManagedBean(name = "controleGuiaAutorizacao")
@SessionScoped
public class ControladorGuiaAutorizacao {

    private repositorios.RepositorioGuiaAutorizacao rgau;

    private GuiaAutorizacao selectedGuiaAutorizacao;

    private Usuario autorizarGuiaPendente;

    private RepositorioUsuario repositorioUsuario;
    
    private GuiaAutorizacao pdf;

    public ControladorGuiaAutorizacao() {
        this.rgau = new RepositorioGuiaAutorizacao();
        this.repositorioUsuario = new RepositorioUsuario();
    }

    public GuiaAutorizacao getSelectedGuiaAutorizacao() {
        return selectedGuiaAutorizacao;
    }

    public void setSelectedGuiaAutorizacao(GuiaAutorizacao selectedGuiaAutorizacao) {
        this.selectedGuiaAutorizacao = selectedGuiaAutorizacao;
    }

    public Usuario getAutorizarGuiaPendente() {
        return autorizarGuiaPendente;
    }

    public GuiaAutorizacao getPdf() {
        return pdf;
    }

    public void setPdf(GuiaAutorizacao pdf) {
        this.pdf = pdf;
    }

    
    public void setAutorizarGuiaPendente(Usuario autorizarGuiaPendente) {
        this.autorizarGuiaPendente = autorizarGuiaPendente;
    }
    public String verificaStatus(){
        
        if(this.selectedGuiaAutorizacao.isStatus()){
            return "AUTORIZADO";
        }
        else{
            return "PENDENTE";
        }
             
    }

    public String inserirgau(GuiaAutorizacao t) {

        this.rgau.inserir(t);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Guia de autorizacao cadastrado com Sucesso!"));
        return "listarGuiasAutorizacao.xhtml";
    }

    public String editargau(GuiaAutorizacao t) {
        this.rgau.editar(t);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Guia de autorizacao editado com Sucesso!"));
        return "listarGuiasAutorizacao.xhtml";
    }

    public GuiaAutorizacao recuperargau(int g) {
        return (GuiaAutorizacao) this.rgau.recuperar(g);
    }

    public void deletargau(GuiaAutorizacao t) {
        new ControladorGuiaAtendimento().deletarSeqAtendimento(new ControladorGuiaAtendimento().recuperargat(t.getCodigoguiaautorizacao()));
        this.rgau.deletar(t);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Guia de autorizacao deletado com Sucesso!"));

    }

    public void deletarSeqautorizacao(GuiaAutorizacao t) {
        this.rgau.deletar(t);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Guia de autorizacao deletado com Sucesso!"));

    }

    public List<GuiaAutorizacao> recuperarTodosGuiaAutorizacao() {
        return this.rgau.recuperarTodos();
    }

    public List<GuiaAutorizacao> recuperarTodasGuiasPendentes(boolean n) {
        List<GuiaAutorizacao> lista = new ArrayList<>();
        ControladorGuiaAutorizacao r = new ControladorGuiaAutorizacao();
        List<GuiaAutorizacao> lg = r.recuperarTodosGuiaAutorizacao();
        if (n) {

            for (int i = 0; i < lg.size(); i++) {
                if (lg.get(i).isStatus() == false) {
                    lista.add(lg.get(i));
                }
            }
            return lista;
        } else {
            for (int i = 0; i < lg.size(); i++) {
                if (lg.get(i).isStatus() == true) {
                    lista.add(lg.get(i));
                }
            }
            return lista;
        }
    }

    public List<GuiaAutorizacao> recuperarTodasGuiasAutorizadas() {
        List<GuiaAutorizacao> lista = new ArrayList<>();
        ControladorGuiaAutorizacao r = new ControladorGuiaAutorizacao();
        List<GuiaAutorizacao> lg = r.recuperarTodosGuiaAutorizacao();
        for (int i = 0; i < lg.size(); i++) {
            if (lg.get(i).isStatus() == true) {
                lista.add(lg.get(i));
            }
        }
        return lista;
    }

    public String autorizacaoGuiaPendente(Usuario usuario, GuiaAutorizacao ga) {
        //JOptionPane.showMessageDialog(null,usuario.toString() + "\n"+ga.toString());
        if (ga.getDataagendada() == null) {
            JSFUtil.addMensagemErro("Data agendada obrigatória!");
        } else {
            SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
            ga.setDataagendadaDate(sdfr.format(ga.getDataagendada()));
            ga.setDatasolicitanteDate(sdfr.format(ga.getDatasolicitante()));
            usuario.setSenha(usuario.getSenha());
            usuario.setSenha(DigestUtils.md5Hex(usuario.getSenha()));
            this.autorizarGuiaPendente = this.repositorioUsuario.recuperarPorLogin(usuario.getLogin(), usuario.getSenha());
            if (this.autorizarGuiaPendente != null && this.autorizarGuiaPendente.getPerfil().equals("Administrador")) {
                JSFUtil.addMensagemSucesso("Autorização de Guia Realizada com Sucesso!");
                
                ga.setStatus(true);
                
                this.editargau(ga);
                this.selectedGuiaAutorizacao = null;
                return "listarGuiasAutorizacao.xhtml";
            } else {
                JSFUtil.addMensagemErro("Login/Senha inválido(s) ou Permissão não permitida para o Usuário");
            }
        }
        return null;
    }

    public String verificaStatusGuia(GuiaAutorizacao ga) {
        if (ga.isStatus() == true) {
            return "Autorizado";
        } else {
            return "Pendente";
        }
    }
    public String gerarPdf2HtmlGaut(GuiaAutorizacao ga){
        this.pdf = null;
        this.pdf = ga;
        return "pdfguiaautorizacao.xhtml";
    }
public String verificaAtendimentoPrioritario(){
        return (this.selectedGuiaAutorizacao.isAtendimentoprioritario()==true)?"SIM":"NÃO";
    }

    public void setarDadosUnidat(Unidade s) {
        this.getSelectedGuiaAutorizacao().setUnidadeatendimento(s);

    }
    public static void main(String[] args) throws FileNotFoundException, DocumentException, IOException {
        Document doc = null;
        OutputStream os = null;

        try {
            //cria o documento tamanho A4, margens de 2,54cm
            doc = new Document(PageSize.A4, 0, 0, 0, 0);

            //cria a stream de saída
            os = new FileOutputStream("C://Users//José//Documents//Nova pasta//out.pdf");

            //associa a stream de saída ao 
            PdfWriter.getInstance(doc, os);

            //abre o documento
            doc.open();

            PdfPTable table = new PdfPTable(3);
            Image imagem = Image.getInstance("C://Users//José//Documents//Nova pasta//SecSaude3//web//img//Angelim.fw.jpg");
            table.addCell(imagem);
            table.addCell("Fundo Municipal de Saúde de Angelim - Rua Bela Vista, 12 - Centro / 55430-000 Angelim - PE");
            table.addCell("C://Users//José//Documents//Nova pasta//SecSaude3//web//img//Angelim.png");
            PdfPCell numGuia = new PdfPCell(new Paragraph("GUIA DE ATENDIMENTO Nº: 0001"));
            numGuia.setColspan(3);
            table.addCell(numGuia);
            PdfPCell dadosPac = new PdfPCell(new Paragraph("PACIENTE: Joselma Ferreira de Andrade Melo \n "
                    + "ENDEREÇO: Rua Padre Cícero, 200 \n "
                    + "BAIRRO: Loteamento Nova Aliança \n "
                    + "CIDADE: Angelim \n"
                    + "ESTADO: PE \n "
                    + "CEP: 55430-000 \n "
                    + "AGENTE SAÚDE: Maria"));
            dadosPac.setColspan(2);
            table.addCell(dadosPac);
            PdfPCell dadosPac1 = new PdfPCell(new Paragraph("CPF: 87346732092 \n "
                    + "RG: 879237483 \n "
                    + "TÍTULO: 837481938743535 \n "
                    + "CARTÃO-SUS: 74827582475724852"));
            table.addCell(dadosPac1);
            table.addCell("Atendido por: Ailton");
            table.addCell(":::PROCEDIMENTO SOLICITADO:::");
            table.addCell("STATUS: PENDENTE");
            doc.add(table);
            
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
