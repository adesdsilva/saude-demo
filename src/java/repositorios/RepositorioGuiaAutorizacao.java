/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package repositorios;

import BancoDadosDao.DaoManagerHiber;
import java.util.List;
import negocio.GuiaAutorizacao;

/**
 *
 * @author José
 */
public class RepositorioGuiaAutorizacao implements interfaces.InterfaceRepositorioDB<GuiaAutorizacao,Integer>{

    @Override
    public void inserir(GuiaAutorizacao t) {
        DaoManagerHiber.persist(t);
    }

    @Override
    public void editar(GuiaAutorizacao t) {
        DaoManagerHiber.update(t);
    }

    @Override
    public GuiaAutorizacao recuperar(Integer g) {
        return (GuiaAutorizacao) DaoManagerHiber.recover("From GuiaAutorizacao where codigoguiaautorizacao="+g).get(0);
    }

    @Override
    public void deletar(GuiaAutorizacao t) {
        DaoManagerHiber.delete(t);
    }

    @Override
    public List<GuiaAutorizacao> recuperarTodos() {
        return DaoManagerHiber.recover("From GuiaAutorizacao");
    }
    
}

