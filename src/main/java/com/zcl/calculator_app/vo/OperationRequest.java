package com.zcl.calculator_app.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OperationRequest {

    /**
     * 初始值
     */
    private Double initialValue;

    /**
     * 操作数
     */
    private Double operand;

    /**
     * 操作符
     */
    @NotBlank
    private String operator;
}
