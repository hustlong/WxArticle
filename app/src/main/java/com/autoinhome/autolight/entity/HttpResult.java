package com.autoinhome.autolight.entity;

/**
 * Created by yanglong on 2016/10/25.
 */

public class HttpResult<T> {
    private String msg;

    private String retCode;

    private T result ;

    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }
    public void setRetCode(String retCode){
        this.retCode = retCode;
    }
    public String getRetCode(){
        return this.retCode;
    }
    public void setResult(T result){
        this.result = result;
    }
    public T getResult(){
        return this.result;
    }
}
