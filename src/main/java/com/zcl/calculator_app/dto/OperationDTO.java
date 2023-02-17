package com.zcl.calculator_app.dto;

import lombok.Data;

@Data
public class OperationDTO {
    private Double initialValue;

    private Double operand;

    /**
     * 操作符
     */
    private String operator;
}
