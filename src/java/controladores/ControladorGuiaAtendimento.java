/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import util.JSFUtil;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import negocio.GuiaAtendimento;
import negocio.GuiaAutorizacao;
import negocio.Medico;
import negocio.Paciente;
import negocio.Procedimento;
import negocio.Unidade;
import negocio.Usuario;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.context.RequestContext;
import repositorios.RepositorioGuiaAtendimento;
import repositorios.RepositorioUsuario;

/**
 *
 * @author Adelino
 */
@ManagedBean(name = "controleGuiaAtendimento")
@SessionScoped
public class ControladorGuiaAtendimento {

    private repositorios.RepositorioGuiaAtendimento rgat = null;

    private GuiaAtendimento selectedGuiaAtendimento = new GuiaAtendimento();

    private Usuario User;
    
    private GuiaAtendimento pdf;

    public ControladorGuiaAtendimento() {
        this.rgat = new RepositorioGuiaAtendimento();
    }

    public RepositorioGuiaAtendimento getRgat() {
        return rgat;
    }

    public void setRgat(RepositorioGuiaAtendimento rgat) {
        this.rgat = rgat;
    }

    public GuiaAtendimento getPdf() {
        return pdf;
    }

    public void setPdf(GuiaAtendimento pdf) {
        this.pdf = pdf;
    }
    
    

    public Usuario getUser() {
        return User;
    }

    public void setUser(Usuario User) {
        this.User = User;
    }

    public GuiaAtendimento getSelectedGuiaAtendimento() {
        return selectedGuiaAtendimento;
    }

    public void setSelectedGuiaAtendimento(GuiaAtendimento selectedGuiaAtendimento) {
        this.selectedGuiaAtendimento = selectedGuiaAtendimento;
    }
    public void setarValoresPacient(Paciente p){
//        ControladorPaciente cp = new ControladorPaciente();
//        p = cp.getSelectedPaciente();
        this.getSelectedGuiaAtendimento().getPaciente().setCodigopaciente(p.getCodigopaciente());
        this.getSelectedGuiaAtendimento().getPaciente().setNome(p.getNome());
        this.getSelectedGuiaAtendimento().getPaciente().setCpf(p.getCpf());
        this.getSelectedGuiaAtendimento().getPaciente().setRg(p.getRg());
        this.getSelectedGuiaAtendimento().getPaciente().setTitulozona(p.getTitulozona());
        this.getSelectedGuiaAtendimento().getPaciente().setCartaosus(p.getCartaosus());
        this.getSelectedGuiaAtendimento().getPaciente().setCep(p.getCep());
        this.getSelectedGuiaAtendimento().getPaciente().setLogradouro(p.getLogradouro());
        this.getSelectedGuiaAtendimento().getPaciente().setComplemento(p.getComplemento());
        this.getSelectedGuiaAtendimento().getPaciente().setBairro(p.getBairro());
        this.getSelectedGuiaAtendimento().getPaciente().setNumero(p.getNumero());
        this.getSelectedGuiaAtendimento().getPaciente().setCidade(p.getCidade());
        this.getSelectedGuiaAtendimento().getPaciente().setAgentesaude(p.getAgentesaude());
        this.getSelectedGuiaAtendimento().getPaciente().setDataNascimento(p.getDataNascimento());
        //JOptionPane.showMessageDialog(null, this.getPaciente().getNome());
        
    }
    
    public String verificaAtendimentoPrioritario(){
        return (this.selectedGuiaAtendimento.isAtendimentoprioritario()==true)?"SIM":"NÃO";
    }
    
    public String verificaStatus(){
        
        if(this.selectedGuiaAtendimento.isStatus()){
            return "AUTORIZADO";
        }
        else{
            return "PENDENTE";
        }
             
    }
    
