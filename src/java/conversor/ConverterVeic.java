/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package conversor;

import controladores.ControladorVeiculo;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import negocio.Veiculo;

/**
 *
 * @author Adelino
 */
@FacesConverter(value = "veicConverter", forClass = Veiculo.class)
public class ConverterVeic implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.isEmpty()) {
            return new ControladorVeiculo().recuperarveic(Integer.parseInt(value));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object != null && object instanceof Veiculo) {
            return ((Veiculo) object).getCodigoVeiculoString();
        }
        return null;
    }
    
}
