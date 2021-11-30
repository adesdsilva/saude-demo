/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package repositorios;

import BancoDadosDao.DaoManagerHiber;
import java.util.List;
import negocio.GuiaAtendimento;

/**
 *
 * @author José
 */
public class RepositorioGuiaAtendimento implements interfaces.InterfaceRepositorioDB<GuiaAtendimento,Integer>{

    @Override
    public void inserir(GuiaAtendimento t) {
        DaoManagerHiber.persist(t);
    }

    @Override
    public void editar(GuiaAtendimento t) {
        DaoManagerHiber.update(t);
    }

    @Override
    public GuiaAtendimento recuperar(Integer g) {
        return (GuiaAtendimento) DaoManagerHiber.recover("From GuiaAtendimento where codigoguiaatendimento="+g).get(0);
    }

    @Override
    public void deletar(GuiaAtendimento t) {
        DaoManagerHiber.delete(t);
    }

    @Override
    public List<GuiaAtendimento> recuperarTodos() {
        return DaoManagerHiber.recover("From GuiaAtendimento");
    }
    
}
