package com.skills.rentaride.exceptions;

/**
 * Created by sylvester
 * Project rent-a-ride
 * User: sylvester
 * Date: 2/25/20
 * Time: 11:01 PM
 */
public class ItemTypeNotFoundException extends Throwable {
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new Item type not found exception.
     *
     * @param message the message
     */
    public ItemTypeNotFoundException(String message)
    {
        super(message);
    }

    /**
     * Instantiates a new Item type not found exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ItemTypeNotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
