package com.cgi.api.web.exceptions.handler;

import com.cgi.api.core.exceptions.GroceryItemNotFoundException;
import com.cgi.api.web.model.ApiResponse;
import com.cgi.api.web.model.ErrorItem;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class ApiResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String ERR_UNKNOWN_ERROR = "We are currently facing some issue. Kindly try after some time.";

    @Override
    public ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(BAD_REQUEST).body(ApiResponse.builder().message(ex.getMessage()).build());
    }

    @Override
    public ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(BAD_REQUEST).body(ApiResponse.builder().message(ex.getMessage()).build());
    }

    @Override
    public ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(BAD_REQUEST).body(ApiResponse.builder().message(ex.getMessage()).build());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ObjectError> objectErrorList = ex.getBindingResult().getAllErrors();
        List<ErrorItem> errorItems = objectErrorList.stream()
                .map(objectError -> ErrorItem.builder()
                        .name(objectError.getObjectName())
                        .message(objectError.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.status(BAD_REQUEST).body(ApiResponse.builder()
                .errors(errorItems)
                .build());
    }

    @Override
    public ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(ApiResponse.builder().message(ERR_UNKNOWN_ERROR).build());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ApiResponse> handleConstraintViolationExceptionException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolationSet = ex.getConstraintViolations();
        List<ErrorItem> errorItems = constraintViolationSet.stream()
                .map(constraintViolation -> ErrorItem.builder()
                        .name(constraintViolation.getPropertyPath().toString())
                        .message(constraintViolation.getMessage())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.status(BAD_REQUEST).body(ApiResponse.builder()
                .errors(errorItems)
                .build());
    }

    @ExceptionHandler({GroceryItemNotFoundException.class})
    public ResponseEntity<ApiResponse> handleGroceryItemNotFoundException(GroceryItemNotFoundException ex) {
        return ResponseEntity.status(BAD_REQUEST).body(ApiResponse.builder().message(ex.getMessage()).build());
    }
}
