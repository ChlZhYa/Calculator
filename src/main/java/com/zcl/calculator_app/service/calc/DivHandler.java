package com.zcl.calculator_app.service.calc;

import com.zcl.calculator_app.enums.OperationEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class DivHandler implements CalculationHandler {

    @Value("${operation.scale:16}")
    private Integer operationScale;

    @Override
    public boolean support(@NotEmpty String operator) {
        return  OperationEnum.DIVISION.getOperator().equals(operator);
    }

    @Override
    public BigDecimal calc(BigDecimal initialValue, BigDecimal operand) {
        return initialValue.divide(operand, operationScale, RoundingMode.HALF_DOWN);
    }
}
