package com.flipkart.controller;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.*;
import com.flipkart.service.AdminInterface;
import com.flipkart.service.AdminServiceOperation;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.*;

@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminRestAPI {

    private final Validator validator;

    public AdminRestAPI(Validator validator) {
        this.validator = validator;
    }

    AdminInterface service = new AdminServiceOperation();
    @GET
    @Path("/unapprovedStudents")
    public Response getUnapprovedStudents()
    {
        return Response.ok(service.viewUnapprovedStudents()).build();
    }
    @PUT
    @Path("/approveStudentByID")
    public Response approveStudentByID(@QueryParam("studentID") Integer studentID) {
        try {

            service.approveStudentRegistration(studentID);
            return Response.ok("Approved Student " + studentID + " successfully").build();

        } catch (StudentNotFoundForApprovalException e) {
            return Response.status(400).entity(e.getMessage()).build();
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
    @POST
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
    @PUT
    @Path("/generateGradeCard")
    public Response generateGradeCard()
    {
        service.generateGradeCard();
        return Response.status(201).entity( "Published Grade Card successfully!!!").build();
    }

    @POST
    @Path("/addCourse")
    public Response addCourse(@QueryParam("courseID") Integer courseID, @QueryParam("courseName") String courseName, @QueryParam("semID") Integer semID){

        try {
            service.addCourse(courseID, courseName, semID);
            return Response.ok("Course added Successfully!").build();
        }
        catch (CourseAlreadyPresentException exception){
            return Response.status(201).entity("Course is Already presesnt. Cannot be added").build();
        }
    }

    @DELETE
    @Path("/removeCourse")
    public Response removeCourse(@QueryParam("semID") Integer semID,@QueryParam("courseID") Integer courseID)
    {
        try {
            service.removeCourse(semID, courseID);
            return Response.status(201).entity( "Course removed Successfully!").build();
        }
        catch(CourseNotFoundException e)
        {
            return Response.status(201).entity( "Course can not be deleted since course is not present!").build();
        }
        catch (CourseNotDeletedException e)
        {
            return Response.status(201).entity( "Course Not deleted!").build();
        }
    }
}
