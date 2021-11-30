/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controladores;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import negocio.Motorista;
import repositorios.RepositorioMotorista;

/**
 *
 * @author Adelino
 */
@ManagedBean(name = "controleMotorista")
@SessionScoped
public class ControladorMotorista {
    
    private RepositorioMotorista rmotor;
    
    private Motorista selectedMotorista;
    
    private List<SelectItem> listaMotor;

    public ControladorMotorista() {
        this.rmotor = new RepositorioMotorista();
    }

    public List<SelectItem> getListaMotor() {
        List<Motorista> lista = this.recuperarTodosMotoristas();
        listaMotor = new ArrayList<>(lista.size());
        for(Motorista m : lista){
            listaMotor.add(new SelectItem(m.getCodigoMotorista(), m.getNome()));
        }
        return listaMotor;
    }

    public void setListaMotor(List<SelectItem> listaMotor) {
        this.listaMotor = listaMotor;
    }
    
    public Motorista getSelectedMotorista() {
        return selectedMotorista;
    }

    public void setSelectedMotorista(Motorista selectedMotorista) {
        this.selectedMotorista = selectedMotorista;
    }
    
    public String inserirmotor(Motorista t){
        SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
        t.setDataVencimentoDate(sdfr.format(t.getDataVencimentoCnh()));
       this.rmotor.inserir(t); 
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Motorista cadastrado com Sucesso!"));
        return "listarMotoristas.xhtml";
    }
    
    public String editarmotor(Motorista t){ 
        SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
        t.setDataVencimentoDate(sdfr.format(t.getDataVencimentoCnh()));
        this.rmotor.editar(t);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Motorista editado com Sucesso!"));
        return "listarMotoristas.xhtml";
    }
    
    public Motorista recuperarmotor(int g){
        return (Motorista) this.rmotor.recuperar(g);
    }
    
    public void deletarmotor(Motorista t){
        this.rmotor.deletar(t);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Motorista deletado com Sucesso!"));

    }
    
    public List<Motorista> recuperarTodosMotoristas(){
        return this.rmotor.recuperarTodos();
    }
    
    
}



