package com.tuling.modal;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2017/8/4.
 */
public enum StatusEnum {
    @ApiModelProperty("可用")
    available(1),

    @ApiModelProperty("不可用")
    unavailable(-1);
    private int status;

    StatusEnum(int status){
        this.status = status;
    }
}
