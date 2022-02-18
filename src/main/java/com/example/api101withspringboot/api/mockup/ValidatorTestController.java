package com.example.api101withspringboot.api.mockup;

import com.example.api101withspringboot.api.mockup.model.ParseToIntReq;
import com.example.api101withspringboot.api.mockup.model.ValidatorTestReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/mockup")
public class ValidatorTestController {


    @PostMapping(value = "/v1/validator/test")
    public ResponseEntity<Double> test(@RequestBody() @Valid() ValidatorTestReq req) {
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/v1/parse/int")
    public ResponseEntity<Void> parseToInt(@RequestBody ParseToIntReq req) {
        // should be error cause String can't parse to int
        System.out.println(Integer.parseInt(req.getText()));
        return ResponseEntity.ok().build();
    }

}
