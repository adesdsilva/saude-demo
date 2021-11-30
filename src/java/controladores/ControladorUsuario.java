/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controladores;

import java.io.IOException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import negocio.Usuario;
import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.context.RequestContext;
import repositorios.RepositorioUsuario;
import util.JSFUtil;

/**
 *
 * @author Adelino
 */
@ManagedBean(name = "controleUsuario")
@SessionScoped
public class ControladorUsuario {
    
    private RepositorioUsuario rusuario;
    private Usuario login;
    private Usuario selectedUsuario;

    public ControladorUsuario() {
        this.rusuario = new RepositorioUsuario();
    }

    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public Usuario getLogin() {
        return login;
    }

    public void setLogin(Usuario login) {
        this.login = login;
    }
    
    public String inserirusuario(Usuario t){
        if(verificarUsuarioCpf(t.getCpf())){
            JSFUtil.addMensagemErro("CPF já cadastrado");
        }
        else if(verificarUsuarioLogin(t.getLogin())){
            JSFUtil.addMensagemErro("Login já cadastrado");
        }
        else{
        t.setSenha(DigestUtils.md5Hex(t.getSenha()));
       this.rusuario.inserir(t); 
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Usuario cadastrado com Sucesso!"));
        return "listarUsuarios.xhtml";
    }
        return null;
    }
    
    public String editarusuario(Usuario t){
        //JOptionPane.showMessageDialog(null, t.toString());
        t.setSenha(DigestUtils.md5Hex(t.getSenha()));
        this.rusuario.editar(t);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Usuario editado com Sucesso!"));
        return "listarUsuarios.xhtml";
    }
    public String editarusuarioLogin(){
        //JOptionPane.showMessageDialog(null, login.toString());
        login.setSenha(DigestUtils.md5Hex(login.getSenha()));
        this.rusuario.editar(login);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Usuario editado com Sucesso!"));
        return "apresentaUsuario.xhtml";
    }
    
    public Usuario recuperarusuario(int g){
        return (Usuario) this.rusuario.recuperar(g);
    }
    
    public void deletarusuario(Usuario t){
        this.rusuario.deletar(t);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Usuario deletado com Sucesso!"));

    }
    
    public List<Usuario> recuperarTodosUsuarios(){
        return this.rusuario.recuperarTodos();
    }
    public String validarLogin(Usuario usuario) {
        usuario.setSenha(usuario.getSenha());
        usuario.setSenha(DigestUtils.md5Hex(usuario.getSenha()));
        this.login = this.rusuario.recuperarPorLogin(usuario.getLogin(), usuario.getSenha());
        if (this.login != null) {
            ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute("usuarioLogado", this.login);
//           this.redirecionarUsuario(this.login.getPerfil1());
            this.selectedUsuario = login;
            return "faces/telaInicial.xhtml";

        } else {
            JSFUtil.addMensagemErro("Login e/ou Senha inválido(s)");
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login e/ou Senha inválido(s)"));
        }
        return null;
    }
     public void abrirDialogo(){
        org.primefaces.context.RequestContext.getCurrentInstance().execute("confirmatt.show();");
    }
     public String redirecionar(){
         return "editarUsuario.xhtml";
     }
     
     public boolean verificarUsuarioCpf(String cpf){
         boolean jaexiste = false;
         List<Usuario> users = new ControladorUsuario().recuperarTodosUsuarios();
         for(int i=0; i<users.size(); i++){
             if(users.get(i).getCpf().equals(cpf)){
                 jaexiste = true;
                 break;
             }
         }
         return jaexiste;
     }
     
     public boolean verificarUsuarioLogin(String login){
         boolean jaexiste = false;
         List<Usuario> users = new ControladorUsuario().recuperarTodosUsuarios();
         for(int i=0; i<users.size(); i++){
             if(users.get(i).getLogin().equals(login)){
                 jaexiste = true;
                 break;
             }
         }
         return jaexiste;
     }
    
     public void logoff() {
        this.login = null;
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../faces/login.xhtml");
        } catch (IOException ex) {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário", ex.getMessage()));
        }
    }

     
}


