package com.flipkart.controller;


import com.flipkart.bean.Student;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentServiceOperation;
import com.flipkart.service.UserInterface;
import com.flipkart.service.UserServiceOperation;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/user")
public class UserRestAPI {

    private final Validator validator;

    public UserRestAPI(Validator validator) {
        this.validator = validator;
    }

    UserInterface userInterface = new UserServiceOperation();


    @POST
    @Path("/login")
    public Response verifyCredentials(@QueryParam("userID") Integer userID,@QueryParam("password") String password, @QueryParam("role") String role) throws ValidationException, UserNotFoundException {
        boolean verified = userInterface.verifyCredentials(userID, password, role);
        if(verified)
        {
            return Response.status(200).entity("Login successful").build();
        }
        else
        {
            return Response.status(500).entity("Invalid credentials!").build();
        }
    }


    @POST
    @Path("/studentRegistration")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerStudent(@Valid Student student) {

        StudentInterface studentInterface = new StudentServiceOperation();
        try
        {
            studentInterface.register(student.getUserID(), student.getName(), student.getAddress(), student.getUsername(), student.getPassword(), student.getBranch(), student.getDegree());
        }
        catch(Exception ex)
        {
            return Response.status(500).entity("Something went wrong! Please try again.").build();
        }

        return Response.status(201).entity("Registration Successful for "+student.getUserID()).build();
    }

    @POST
    @Path("/updatePassword")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePassword(@QueryParam("userID") Integer userID, @QueryParam("newPassword") String newPassword) throws ValidationException {
        try {
            userInterface.updatePassword(userID, newPassword);
        }
        catch (Exception ex){
            return Response.status(500).entity("Something went wrong, please try again!").build();
        }

        return Response.status(201).entity("Password updated successfully! ").build();
    }
}