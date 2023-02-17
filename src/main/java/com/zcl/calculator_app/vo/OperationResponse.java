package com.zcl.calculator_app.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 响应结果，这里只用 success 表示成功/失败；也可以用 code 更细化区分
 */
@Data
public class OperationResponse {


    private boolean success;

    private BigDecimal result;

    private String message;

    public OperationResponse(BigDecimal result){
        success = true;
        this.result = result;
    }

    public OperationResponse(String message){
        success = false;
        this.message = message;
    }

}
