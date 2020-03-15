package com.skills.rentaride.exceptions;

/**
 * Created by lilian
 * Project rent-a-ride
 * User: lilian
 * Date: 2/25/20
 * Time: 10:37 PM
 */
public class GenericException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new Generic exception.
     *
     * @param message the message
     */
    public GenericException(String message)
    {
        super(message);
    }

    /**
     * Instantiates a new Generic exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public GenericException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
