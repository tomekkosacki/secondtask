package com.comarch.tomasz.kosacki.serviceException;

import javax.ws.rs.core.Response;

public class NullArgumentException extends AppException {

     public NullArgumentException() throws AppException {

         throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400, "Argument can not be null");
     }
}
