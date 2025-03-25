package com.birdregistry.bird_registry_api.bird.infraestructure.exception;

import com.birdregistry.bird_registry_api.bird.config.utils.Constants;
import com.birdregistry.bird_registry_api.bird.domain.exception.InvalidDateCreateException;
import com.birdregistry.bird_registry_api.bird.domain.exception.NotFoundBirdException;
import com.birdregistry.bird_registry_api.bird.infraestructure.wrapper.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ApiResponse<Object>> buildResponse(String message, WebRequest request, HttpStatus status) {
        String path = "";

        if (request instanceof ServletWebRequest servletWebRequest) {
            HttpServletRequest httpServletRequest = servletWebRequest.getRequest();
            path = httpServletRequest.getMethod() + " " + httpServletRequest.getRequestURI();
        }


        ApiResponse<Object> response = new ApiResponse<>(
                Constants.ERROR,
                message + " | Path: " + path,
                null
        );
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Object>> handleRuntimeException(RuntimeException exception, WebRequest request) {
        return buildResponse(exception.getMessage(), request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>> handleIllegalArgumentException(IllegalArgumentException exception, WebRequest request) {
        return buildResponse(exception.getMessage(), request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception exception, WebRequest request) {
        return buildResponse("An unexpected error occurred", request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidDateCreateException.class)
    public ResponseEntity<ApiResponse<Object>> handleInvalidDateCreateException(Exception exception, WebRequest request){
        return buildResponse(exception.getMessage(),request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundBirdException.class)
    public ResponseEntity<ApiResponse<Object>> handlerNotFoundBirdException(Exception exception, WebRequest request){
        return buildResponse(exception.getMessage(),request,HttpStatus.NOT_FOUND);
    }
}