/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ementas.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

/**
 *
 * @author alex
 */
public class FacesUtil {
    
    public static void addMessageErro(String summary) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
    
    public static Flash flashScope (){
    	return (FacesContext.getCurrentInstance().getExternalContext().getFlash());
    }
    
}
