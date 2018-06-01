package com.baigan.smartbox.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/v1")
public class CoreResource {

    @GET
    @Path("/hello")
    public Response getGreeting() {
        return Response.ok("Hello World!").build();
    }
}
