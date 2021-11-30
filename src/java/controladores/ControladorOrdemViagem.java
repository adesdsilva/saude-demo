/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.swing.JOptionPane;
import negocio.OrdemViagem;
import negocio.Paciente;
import repositorios.RepositorioOrdemViagem;
import util.JSFUtil;

/**
 *
 * @author Adelino
 */
@ManagedBean(name = "controleOrdemViagem")
@SessionScoped
public class ControladorOrdemViagem {

    private repositorios.RepositorioOrdemViagem rov;

    private OrdemViagem selectedOrdemViagem;

    public ControladorOrdemViagem() {
        this.rov = new RepositorioOrdemViagem();
    }

    public OrdemViagem getSelectedOrdemViagem() {
        return selectedOrdemViagem;
    }

    public void setSelectedOrdemViagem(OrdemViagem selectedOrdemViagem) {
        this.selectedOrdemViagem = selectedOrdemViagem;
    }

    public String inserirov(OrdemViagem t) {
        if (t.getPacientes().isEmpty()) {
            JSFUtil.addMensagemErro("Não é possível cadastrar a ordem de viagem. Selecione pelo menos um paciente");
        } else {
            editarPacientesOrdemViagem(t.getPacientes());
            //JOptionPane.showMessageDialog(null,t.toString());
            SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
            t.setDataviagemDate(sdfr.format(t.getDataviagem()));
            this.rov.inserir(t);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Ordem de viagem cadastrado com Sucesso!"));
            return "listarOrdensViagens.xhtml";
        }
        return null;
    }

    public String editarov(OrdemViagem t) {
        if (t.getPacientes().isEmpty()) {
            JSFUtil.addMensagemErro("Não é possível cadastrar a ordem de viagem. Selecione pelo menos um paciente");
        } else {
            editarPacientesOrdemViagem(t.getPacientes());
            this.rov.editar(t);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Ordem de viagem editado com Sucesso!"));
            return "listarOrdensViagens.xhtml";
        }
        return null;
    }

    public OrdemViagem recuperarov(int g) {
        return (OrdemViagem) this.rov.recuperar(g);
    }

    public void deletarov(OrdemViagem t) {
        this.rov.deletar(t);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Ordem de viagem deletado com Sucesso!"));

    }

    public List<OrdemViagem> recuperarTodosOrdemViagens() {
        return this.rov.recuperarTodos();
    }

    public void editarPacientesOrdemViagem(List<Paciente> p) {
        ControladorPaciente cp = new ControladorPaciente();
        for (int i = 0; i < p.size(); i++) {
            cp.editarpac(p.get(i));
        }
    }
}
