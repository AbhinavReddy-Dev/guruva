package dev.abhinavreddy.guruva.exceptions;

import dev.abhinavreddy.guruva.config.ResponseBody;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

//    Path variable mismatch MethodArgumentTypeMismatchException
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatchException(org.springframework.web.method.annotation.MethodArgumentTypeMismatchException ex) {
        String error = "Path variable mismatch: ";
        ResponseBody responseException =  new ResponseBody( error + ex.getLocalizedMessage(), true, HttpStatus.BAD_REQUEST, null);
        return new ResponseEntity<>(responseException, responseException.getStatus());
    }

    // User already exists exception
    @ExceptionHandler(UserAlreadyExists.class)
    protected ResponseEntity<Object> handleUserAlreadyExistsException( UserAlreadyExists ex) {
        // create a response entity with a response exception
        ResponseBody responseException = new ResponseBody(ex.getMessage(), true, HttpStatus.CONFLICT, null);

        return new ResponseEntity<>(responseException, responseException.getStatus());
    }

    // User not found exception
    @ExceptionHandler(UserNotFound.class)
    protected ResponseEntity<Object> handleUserNotFoundException(UserNotFound ex){
        ResponseBody responseException = new ResponseBody(ex.getMessage(), true, HttpStatus.NOT_FOUND, null);

        return new ResponseEntity<>(responseException, responseException.getStatus());
    }

//    IllegalArgumentException exception
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        // create a response entity with a response exception
        ResponseBody responseException = new ResponseBody(ex.getLocalizedMessage(), true, HttpStatus.PARTIAL_CONTENT, null);

        return new ResponseEntity<>(responseException, responseException.getStatus());
    }
    // Unknown Exceptions
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleUnknownException(Exception ex) {
        // create a response entity with a response exception
        ResponseBody responseException = new ResponseBody(ex.getLocalizedMessage(), true, HttpStatus.INTERNAL_SERVER_ERROR, null);

        return new ResponseEntity<>(responseException, responseException.getStatus());
    }
    // Unknown RuntimeExceptions
    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleUnknownException(RuntimeException ex) {
        // create a response entity with a response exception
        ResponseBody responseException = new ResponseBody(ex.getLocalizedMessage(), true, HttpStatus.INTERNAL_SERVER_ERROR, null);

        return new ResponseEntity<>(responseException, responseException.getStatus());
    }
}
