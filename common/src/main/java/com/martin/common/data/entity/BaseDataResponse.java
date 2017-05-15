package com.martin.common.data.entity;

/**
 * Created by martin on 2017/5/5.
 */

public class BaseDataResponse<T> {



    private int code;
    private String msg;
    private T newsList;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getNewsList() {
        return newsList;
    }

    public void setNewsList(T newsList) {
        this.newsList = newsList;
    }
    @Override
    public String toString() {
        return "BaseDataResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", newsList=" + newsList +
                '}';
    }


}
