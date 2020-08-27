package com.tjj.springcloud.springCloud_client_test.modules.common.vo;

public class Result<T> {
    private int status;
    private String message;
    private T object;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResults() {
        return object;
    }

    public void setResults(T results) {
        this.object = results;
    }

    public Result(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public Result(int status, String message, T results) {
        this.status = status;
        this.message = message;
        this.object = results;
    }
    public enum ResultStatus {
        SUCCESS(200),FAILED(500);
       public int status;

        ResultStatus(int status) {
            this.status = status;
        }
    }
}
