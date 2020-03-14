package com.skills.rentaride.exceptions;

/**
 * Created by sylvester
 * Project rent-a-ride
 * User: sylvester
 * Date: 2/25/20
 * Time: 10:37 PM
 */
public class InvalidPinStatusException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new Invalid pin status exception.
     *
     * @param message the message
     */
    public InvalidPinStatusException(String message)
    {
        super(message);
    }

    /**
     * Instantiates a new Invalid pin status exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public InvalidPinStatusException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
