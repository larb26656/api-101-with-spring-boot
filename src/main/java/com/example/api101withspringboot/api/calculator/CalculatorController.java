package com.example.api101withspringboot.api.calculator;

import com.example.api101withspringboot.api.calculator.model.AreaOfACircleReq;
import com.example.api101withspringboot.api.calculator.model.MinusReq;
import com.example.api101withspringboot.api.calculator.model.PlusReq;
import com.example.api101withspringboot.api.calculator.model.SumReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/calculator")
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;


    @PostMapping(value = "/v1/plus")
    public ResponseEntity<Double> plus(@RequestBody() PlusReq req) {

        final double total = calculatorService.plus(
                req.getSource(),
                req.getTarget()
        );

        return ResponseEntity.ok(
                total
        );

    }

    @PostMapping(value = "/v1/minus")
    public ResponseEntity<Double> minus(@RequestBody() MinusReq req) {

        final double total = calculatorService.minus(
                req.getSource(),
                req.getTarget()
        );

        return ResponseEntity.ok(
                total
        );

    }

    @PostMapping(value = "/v1/circle/area")
    public ResponseEntity<Double> areaOfACircle(@RequestBody() AreaOfACircleReq req) {

        final double area = calculatorService.areaOfACircle(
                req.getRadius()
        );

        return ResponseEntity.ok(
                area
        );

    }

    @PostMapping(value = "/v1/sum")
    public ResponseEntity<Double> sum(@RequestBody() SumReq req) {

        final double total = calculatorService.sum(
                req.getNumberList()
        );

        return ResponseEntity.ok(
                total
        );

    }
}
