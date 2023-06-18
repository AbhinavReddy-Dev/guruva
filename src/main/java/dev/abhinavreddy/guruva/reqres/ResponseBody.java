package dev.abhinavreddy.guruva.reqres;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseBody {
    private String message;
    private Boolean error;
    private Integer status;
    private Object data;
}
