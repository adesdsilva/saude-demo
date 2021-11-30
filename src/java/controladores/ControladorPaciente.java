/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
import javax.faces.model.DataModelListener;
import negocio.OrdemViagem;
import negocio.Paciente;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import repositorios.RepositorioPaciente;

@ManagedBean(name = "controlePaciente")
@SessionScoped
public class ControladorPaciente {
    private repositorios.RepositorioPaciente rpac;
    
    private Paciente selectedPaciente;
    
    private List<Paciente> pacientesSelecionados;
    
    private DataModel<Paciente> listaPacientes = new ArrayDataModel<>();

    public ControladorPaciente() {
        this.rpac = new RepositorioPaciente();
    }

    public Paciente getSelectedPaciente() {
        return selectedPaciente;
    }

    public void setSelectedPaciente(Paciente selectedPaciente) {
        this.selectedPaciente = selectedPaciente;
    }

    public List<Paciente> getPacientesSelecionados() {
        //pacientesSelecionados = this.recuperarTodosPacientes();
        return pacientesSelecionados;
    }

    public void setPacientesSelecionados(List<Paciente> pacientesSelecionados) {
        this.pacientesSelecionados = pacientesSelecionados;
    }

    public DataModel<Paciente> getListaPacientes() {
        //listaPacientes = (DataModel<Paciente>) this.recuperarTodosPacientes();
        return listaPacientes;
    }

    public void setListaPacientes(DataModel<Paciente> listaPacientes) {
        this.listaPacientes = listaPacientes;
    }
    
    
    
    public String inserirpac(Paciente t){
       this.rpac.inserir(t); 
       //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Paciente cadastrado com Sucesso!"));
        return "listarPacientes.xhtml";
    }
    
    public String editarpac(Paciente t){       
        this.rpac.editar(t);
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Paciente editado com Sucesso!"));
        return "listarPacientes.xhtml";
    }
    
    public Paciente recuperarpac(int g){
        return (Paciente) this.rpac.recuperar(g);
    }
    
    public void deletarpac(Paciente t){
        this.rpac.deletar(t);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Paciente deletado com Sucesso!"));

    }
    
    public List<Paciente> recuperarTodosPacientes(){
        return this.rpac.recuperarTodos();
    }
    
    public void abrirDialogo(){
        Map<String, Object> opcoes = new HashMap<>();
        opcoes.put("modal", true);
        opcoes.put("resizable", false);
        opcoes.put("contentHeight", 550);
        opcoes.put("draggable", false);
        
        RequestContext.getCurrentInstance().openDialog("selecaoPaciente", opcoes, null);
    }
    
    public void selecionar(Paciente paciente){
        RequestContext.getCurrentInstance().closeDialog(paciente);
    }
    
    public String reiniciar(){
        this.selectedPaciente = new Paciente();
        return null;        
    }
    
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Paciente Editado!", ((Paciente) event.getObject()).getNome());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edição Cancelada!", ((Paciente) event.getObject()).getNome());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public String statusAcompanhante(Paciente p){
        if(p.isAcompanhante() == true){
            return "Sim";
        }
        return "Não";
    }
    
}
