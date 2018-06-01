package com.baigan.smartbox.health;

import com.codahale.metrics.health.HealthCheck;

public class SmartBoxHealthCheck extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
