/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codershift.web;

import com.codershift.service.Email;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;


/**
 *
 * @author ISSAH OJIVO
 */
@Named(value = "index")
@SessionScoped
public class Index implements Serializable {
private String name, email, subject, body;
@EJB
private  Email mailSession;
    /**
     * Creates a new instance of Index
     */
    public Index() {
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
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

    /**
     * @return the mailSession
     */
    public Email getMailSession() {
        return mailSession;
    }
    
    
    public String sendEmail(){
        try{
        List<String>to = new ArrayList<>();
                to.add("larrie4christ@gmail.com");
              setBody(getBody()+" \nSender Email: "+getEmail()+"\nSender Name"+getName()) ;         
        getMailSession().sendMail(to, getSubject(), getBody(), getEmail());
        System.out.println("Success");
         //FacesContext.getCurrentInstance().getExternalContext().getFlash().put("failure", "Internal Error!");
          FacesContext.getCurrentInstance().getExternalContext().getFlash().put("success", "Mail Sent Successfully!");
        }catch(Exception e){

            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("failure", "Internal Error!");
        }
        setBody("");
        return null;
   // return "index.xhtml?faces-redirect=true";
    }



    
}