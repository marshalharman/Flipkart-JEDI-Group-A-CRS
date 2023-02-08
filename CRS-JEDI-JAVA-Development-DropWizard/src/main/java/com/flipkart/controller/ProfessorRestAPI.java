package com.flipkart.controller;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseNotFoundByNameException;
import com.flipkart.service.ProfessorServiceOperation;

import java.util.*;

import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/professor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfessorRestAPI {

    private final Validator validator;

    public ProfessorRestAPI(Validator validator) {
        this.validator = validator;
    }

    @GET
    @Path("/viewCourses/{semID}")
    public Response viewCourses(@PathParam("semID") Integer semID){

        ProfessorServiceOperation professorService = new ProfessorServiceOperation();
        List<Course> courses = professorService.viewCourse(semID);

        if( !courses.isEmpty() ){
            return Response.ok(courses).build();
        }
        else{
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/viewEnrolledStudents")
    public Response viewEnrolledStudents(@QueryParam("semID") Integer semID, @QueryParam("courseName") String courseName){

        ProfessorServiceOperation professorService = new ProfessorServiceOperation();
        List<Student> students = professorService.viewEnrolledStudents(semID, courseName);

        if( !students.isEmpty() ){
            return Response.ok(students).build();
        }
        else{
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/viewRegisteredCourses")
    public Response viewRegisteredCoursesurprosem(@QueryParam("profID") Integer profID){

        ProfessorServiceOperation professorService = new ProfessorServiceOperation();
        List<Course> registeredCourses = professorService.viewRegisteredCourses(profID);

        if( !registeredCourses.isEmpty() ) {
            return Response.ok(registeredCourses).build();
        }
        else{
            return Response.status(Status.NOT_FOUND).build();
        }
    }


    @PUT
    @Path("/registerCourse")
    public Response registerCourse(@QueryParam("profID") int profID, @QueryParam("courseName") String courseName, @QueryParam("semID") int semID){

        ProfessorServiceOperation professorService = new ProfessorServiceOperation();
        try {
            professorService.registerCourse(profID, courseName, semID);
        }
        catch (CourseNotFoundByNameException exception){
            System.out.println(exception.getMessage());
            return Response.status(Status.BAD_REQUEST).build();
        }

        return Response.ok(Status.ACCEPTED).build();
    }

    @PUT
    @Path("/deregisterCourse")
    public Response deregisterCourse(@QueryParam("profID") Integer profID, @QueryParam("courseName") String courseName, @QueryParam("semID") Integer semID){

        ProfessorServiceOperation professorService = new ProfessorServiceOperation();

        try{
            professorService.deregisterCourse(profID, courseName, semID);
        }
        catch (CourseNotFoundByNameException exception){
            System.out.println(exception.getMessage());
            return Response.status(Status.BAD_REQUEST).build();
        }

        return Response.ok(Status.ACCEPTED).build();
    }

    @PUT
    @Path("/addGrade")
    public Response addGrade(@QueryParam("profID") Integer profID, @QueryParam("courseName") String courseName, @QueryParam("studentID") Integer studentID, @QueryParam("grade") String grade){

        ProfessorServiceOperation professorService = new ProfessorServiceOperation();

        professorService.addGrade(profID, courseName, studentID, grade);

        return Response.ok(Status.ACCEPTED).build();
    }
}
