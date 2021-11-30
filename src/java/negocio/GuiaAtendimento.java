/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio;

import controladores.ControladorUsuario;
import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import org.primefaces.event.SelectEvent;

@Table
@Entity
@ViewScoped
@ManagedBean(name="guiaatendimento")
public class GuiaAtendimento implements Serializable{
    @Id
    @GeneratedValue
    private int codigoguiaatendimento;
    
    @OneToOne
    private Paciente paciente = new Paciente();
    
    @OneToOne
    private Usuario usuario = new Usuario();
    
    @OneToOne
    private Medico medico = new Medico();
    
    @Type(type = "true_false")
    private boolean status;
    
    @OneToOne
    private Unidade unidadesolicitante = new Unidade();
    
    @Temporal(TemporalType.DATE)
    private Date datasolicitante = new Date();
    
    @Column(length = 10)
    private String datasolicitanteDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    
    @Temporal(TemporalType.DATE)
    private Date dataautorizacao;
    
    @Column(length = 10)
    private String dataautorizacaoDate;
    
    @Temporal(TemporalType.DATE)
    private Date dataagendada;
    
    @Column(length = 10)
    private String dataagendadaDate;
    
    @OneToOne
    private Unidade unidadeatendimento = new Unidade();
            
    private String observacao;
    
    @Column(length = 10)
    private String horaagendada;
    
    @Type(type = "true_false")
    private boolean atendimentoprioritario;
    
    private String atencao;
    
    @OneToOne
    private Procedimento procedimento = new Procedimento();

    
    public GuiaAtendimento() {
    }

    public GuiaAtendimento(int codigoguiaatendimento, Usuario usuario, Medico medico, boolean status, Unidade unidadesolicitante, Date datasolicitante, String datasolicitanteDate, Date dataautorizacao, String dataautorizacaoDate, Date dataagendada, String dataagendadaDate, Unidade unidadeatendimento, String observacao, String horaagendada, boolean atendimentoprioritario, String atencao, Procedimento procedimento) {
        this.codigoguiaatendimento = codigoguiaatendimento;
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

    
    public int getCodigoguiaatendimento() {
        return codigoguiaatendimento;
    }

    public void setCodigoguiaatendimento(int codigoguiaautorizacao) {
        this.codigoguiaatendimento = codigoguiaautorizacao;
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

    public Procedimento getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(Procedimento procedimento) {
        this.procedimento = procedimento;
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
        this.horaagendada = horaagendada;
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
        int hash = 7;
        hash = 11 * hash + this.codigoguiaatendimento;
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
        final GuiaAtendimento other = (GuiaAtendimento) obj;
        if (this.codigoguiaatendimento != other.codigoguiaatendimento) {
            return false;
        }
        return true;
    }

   
    public void pacienteSelecionado(SelectEvent event) {
        Paciente pac = (Paciente) event.getObject();
        this.setPaciente(pac);
    }
    
    public void setNomePaciente(String nome){
        
    }
    
    public void setarValoresPaciente(Paciente p){
//        ControladorPaciente cp = new ControladorPaciente();
//        p = cp.getSelectedPaciente();
        this.getPaciente().setCodigopaciente(p.getCodigopaciente());
        this.getPaciente().setNome(p.getNome());
        this.getPaciente().setCpf(p.getCpf());
        this.getPaciente().setRg(p.getRg());
        this.getPaciente().setTitulozona(p.getTitulozona());
        this.getPaciente().setCartaosus(p.getCartaosus());
        this.getPaciente().setCep(p.getCep());
        this.getPaciente().setLogradouro(p.getLogradouro());
        this.getPaciente().setComplemento(p.getComplemento());
        this.getPaciente().setBairro(p.getBairro());
        this.getPaciente().setNumero(p.getNumero());
        this.getPaciente().setCidade(p.getCidade());
        this.getPaciente().setAgentesaude(p.getAgentesaude());
        this.getPaciente().setDataNascimento(p.getDataNascimento());
        //JOptionPane.showMessageDialog(null, this.getPaciente().getNome());
        
    }
    
    public void setarValoresMedico(Medico m){
       
       this.getMedico().setCodigoMedico(m.getCodigoMedico());
       this.getMedico().setNome(m.getNome());
       this.getMedico().setCrm(m.getCrm());
       this.getMedico().setEspecialidade(m.getEspecialidade());
       
    }
    public void setarValoresProc(Procedimento p){
        this.getProcedimento().setCodigoprocedimento(p.getCodigoprocedimento());
        this.getProcedimento().setNome(p.getNome());
        
    }
    
    public void setarValoresUnsol(Unidade s){
        this.getUnidadesolicitante().setAtencao(s.getAtencao());
        this.getUnidadesolicitante().setBairro(s.getBairro());
        this.getUnidadesolicitante().setCep(s.getCep());
        this.getUnidadesolicitante().setCidade(s.getCidade());
        this.getUnidadesolicitante().setCodigoUnidade(s.getCodigoUnidade());
        this.getUnidadesolicitante().setComplemento(s.getComplemento());
        this.getUnidadesolicitante().setEstado(s.getEstado());
        this.getUnidadesolicitante().setLogradouro(s.getLogradouro());
        this.getUnidadesolicitante().setNome(s.getNome());
        this.getUnidadesolicitante().setNumero(s.getNumero());
        this.getUnidadesolicitante().setObservacao(s.getObservacao());
    }
    
    public void setarValoresUnidat(Unidade s){
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
        
    }

    @Override
    public String toString() {
        return "GuiaAtendimento{" + "codigoguiaatendimento=" + codigoguiaatendimento + ", paciente=" + paciente + ", usuario=" + usuario + ", medico=" + medico + ", status=" + status + ", unidadesolicitante=" + unidadesolicitante + ", datasolicitante=" + datasolicitante + ", datasolicitanteDate=" + datasolicitanteDate + ", dataautorizacao=" + dataautorizacao + ", dataautorizacaoDate=" + dataautorizacaoDate + ", dataagendada=" + dataagendada + ", dataagendadaDate=" + dataagendadaDate + ", unidadeatendimento=" + unidadeatendimento + ", observacao=" + observacao + ", horaagendada=" + horaagendada + ", atendimentoprioritario=" + atendimentoprioritario + ", atencao=" + atencao + ", procedimento=" + procedimento + '}';
    }

    
    
   
    
}
