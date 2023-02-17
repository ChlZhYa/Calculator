package com.zcl.dto;

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
