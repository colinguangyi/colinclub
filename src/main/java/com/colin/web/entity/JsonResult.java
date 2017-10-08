package com.colin.web.entity;

import java.util.HashMap;

/**
 * 返回json数据对象
 * @author zhaolz
 * @create 2017-09-06
 */
public class JsonResult {

    private String code;
    private String msg;
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
