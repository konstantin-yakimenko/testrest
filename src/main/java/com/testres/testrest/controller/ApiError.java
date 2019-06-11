package com.testres.testrest.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * @author konst
 */
public class ApiError {

    private final HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ssZ")
    private final ZonedDateTime timestamp;
    private final String message;
    private final String debugMessage;
    private final Cause cause;

    public ApiError(HttpStatus status, ZonedDateTime timestamp, String message, String debugMessage, Cause cause) {
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
        this.debugMessage = debugMessage;
        this.cause = cause;
    }

    public ApiError(HttpStatus status, String message, Throwable ex) {
        this(status, ZonedDateTime.now(), message,
                ex.getLocalizedMessage() == null ? "" : ex.getLocalizedMessage(),
                ((ex.getCause() == null) ? null : new Cause(
                        ((Throwable) ex.getCause()).getClass().getCanonicalName(),
                        ex.getCause().getLocalizedMessage())
                ));
    }

    public ApiError(HttpStatus status, Throwable ex) {
        this(status, "Unexpected error", ex);
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "status=" + status +
                ", timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", debugMessage='" + debugMessage + '\'' +
                ", cause=" + cause +
                '}';
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public Cause getCause() {
        return cause;
    }
}
