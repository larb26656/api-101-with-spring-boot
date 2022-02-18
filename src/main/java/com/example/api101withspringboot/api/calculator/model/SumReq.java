package com.example.api101withspringboot.api.calculator.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SumReq {
    private List<Double> numberList;
}
