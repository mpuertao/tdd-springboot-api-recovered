package com.covid19.recovereds.exceptionhandling.exceptions;

public class DataBaseEmptyException extends RuntimeException{

    private static final long serialVersionUID = -278989289883727L;
    private static final String NOT_FOUND_CLIENTS = "No exist clients in DB";

    public DataBaseEmptyException(){
        super(NOT_FOUND_CLIENTS);
    }
}
