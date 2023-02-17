package com.zcl.calculator_app.service.opr;

import com.zcl.calculator_app.service.calc.CalculationHandler;
import com.zcl.calculator_app.util.Memory;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public abstract class AbstractOperationHandler implements OperationHandler {

    @Resource
    private List<CalculationHandler> calculationHandlerList;

    @Override
    public boolean support(String operator) {
        return false;
    }

    @Override
    public BigDecimal process() {
        return null;
    }

    public BigDecimal processByBase(String operator,Double operand){
        Optional<CalculationHandler> calculationHandlerOptional = calculationHandlerList.stream().filter(o -> o.support(operator)).findAny();
        Assert.isTrue(calculationHandlerOptional.isPresent(), "calc enum doesn't match calc handler");
        CalculationHandler calculationHandler = calculationHandlerOptional.get();

        BigDecimal result = calculationHandler.calc(new BigDecimal(Memory.current.toString()), BigDecimal.valueOf(operand));
        Memory.updateCurrent(result);
        return result;
    }
}
