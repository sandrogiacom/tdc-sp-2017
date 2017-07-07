/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.giacom.tdc.demo.rest;

import java.security.Principal;
import java.util.logging.Logger;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import com.giacom.tdc.demo.service.LoginServiceDemoLocal;

/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the members table.
 */
@Path("/user")
@RequestScoped
@DeclareRoles({"ADMIN", "TutorialUser"})
@Provider
public class LoginDemoRESTService {
    @Inject
    private Logger log;
    
    @Inject 
    private Principal principal; // get logged-in principal
    
    @Inject
    private LoginServiceDemoLocal service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("TutorialUser")
    public String getUser() {
    	log.fine("getUser...");
    	
    	System.out.println(principal.getName());
    	
        return service.getUser();
    }
    
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Member> listAllMembers() {
//        return repository.findAllOrderedByName();
//    }
//
//    @GET
//    @Path("/{id:[0-9][0-9]*}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Member lookupMemberById(@PathParam("id") long id) {
//        Member member = repository.findById(id);
//        if (member == null) {
//            throw new WebApplicationException(Response.Status.NOT_FOUND);
//        }
//        return member;
//    }
//
//    /**
//     * Creates a new member from the values provided. Performs validation, and will return a JAX-RS response with either 200 ok,
//     * or with a map of fields, and related errors.
//     */
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response createMember(Member member) {
//
//        Response.ResponseBuilder builder = null;
//
//        try {
//            // Validates member using bean validation
//            validateMember(member);
//
//            registration.register(member);
//
//            // Create an "ok" response
//            builder = Response.ok();
//        } catch (ConstraintViolationException ce) {
//            // Handle bean validation issues
//            builder = createViolationResponse(ce.getConstraintViolations());
//        } catch (ValidationException e) {
//            // Handle the unique constrain violation
//            Map<String, String> responseObj = new HashMap<>();
//            responseObj.put("email", "Email taken");
//            builder = Response.status(Response.Status.CONFLICT).entity(responseObj);
//        } catch (Exception e) {
//            // Handle generic exceptions
//            Map<String, String> responseObj = new HashMap<>();
//            responseObj.put("error", e.getMessage());
//            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
//        }
//
//        return builder.build();
//    }
//
// 

  

   
}
