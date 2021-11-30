/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio;

/**
 *
 * @author Adelino
 */
public class BuscaLogradouro {
    
public static void main(String[] args) {
        new BuscaLogradouro();
    }
    
    public CepWebService BuscaLogradouro(String cep) {
        try {            

            CepWebService cepWebService = new CepWebService(cep);
                
            /*if (cepWebService.getResultado()==1) {
                System.out.println(cepWebService.getTipo_logradouro() + " " + cepWebService.getLogradouro());
                System.out.println(cepWebService.getCidade());
                System.out.println(cepWebService.getBairro());
                //System.out.println(cepWebService.getResultado());
                System.out.println(cepWebService.getResultado_txt());
            }
            else {
                System.out.println("Servidor nÃ£o estÃ¡ respondendo.");
            } */
            
            return  cepWebService;
            
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }  
        return null;
    }
}

