package com.azl.common;

/**
 * Created by jazl on 7/6/2017.
 */
public class SharedObject {
    private Integer counter = 0;
    private String message = "(no message)";

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
