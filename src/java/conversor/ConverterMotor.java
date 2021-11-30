/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversor;

import controladores.ControladorMotorista;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import negocio.Motorista;

/**
 *
 * @author Adelino
 */
@FacesConverter(value = "motorConverter", forClass = Motorista.class)
public class ConverterMotor implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.isEmpty()) {
            return new ControladorMotorista().recuperarmotor(Integer.parseInt(value));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object != null && object instanceof Motorista) {
            return ((Motorista) object).getCodigoMotoristaString();
        }
        return null;
    }

}
