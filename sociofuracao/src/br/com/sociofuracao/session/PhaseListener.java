/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sociofuracao.session;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.servlet.http.HttpSession;

import br.com.sociofuracao.beans.LoginController;

/**
 *
 * @author alex
 */
public class PhaseListener implements javax.faces.event.PhaseListener {
    /** */
    private static final long serialVersionUID = 1L;
    private final static String SESSION_BEAN = LoginController.BEAN_NAME;
    
    
   
    @Override
    public void afterPhase(PhaseEvent pe) {
        
            //check have correct access
            FacesContext context = pe.getFacesContext();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
            LoginController sessionBean = (LoginController) session.getAttribute(SESSION_BEAN);
            String currentPage = context.getViewRoot().getViewId();
            
            //redirect page.
            //NavigationHandler nh = context.getApplication().getNavigationHandler();;
            //nh.handleNavigation(context, null, "loginPage");
            if(currentPage.contains("member")){
                   if(sessionBean != null && sessionBean.getUsuario() != null && sessionBean.getUsuario().getEmail() != null){
                   
                    }else{
                    	try {
							context.getExternalContext().redirect("/sociofuracao/index.xhtml");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							 throw new UnsupportedOperationException(e.getMessage()); 
						}
                    	
                    }
            }
            
    }

    @Override
    public void beforePhase(PhaseEvent pe) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PhaseId getPhaseId() {
       return PhaseId.RESTORE_VIEW;
    }

    
    
}

    
