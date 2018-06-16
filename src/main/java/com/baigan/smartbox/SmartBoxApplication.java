package com.baigan.smartbox;

import com.baigan.smartbox.health.SmartBoxHealthCheck;
import com.baigan.smartbox.resources.CoreResource;
import com.baigan.smartbox.services.NotificationService;
import io.dropwizard.Application;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;

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
        // health check
        environment.healthChecks().register("SmartBox", new SmartBoxHealthCheck());

        // database
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");

        environment.jersey().register(new CoreResource(new NotificationService(configuration.getAppId(), jdbi)));


    }

}
