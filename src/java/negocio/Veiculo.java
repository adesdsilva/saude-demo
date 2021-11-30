/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio;

import controladores.ControladorVeiculo;
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
public class Veiculo implements Serializable{
    @Id
    @GeneratedValue
    private int codigoVeiculo;
    
    @Column(length = 30)
    private String marca;
    
    @Column(length = 40)
    private String modelo;
    
    @Column(length = 10)
    private String placa;
    
    @Column(length = 10)
    private String anoModelo;

    public Veiculo() {
    }

    public Veiculo(int codigoVeiculo, String marca, String modelo, String placa, String anoModelo) {
        this.codigoVeiculo = codigoVeiculo;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.anoModelo = anoModelo;
    }

    public int getCodigo() {
        return codigoVeiculo;
    }

    public void setCodigo(int codigoVeiculo) {
        this.codigoVeiculo = codigoVeiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(String anoModelo) {
        this.anoModelo = anoModelo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.codigoVeiculo;
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
        final Veiculo other = (Veiculo) obj;
        if (this.codigoVeiculo != other.codigoVeiculo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return placa;
    }
    
    public String getCodigoVeiculoString() {
        return codigoVeiculo+"";
    }
    
    public static void main(String[] args) {
        Veiculo v = new Veiculo();
        v.setAnoModelo("2001");
        v.setMarca("Ford KA");
        v.setPlaca("PEV-3276");
        v.setModelo("Ford");
        
        new ControladorVeiculo().inserirveic(v);
    }

}
