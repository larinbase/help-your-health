package ru.itis.healthauthimpl.controller.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.itis.healthauthimpl.exception.ServiceException;

import java.util.List;

/**
 * Exception handler
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle service exceptions and return an ExceptionMessage wrapped in a ResponseEntity.
     *
     * @param exception the Service exception to be handled
     * @return the ResponseEntity containing the ExceptionMessage
     */
    @ExceptionHandler(ServiceException.class)
    public final ResponseEntity<ExceptionMessage> handleServiceException(ServiceException exception) {
        return ResponseEntity.status(exception.getStatus())
                .body(ExceptionMessage.builder()
                        .exceptionName(exception.getClass().getSimpleName())
                        .message(exception.getMessage())
                        .build()
                );
    }

    /**
     * Generates a response based on all {@link Exception} exceptions.
     *
     * @param exception the exception
     * @return the response generated based on the exception
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ExceptionMessage onAllExceptions(Exception exception) {
        return ExceptionMessage.builder()
                .exceptionName(exception.getClass().getSimpleName())
                .message(exception.getMessage())
                .build();
    }

    /**
     * Handle validation exceptions and return an ExceptionMessage wrapped in a ResponseEntity.
     *
     * @param exception the validation exception to be handled
     * @return the ResponseEntity containing the ExceptionMessage
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<List<ExceptionMessage>> onValidationExceptions(MethodArgumentNotValidException exception) {
        List<ExceptionMessage> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> ExceptionMessage.builder()
                        .exceptionName(exception.getClass().getSimpleName())
                        .message(fieldError.getDefaultMessage())
                        .build()
                )
                .toList();
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
