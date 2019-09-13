package com.codershift.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Anghel Leonard
 */
@Named
@SessionScoped
public class FeedbackBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(FeedbackBean.class.getName());
    private List<Message>lastSave = new ArrayList<>();

    
    
    public FeedbackBean() {
        // NOOP
   
    }

    public List<Message> getLastSave() {
        return lastSave;
    }

    public void setLastSave(List<Message> lastSave) {
        this.lastSave = lastSave;
    }

    public void send() {
        
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
        String user = params.get("user");
        String feedback = params.get("feedback"); 
         String msg  = "Saved by: "+user+" "+new Date(System.currentTimeMillis()).toString();
       lastSave.add(new Message(msg));
       
        
        logger.log(Level.INFO, "Feedback saved at {0} !", lastSave);
        logger.log(Level.INFO, "Saved by: {0} \n Content: {1}", new String[]{
            user, feedback
        });
    }

}
