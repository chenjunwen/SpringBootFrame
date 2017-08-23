package com.tuling.common.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by Administrator on 2017/8/9.
 * 自定义异常
 */
public class RRException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String err;

    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public RRException(String err) {
        super(err);
        this.err = err;
    }

    public RRException(String err, HttpStatus status) {
        super(err);
        this.err = err;
        this.status = status;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}