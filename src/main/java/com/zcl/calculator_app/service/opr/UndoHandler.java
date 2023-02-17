package com.zcl.calculator_app.service.opr;

import com.zcl.calculator_app.enums.OperationEnum;
import com.zcl.calculator_app.exception.BusinessException;
import com.zcl.calculator_app.util.Memory;
import com.zcl.dto.OperationDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Deque;


/**
 * undo 处理器 -》 撤销上一次操作
 */
@Service
public class UndoHandler extends AbstractOperationHandler implements OperationHandler {

    @Override
    public boolean support(String operator) {
        return OperationEnum.UNDO.getOperator().equals(operator);
    }

    @Override
    public BigDecimal process() {
        // 撤销操作
        Deque<OperationDTO> undoRecords = Memory.recordDeque;
        if (undoRecords.isEmpty()) {
            throw new BusinessException("The calculator doesn't support this operator now!");
        }
        // 最后一次操作
        OperationDTO operationDTO = Memory.pollLastRecord();
        // 如果原记录是有初始值的，则直接返回初始值即可
        if (operationDTO.getInitialValue() != null) {
            return BigDecimal.valueOf(operationDTO.getInitialValue());
        }
        // 原纪录没有初始值的情况下，需要进行逆操作
        OperationEnum calcEnum = OperationEnum.getByOperator(operationDTO.getOperator());
        String operator = calcEnum.getOppositeOperation();
        return super.processByBase(operator, operationDTO.getOperand());
    }
}
