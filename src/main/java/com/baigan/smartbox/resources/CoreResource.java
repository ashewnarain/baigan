package com.baigan.smartbox.resources;

import com.baigan.smartbox.domain.Event;
import com.baigan.smartbox.domain.Notification;
import com.baigan.smartbox.services.NotificationService;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.MessageFormat;

@Slf4j
@Path("/v1")
public class CoreResource {
    private NotificationService service;

    public CoreResource(NotificationService service) {
        this.service = service;
    }

    @GET
    @Path("/hello")
    public Response getGreeting() {
        return Response.ok("Hello World!").build();
    }

    @POST
    @Path("/events")
    public Response createEvent(Event event) {
        log.info("Received event - event={} timestamp={}", event.getEvent(), event.getTimestamp());
        return Response.status(Response.Status.CREATED).entity("Success").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/notifications")
    public Response createNotification(Notification notification) {
        log.info("Received request - event={} timestamp={} id={}", notification.getEvent(), notification.getEventTimestamp(), notification.getNotificationId());
        try {
            String id = service.saveNotification(notification);
            String message = MessageFormat.format("Successfully saved notification - id={0}", id);
            return Response.status(Response.Status.CREATED).entity(message).build();

        } catch (Exception e) {
            String error = MessageFormat.format("Error while saving notification - id={0} error={1}", notification.getNotificationId(), e.getMessage());
            log.error(error, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }

    }

}
