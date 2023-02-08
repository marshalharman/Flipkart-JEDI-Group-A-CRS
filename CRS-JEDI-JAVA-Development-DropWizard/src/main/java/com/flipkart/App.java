package com.flipkart;

import com.flipkart.controller.*;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App extends Application<Configuration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    @Override
    public void initialize(Bootstrap<Configuration> b) {
    }

    @Override
    public void run(Configuration c, Environment e) throws Exception {
        LOGGER.info("Registering REST resources");

        //registering all the RESTful service classes.
        e.jersey().register(new AdminRestAPI());
        e.jersey().register(new PaymentRestAPI());
        e.jersey().register(new ProfessorRestAPI());
        e.jersey().register(new StudentRestAPI());
        e.jersey().register(new UserRestAPI());

    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}

