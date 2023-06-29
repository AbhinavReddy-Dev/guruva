package dev.abhinavreddy.guruva.reqres;

import dev.abhinavreddy.guruva.exceptions.UserAlreadyExists;
import dev.abhinavreddy.guruva.exceptions.UserNotFound;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

    //    Not Readable Exceptions
    @ExceptionHandler(HttpMessageConversionException.class)
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageConversionException ex) {
        String error = "Malformed JSON: ";
        ResponseBody responseException =  new ResponseBody( error + ex.getLocalizedMessage(), true, HttpStatus.BAD_REQUEST, null);
        return new ResponseEntity<>(responseException, responseException.getStatus());
    }

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

    // Unknown Exceptions
    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleUnknownException(RuntimeException ex) {
        // create a response entity with a response exception
        ResponseBody responseException = new ResponseBody(ex.getLocalizedMessage(), true, HttpStatus.INTERNAL_SERVER_ERROR, null);

        return new ResponseEntity<>(responseException, responseException.getStatus());
    }
}
