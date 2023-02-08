package com.flipkart.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
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

//    @PUT
//    @Path("/{id}")
//    public Response updateEmployeeById(@PathParam("id") Integer id, Employee employee) {
//        // validation
//        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
//        Employee e = EmployeeDB.getEmployee(employee.getId());
//        if (violations.size() > 0) {
//            ArrayList<String> validationMessages = new ArrayList<String>();
//            for (ConstraintViolation<Employee> violation : violations) {
//                validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
//            }
//            return Response.status(Status.BAD_REQUEST).entity(validationMessages).build();
//        }
//        if (e != null) {
//            employee.setId(id);
//            EmployeeDB.updateEmployee(id, employee);
//            return Response.ok(employee).build();
//        } else
//            return Response.status(Status.NOT_FOUND).build();
//    }

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
}
