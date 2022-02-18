package com.example.api101withspringboot.api.student;

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

        return studentRepository.add(conn, dto);

    }

    public void edit(Connection conn, StudentDto dto) throws SQLException {

        studentRepository.edit(conn, dto);

    }

    public void delete(Connection conn, Long id) throws SQLException {

        studentRepository.delete(conn, id);
    }
}
