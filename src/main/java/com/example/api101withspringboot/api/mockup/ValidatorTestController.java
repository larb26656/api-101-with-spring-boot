package com.example.api101withspringboot.api.mockup;

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

}
