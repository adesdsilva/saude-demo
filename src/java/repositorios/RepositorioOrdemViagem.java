/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package repositorios;

import BancoDadosDao.DaoManagerHiber;
import java.util.List;
import negocio.OrdemViagem;

/**
 *
 * @author José
 */
public class RepositorioOrdemViagem implements interfaces.InterfaceRepositorioDB<OrdemViagem,Integer>{

    @Override
    public void inserir(OrdemViagem t) {
        DaoManagerHiber.persist(t);
    }

    @Override
    public void editar(OrdemViagem t) {
        DaoManagerHiber.update(t);
    }

    @Override
    public OrdemViagem recuperar(Integer g) {
        return (OrdemViagem) DaoManagerHiber.recover("From OrdemViagem where codigoordemviagem="+g).get(0);
    }

    @Override
    public void deletar(OrdemViagem t) {
        DaoManagerHiber.delete(t);
    }

    @Override
    public List<OrdemViagem> recuperarTodos() {
        return DaoManagerHiber.recover("From OrdemViagem");
    }
    
}
