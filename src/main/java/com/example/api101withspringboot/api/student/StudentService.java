package com.example.api101withspringboot.api.student;

import com.example.api101withspringboot.api.registration.RegistrationService;
import com.example.api101withspringboot.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Service

public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RegistrationService registrationService;

    public List<StudentDto> search(Connection conn) throws SQLException {

        return studentRepository.search(conn);

    }

    public StudentDto getById(Connection conn, Long id) throws SQLException {

        return studentRepository.getById(conn, id);

    }

    public List<StudentDto> searchByName(Connection conn, String name) throws SQLException {

        return studentRepository.searchByName(conn, name);

    }

    public Long add(Connection conn, StudentDto dto) throws SQLException {

        Long id = studentRepository.add(conn, dto);

        // register
        registrationService.add(conn, id);

        return id;

    }

    public void edit(Connection conn, StudentDto dto) throws SQLException {

        studentRepository.edit(conn, dto);

    }

    public void delete(Connection conn, Long id) throws SQLException {

        studentRepository.delete(conn, id);
    }
}
