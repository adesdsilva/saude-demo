/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primefaces;

import controladores.ControladorPaciente;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import negocio.Paciente;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author Adelino
 */
@ManagedBean(name = "manipuladorTabelaDaosMB")
@ViewScoped
public class TabelaDados {

    private DataTable dataTable;
    
    private List<Paciente> listaPac = new ArrayList<Paciente>();

    public TabelaDados() {
        ControladorPaciente cp = new ControladorPaciente();
        for (int i = 0; i < cp.recuperarTodosPacientes().size(); i++) {
            this.listaPac.add(cp.recuperarTodosPacientes().get(i));
        }
    }

    public DataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(DataTable dataTable) {
        this.dataTable = dataTable;
    }

    public List<Paciente> getListaPac() {
        return listaPac;
    }

    public void setListaPac(List<Paciente> listaPac) {
        this.listaPac = listaPac;
    }
    
    public void selecionarLinha(){ 
        Paciente pacienteSelecionada = (Paciente) dataTable.getRowData(); 
        System.out.println("Noticia Selecionada = "+pacienteSelecionada.getCodigopaciente() + "|" + pacienteSelecionada.getNome()); 
    }
    
    public void qntLinhasSelecionadas(){ 
        int count = dataTable.getRowCount();
        System.out.println("quantidade de registros = "+count);
    }

    
}
