/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio;

import controladores.ControladorMotorista;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Adelino
 */
@Table
@Entity
@ManagedBean
@RequestScoped
public class Motorista implements Serializable{
    @Id
    @GeneratedValue
    private int codigoMotorista;
    
    @Column(length = 100)
    private String nome;
    
    private int cnh;
    
    @Temporal(TemporalType.DATE)
    private Date dataVencimentoCnh;
    
    @Column(length = 10)
    private String dataVencimentoDate;

    public Motorista() {
    }

    public Motorista(int codigoMotorista, String nome, int cnh, Date dataVencimentoCnh, String dataVencimentoDate) {
        this.codigoMotorista = codigoMotorista;
        this.nome = nome;
        this.cnh = cnh;
        this.dataVencimentoCnh = dataVencimentoCnh;
        this.dataVencimentoDate = dataVencimentoDate;
    }

    public int getCodigoMotorista() {
        return codigoMotorista;
    }

    public void setCodigoMotorista(int codigoMotorista) {
        this.codigoMotorista = codigoMotorista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCnh() {
        return cnh;
    }

    public void setCnh(int cnh) {
        this.cnh = cnh;
    }

    public Date getDataVencimentoCnh() {
        return dataVencimentoCnh;
    }

    public void setDataVencimentoCnh(Date dataVencimentoCnh) {
        this.dataVencimentoCnh = dataVencimentoCnh;
    }

    public String getDataVencimentoDate() {
        return dataVencimentoDate;
    }

    public void setDataVencimentoDate(String dataVencimentoDate) {
        this.dataVencimentoDate = dataVencimentoDate;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.codigoMotorista;
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
        final Motorista other = (Motorista) obj;
        if (this.codigoMotorista != other.codigoMotorista) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Motorista{" + "codigoMotorista=" + codigoMotorista + ", nome=" + nome + ", cnh=" + cnh + ", dataVencimentoCnh=" + dataVencimentoCnh + ", dataVencimentoDate=" + dataVencimentoDate + '}';
    }
    
    public String getCodigoMotoristaString() {
        return codigoMotorista+"";
    }
    
    public static void main(String[] args) {
        Motorista m = new Motorista();
        m.setCnh(12123);
        m.setDataVencimentoCnh(new Date());
        m.setDataVencimentoDate("23/01/2016");
        m.setNome("José Carlos de Oliveira");
        
        new ControladorMotorista().inserirmotor(m);
    }
    
}
