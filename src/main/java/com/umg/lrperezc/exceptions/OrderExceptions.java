package com.umg.lrperezc.exceptions;

public class OrderExceptions {
    public static class OrderNotFoundException extends RuntimeException {
        public OrderNotFoundException(String message) { super(message); }
    }
    public static class OrderCreationException extends RuntimeException {
        public OrderCreationException(String message) { super(message); }
        public OrderCreationException(String message, Throwable cause) { super(message, cause); }
    }
}
