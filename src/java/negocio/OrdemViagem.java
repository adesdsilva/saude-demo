/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table
@Entity
@ViewScoped
@ManagedBean(name = "ordemviagem")
public class OrdemViagem implements Serializable{
    @Id
    @GeneratedValue
    private int codigoordem;
    
    @OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
    @JoinColumn(name="ordemviagem_codigoordem")
    private List<Paciente> pacientes = new ArrayList<>();    
   
    @OneToOne
    private Veiculo veiculo = new Veiculo();       
  
    @OneToOne
    private Motorista motorista = new Motorista();
                
    @Temporal(TemporalType.DATE)
    private Date dataviagem;
    
     private String dataviagemDate;
    
    @Column(length = 30)
    private String cidadedestino;

    public OrdemViagem() {
    }

    public OrdemViagem(int codigoordem, List<Paciente> pacientes, Veiculo veiculo, Motorista motorista, Date dataviagem, String dataviagemDate, String cidadedestino) {
        this.codigoordem = codigoordem;
        this.pacientes = pacientes;
        this.veiculo = veiculo;
        this.motorista = motorista;
        this.dataviagem = dataviagem;
        this.dataviagemDate = dataviagemDate;
        this.cidadedestino = cidadedestino;
    }

   

    public int getCodigoordem() {
        return codigoordem;
    }

    public void setCodigoordem(int codigoordem) {
        this.codigoordem = codigoordem;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public Date getDataviagem() {
        return dataviagem;
    }

    public void setDataviagem(Date dataviagem) {
        this.dataviagem = dataviagem;
    }

    public String getCidadedestino() {
        return cidadedestino;
    }

    public void setCidadedestino(String cidadedestino) {
        this.cidadedestino = cidadedestino;
    }

    public String getDataviagemDate() {
        return dataviagemDate;
    }

    public void setDataviagemDate(String dataviagemDate) {
        this.dataviagemDate = dataviagemDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.codigoordem;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrdemViagem other = (OrdemViagem) obj;
        if (this.codigoordem != other.codigoordem) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OrdemViagem{" + "codigoordem=" + codigoordem + ", pacientes=" + pacientes + ", veiculo=" + veiculo + ", motorista=" + motorista  + ", dataviagem=" + dataviagem + ", dataviagemDate=" + dataviagemDate + ", cidadedestino=" + cidadedestino + '}';
    } 
    
    public void addPacientesNaLista(List<Paciente> lista){
        
        for (int i = 0; i < lista.size(); i++) {
            //JOptionPane.showMessageDialog(null, lista.get(i).getNome());
            this.pacientes.add(lista.get(i));
        }
        
    }
    
    public void selectPaciente(Paciente p){
        //ControladorPaciente cp = new ControladorPaciente();
        this.pacientes.add(p);
    }
    
//    public void setarValoresPaciente(Paciente p){
////        ControladorPaciente cp = new ControladorPaciente();
////        p = cp.getSelectedPaciente();
//        
//        this.getPaciente().setCodigopaciente(p.getCodigopaciente());
//        this.getPaciente().setNome(p.getNome());
//        this.getPaciente().setCpf(p.getCpf());
//        this.getPaciente().setRg(p.getRg());
//        this.getPaciente().setTitulozona(p.getTitulozona());
//        this.getPaciente().setCartaosus(p.getCartaosus());
//        this.getPaciente().setCep(p.getCep());
//        this.getPaciente().setLogradouro(p.getLogradouro());
//        this.getPaciente().setComplemento(p.getComplemento());
//        this.getPaciente().setBairro(p.getBairro());
//        this.getPaciente().setNumero(p.getNumero());
//        this.getPaciente().setCidade(p.getCidade());
//        this.getPaciente().setAgentesaude(p.getAgentesaude());
//        this.getPaciente().setDataNascimento(p.getDataNascimento());
//        //JOptionPane.showMessageDialog(null, this.getPaciente().getNome());
//        
//    }
    
}
