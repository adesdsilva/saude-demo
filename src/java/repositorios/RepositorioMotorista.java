/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package repositorios;

import BancoDadosDao.DaoManagerHiber;
import interfaces.InterfaceRepositorioDB;
import java.util.List;
import negocio.Motorista;

/**
 *
 * @author Adelino
 */
public class RepositorioMotorista implements InterfaceRepositorioDB<Motorista, Integer>{

    @Override
    public void inserir(Motorista t) {
        DaoManagerHiber.persist(t);
    }

    @Override
    public void editar(Motorista t) {
        DaoManagerHiber.update(t);
    }

    @Override
    public Motorista recuperar(Integer g) {
        return (Motorista) DaoManagerHiber.recover("From Motorista where codigoMotorista="+g).get(0);
    }

    @Override
    public void deletar(Motorista t) {
        DaoManagerHiber.delete(t);
    }

    @Override
    public List<Motorista> recuperarTodos() {
        return DaoManagerHiber.recover("From Motorista");
    }
    
}
