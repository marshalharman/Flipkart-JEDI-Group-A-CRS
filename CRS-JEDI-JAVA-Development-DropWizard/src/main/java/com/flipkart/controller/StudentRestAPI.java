package com.flipkart.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseNotFoundByNameException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.service.ProfessorServiceOperation;
import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentServiceOperation;

@Path("/student")
@Produces(MediaType.APPLICATION_JSON)
public class StudentRestAPI {
    private final Validator validator;

    StudentInterface studentServiceOperation = new StudentServiceOperation();

    public StudentRestAPI(Validator validator) {
        this.validator = validator;
    }

    @PUT
    @Path("/semRegistration")
    public Response semRegistration(@QueryParam("studentID") int stuID, @QueryParam("semID") int semID) {
        studentServiceOperation.setSemID(stuID,semID);
        return Response.ok(Status.ACCEPTED).build();
    }

    @GET
    @Path("/viewCourses/{id}")
    public Response viewCourses(@PathParam("id") Integer id) {
        List<Course> sem_courses = studentServiceOperation.getCourses(id);
        if(sem_courses!=null) {
            return Response.ok(sem_courses).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Path("/viewGrades/{id}")
    public Response viewGrades(@PathParam("id") Integer id) {
        HashMap<Course,String> grades = studentServiceOperation.viewGrades(id);
        if(grades!=null) {
            return Response.ok(grades).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Path("/viewRegisteredCourses/{id}")
    public Response viewRegisteredCourses(@PathParam("id") Integer id) {
        List<Course> registered_courses = studentServiceOperation.getRegisteredCourses(id);
        if(registered_courses!=null) {
            return Response.ok(registered_courses).build();
        }
        return Response.status(Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/dropCourse")
    public Response dropCourse(@QueryParam("studentID") Integer studentID, @QueryParam("courseID") Integer courseID){
        studentServiceOperation.dropCourse(studentID, courseID);
        return Response.ok(Status.ACCEPTED).build();
    }



}
