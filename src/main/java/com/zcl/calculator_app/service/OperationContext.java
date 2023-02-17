package com.zcl.calculator_app.service;

import com.zcl.calculator_app.enums.OperationEnum;
import com.zcl.calculator_app.exception.BusinessException;
import com.zcl.calculator_app.service.calc.CalculationHandler;
import com.zcl.calculator_app.service.opr.OperationHandler;
import com.zcl.calculator_app.util.Memory;
import com.zcl.dto.OperationDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class OperationContext {

    @Resource
    private List<CalculationHandler> calculationHandlerList;
    @Resource
    private List<OperationHandler> operationHandlerList;

    public BigDecimal calculation(OperationDTO operationDTO) {

        String operator = operationDTO.getOperator();
        // 判断是否为当前支持的操作符
        if (Arrays.stream(OperationEnum.values()).noneMatch(o -> o.getOperator().equals(operator))) {
            throw new BusinessException("The calculator doesn't support this operator now!");
        }

        BigDecimal result;
        // 判断是否为计算符
        if (OperationEnum.isCalcEnum(operator)) {
            Optional<CalculationHandler> calculationHandlerOptional = calculationHandlerList.stream().filter(o -> o.support(operator)).findAny();
            Assert.isTrue(calculationHandlerOptional.isPresent(), "calc enum doesn't match calc handler");
            CalculationHandler calculationHandler = calculationHandlerOptional.get();
            Double initialValue = operationDTO.getInitialValue();
            Double operand = operationDTO.getOperand();
            if (initialValue == null) {
                result = calculationHandler.calc(new BigDecimal(Memory.current.toString()), BigDecimal.valueOf(operand));
            } else {
                // 如果 initialValue 不为空，表示输入了新的计算起始值，需要清空原来的 undoRecord
                result = calculationHandler.calc(BigDecimal.valueOf(initialValue), BigDecimal.valueOf(operand));
                Memory.clearUndoRecord();
            }
            // 操作后记录操作数据
            Memory.addUndoRecord(operationDTO);
        }
        // 判断是否为操作
        else if (OperationEnum.isOprEnum(operator)) {
            Optional<OperationHandler> operationHandlerOptional = operationHandlerList.stream().filter(o -> o.support(operator)).findAny();
            Assert.isTrue(operationHandlerOptional.isPresent(), "opr enum doesn't match opr handler");
            OperationHandler operationHandler = operationHandlerOptional.get();
            result = operationHandler.process();
        }else {
            throw new BusinessException("error config in operation enum");
        }
        return result;

    }

}
