package com.buxiu.bootexample.controller.model;

import com.buxiu.bootexample.utils.I18nUtils;

public class RSResult {

    private int result; // 返回客户端的错误代码，0表示没有错误。不等于0时，message内填充了错误文本提示
    private String message;    // 返回客户端的文本提示
    private long total;    // 返回客户端的列表元素个数，在查询list时这个值才有效
    private Object data; // 返回客户端的数据对象

    public RSResult() {
        super();
    }

    public RSResult(int errorcode) {
        this.result = errorcode;
        this.message = I18nUtils.getErrorDescription(errorcode);
    }

    public int getResult() {
        return result;
    }

    /**
     * 操作失败
     *
     * @param errorcode
     */
    public void setResult(int errorcode) {
        this.result = errorcode;
        this.message = I18nUtils.getErrorDescription(errorcode);
    }

    /**
     * 业务处理失败
     *

     * @param errorcode
     * @param remark    业务处理
     */
    /*public void setResult(int errorcode, String remark) {
        this.result = errorcode;
        this.message = I18nUtils.getErrorDescription(errorcode);

    }*/



    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message.toString();
    }
}
