package dev.abhinavreddy.guruva.reqres;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

//    Unknown Exception
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleUnknownException(Exception ex) {
        // create a response entity with a response exception
        ResponseBody responseException = new ResponseBody(ex.getMessage(), true, 500, null);
        return ResponseEntity.status(500).body(responseException);
    }

//  duplicate entry exception
    @ExceptionHandler(DuplicateKeyException.class)
    protected ResponseEntity<Object> handleDuplicateKeyException( DuplicateKeyException ex) {
        // create a response entity with a response exception
        ResponseBody responseException = new ResponseBody(ex.getMessage(), true, 409, null);
        return ResponseEntity.status(409).body(responseException);
    }

}
