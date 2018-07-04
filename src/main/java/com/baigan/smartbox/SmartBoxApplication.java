package com.baigan.smartbox;

import com.baigan.smartbox.health.SmartBoxHealthCheck;
import com.baigan.smartbox.resources.CoreResource;
import com.baigan.smartbox.services.CoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.dropwizard.Application;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class SmartBoxApplication extends Application<SmartBoxConfiguration> {

    public static void main(final String[] args) throws Exception {
        new SmartBoxApplication().run(args);
    }

    @Override
    public String getName() {
        return "Box";
    }

    @Override
    public void initialize(final Bootstrap<SmartBoxConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final SmartBoxConfiguration configuration,
                    final Environment environment) {

        environment.getObjectMapper().registerModule(new JavaTimeModule())
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")) //SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSX")
                .setTimeZone(TimeZone.getTimeZone("UTC"))
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        // health check
        environment.healthChecks().register("SmartBox", new SmartBoxHealthCheck());

        // database
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");

        environment.jersey().register(new CoreResource(new CoreService(configuration.getAppId(), jdbi)));


    }

}
