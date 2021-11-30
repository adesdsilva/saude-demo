/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package repositorios;

import BancoDadosDao.DaoManagerHiber;
import interfaces.InterfaceRepositorioDB;
import java.util.List;
import negocio.Unidade;

/**
 *
 * @author Adelino
 */
public class RepositorioUnidade implements InterfaceRepositorioDB<Unidade, Integer>{

    @Override
    public void inserir(Unidade t) {
        DaoManagerHiber.persist(t);
    }

    @Override
    public void editar(Unidade t) {
        DaoManagerHiber.update(t);
    }

    @Override
    public Unidade recuperar(Integer g) {
        return (Unidade) DaoManagerHiber.recover("From Unidade where codigoUnidade="+g).get(0);
    }

    @Override
    public void deletar(Unidade t) {
        DaoManagerHiber.delete(t);
    }

    @Override
    public List<Unidade> recuperarTodos() {
        return DaoManagerHiber.recover("From Unidade");
    }
    
}
