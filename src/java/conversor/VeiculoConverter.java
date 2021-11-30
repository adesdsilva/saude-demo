/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package conversores;


import controladores.ControladorMotorista;
import controladores.ControladorVeiculo;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import negocio.Motorista;
import negocio.Veiculo;

@FacesConverter(value="veiculoConverter")
public class VeiculoConverter implements Converter{

    @Override
    public Veiculo getAsObject(FacesContext fc, UIComponent uic, String key) {
        
        FacesContext context = FacesContext.getCurrentInstance();
        ControladorVeiculo ch = (ControladorVeiculo) context.getELContext().getELResolver().getValue(context.getELContext(), null, "controleVeiculo");
 
        return ch.recuperarveic(Integer.parseInt((key)));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
       return o.toString();
    }
    
}
