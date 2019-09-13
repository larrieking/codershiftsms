/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codershift.util;

import com.messagebird.MessageBirdClient;
import com.messagebird.MessageBirdService;
import com.messagebird.MessageBirdServiceImpl;
import com.messagebird.objects.MessageResponse;
import com.messagebird.exceptions.GeneralException;
import com.messagebird.exceptions.UnauthorizedException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ISSAH OJIVO
 */
public class SendMessage {

    public static final String API_KEY = "SebVITg1Ga5vervqaisatlINz";
    MessageResponse response;

    public String send(String recipients, String originator, String body) {
        String responseCode = "";
        int recipientsCount = 0;

        // First create your service object
        final MessageBirdService wsr = new MessageBirdServiceImpl(API_KEY);

        // Add the service to the client
        final MessageBirdClient messageBirdClient = new MessageBirdClient(wsr);

        try {
            // Get Hlr using msgId and msisdn
            System.out.println("Sending message:");
            List<BigInteger> phones = new ArrayList<>();
            for (final String phoneNumber : recipients.trim().split(",")) {
                phones.add(new BigInteger(phoneNumber));
            }
            while (phones.size() > 50) {

                response = messageBirdClient.sendMessage(originator, body, splitTo50(phones));
                phones.subList(0, 50).clear();
                recipientsCount += response.getRecipients().getTotalSentCount();
                System.out.println("greatter than 50: " + response.getRecipients().getTotalSentCount());
            }
            if (phones.size() <= 50 && phones.isEmpty() == false) {
                response = messageBirdClient.sendMessage(originator, body, phones);
                //Display message response
                recipientsCount += response.getRecipients().getTotalSentCount();

        
                
                
                System.out.println("Less than 50: " + response.getRecipients().getTotalSentCount());
            }
            return "Message sent to "+String.valueOf(recipientsCount);
        } catch (UnauthorizedException | GeneralException exception) {
            if (exception.getErrors() != null) {
                System.out.println(exception.getErrors().toString());
            }
            return "fail: "+exception.getMessage();
        }
        
        catch(Exception e){
            return "fail: "+ e.getMessage();
        }
        
        
        
        
    }

    public List<BigInteger> splitTo50(List<BigInteger> numbers) {
        List<BigInteger> first50Numbers = new ArrayList<>();
        //List<BigInteger> first50Numbers = numbers.stream().limit(50).collect(Collectors.toList());
        first50Numbers = numbers.subList(0, 50);
        return first50Numbers;
    }
}
