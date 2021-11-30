/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controladores;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import negocio.Unidade;
import repositorios.RepositorioUnidade;

/**
 *
 * @author Adelino
 */
@ManagedBean(name = "controleUnidade")
@SessionScoped
public class ControladorUnidade {
    
    private RepositorioUnidade runid;
    
    private Unidade selectedUnidade;

    public ControladorUnidade() {
        this.runid = new RepositorioUnidade();
    }

    public Unidade getSelectedUnidade() {
        return selectedUnidade;
    }

    public void setSelectedUnidade(Unidade selectedUnidade) {
        this.selectedUnidade = selectedUnidade;
    }
    
    public String inserirunid(Unidade t){
       this.runid.inserir(t); 
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Unidade cadastrado com Sucesso!"));
        return "listarUnidades.xhtml";
    }
    
    public String editarunid(Unidade t){       
        this.runid.editar(t);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Unidade editado com Sucesso!"));
        return "listarUnidades.xhtml";
    }
    
    public Unidade recuperarunid(int g){
        return (Unidade) this.runid.recuperar(g);
    }
    
    public void deletarunid(Unidade t){
        this.runid.deletar(t);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Unidade deletado com Sucesso!"));

    }
    
    public List<Unidade> recuperarTodosUnidades(){
        return this.runid.recuperarTodos();
    }
    
    
}
