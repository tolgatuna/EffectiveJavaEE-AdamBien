package com.tt.doit.business;

import javax.ejb.EJBException;
import javax.persistence.OptimisticLockException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EJBExceptionMapper implements ExceptionMapper<EJBException> {
    @Override
    public Response toResponse(EJBException e) {
        Throwable cause = e.getCause();

        if(cause instanceof OptimisticLockException) {
            return Response.status(Response.Status.CONFLICT)
                    .header("cause", "conflict occurred: " + cause)
                    .build();
        }

        return Response.serverError().
                header("cause", e.toString()).build();
    }
}
