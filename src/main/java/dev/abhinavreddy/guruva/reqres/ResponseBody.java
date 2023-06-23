package dev.abhinavreddy.guruva.reqres;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ResponseBody {
    private String message;
    private Boolean error;
    private HttpStatus status;
    private Object data;
}
