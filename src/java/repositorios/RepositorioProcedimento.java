/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package repositorios;

import BancoDadosDao.DaoManagerHiber;
import interfaces.InterfaceRepositorioDB;
import java.util.List;
import negocio.Procedimento;


public class RepositorioProcedimento implements InterfaceRepositorioDB<Procedimento, Integer>{
    
    @Override
    public void inserir(Procedimento t){
        DaoManagerHiber.persist(t);
    }
    
    @Override
    public void editar(Procedimento t){
    DaoManagerHiber.update(t);
}
    @Override
    public Procedimento recuperar(Integer g){
        return (Procedimento) DaoManagerHiber.recover("From Procedimento where codigoprocedimento="+g).get(0);
    }
    
    @Override
    public List<Procedimento> recuperarTodos(){
        return DaoManagerHiber.recover("From Procedimento");
    }
    
    @Override
    public void deletar(Procedimento t){
        DaoManagerHiber.delete(t);
    }
}
