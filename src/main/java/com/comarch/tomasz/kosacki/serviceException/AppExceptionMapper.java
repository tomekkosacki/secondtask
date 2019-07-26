package com.comarch.tomasz.kosacki.serviceException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class AppExceptionMapper implements ExceptionMapper<AppException> {
    @Override
    public Response toResponse(AppException exception) {
        return Response.status(exception.getStatus())
                .entity(new ExceptionMessage(exception))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
