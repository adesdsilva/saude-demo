/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import negocio.Veiculo;
import repositorios.RepositorioVeiculo;

/**
 *
 * @author Adelino
 */
@ManagedBean(name = "controleVeiculo")
@SessionScoped
public class ControladorVeiculo {

    private RepositorioVeiculo rveic;

    private Veiculo selectedVeiculo;
    
    private List<SelectItem> listaVeic;

    public ControladorVeiculo() {
        this.rveic = new RepositorioVeiculo();
    }

    public List<SelectItem> getListaVeic() {       
        List<Veiculo> lista = this.recuperarTodosVeiculos();
        listaVeic = new ArrayList<>(lista.size());
        for(Veiculo v : lista){
            listaVeic.add(new SelectItem(v.getCodigo(), v.getPlaca()));
        }
        return listaVeic;
    }

    public void setListaVeic(List<SelectItem> listaVeic) {
        this.listaVeic = listaVeic;
    }

    
    public Veiculo getSelectedVeiculo() {
        return selectedVeiculo;
    }

    public void setSelectedVeiculo(Veiculo selectedVeiculo) {
        this.selectedVeiculo = selectedVeiculo;
    }

    public String inserirveic(Veiculo t) {
        this.rveic.inserir(t);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Veiculo cadastrado com Sucesso!"));
        return "listarVeiculos.xhtml";
    }

    public String editarveic(Veiculo t) {
        this.rveic.editar(t);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Veiculo editado com Sucesso!"));
        return "listarVeiculos.xhtml";
    }

    public Veiculo recuperarveic(int g) {
        return (Veiculo) this.rveic.recuperar(g);
    }

    public void deletarveic(Veiculo t) {
        this.rveic.deletar(t);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CONCLUÍDO", "Veiculo deletado com Sucesso!"));

    }

    public List<Veiculo> recuperarTodosVeiculos() {
        return this.rveic.recuperarTodos();
    }


}
