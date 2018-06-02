package com.baigan.smartbox;

import com.baigan.smartbox.health.SmartBoxHealthCheck;
import com.baigan.smartbox.resources.CoreResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
        environment.healthChecks().register("SmartBox", new SmartBoxHealthCheck());
        environment.jersey().register(new CoreResource());
    }

}
