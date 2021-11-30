/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.sun.corba.se.spi.presentation.rmi.StubAdapter;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import negocio.Procedimento;
import negocio.Usuario;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.context.RequestContext;
import repositorios.RepositorioProcedimento;
import repositorios.RepositorioUsuario;
import util.JSFUtil;

@ManagedBean(name = "controleProcedimento")
@SessionScoped
public class ControladorProcedimento {

    private RepositorioProcedimento rproc;

    private Procedimento selectedProcedimento;

    private Usuario cadastrarProcedimento;
    
    public ControladorProcedimento() {
        this.rproc = new RepositorioProcedimento();
    }

    public Procedimento getSelectedProcedimento() {
        return selectedProcedimento;
    }

    public void setSelectedProcedimento(Procedimento selectedProcedimento) {
        this.selectedProcedimento = selectedProcedimento;
    }

    public String inserirproc(Procedimento t) {
        //org.primefaces.context.RequestContext.getCurrentInstance().openDialog("dial.show();");
        this.rproc.inserir(t);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Procedimento cadastrado com Sucesso!"));
        return "listarProcedimentos.xhtml";
    }

    public String editarproc(Procedimento t) {
        this.rproc.editar(t);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Procedimento editado com Sucesso!"));
        return "listarProcedimentos.xhtml";
    }

    public Procedimento recuperarproc(int g) {
        return (Procedimento) this.rproc.recuperar(g);
    }

    public void deletarproc(Procedimento t) {
        this.rproc.deletar(t);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Procedimento deletado com Sucesso!"));

    }

    public Usuario getCadastrarProcedimento() {
        return cadastrarProcedimento;
    }

    public void setCadastrarProcedimento(Usuario cadastrarProcedimento) {
        this.cadastrarProcedimento = cadastrarProcedimento;
    }
    
    

    public List<Procedimento> recuperarTodosProcedimentos() {
        return this.rproc.recuperarTodos();
    }

    public void abrirDialogo() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("dial.show();");

    }
    
    public boolean recuperarPorData(){
        List<Procedimento> lg = new ControladorProcedimento().recuperarTodosProcedimentos();  
        //return lg.size();
        if(lg.size() > 5){
            return true;
        }else{
            return false;
        }
    }
    
    public String validarAdmin(Usuario usuario) {
        usuario.setSenha(usuario.getSenha());
        usuario.setSenha(DigestUtils.md5Hex(usuario.getSenha()));
        cadastrarProcedimento = new RepositorioUsuario().recuperarPorLogin(usuario.getLogin(), usuario.getSenha());
        if (cadastrarProcedimento != null) {
            JSFUtil.addMensagemSucesso("Validação concluída com Sucesso!");
            return "procedimentoAdmin.xhtml";

        } else {
            JSFUtil.addMensagemErro("Login e/ou Senha inválido(s)");
        }
        return null;
    }

}
