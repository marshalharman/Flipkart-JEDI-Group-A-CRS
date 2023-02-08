package com.flipkart.controller;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.DuplicateUserException;
import com.flipkart.exception.ProfessorNotAddedException;
import com.flipkart.exception.StudentNotFoundForApprovalException;
import com.flipkart.service.AdminInterface;
import com.flipkart.service.AdminServiceOperation;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminRestAPI {

    AdminInterface service = new AdminServiceOperation();
    @GET
    @Path("/unapprovedStudents")
    public Response getUnapprovedStudents()
    {
        return Response.ok(service.viewUnapprovedStudents()).build();
    }
    @PUT
    @Path("/approveStudentByID")
    public Response approveStudentByID(@QueryParam("studentID") Integer studentID)
    {
        try {
            service.approveStudentRegistration(studentID);
            return Response.status(201).entity( "Approved Student "+studentID+" successfully").build();
        }
        catch (StudentNotFoundForApprovalException e)
        {
            return Response.status(201).entity( e.getMessage()).build();
        }
//        return Response.status(500).entity("Something went wrong, please try again!").build();
    }

    @PUT
    @Path("/approveAll")
    public Response approveAll()
    {
         service.approveAllStudents();
         int s = service.viewUnapprovedStudents().size();
         if(s==0)
         {
             return Response.status(201).entity( "No Students to approve!!").build();
         }
         return Response.status(201).entity( "Approved all students successfully!!!").build();
    }
    @POST
    @Path("/addAdmin")
    public Response addAdmin(@Valid Admin a)
    {
        try {
            service.addAdmin(a);
            return Response.status(201).entity( "Added admin successfully!!!").build();
        }
        catch (DuplicateUserException e)
        {
            return Response.status(201).entity( "Already Admin exists. Cannot add duplicate entry!!").build();
        }
    }
    @DELETE
    @Path("/addProfessor")
    public Response addProfessor(@Valid Professor p)
    {
        try{
            service.addProfessor(p);
            return Response.status(201).entity( "Added Professor successfully!!!").build();
        }
        catch (DuplicateUserException e)
        {
            return Response.status(201).entity( "Already Professor exists. Cannot add duplicate entry!!").build();
        }
        catch (ProfessorNotAddedException e)
        {
            return Response.status(201).entity( "Professor Not Added successfully!!!").build();
        }
    }

}
