package com.comarch.tomasz.kosacki.serviceException;

import javax.ws.rs.core.Response;

public class TagEntityNotFoundException extends AppException {

    public TagEntityNotFoundException(String tagId) throws AppException {
        throw new AppException(Response.Status.NOT_FOUND.getStatusCode(), 404, "Tag " + tagId + " not found");
    }
}
