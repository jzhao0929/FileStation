package com.station.file.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @描述：
 * @创建人： 2020-1-14 11:10 企业平台事业部/jzhao1
 */
public final class Jo {
    private int code;//如果使用到code,建议使用200以上编码
    private boolean success;
    private String msg;
    private Object data;

    private Jo() {
        this.code = 200;
    }

    public boolean isSuccess() {
        return success;
    }
    public Jo setSuccess(boolean success) {
        this.success = success;
        return this;
    }
    public String getMsg() {
        return msg;
    }
    public Jo setMsg(String msg) {
        this.msg = msg;
        return this;
    }
    public Object getData() {
        return data;
    }
    public Jo setData(Object data) {
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }
    public Jo setCode(int code) {
        this.code = code;
        return this;
    }

    public static Jo build(boolean success) {
        Jo jo = new Jo();
        jo.setSuccess(success);
        jo.setMsg(success ? "操作成功" : "操作失败");
        return jo;
    }
    public static Jo success() {
        Jo jo = new Jo();
        jo.setSuccess(true);
        jo.setMsg("操作成功");
        return jo;
    }
    public static Jo failed() {
        Jo jo = new Jo();
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