    public void setarValoresMedic(Medico m){
       
       this.getSelectedGuiaAtendimento().getMedico().setCodigoMedico(m.getCodigoMedico());
       this.getSelectedGuiaAtendimento().getMedico().setNome(m.getNome());
       this.getSelectedGuiaAtendimento().getMedico().setCrm(m.getCrm());
       this.getSelectedGuiaAtendimento().getMedico().setEspecialidade(m.getEspecialidade());
       
    }
    public void setarValoresProc(Procedimento p){
        this.getSelectedGuiaAtendimento().getProcedimento().setCodigoprocedimento(p.getCodigoprocedimento());
        this.getSelectedGuiaAtendimento().getProcedimento().setNome(p.getNome());
        
    }
    public void setarValoresUnsol(Unidade s){
        this.getSelectedGuiaAtendimento().getUnidadesolicitante().setAtencao(s.getAtencao());
        this.getSelectedGuiaAtendimento().getUnidadesolicitante().setBairro(s.getBairro());
        this.getSelectedGuiaAtendimento().getUnidadesolicitante().setCep(s.getCep());
        this.getSelectedGuiaAtendimento().getUnidadesolicitante().setCidade(s.getCidade());
        this.getSelectedGuiaAtendimento().getUnidadesolicitante().setCodigoUnidade(s.getCodigoUnidade());
        this.getSelectedGuiaAtendimento().getUnidadesolicitante().setComplemento(s.getComplemento());
        this.getSelectedGuiaAtendimento().getUnidadesolicitante().setEstado(s.getEstado());
        this.getSelectedGuiaAtendimento().getUnidadesolicitante().setLogradouro(s.getLogradouro());
        this.getSelectedGuiaAtendimento().getUnidadesolicitante().setNome(s.getNome());
        this.getSelectedGuiaAtendimento().getUnidadesolicitante().setNumero(s.getNumero());
        this.getSelectedGuiaAtendimento().getUnidadesolicitante().setObservacao(s.getObservacao());
    }
    public void setarValoresUnidat(Unidade s){
        this.getSelectedGuiaAtendimento().getUnidadeatendimento().setAtencao(s.getAtencao());
        this.getSelectedGuiaAtendimento().getUnidadeatendimento().setBairro(s.getBairro());
        this.getSelectedGuiaAtendimento().getUnidadeatendimento().setCep(s.getCep());
        this.getSelectedGuiaAtendimento().getUnidadeatendimento().setCidade(s.getCidade());
        //JOptionPane.showMessageDialog(null,s);
        this.getSelectedGuiaAtendimento().getUnidadeatendimento().setCodigoUnidade(s.getCodigoUnidade());
        this.getSelectedGuiaAtendimento().getUnidadeatendimento().setComplemento(s.getComplemento());
        this.getSelectedGuiaAtendimento().getUnidadeatendimento().setEstado(s.getEstado());
        this.getSelectedGuiaAtendimento().getUnidadeatendimento().setLogradouro(s.getLogradouro());
        this.getSelectedGuiaAtendimento().getUnidadeatendimento().setNome(s.getNome());
        this.getSelectedGuiaAtendimento().getUnidadeatendimento().setNumero(s.getNumero());
        this.getSelectedGuiaAtendimento().getUnidadeatendimento().setObservacao(s.getObservacao());
//        Usuario u = new ControladorUsuario().recuperarusuario(1);
//        this.setUsuario(u);
    }
    
    public String verificacoesGuiaAt(GuiaAtendimento t,Usuario u) {
        if (t.getMedico().getCrm() == null) {
            JSFUtil.addMensagemErro("Selecione um médico para prosseguir");
        } else if (t.getPaciente().getCpf() == null) {
            JSFUtil.addMensagemErro("Selecione um paciente para prosseguir");
        } else if (t.getProcedimento().getNome() == null) {
            JSFUtil.addMensagemErro("Selecione um procedimento para prosseguir");
            } else if (t.getUnidadesolicitante().getCidade() == null) {
            JSFUtil.addMensagemErro("Selecione uma unidade solicitante");
        } else {
            t.setUsuario(u);
            inserirgat(t);
            return "listarGuiasAtendimento.xhtml";
        }
        return null;
    }

