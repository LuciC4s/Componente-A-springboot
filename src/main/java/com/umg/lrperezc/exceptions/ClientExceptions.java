package com.umg.lrperezc.exceptions;

public class ClientExceptions {

    public static class ClientNotFoundException extends RuntimeException {
        public ClientNotFoundException() {
            super("Client not found");
        }
        public ClientNotFoundException(String message) {
            super(message);
        }
    }

    public static class ClientCreationException extends RuntimeException {

        public ClientCreationException(String message) {
            super(message);
        }
        public ClientCreationException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
