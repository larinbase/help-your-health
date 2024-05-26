package ru.itis.healthserviceimpl.controller.handler;
import lombok.Builder;
import lombok.Data;

/**
 * Description of the exception that occurred.
 */
@Data
@Builder
public class ExceptionMessage {

    /** Exception message */
    private String message;

    /** Exception name */
    private String exceptionName;
}