    public void abrirDialogo() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("abrir.show();");

    }
    
    public String gerarPdf2HtmlGat(GuiaAtendimento ga){
        this.pdf = null;
        this.pdf = ga;
        return "pdfguiaatendimento.xhtml";
    }

    public String inserirgat(GuiaAtendimento t) {

        
t.setUnidadeatendimento(null);
        this.rgat.inserir(t);
        new ControladorGuiaAutorizacao().inserirgau(copiarDadosParaAutorizacao(new GuiaAutorizacao(), t));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Guia de atendimento cadastrado com Sucesso!"));
        return "listarGuiasAtendimento.xhtml";

    }

    public String editargat(GuiaAtendimento t) {
        
        SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
        t.setDataagendadaDate(sdfr.format(t.getDataagendada()));
        t.setDatasolicitanteDate(sdfr.format(t.getDatasolicitante()));
        this.rgat.editar(t);
        new ControladorGuiaAutorizacao().editargau(copiarDadosParaAutorizacao(new ControladorGuiaAutorizacao().recuperargau(t.getCodigoguiaatendimento()), t));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Guia de atendimento editado com Sucesso!"));
        return "listarGuiasAtendimento.xhtml";
    }

    public GuiaAtendimento recuperargat(int g) {
        return (GuiaAtendimento) this.rgat.recuperar(g);
    }

    public void deletargat(GuiaAtendimento t) {
        new ControladorGuiaAutorizacao().deletarSeqautorizacao(new ControladorGuiaAutorizacao().recuperargau(t.getCodigoguiaatendimento()));
        this.rgat.deletar(t);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Guia de atendimento deletado com Sucesso!"));

    }
    public void deletarSeqAtendimento(GuiaAtendimento t){
        this.rgat.deletar(t);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Guia de atendimento deletado com Sucesso!"));

    }

    public List<GuiaAtendimento> recuperarTodosGuiaAtendimentos() {
        return this.rgat.recuperarTodos();
    }

    public GuiaAutorizacao copiarDadosParaAutorizacao(GuiaAutorizacao guia, GuiaAtendimento gat) {

        guia.setAtencao(gat.getAtencao());
        guia.setAtendimentoprioritario(gat.isAtendimentoprioritario());
        guia.setDataagendada(gat.getDataagendada());
        guia.setDataagendadaDate(gat.getDataagendadaDate());
        guia.setDatasolicitante(gat.getDatasolicitante());
        guia.setDatasolicitanteDate(gat.getDatasolicitanteDate());
        guia.setMedico(gat.getMedico());
        guia.setObservacao(gat.getObservacao());
        guia.setPaciente(gat.getPaciente());
        guia.setUnidadesolicitante(gat.getUnidadesolicitante());
        guia.setUnidadeatendimento(gat.getUnidadeatendimento());
        guia.setUsuario(gat.getUsuario());
        guia.setProcedimento(gat.getProcedimento());
        return guia;

    }

    public void setarValoresPaciente(Paciente paciente) {

        this.selectedGuiaAtendimento.setPaciente(new Paciente());
        this.selectedGuiaAtendimento.getPaciente().setCodigopaciente(paciente.getCodigopaciente());
        this.selectedGuiaAtendimento.getPaciente().setNome(paciente.getNome());
        this.selectedGuiaAtendimento.getPaciente().setCpf(paciente.getCpf());
        this.selectedGuiaAtendimento.getPaciente().setRg(paciente.getRg());
        this.selectedGuiaAtendimento.getPaciente().setTitulozona(paciente.getTitulozona());
        this.selectedGuiaAtendimento.getPaciente().setCartaosus(paciente.getCartaosus());
        this.selectedGuiaAtendimento.getPaciente().setCep(paciente.getCep());
        this.selectedGuiaAtendimento.getPaciente().setLogradouro(paciente.getLogradouro());
        this.selectedGuiaAtendimento.getPaciente().setComplemento(paciente.getComplemento());
        this.selectedGuiaAtendimento.getPaciente().setBairro(paciente.getBairro());
        this.selectedGuiaAtendimento.getPaciente().setNumero(paciente.getNumero());
        this.selectedGuiaAtendimento.getPaciente().setCidade(paciente.getCidade());
        this.selectedGuiaAtendimento.getPaciente().setAgentesaude(paciente.getAgentesaude());
        this.selectedGuiaAtendimento.getPaciente().setDataNascimento(paciente.getDataNascimento());

    }

    public void setarValoresMedico(Medico m) {
        this.selectedGuiaAtendimento.setMedico(new Medico());
        this.selectedGuiaAtendimento.getMedico().setCodigoMedico(m.getCodigoMedico());
        this.selectedGuiaAtendimento.getMedico().setCrm(m.getCrm());
        this.selectedGuiaAtendimento.getMedico().setEspecialidade(m.getEspecialidade());
        this.selectedGuiaAtendimento.getMedico().setNome(m.getNome());

    }

    public void setValoresProcedimento(Procedimento p) {
        this.selectedGuiaAtendimento.setProcedimento(p);
        this.selectedGuiaAtendimento.getProcedimento().setCodigoprocedimento(p.getCodigoprocedimento());
        this.selectedGuiaAtendimento.getProcedimento().setNome(p.getNome());

    }

    public void setValoresUsuario(Usuario u) {
        this.selectedGuiaAtendimento.setUsuario(new Usuario());
        this.selectedGuiaAtendimento.getUsuario().setCodigoUsuario(u.getCodigoUsuario());
        this.selectedGuiaAtendimento.getUsuario().setCpf(u.getCpf());
        this.selectedGuiaAtendimento.getUsuario().setLogin(u.getLogin());
        this.selectedGuiaAtendimento.getUsuario().setNome(u.getNome());
        this.selectedGuiaAtendimento.getUsuario().setPerfil(u.getPerfil());
        this.selectedGuiaAtendimento.getUsuario().setSenha(u.getSenha());

    }

    public void setValoresUnidadeSol(Unidade u) {
        this.selectedGuiaAtendimento.setUnidadesolicitante(new Unidade());
        this.selectedGuiaAtendimento.getUnidadesolicitante().setAtencao(u.getAtencao());
        this.selectedGuiaAtendimento.getUnidadesolicitante().setBairro(u.getBairro());
        this.selectedGuiaAtendimento.getUnidadesolicitante().setCep(u.getCep());
        this.selectedGuiaAtendimento.getUnidadesolicitante().setCidade(u.getCidade());
        this.selectedGuiaAtendimento.getUnidadesolicitante().setCodigoUnidade(u.getCodigoUnidade());
        this.selectedGuiaAtendimento.getUnidadesolicitante().setComplemento(u.getComplemento());
        this.selectedGuiaAtendimento.getUnidadesolicitante().setEstado(u.getEstado());
        this.selectedGuiaAtendimento.getUnidadesolicitante().setLogradouro(u.getLogradouro());
        this.selectedGuiaAtendimento.getUnidadesolicitante().setNome(u.getNome());
        this.selectedGuiaAtendimento.getUnidadesolicitante().setNumero(u.getNumero());
        this.selectedGuiaAtendimento.getUnidadesolicitante().setObservacao(u.getObservacao());

    }

    public void setValoresUnidadeAt(Unidade u) {
        this.selectedGuiaAtendimento.setUnidadeatendimento(new Unidade());
        this.selectedGuiaAtendimento.getUnidadeatendimento().setAtencao(u.getAtencao());
        this.selectedGuiaAtendimento.getUnidadeatendimento().setBairro(u.getBairro());
        this.selectedGuiaAtendimento.getUnidadeatendimento().setCep(u.getCep());
        this.selectedGuiaAtendimento.getUnidadeatendimento().setCidade(u.getCidade());
        this.selectedGuiaAtendimento.getUnidadeatendimento().setCodigoUnidade(u.getCodigoUnidade());
        this.selectedGuiaAtendimento.getUnidadeatendimento().setComplemento(u.getComplemento());
        this.selectedGuiaAtendimento.getUnidadeatendimento().setEstado(u.getEstado());
        this.selectedGuiaAtendimento.getUnidadeatendimento().setLogradouro(u.getLogradouro());
        this.selectedGuiaAtendimento.getUnidadeatendimento().setNome(u.getNome());
        this.selectedGuiaAtendimento.getUnidadeatendimento().setNumero(u.getNumero());
        this.selectedGuiaAtendimento.getUnidadeatendimento().setObservacao(u.getObservacao());

    }

    public boolean recuperarPorData() {
        List<GuiaAtendimento> lg = new ControladorGuiaAtendimento().recuperarTodosGuiaAtendimentos();
        List<GuiaAtendimento> listarecuperada = new ArrayList<>();
        String data = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        for (int i = 0; i < lg.size(); i++) {
            if (lg.get(i).getDatasolicitanteDate().equals(data)) {
                listarecuperada.add(lg.get(i));
            }
        }
        if (listarecuperada.size() > 14) {
            return true;
        } else {
            return false;
        }
    }

