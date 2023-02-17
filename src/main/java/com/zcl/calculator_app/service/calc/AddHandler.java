package com.zcl.calculator_app.service.calc;

import com.zcl.calculator_app.enums.OperationEnum;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Service
public class AddHandler implements CalculationHandler {
    @Override
    public boolean support(@NotEmpty String operator) {
        return OperationEnum.ADD.getOperator().equals(operator);
    }

    @Override
    public BigDecimal calc(BigDecimal initialValue, BigDecimal operand) {
        return initialValue.add(operand);
    }
}
