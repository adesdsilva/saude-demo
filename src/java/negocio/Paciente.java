/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import br.com.caelum.stella.bean.validation.CPF;
import br.com.caelum.stella.bean.validation.NIT;
import br.com.caelum.stella.bean.validation.TituloEleitoral;
import controladores.ControladorPaciente;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.swing.JOptionPane;
import util.JSFUtil;

@Table
@Entity
@ViewScoped
@ManagedBean(name = "paciente")
public class Paciente implements Serializable {

    @Id
    @GeneratedValue
    private int codigopaciente;

    @Column(length = 100)
    private String nome;

    @CPF
    private String cpf;

    private String rg;

    @Column(length = 10)
    private String dataNascimento;

    @Column(length = 10)
    private String cep;

    @Column(length = 100)
    private String logradouro;

    @Column(length = 10)
    private String numero;

    @Column(length = 100)
    private String cidade;

    @Column(length = 5)
    private String estado;

    @Column(length = 100)
    private String bairro;

    @Column(length = 100)
    private String complemento;

    @TituloEleitoral
    @Column(length = 16)
    private String titulozona;

    @NIT
    @Column(length = 20)
    private String cartaosus;

    @Column(length = 100)
    private String agentesaude;

    private boolean acompanhante;
    
    private String hospitalDestino;
    
    @Column(length = 10)
    private String horarioConsulta;
    
    
    public Paciente() {
    }

    public Paciente(int codigopaciente, String nome, String cpf, String rg, String dataNascimento, String cep, String logradouro, String numero, String cidade, String estado, String bairro, String complemento, String titulozona, String cartaosus, String agentesaude, boolean acompanhante, String hospitalDestino, String horarioConsulta) {
        this.codigopaciente = codigopaciente;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.bairro = bairro;
        this.complemento = complemento;
        this.titulozona = titulozona;
        this.cartaosus = cartaosus;
        this.agentesaude = agentesaude;
        this.acompanhante = acompanhante;
        this.hospitalDestino = hospitalDestino;
        this.horarioConsulta = horarioConsulta;
    }

        
    public int getCodigopaciente() {
        return codigopaciente;
    }

    public void setCodigopaciente(int codigopaciente) {
        this.codigopaciente = codigopaciente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCep() {
        return cep;
    }

    
    
    public void preencheCampos(String cep){
       CepWebService c = new BuscaLogradouro().BuscaLogradouro(cep);

        if (c != null) {
            this.bairro = c.getBairro();
            this.logradouro = c.getLogradouro();
            this.cidade = c.getCidade();
            this.estado = c.getEstado();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível encontar o CEP!", "Erro!!!"));
        } 
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    public String getTitulozona() {
        return titulozona;
    }

    public void setTitulozona(String titulozona) {
        this.titulozona = titulozona;
    }

    public String getCartaosus() {
        return cartaosus;
    }

    public void setCartaosus(String cartaosus) {
        this.cartaosus = cartaosus;
    }

    public String getAgentesaude() {
        return agentesaude;
    }

    public void setAgentesaude(String agentesaude) {
        this.agentesaude = agentesaude;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public boolean isAcompanhante() {
        return acompanhante;
    }

    public void setAcompanhante(boolean acompanhante) {
        this.acompanhante = acompanhante;
    }

    public String getHospitalDestino() {
        return hospitalDestino;
    }

    public void setHospitalDestino(String hospitalDestino) {
        this.hospitalDestino = hospitalDestino;
    }

    public String getHorarioConsulta() {
        return horarioConsulta;
    }

    public void setHorarioConsulta(String horarioConsulta) {
        this.horarioConsulta = horarioConsulta;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.codigopaciente;
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
        final Paciente other = (Paciente) obj;
        if (this.codigopaciente != other.codigopaciente) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Paciente{" + "codigopaciente=" + codigopaciente + ", nome=" + nome + 
                ", cpf=" + cpf + ", rg=" + rg + ", dataNascimento=" + dataNascimento + 
                ", cep=" + cep + ", logradouro=" + logradouro + ", numero=" + numero + 
                ", cidade=" + cidade + ", estado=" + estado + ", bairro=" + bairro + 
                ", complemento=" + complemento + ", titulozona=" + titulozona + ", cartaosus=" + cartaosus + 
                ", agentesaude=" + agentesaude + ", acompanhante=" + acompanhante + ", hospitalDestino=" + hospitalDestino + 
                ", horarioConsulta=" + horarioConsulta + '}';
    }

    
    public void addMessage() {
        String summary = this.acompanhante ? "Acompanhante" : "Sem Acompanhante";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }
    public void isValid(String s) {
        if (s.matches("[1-2]\\d{10}00[0-1]\\d") || s.matches("[7-9]\\d{14}")) {
            if (somaPonderada(s) % 11 == 1) {
                JSFUtil.addMensagemErro("Cartão SUS inválido!");
            }
        }
    }

    private int somaPonderada(String s) {
        char[] cs = s.toCharArray();
        int soma = 0;
        for (int i = 0; i < cs.length; i++) {
            soma += Character.digit(cs[i], 10) * (15 - i);
        }
        return soma;
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
    
    
    
    public static void main(String[] args) {
//        Paciente p = new Paciente();
//        p.setAgentesaude("Carlos");
//        p.setCartaosus("532246");
//        p.setCep("55.292-300");
//        p.setCidade("Garanhuns");
//        p.setEstado("PE");
//        p.setBairro("Boa Vista");
//        p.setDataNascimento("10/10/2000");
//        p.setCpf("13234870420");
//        //p.setCpf("06755840432");
//        p.setEstado("PE");
//        p.setLogradouro("Rua Vitória");
//        p.setNome("Adelino Santos da Silva");
//        p.setNumero("102");
//        p.setRg("123456789");
//        p.setTitulozona("343422356");
//        
//        new ControladorPaciente().inserirpac(p);
        WebServiceCep webServiceCep = WebServiceCep.searchCep("05001-200");
        //A ferramenta de busca ignora qualquer caracter que n?o seja n?mero.

        //caso a busca ocorra bem, imprime os resultados.
        if (webServiceCep.wasSuccessful()) {
            
            System.out.println("Cep: " + webServiceCep.getCep());
            System.out.println("Logradouro: " + webServiceCep.getLogradouroFull());
            System.out.println("Bairro: " + webServiceCep.getBairro());
            System.out.println("Cidade: "
                    + webServiceCep.getCidade() + "/" + webServiceCep.getUf());

            //caso haja problemas imprime as exce??es.
        } else {
            JOptionPane.showMessageDialog(null, webServiceCep.getResultText());
            
        }
        
    }

}
