/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import br.com.caelum.stella.bean.validation.CPF;
import controladores.ControladorUsuario;
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
public class Usuario implements Serializable {

    @Id
    @GeneratedValue
    private int codigoUsuario;

    @Column(length = 100)
    private String nome;

    @CPF
    private String cpf;

    @Column(length = 30)
    private String login;

    @Column(length = 50)
    private String senha;

    @Column(length = 20)
    private String perfil;

    public Usuario() {
    }

    public Usuario(int codigoUsuario, String nome, String cpf, String login, String senha, String perfil) {
        this.codigoUsuario = codigoUsuario;
        this.nome = nome;
        this.cpf = cpf;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.codigoUsuario;
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
        final Usuario other = (Usuario) obj;
        if (this.codigoUsuario != other.codigoUsuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "codigoUsuario=" + codigoUsuario + ", nome=" + nome + ", cpf=" + cpf + ", login=" + login + ", senha=" + senha + ", perfil=" + perfil + '}';
    }

    public static void main(String[] args) {
        Usuario u = new Usuario();
        u.setCpf("120239349");
        u.setLogin("joseaugusto");
        u.setNome("augusto");
        u.setPerfil("Administrador");
        u.setSenha("1028");

        new ControladorUsuario().inserirusuario(u);
    }
}
