/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package repositorios;

import BancoDadosDao.DaoManagerHiber;
import controladores.ControladorUsuario;
import interfaces.InterfaceRepositorioDB;
import java.util.List;
import javax.swing.JOptionPane;
import negocio.Usuario;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Adelino
 */
public class RepositorioUsuario implements InterfaceRepositorioDB<Usuario, Integer>{

    @Override
    public void inserir(Usuario t) {
        DaoManagerHiber.persist(t);
    }

    @Override
    public void editar(Usuario t) {
        DaoManagerHiber.update(t);
    }

    @Override
    public Usuario recuperar(Integer g) {
        return (Usuario) DaoManagerHiber.recover("From Usuario where codigoUsuario="+g).get(0);
    }

    @Override
    public void deletar(Usuario t) {
        DaoManagerHiber.delete(t);
    }

    @Override
    public List<Usuario> recuperarTodos() {
        return DaoManagerHiber.recover("From Usuario");
    }

    public Usuario recuperarPorLogin(String login, String senha) {
        List<Usuario> list = DaoManagerHiber.recover("from Usuario where login='"+login+"' and senha='"+senha+"'");
        if(list.isEmpty()){
            return null;
        }else{
            return (Usuario) list.get(0);
        }
    }
    
    public static void main(String[] args) {
        RepositorioUsuario usuario = new RepositorioUsuario();
        
        JOptionPane.showMessageDialog(null, usuario.recuperarPorLogin("joseaugusto", DigestUtils.md5Hex("1028")));
    }
}
