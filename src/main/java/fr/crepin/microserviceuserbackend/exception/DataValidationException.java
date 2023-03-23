package fr.crepin.microserviceuserbackend.exception;

public class DataValidationException extends Exception {
    public DataValidationException(final String message) {
        super(message);
    }
}
