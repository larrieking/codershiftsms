/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codershift.web;

import com.codershift.entity.Account;
import com.codershift.entity.Role;
import com.codershift.service.AccountFacade;
import com.codershift.service.RoleFacade;
import com.codershift.util.GenerateSHA56;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author golan
 */
@Named(value = "userController")
@SessionScoped
public class UserController implements Serializable {

    /**
     * Creates a new instance of UserController
     */
    private Account account;
    @EJB
    private AccountFacade accountFacade;
    @EJB
    private RoleFacade roleFacade;

    public UserController() {
    }

    @PostConstruct
    public void init() {
        account = new Account();

    }

    public void create_user() {

        Role tableRole = getRoleFacade().find("user");

        // role.setRoleName("user");
        //  Account account = new Account();
        try {
            getAccount().setPassword(new GenerateSHA56().generateSha256(getAccount().getPassword()));
            getAccount().setRole(tableRole);
            // account = new Account(getUsername(),new GenerateSHA56().generateSha256(getPassword()), getFirstname(), getLastname(), tableRole);

        } catch (NoSuchAlgorithmException ex) {

            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);

        }

        //account.getRole().add(role);
        //role.getAccount().add(account);
        getAccountFacade().create(getAccount());

        //getRoleFacade().create(role);
        // System.out.println(getUsername() + getPassword()+getFirstname()+getLastname());
        System.out.print("Account Creation Successful!");
    }

    /**
     * @return the accountFacade
     */
    public AccountFacade getAccountFacade() {
        return accountFacade;
    }

    /**
     * @return the roleFacade
     */
    public RoleFacade getRoleFacade() {
        return roleFacade;
    }

    /**
     * @return the account
     */
    public Account getAccount() {
        return account;
    }
}
