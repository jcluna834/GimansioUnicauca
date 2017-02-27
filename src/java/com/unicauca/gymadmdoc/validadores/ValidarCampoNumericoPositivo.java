
package com.unicauca.gymadmdoc.validadores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator(value="ValidarCampoNumericoPositivo")
public class ValidarCampoNumericoPositivo implements Validator
{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {
        String texto = String.valueOf(value);
        
        try
        {
            float campo= Float.parseFloat(texto);
            if(campo<0){
                FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Solo números positivos.","Solo números positivos.");
                throw new ValidatorException(msg);
            }
        }catch(Exception e)
        {
           FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Solo números positivos.","Solo números positivos.");
           throw new ValidatorException(msg);
        }
        
        

    }
    
    
    
    
    
}
