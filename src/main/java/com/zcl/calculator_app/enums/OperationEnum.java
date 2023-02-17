package com.zcl.calculator_app.enums;

import java.util.Arrays;
import java.util.List;

public enum OperationEnum {
    ADD("+", "-"),
    SUBTRACTION("-", "+"),
    MULTIPLY("*", "/"),
    DIVISION("/", "*"),
    UNDO("undo", ""),
    REDO("redo", "");

    private static final List<OperationEnum> calcEnums = List.of(ADD, SUBTRACTION, MULTIPLY, DIVISION);

    private static final List<OperationEnum> oprEnums = List.of(UNDO, REDO);


    private final String operator;

    /**
     * 逆操作，如果没有逆操作，则置空
     */
    private final String oppositeOperation;


    OperationEnum(String operator, String oppositeOperation) {
        this.operator = operator;
        this.oppositeOperation = oppositeOperation;
    }

    public String getOperator() {
        return operator;
    }

    public String getOppositeOperation() {
        return oppositeOperation;
    }

    public static boolean isCalcEnum(String operator) {
        return calcEnums.stream().anyMatch(o -> o.getOperator().equals(operator));
    }

    public static boolean isOprEnum(String operator) {
        return oprEnums.stream().anyMatch(o -> o.getOperator().equals(operator));
    }

    public static OperationEnum getByOperator(String operator) {
        return Arrays.stream(OperationEnum.values()).filter(o -> o.getOperator().equals(operator)).findFirst().orElse(null);
    }
}
