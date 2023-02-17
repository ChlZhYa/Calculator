package com.zcl.calculator_app.service.opr;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

public interface OperationHandler {

    /**
     * 是否支持当前操作
     */
    boolean support(@NotEmpty(message = "the operator must not be empty") String operator);

    BigDecimal process();
}
