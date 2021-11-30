/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import controladores.ControladorUsuario;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.JOptionPane;
import org.hibernate.annotations.Type;

@Table
@Entity
@ViewScoped
@ManagedBean(name = "guiaautorizacao")
public class GuiaAutorizacao implements Serializable {

    @Id
    @GeneratedValue
    private int codigoguiaautorizacao;

    @OneToOne
    private Paciente paciente;

    @OneToOne
    private Usuario usuario;

    @OneToOne
    private Medico medico;

    @Type(type = "true_false")
    private boolean status;

    @OneToOne
    private Unidade unidadesolicitante;

    @Temporal(TemporalType.DATE)
    private Date datasolicitante;

    @Column(length = 10)
    private String datasolicitanteDate;

    @Temporal(TemporalType.DATE)
    private Date dataautorizacao;

    @Column(length = 10)
    private String dataautorizacaoDate;

    @Temporal(TemporalType.DATE)
    private Date dataagendada;

    @Column(length = 10)
    private String dataagendadaDate;

    @OneToOne
    private Unidade unidadeatendimento;

    private String observacao;

    @Column(length = 10)
    private String horaagendada;

    @Type(type = "true_false")
    private boolean atendimentoprioritario;

    private String atencao;

    @OneToOne
    private Procedimento procedimento;

    public GuiaAutorizacao() {
    }

    public GuiaAutorizacao(int codigoguiaautorizacao, Paciente paciente, Usuario usuario, Medico medico, boolean status, Unidade unidadesolicitante, Date datasolicitante, String datasolicitanteDate, Date dataautorizacao, String dataautorizacaoDate, Date dataagendada, String dataagendadaDate, Unidade unidadeatendimento, String observacao, String horaagendada, boolean atendimentoprioritario, String atencao, Procedimento procedimento) {
        this.codigoguiaautorizacao = codigoguiaautorizacao;
        this.paciente = paciente;
        this.usuario = usuario;
        this.medico = medico;
        this.status = status;
        this.unidadesolicitante = unidadesolicitante;
        this.datasolicitante = datasolicitante;
        this.datasolicitanteDate = datasolicitanteDate;
        this.dataautorizacao = dataautorizacao;
        this.dataautorizacaoDate = dataautorizacaoDate;
        this.dataagendada = dataagendada;
        this.dataagendadaDate = dataagendadaDate;
        this.unidadeatendimento = unidadeatendimento;
        this.observacao = observacao;
        this.horaagendada = horaagendada;
        this.atendimentoprioritario = atendimentoprioritario;
        this.atencao = atencao;
        this.procedimento = procedimento;
    }

    public Procedimento getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(Procedimento procedimento) {
        this.procedimento = procedimento;
    }

    public int getCodigoguiaautorizacao() {
        return codigoguiaautorizacao;
    }

    public void setCodigoguiaautorizacao(int codigoguiaautorizacao) {
        this.codigoguiaautorizacao = codigoguiaautorizacao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Unidade getUnidadesolicitante() {
        return unidadesolicitante;
    }

    public void setUnidadesolicitante(Unidade unidadesolicitante) {
        this.unidadesolicitante = unidadesolicitante;
    }

    public Date getDatasolicitante() {
        return datasolicitante;
    }

    public void setDatasolicitante(Date datasolicitante) {
        this.datasolicitante = datasolicitante;
    }

    public String getDatasolicitanteDate() {
        return datasolicitanteDate;
    }

    public void setDatasolicitanteDate(String datasolicitanteDate) {
        this.datasolicitanteDate = datasolicitanteDate;
    }

    public Date getDataautorizacao() {
        return dataautorizacao;
    }

    public void setDataautorizacao(Date dataautorizacao) {
        this.dataautorizacao = dataautorizacao;
    }

    public String getDataautorizacaoDate() {
        return dataautorizacaoDate;
    }

    public void setDataautorizacaoDate(String dataautorizacaoDate) {
        this.dataautorizacaoDate = dataautorizacaoDate;
    }

    public Date getDataagendada() {
        return dataagendada;
    }

    public void setDataagendada(Date dataagendada) {
        this.dataagendada = dataagendada;
    }

    public String getDataagendadaDate() {
        return dataagendadaDate;
    }

    public void setDataagendadaDate(String dataagendadaDate) {
        this.dataagendadaDate = dataagendadaDate;
    }

    public Unidade getUnidadeatendimento() {
        return unidadeatendimento;
    }

    public void setUnidadeatendimento(Unidade unidadeatendimento) {
        this.unidadeatendimento = unidadeatendimento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getHoraagendada() {
        return horaagendada;
    }

    public void setHoraagendada(String horaagendada) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Calendar c = Calendar.getInstance();
        horaagendada = sdf.format(c.getTime());
        this.horaagendada = horaagendada;
    }
    
    public String horaAtual(String s){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar c = Calendar.getInstance();
        return s = sdf.format(c.getTime());
    }

    public boolean isAtendimentoprioritario() {
        return atendimentoprioritario;
    }

    public void setAtendimentoprioritario(boolean atendimentoprioritario) {
        this.atendimentoprioritario = atendimentoprioritario;
    }

    public String getAtencao() {
        return atencao;
    }

    public void setAtencao(String atencao) {
        this.atencao = atencao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.codigoguiaautorizacao;
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
        final GuiaAutorizacao other = (GuiaAutorizacao) obj;
        if (this.codigoguiaautorizacao != other.codigoguiaautorizacao) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GuiaAutorizacao{" + "codigoguiaautorizacao=" + codigoguiaautorizacao + ", paciente=" + paciente + ", usuario=" + usuario + ", medico=" + medico + ", status=" + status + ", unidadesolicitante=" + unidadesolicitante + ", datasolicitante=" + datasolicitante + ", datasolicitanteDate=" + datasolicitanteDate + ", dataautorizacao=" + dataautorizacao + ", dataautorizacaoDate=" + dataautorizacaoDate + ", dataagendada=" + dataagendada + ", dataagendadaDate=" + dataagendadaDate + ", unidadeatendimento=" + unidadeatendimento + ", observacao=" + observacao + ", horaagendada=" + horaagendada + ", atendimentoprioritario=" + atendimentoprioritario + ", atencao=" + atencao + ", procedimento=" + procedimento + '}';
    }

    public void setarValoresUnidat(Unidade s) {

        this.getUnidadeatendimento().setAtencao(s.getAtencao());
        this.getUnidadeatendimento().setBairro(s.getBairro());
        this.getUnidadeatendimento().setCep(s.getCep());
        this.getUnidadeatendimento().setCidade(s.getCidade());
        this.getUnidadeatendimento().setCodigoUnidade(s.getCodigoUnidade());
        this.getUnidadeatendimento().setComplemento(s.getComplemento());
        this.getUnidadeatendimento().setEstado(s.getEstado());
        this.getUnidadeatendimento().setLogradouro(s.getLogradouro());
        this.getUnidadeatendimento().setNome(s.getNome());
        this.getUnidadeatendimento().setNumero(s.getNumero());
        this.getUnidadeatendimento().setObservacao(s.getObservacao());
//        Usuario u = new ControladorUsuario().recuperarusuario(1);
//        this.setUsuario(u);
    }

    
    
    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Calendar c = Calendar.getInstance();
        System.out.println(sdf.format(c.getTime()));

    }

}
