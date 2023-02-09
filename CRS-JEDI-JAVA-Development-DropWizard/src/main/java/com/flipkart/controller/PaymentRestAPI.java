package com.flipkart.controller;

import com.flipkart.service.PaymentInterface;
import com.flipkart.service.PaymentServiceOperation;

import javax.validation.Validator;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/payment")
public class PaymentRestAPI {

    private final Validator validator;

    public PaymentRestAPI(Validator validator) {
        this.validator = validator;
    }
    PaymentInterface service = new PaymentServiceOperation();
    @POST
    @Path("/notification")
    public Response sendNotification(@QueryParam("studentID") Integer studentID, @QueryParam("transactionID") String transactionID, @QueryParam("msg") String msg) {
        try {
            service.sendNotification(studentID, transactionID, msg);
            return Response.ok("Payment Notification successfully").build();
        } catch (Exception e) {
            return Response.status(400).entity(studentID + ": " + transactionID + ": " + msg).build();
        }
    }

}
