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
import negocio.Medico;
import repositorios.RepositorioMedico;

/**
 *
 * @author Adelino
 */
@ManagedBean(name = "controleMedico")
@SessionScoped
public class ControladorMedico {
    
    private RepositorioMedico rmed;
    
    private Medico selectedMedico;

    public ControladorMedico() {
        this.rmed = new RepositorioMedico();
    }

    public Medico getSelectedMedico() {
        return selectedMedico;
    }

    public void setSelectedMedico(Medico selectedMedico) {
        this.selectedMedico = selectedMedico;
    }
    
    public String inserirmed(Medico t){
       this.rmed.inserir(t); 
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Medico cadastrado com Sucesso!"));
       return "listarMedicos.xhtml";
    }
    
    public String editarmed(Medico t){       
        this.rmed.editar(t);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Medico editado com Sucesso!"));
        return "listarMedicos.xhtml";
    }
    
    public Medico recuperarmed(int g){
        return (Medico) this.rmed.recuperar(g);
    }
    
    public void deletarmed(Medico t){
        this.rmed.deletar(t);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Medico deletado com Sucesso!"));

    }
    
    public List<Medico> recuperarTodosMedicos(){
        return this.rmed.recuperarTodos();
    }
    
    
}

