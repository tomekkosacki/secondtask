package com.comarch.tomasz.kosacki.serviceException;

import javax.ws.rs.core.Response;

public class DuplicateTagNameException extends AppException {

    public DuplicateTagNameException() throws AppException {

        throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400, "User can not have two or more tags with the same tag name for one user.");
    }
}
