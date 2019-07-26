package com.comarch.tomasz.kosacki.serviceException;

import javax.ws.rs.core.Response;

public class DuplicateKeyExceptionTagId extends AppException {

    public DuplicateKeyExceptionTagId() throws AppException {

        throw new AppException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), 500, "TagId already exist in database");
    }
}
