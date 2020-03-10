package com.skills.rentaride.exceptions;

/**
 * Created by sylvester
 * Project rent-a-ride
 * User: sylvester
 * Date: 2/25/20
 * Time: 11:01 PM
 */
public class ItemOwnerNotFoundException extends Throwable {
    private static final long serialVersionUID = 1L;

    public ItemOwnerNotFoundException(String message)
    {
        super(message);
    }

    public ItemOwnerNotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
