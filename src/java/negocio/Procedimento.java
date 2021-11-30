/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio;

import controladores.ControladorProcedimento;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
@ManagedBean
@RequestScoped
public class Procedimento implements Serializable{
    @Id
    @GeneratedValue
    private int codigoprocedimento;
    
    @Column(length = 50)
    private String nome;

    public Procedimento() {
    }

    public Procedimento(int codigoprocedimento, String nome) {
        this.codigoprocedimento = codigoprocedimento;
        this.nome = nome;
    }

    public int getCodigoprocedimento() {
        return codigoprocedimento;
    }

    public void setCodigoprocedimento(int codigoprocedimento) {
        this.codigoprocedimento = codigoprocedimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.codigoprocedimento;
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
        final Procedimento other = (Procedimento) obj;
        if (this.codigoprocedimento != other.codigoprocedimento) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Procedimento{" + "codigoprocedimento=" + codigoprocedimento + ", nome=" + nome + '}';
    }
    
    public static void main(String[] args) {
        Procedimento p = new Procedimento();
        p.setNome("Pediatria");
        new ControladorProcedimento().inserirproc(p);
    }
}
