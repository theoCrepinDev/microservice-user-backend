package fr.crepin.microserviceuserbackend.exception;

public class EventValidationException extends Exception{

    public EventValidationException(final String message){
        super(message);
    }
}
