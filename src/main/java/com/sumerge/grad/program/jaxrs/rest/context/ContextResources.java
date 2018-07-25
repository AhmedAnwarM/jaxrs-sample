package com.sumerge.grad.program.jaxrs.rest.context;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import static com.sumerge.grad.program.jaxrs.util.Util.getAsJsonFormatted;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@Path("context")
public class ContextResources {

    @Context
    HttpServletRequest request;

    @Context
    HttpHeaders headers;

    @Context
    UriInfo uriInfo;

    @Context
    SecurityContext securityContext;

    @GET
    @Path("request")
    public Response showRequest() {
        return Response.ok().
                entity(getAsJsonFormatted(request)).
                build();
    }

    @GET
    @Path("headers")
    public Response showHeaders() {
        return Response.ok().
                entity(getAsJsonFormatted(headers)).
                build();
    }

    @GET
    @Path("security")
    public Response showSecurityContext() {
        return Response.ok().
                entity(getAsJsonFormatted(securityContext)).
                build();
    }

    @GET
    @Path("uri-info")
    public Response showUriInfo() {
        return Response.ok().
                entity(getAsJsonFormatted(uriInfo)).
                build();
    }
}
