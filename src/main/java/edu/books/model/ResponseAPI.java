package edu.books.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseAPI implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;
    private String message;
    private Object data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public ResponseAPI() {
    }

    public ResponseAPI(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCode(){ return code; }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
