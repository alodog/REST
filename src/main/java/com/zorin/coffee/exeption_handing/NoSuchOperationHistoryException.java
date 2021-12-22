package com.zorin.coffee.exeption_handing;

/**
 * @author Zorin Sergey
 */

public class NoSuchOperationHistoryException extends Exception{
    public NoSuchOperationHistoryException(String message) {
        super(message);
    }
}
