package com.example.api101withspringboot.api.calculator;

import com.example.api101withspringboot.api.calculator.model.SumReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculatorService {

    public double plus(double source, double target) {
        return source + target;
    }

    public double minus(double source, double target) {
        return source - target;
    }

    public double areaOfACircle(double radius) {
        return Math.PI * (radius * radius);
    }

    public double sum(List<Double> numberList) {
        double sum = 0;

        for (double number : numberList) {
            sum = sum + number;
        }

        // alternative
        // Ref# https://www.javatpoint.com/java-8-stream
//        double sum = numberList
//                .stream()
//                .reduce(0D, (subtotal, element) -> subtotal + element);

        return sum;
    }

}
