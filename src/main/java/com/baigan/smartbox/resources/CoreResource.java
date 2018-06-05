package com.baigan.smartbox.resources;

import com.baigan.smartbox.domain.Event;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/v1")
public class CoreResource {

    @GET
    @Path("/hello")
    public Response getGreeting() {
        return Response.ok("Hello World!").build();
    }

    @POST
    @Path("/events")
    public Response createEvent(Event event) {
        return Response.status(Response.Status.CREATED).entity("Success").build();
    }

}
