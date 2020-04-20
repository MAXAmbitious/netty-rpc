package com.beidao.netty.dubbo.facade.api.message;

/**
 * @author beidao
 *
 */
public class DubboResponseMessage {
    /**
     * 请求id
     */
    private String requestId;
    /**
     * 错误码
     */
    private String error;
    /**
     * 返回值
     */
    private Object result;

    public String getRequestId() {
        return requestId;
    }

    public String getError() {
        return error;
    }

    public Object getResult() {
        return result;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setResult(Object result) {
        this.result = result;
    }
    public boolean isError() {
        return error != null;
    }
}