//    public void autorizacaoGuiaAt(Usuario usuario){
//        usuario.setSenha(usuario.getSenha());
//        usuario.setSenha(DigestUtils.md5Hex(usuario.getSenha()));
//        Usuario autorizarGuiaPendente = new RepositorioUsuario().recuperarPorLogin(usuario.getLogin(), usuario.getSenha());
//        if(autorizarGuiaPendente != null && autorizarGuiaPendente.getPerfil().equals("Administrador")){
//            JSFUtil.addMensagemSucesso("Verificação do login de administrador realizada com sucesso!");
//            
//        }else{
//            JSFUtil.addMensagemErro("Login/Senha inválido(s) ou Permissão não permitida para o Usuário");
//        }
//    }
//    public boolean habilitarBotaoCadastrarGuiaAtendimento(){
//        if(verificarQuantRegistros()){
//        if(this.User==null){
//            return false;
//        }
//        else{
//            return autorizacaoGuiaAt(this.User);
//        }
//        }
//        return false;
//    }
    public boolean autorizacaoGuiaAt(Usuario usuario) {
        usuario.setSenha(usuario.getSenha());
        usuario.setSenha(DigestUtils.md5Hex(usuario.getSenha()));
        Usuario autorizarGuiaPendente = new RepositorioUsuario().recuperarPorLogin(usuario.getLogin(), usuario.getSenha());
        if (autorizarGuiaPendente != null && autorizarGuiaPendente.getPerfil().equals("Administrador")) {
            JSFUtil.addMensagemSucesso("Inserir Novo Guia de Atendimento Realizada com Sucesso!");
            return false;

        } else {
            JSFUtil.addMensagemErro("Login/Senha inválido(s) ou Permissão não permitida para o Usuário");
            return true;
        }
    }

    public String validarAdmin(Usuario usuario) {
        usuario.setSenha(usuario.getSenha());
        usuario.setSenha(DigestUtils.md5Hex(usuario.getSenha()));
        this.User = new RepositorioUsuario().recuperarPorLogin(usuario.getLogin(), usuario.getSenha());
        if (this.User != null && this.User.getPerfil().equals("Administrador")) {
            JSFUtil.addMensagemSucesso("Validação concluída com Sucesso!");
            return "cadastrarGuiaAtendimentoAdmin.xhtml";

        } else {
            JSFUtil.addMensagemErro("Login e/ou Senha inválido(s)");
        }
        return null;
    }

    public String verificaAtendimentoPrioritario(GuiaAtendimento ga) {
        if (ga.isAtendimentoprioritario() == true) {
            return "Sim";
        } else {
            return "Não";
        }
    }

    public static void main(String[] args) {
        Usuario u = new Usuario();
      u.setCpf("02175922871");
      u.setLogin("ailton");
      u.setNome("José Ailton Rodrigues Lima");
      u.setPerfil("Administrador");
      u.setSenha("250699");
      
        new ControladorUsuario().inserirusuario(u);
    }
}
