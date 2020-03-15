package com.skills.rentaride.exceptions;

/**
 * Created by lilian
 * Project rent-a-ride
 * User: lilian
 * Date: 2/25/20
 * Time: 11:01 PM
 */
public class ProfileNotFoundException extends Throwable {
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new Profile not found exception.
     *
     * @param message the message
     */
    public ProfileNotFoundException(String message)
    {
        super(message);
    }

    /**
     * Instantiates a new Profile not found exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ProfileNotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
