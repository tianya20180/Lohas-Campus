package com.wx.happy.dto;

public class Result<T> {
    private boolean  success;

    private T data;

    private String errorMsg;

    private int errorCode;

    public Result(){

    }

    //success
    public Result(boolean success,T data){
        this.success=success;
        this.data=data;
    }
    //fail
    public Result(boolean success,int errorCode,String errorMsg){
        this.success=success;
        this.errorCode=errorCode;
        this.errorMsg=errorMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
