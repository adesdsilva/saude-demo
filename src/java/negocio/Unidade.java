/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio;

import controladores.ControladorUnidade;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import util.JSFUtil;

/**
 *
 * @author Adelino
 */
@Table
@Entity
@ManagedBean
@RequestScoped
public class Unidade implements Serializable{
    @Id
    @GeneratedValue
    private int codigoUnidade;
    
    @Column(length = 100)
    private String nome;
    
    private String observacao;
    
    private String atencao;
    
    @Column(length = 10)
    private String cep;
    
    @Column(length = 100)
    private String logradouro;
    
    @Column(length = 100)
    private String bairro;
    
    @Column(length = 10)
    private String numero;
    
    @Column(length = 100)
    private String complemento;
    
    @Column(length = 100)
    private String cidade;
    
    @Column(length = 2)
    private String estado;

    public Unidade() {
    }

    public Unidade(int codigoUnidade, String nome, String observacao, String atencao, String cep, String logradouro, String bairro,String numero, String complemento, String cidade, String estado) {
        this.codigoUnidade = codigoUnidade;
        this.nome = nome;
        this.observacao = observacao;
        this.atencao = atencao;
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
        this.cidade = cidade;
        this.estado = estado;
    }

    public int getCodigoUnidade() {
        return codigoUnidade;
    }

    public void setCodigoUnidade(int codigoUnidade) {
        this.codigoUnidade = codigoUnidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getAtencao() {
        return atencao;
    }

    public void setAtencao(String atencao) {
        this.atencao = atencao;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public void buscaCep(String cep) {
        //JOptionPane.showMessageDialog(null, cep);
        //Faz a busca para o cep 58043-280
        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
        //A ferramenta de busca ignora qualquer caracter que n?o seja n?mero.

        //caso a busca ocorra bem, imprime os resultados.
        if (webServiceCep.wasSuccessful()) {
            this.setLogradouro(webServiceCep.getLogradouroFull());
            this.setCidade(webServiceCep.getCidade());
            this.setEstado(webServiceCep.getUf());
            this.setBairro(webServiceCep.getBairro());
            
            //caso haja problemas imprime as exce??es.
        } else {
            JSFUtil.addMensagemErro(webServiceCep.getResultText());
            
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.codigoUnidade;
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
        final Unidade other = (Unidade) obj;
        if (this.codigoUnidade != other.codigoUnidade) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Unidade{" + "codigoUnidade=" + codigoUnidade + ", nome=" + nome + ", observacao=" + observacao + ", atencao=" + atencao + ", cep=" + cep + ", logradouro=" + logradouro + ", bairro=" + bairro + ", numero=" + numero + ", complemento=" + complemento + ", cidade=" + cidade + ", estado=" + estado + '}';
    }

    public static void main(String[] args) {
        Unidade u = new Unidade();
        u.setAtencao("sem Atenção");
        u.setCep("34039012");
        u.setCidade("Angelim");
        u.setComplemento("Térreo");
        u.setEstado("PE");
        u.setLogradouro("BR 180 Km 47");
        u.setNome("PSF Garanhuns Central");
        u.setNumero("545454");
        u.setObservacao("Sem observação");
        u.setBairro("São José");
        new ControladorUnidade().inserirunid(u);
    }
   
    
}

