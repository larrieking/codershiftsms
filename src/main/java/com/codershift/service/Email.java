/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codershift.service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ISSAH OJIVO
 */
@Stateless
public class Email {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
     @Resource(lookup = "MyMail")
    private Session mailSession;
     
     
     
    
    public void sendMail( List<String> to, String subject, String content, String from) {
        MimeMessage msg = new MimeMessage(mailSession);
String[]toAddress = to.toArray(new String[to.size()]);
        try {
           msg.setFrom(new InternetAddress(mailSession.getProperty("mail.from")));
            InternetAddress[] address = new InternetAddress[toAddress.length];
            int counter = 0;
            for(String add : toAddress){
                address[counter] = new InternetAddress(add.trim());
                counter++;
            }
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());

           // String add = "<a href = \"www.codershift.com/faces/newPassword.xhtml?id=" + token + "\">" + "click here to reset your password</a>";
           // msg.setContent("<h3>Hello,</h3><p>pls click on the link below to reset your password</p><br />" + add + "<br /><p>Pls note that the link above expires after 15 minutes</p><p>Warm Regards, </p><br />Codershift Team", "text/html");
           //msg.setContent(content, "text/html");
           msg.setText(content);
           Transport.send(msg);
            System.out.println("success");

        } catch (AddressException ex) {
           // Logger.getLogger(EmailSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
           // Logger.getLogger(EmailSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    /**
     * @return the accountFacade
     */
   

}
