package com.zzyy.commerce.response;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseEntity<E> {
    private HttpStatus status;
    private String subMessage;

    private E result;

    public ResponseEntity(HttpStatus status, E result) {
        super();
        this.status = status;
        this.result = result;
    }



    @Override
    public String toString() {
        return "PbmResponse [status=" + status.value() + ", subMessage=" + subMessage + ", result="
                + JSON.toJSONString(result) + "]";
    }
}
