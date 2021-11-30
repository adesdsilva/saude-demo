/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

import java.util.List;

/**
 *
 * @author Adelino
 */
public interface InterfaceRepositorioDB <T, G>{
   public void inserir (T t);
   public void editar (T t);
   public T recuperar (G g);
   public void deletar (T t);
   public List<T> recuperarTodos ();
}
