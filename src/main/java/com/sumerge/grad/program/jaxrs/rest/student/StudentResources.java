package com.sumerge.grad.program.jaxrs.rest.student;

import com.sumerge.grad.program.jaxrs.repositories.boundary.Repository;
import com.sumerge.grad.program.jaxrs.repositories.entity.Student;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@RequestScoped
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Path("students")
public class StudentResources {

    private static final Logger LOGGER = Logger.getLogger(StudentResources.class.getName());

    @EJB
    private Repository repo;

    @Context
    HttpServletRequest request;

    @GET
    public Response getAllStudents() {
        try {
            System.out.println("Entering getAllStudents()");
            return Response.ok().
                    entity(repo.getAllStudents()).
                    build();
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            return Response.serverError().
                    entity(e.getClass() + ": " + e.getMessage()).
                    build();
        }
    }

    @GET
    @Path("{id}")
    public Response getStudent(@PathParam("id") Long id) {
        try {
            return Response.ok().
                    entity(repo.getStudent(id)).
                    build();
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            return Response.serverError().
                    entity(e.getClass() + ": " + e.getMessage()).
                    build();
        }
    }

    @POST
    public Response addStudent(Student student) {
        try {
            if (student.getId() != null)
                throw new IllegalArgumentException("Can't create student since it exists in the database");

            Student merged = (Student) repo.save(student);
            URI uri = new URI(request.getRequestURI() + merged.getId());
            return Response.created(uri).
                    build();
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            return Response.serverError().
                    entity(e.getClass() + ": " + e.getMessage()).
                    build();
        }
    }

    @PUT
    public Response editStudent(Student student) {
        try {
            if (student.getId() == null)
                throw new IllegalArgumentException("Can't edit student since it does not exist in the database");

            repo.save(student);
            return Response.ok().
                    build();
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            return Response.serverError().
                    entity(e.getClass() + ": " + e.getMessage()).
                    build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteStudent(@PathParam("id") Long id) {
        try {
            repo.deleteStudent(id);
            return Response.ok().
                    build();
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            return Response.serverError().
                    entity(e.getClass() + ": " + e.getMessage()).
                    build();
        }
    }
}
