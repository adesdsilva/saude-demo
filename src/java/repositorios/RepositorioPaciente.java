/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package repositorios;

import BancoDadosDao.DaoManagerHiber;
import interfaces.InterfaceRepositorioDB;
import java.util.List;
import negocio.Paciente;


public class RepositorioPaciente implements InterfaceRepositorioDB<Paciente, Integer>{

    @Override
    public void inserir(Paciente t) {
        DaoManagerHiber.persist(t);
    }

    @Override
    public void editar(Paciente t) {
        DaoManagerHiber.update(t);
    }

    @Override
    public Paciente recuperar(Integer g) {
        return (Paciente) DaoManagerHiber.recover("From Paciente where codigoPaciente="+g).get(0);
    }

    @Override
    public void deletar(Paciente t) {
        DaoManagerHiber.delete(t);
    }

    @Override
    public List<Paciente> recuperarTodos() {
        return DaoManagerHiber.recover("From Paciente");
    }
    
}
