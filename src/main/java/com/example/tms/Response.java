package com.example.tms;

public class Response<T> {

    private String status;
    private String message;
    private T data;
    private String errorCode;

    private Response(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.errorCode = null; // No error code for success
    }

    private Response(String status, String message, T data, String errorCode) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.errorCode = errorCode;
    }

    public static <T> Response<T> success(String message, T data){
        return new Response<>("SUCCESS", message, data);
    }
    public static <T> Response<T> error(String message, T data, String errorCode){
        return new Response<>("ERROR", message, data, errorCode);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
