package com.baigan.smartbox;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class SmartBoxConfiguration extends io.dropwizard.Configuration {

    @Valid
    @NotNull
    private String appId;

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty("appId")
    public String getAppId() {
        return appId;
    }

    @JsonProperty("appId")
    public void setAppId(String appId) {
        this.appId = appId;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory factory) {
        this.database = factory;
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }
}
