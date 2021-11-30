/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package repositorios;

import BancoDadosDao.DaoManagerHiber;
import interfaces.InterfaceRepositorioDB;
import java.util.List;
import negocio.Medico;

/**
 *
 * @author Adelino
 */
public class RepositorioMedico implements InterfaceRepositorioDB<Medico, Integer>{

    @Override
    public void inserir(Medico t) {
        DaoManagerHiber.persist(t);
    }

    @Override
    public void editar(Medico t) {
        DaoManagerHiber.update(t);
    }

    @Override
    public Medico recuperar(Integer g) {
        return (Medico) DaoManagerHiber.recover("From Medico where codigoMedico="+g).get(0);
    }

    @Override
    public void deletar(Medico t) {
        DaoManagerHiber.delete(t);
    }

    @Override
    public List<Medico> recuperarTodos() {
        return DaoManagerHiber.recover("From Medico");
    }
    
}
