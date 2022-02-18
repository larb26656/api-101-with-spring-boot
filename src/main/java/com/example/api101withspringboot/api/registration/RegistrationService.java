package com.example.api101withspringboot.api.registration;

import com.example.api101withspringboot.dto.RegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    private String generateStudentCode() {

        Random rnd = new Random();

        int number = rnd.nextInt(999999);

        return String.format("%06d", number);
    }

    public Long add(Connection conn, Long studentId) throws SQLException {

        RegistrationDto dto = new RegistrationDto();

        dto.setStudentCode(generateStudentCode());

        dto.setStudentId(studentId);

        return registrationRepository.add(conn, dto);

    }

}
