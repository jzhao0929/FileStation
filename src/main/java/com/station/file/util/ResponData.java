package com.station.file.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @描述：对返回前端的数据进行封装
 * @创建人： 2020-1-14 11:10 企业平台事业部/jzhao1
 */
public final class ResponData {
    private int code;//如果使用到code,建议使用200以上编码
    private boolean success;
    private String msg;
    private Object data;

    private ResponData() {
        this.code = 200;
    }

    public boolean isSuccess() {
        return success;
    }
    public ResponData setSuccess(boolean success) {
        this.success = success;
        return this;
    }
    public String getMsg() {
        return msg;
    }
    public ResponData setMsg(String msg) {
        this.msg = msg;
        return this;
    }
    public Object getData() {
        return data;
    }
    public ResponData setData(Object data) {
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }
    public ResponData setCode(int code) {
        this.code = code;
        return this;
    }

    public static ResponData build(boolean success) {
        ResponData jo = new ResponData();
        jo.setSuccess(success);
        jo.setMsg(success ? "操作成功" : "操作失败");
        return jo;
    }
    public static ResponData success() {
        ResponData jo = new ResponData();
        jo.setSuccess(true);
        jo.setMsg("操作成功");
        return jo;
    }
    public static ResponData failed() {
        ResponData jo = new ResponData();
        jo.setSuccess(false);
        jo.setCode(500);
        jo.setMsg("操作失败");
        return jo;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}