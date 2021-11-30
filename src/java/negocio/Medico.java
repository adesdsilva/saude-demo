/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio;

import controladores.ControladorMedico;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Adelino
 */
@Table
@Entity
@ManagedBean
@RequestScoped
public class Medico implements Serializable{
    @Id
    @GeneratedValue
    private int codigoMedico;
    
    @Column(length = 100)
    private String nome;
    
    @Column(length = 50)
    private String especialidade;
    
    @Column(length = 15)
    private String crm;

    public Medico() {
    }

    public Medico(int codigoMedico, String nome, String especialidade, String crm) {
        this.codigoMedico = codigoMedico;
        this.nome = nome;
        this.especialidade = especialidade;
        this.crm = crm;
    }

    public int getCodigoMedico() {
        return codigoMedico;
    }

    public void setCodigoMedico(int codigoMedico) {
        this.codigoMedico = codigoMedico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.codigoMedico;
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
        final Medico other = (Medico) obj;
        if (this.codigoMedico != other.codigoMedico) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Medico{" + "codigoMedico=" + codigoMedico + ", nome=" + nome + ", especialidade=" + especialidade + ", crm=" + crm + '}';
    }
        
    public static void main(String[] args) {
        Medico m = new Medico();
        m.setNome("Alison Rodrigo");
        m.setEspecialidade("Cirurgião Plástico");
        m.setCrm("888");
        new ControladorMedico().inserirmed(m);
    }
}
