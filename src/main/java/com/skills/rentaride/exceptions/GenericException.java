package com.skills.rentaride.exceptions;

/**
 * Created by sylvester
 * Project rent-a-ride
 * User: sylvester
 * Date: 2/25/20
 * Time: 10:37 PM
 */
public class GenericException extends Exception {
    private static final long serialVersionUID = 1L;

    public GenericException(String message)
    {
        super(message);
    }

    public GenericException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
