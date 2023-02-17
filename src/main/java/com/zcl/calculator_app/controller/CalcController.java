package com.zcl.calculator_app.controller;


import com.zcl.calculator_app.dto.OperationDTO;
import com.zcl.calculator_app.service.OperationContext;
import com.zcl.calculator_app.vo.OperationRequest;
import com.zcl.calculator_app.vo.OperationResponse;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/calc")
public class CalcController {


    @Resource
    private OperationContext operationContext;

    @PostMapping("/operate")
    public OperationResponse operate(@RequestBody OperationRequest request) {
        OperationDTO operationDTO = new OperationDTO();
        BeanUtils.copyProperties(request, operationDTO);
        BigDecimal bigDecimal = operationContext.calculation(operationDTO);
        return new OperationResponse(bigDecimal);
    }

}
