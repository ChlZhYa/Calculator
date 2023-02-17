package com.zcl.calculator_app.util;

import com.zcl.calculator_app.dto.OperationDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;

@Component
public class Memory {

    public static BigDecimal current = new BigDecimal(0);

    public static Deque<OperationDTO> recordDeque = new ArrayDeque<>();

    public static void clearUndoRecord() {
        recordDeque.clear();
    }

    public static void addUndoRecord(OperationDTO operationDTO) {
        recordDeque.add(operationDTO);
    }

    public static boolean isEmpty() {
        return recordDeque.isEmpty();
    }

    public static OperationDTO pollLastRecord() {
        Assert.isTrue(!recordDeque.isEmpty(), "there is no record");
        return recordDeque.pollLast();
    }

    public static void updateCurrent(BigDecimal newVal){
        current = newVal;
    }

}
