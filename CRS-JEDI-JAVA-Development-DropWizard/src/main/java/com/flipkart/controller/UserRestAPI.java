package com.flipkart.controller;


import com.flipkart.bean.Student;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentServiceOperation;
import com.flipkart.service.UserInterface;
import com.flipkart.service.UserServiceOperation;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
public class UserRestAPI {

    UserInterface userInterface = new UserServiceOperation();

    /**
     *
     * @param userId
     * @param password
     * @param role
     * @return
     */
    @POST
    @Path("/login")
    public Response verifyCredentials(int userId, String password, String role) throws ValidationException, UserNotFoundException {
        boolean verified = userInterface.verifyCredentials(userId, password, role);
        if(verified)
        {
            return Response.status(200).entity("Login successful").build();
        }
        else
        {
            return Response.status(500).entity("Invalid credentials!").build();
        }
    }

    /**
     *
     * @param student
     * @return
     */
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

    /**
     *
     * @param userID: email address of the user
     * @param newPassword: new password to be stored in db.
     * @return @return 201, if password is updated, else 500 in case of error
     */
    @PUT
    @Path("/updatePassword")
    public Response updatePassword(int userID, String newPassword) throws ValidationException {
        try {
            userInterface.updatePassword(userID, newPassword);
        }
        catch (Exception ex){
            return Response.status(500).entity("Something went wrong, please try again!").build();
        }

        return Response.status(201).entity("Password updated successfully! ").build();
    }

}