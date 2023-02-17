package com.zcl.calculator_app.service.calc;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

public interface CalculationHandler {

    /**
     * 是否支持当前操作
     */
    boolean support(@NotEmpty(message = "the operator must not be empty") String operator);

    BigDecimal calc(BigDecimal initialValue, BigDecimal operand);
}
