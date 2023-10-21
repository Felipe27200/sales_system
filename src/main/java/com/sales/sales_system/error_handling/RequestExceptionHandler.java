package com.sales.sales_system.error_handling;

import com.sales.sales_system.error_handling.db_errors.DatabaseErrorsResponse;
import com.sales.sales_system.error_handling.db_errors.DuplicateConstraintException;
import com.sales.sales_system.error_handling.db_errors.NotFoundException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RequestExceptionHandler
{
    // Handle the type mismatch errors
    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<?> handleTypeMismatch(TypeMismatchException exc)
    {
        String errorMessage = "";

        Class<?> requiredType = exc.getRequiredType(); // Get the required type.
        Object offendingValue = exc.getValue(); // Get the offending value.

        if (requiredType == null)
            errorMessage = String.format("The value '%s' is not a correct type.", offendingValue);
        else
        {
            // Create an error message.
            errorMessage = String.format("The value '%s' is not of type '%s'.",
                    offendingValue, requiredType.getName());
        }

        DatabaseErrorsResponse errorsResponse = new DatabaseErrorsResponse();

        errorsResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorsResponse.setMessage(errorMessage);

        // Return an error response.
        return new ResponseEntity<>(errorsResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    // Map<?, ?> Generate a list with the structure of key and value.
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException exc)
    {
        List<String> errors = exc
                .getBindingResult()
                .getFieldErrors()
                /*
                * This last 3 methods give us
                * the opportunity of generate a list of key associations
                * between the errors list and the errors.
                * */
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFound(NotFoundException exc)
    {
        DatabaseErrorsResponse errorResponse = new DatabaseErrorsResponse();

        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exc.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateConstraintException.class)
    public ResponseEntity<?> handleDuplicateConstraint(DuplicateConstraintException exc)
    {
        DatabaseErrorsResponse errorResponse = new DatabaseErrorsResponse();

        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(exc.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        /*
        * HashMap doesn't maintain the order of the element and
        * can store null values.
        *
        * It is an implementation of Map<?, ?>
        * */
        Map<String, List<String>> errorResponse = new HashMap<>();

        // Here we define the key and the value (the list of errors.)
        errorResponse.put("errors", errors);

        return errorResponse;
    }
}
