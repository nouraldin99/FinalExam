package com.noura.anwar.finalexam.model.entity;

import com.google.gson.annotations.SerializedName;

public class ApiError {
    @SerializedName("cod")
    private String code;
    @SerializedName("message")
    private String message ;
    private boolean isRecoverable = true ;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRecoverable() {
        return isRecoverable;
    }

    public void setRecoverable(boolean recoverable) {
        isRecoverable = recoverable;
    }
}
