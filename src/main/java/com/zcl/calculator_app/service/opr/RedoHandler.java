package com.zcl.calculator_app.service.opr;

import com.zcl.calculator_app.dto.OperationDTO;
import com.zcl.calculator_app.enums.OperationEnum;
import com.zcl.calculator_app.exception.BusinessException;
import com.zcl.calculator_app.util.Memory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


/**
 * redo 处理器 -》 重复上一次操作
 */
@Service
public class RedoHandler extends AbstractOperationHandler implements OperationHandler {

    @Override
    public boolean support(String operator) {
        return OperationEnum.REDO.getOperator().equals(operator);
    }

    @Override
    public BigDecimal process() {
        if (Memory.isEmpty()) {
            throw new BusinessException("The calculator doesn't support this operator now!");
        }
        // 最后一次操作
        OperationDTO operationDTO = Memory.pollLastRecord();
        return super.processByBase(operationDTO.getOperator(), operationDTO.getOperand());
    }
}
