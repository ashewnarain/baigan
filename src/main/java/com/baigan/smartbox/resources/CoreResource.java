package com.baigan.smartbox.resources;

import com.baigan.smartbox.domain.Event;
import com.baigan.smartbox.domain.Notification;
import com.baigan.smartbox.domain.PassCode;
import com.baigan.smartbox.services.CoreService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

@Slf4j
@Path("/v1")
public class CoreResource {
    private CoreService service;

    public CoreResource(CoreService service) {
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
        log.info("[EVENT] Received event - event={} timestamp={}", event.getEvent(), event.getTimestamp());
        return Response.status(Response.Status.CREATED).entity("Success").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/notifications")
    public Response createNotification(Notification notification) {
        log.info("[NOTIFICATION] Received request - event={} timestamp={} id={}", notification.getEvent(), notification.getEventTimestamp(), notification.getNotificationId());
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/pass_codes")
    public Response createPassCode(PassCode passCode) {
        log.info("[CREATE PASS CODE] Received request - productId={} passCode={} timestamp={}", passCode.getProductId(), passCode.getPassCode(), passCode.getCreatedTs());
        try {
            String id = service.savePassCode(passCode);
            String message = MessageFormat.format("Successfully saved passCode - productId={0} passCode={1}", passCode.getProductId(), id);
            return Response.status(Response.Status.CREATED).entity(message).build();

        } catch (Exception e) {
            String error = MessageFormat.format("Error while saving passCode - productId={0} error={1}", passCode.getProductId(), e.getMessage());
            log.error(error, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/pass_codes")
    public Response getPassCodeList(@HeaderParam("request_id") String requestId) {
        if (StringUtils.isBlank(requestId)) {
            requestId = UUID.randomUUID().toString();
            log.warn("[GET PASS CODE LIST] No 'request_id' provided, auto-generated new [request_id={}]", requestId);
        }
        log.info("[GET PASS CODE LIST] Received request - request_id={}", requestId);
        try {
            List<PassCode> passCodeList = service.getPassCodeList(requestId);
            return Response.status(Response.Status.OK).entity(passCodeList).build();
        } catch (Exception e) {
            String error = MessageFormat.format("Error while fetching passCodes - request_id={0} error={1}", requestId, e.getMessage());
            log.error(error, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).header("request_id", requestId).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/pass_codes/{product_id}")
    public Response getPassCode(@PathParam("product_id") String productId, @HeaderParam("request_id") String requestId) {
        if (StringUtils.isBlank(requestId)) {
            requestId = UUID.randomUUID().toString();
            log.warn("[GET PASS CODE] No 'request_id' provided, auto-generated new [request_id={}]", requestId);
        }
        log.info("[GET PASS CODE] Received request - request_id={} productId={}", requestId, productId);
        try {
            PassCode passCode = service.getPassCode(requestId, productId);
            return Response.status(Response.Status.OK).entity(passCode).build();
        } catch (Exception e) {
            String error = MessageFormat.format("Error while fetching passCode - request_id={0} productId={1} error={2}", requestId, productId, e.getMessage());
            log.error(error, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
    }
}
