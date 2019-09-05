/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codershift.web;

import com.mycompany.messagebirdjava.SendMessage;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ISSAH OJIVO
 */
@Named(value = "quickSend")
@RequestScoped
public class QuickSend {

    private String originator, recipient, body;

    /**
     * Creates a new instance of QuickSend
     */
    public QuickSend() {
    }

    /**
     * @return the originator
     */
    public String getOriginator() {
        return originator;
    }

    /**
     * @param originator the originator to set
     */
    public void setOriginator(String originator) {
        this.originator = originator;
    }

    /**
     * @return the recipient
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * @param recipient the recipient to set
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }

    public void sendMessage() {
        SendMessage bird = new SendMessage();
        String msg = bird.send(recipient, originator, body);
        if (msg.contains("fail:")) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("failed", msg);
        } else {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("success", msg);
        }

    }

}
