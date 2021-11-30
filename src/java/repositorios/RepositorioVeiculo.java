/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package repositorios;

import BancoDadosDao.DaoManagerHiber;
import interfaces.InterfaceRepositorioDB;
import java.util.List;
import negocio.Veiculo;

/**
 *
 * @author Adelino
 */
public class RepositorioVeiculo implements InterfaceRepositorioDB<Veiculo, Integer>{

    @Override
    public void inserir(Veiculo t) {
        DaoManagerHiber.persist(t);
    }

    @Override
    public void editar(Veiculo t) {
        DaoManagerHiber.update(t);
    }

    @Override
    public Veiculo recuperar(Integer g) {
        return (Veiculo) DaoManagerHiber.recover("From Veiculo where codigoVeiculo="+g).get(0);
    }

    @Override
    public void deletar(Veiculo t) {
        DaoManagerHiber.delete(t);
    }

    @Override
    public List<Veiculo> recuperarTodos() {
        return DaoManagerHiber.recover("From Veiculo");
    }
    
}
