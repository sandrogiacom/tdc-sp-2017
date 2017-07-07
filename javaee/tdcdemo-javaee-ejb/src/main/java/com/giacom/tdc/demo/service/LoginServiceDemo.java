package com.giacom.tdc.demo.service;

import java.security.Principal;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Session Bean implementation class LoginServiceDemo
 */
@Stateless(mappedName = "loginRemote")
@LocalBean
@DeclareRoles({"ADMIN", "TutorialUser"})
public class LoginServiceDemo implements LoginServiceDemoRemote, LoginServiceDemoLocal {

	
    @Inject
    private Logger log;
    
    @Inject 
    private Principal principal; // get logged-in principal
	
    @Resource 
    SessionContext ctx;
    
    /**
     * Default constructor. 
     */
    public LoginServiceDemo() {
        // TODO Auto-generated constructor stub
    }
    
    @RolesAllowed("TutorialUser")
    public String getUser(){
    	
    	
    	System.out.println(ctx.isCallerInRole("user"));
    	System.out.println(ctx.isCallerInRole("TutorialUser"));
    	
    	System.out.println("getUser EJB: " + principal.getName());
    	
    	return principal.getName();
    }

    
    
}
