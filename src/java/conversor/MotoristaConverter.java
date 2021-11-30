/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package conversores;


import controladores.ControladorMotorista;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import negocio.Motorista;

@FacesConverter(value="motoristaConverter")
public class MotoristaConverter implements Converter{

    @Override
    public Motorista getAsObject(FacesContext fc, UIComponent uic, String key) {
        
        FacesContext context = FacesContext.getCurrentInstance();
        ControladorMotorista ch = (ControladorMotorista) context.getELContext().getELResolver().getValue(context.getELContext(), null, "controleMotorista");
 
        return ch.recuperarmotor(Integer.parseInt((key)));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
       return o.toString();
    }
    
}
