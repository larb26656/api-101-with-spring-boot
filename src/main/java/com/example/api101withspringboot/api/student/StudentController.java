package com.example.api101withspringboot.api.student;

import com.example.api101withspringboot.dto.StudentDto;
import com.example.api101withspringboot.model.UpsertRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

@RestController
@RequestMapping("api")
public class StudentController {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private StudentService studentService;


    // -------------------------- GET --------------------------------//
    @GetMapping(value = "/v1/student/{id}")
    public ResponseEntity<StudentDto> getById(@PathVariable long id) throws Exception {
        try(final Connection conn = dataSource.getConnection()) {
            // Set ReadOnly is true
            conn.setReadOnly(true);

            StudentDto dto = studentService.getById(conn, id);

            if (dto == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(dto);
        }
    }

    @GetMapping(value = "/v1/student")
    public ResponseEntity<List<StudentDto>> searchByName(@RequestParam String name) throws Exception {
        try(final Connection conn = dataSource.getConnection()) {
            // Set ReadOnly is true
            conn.setReadOnly(true);

            List<StudentDto> dtoList = studentService.searchByName(conn, name);

            return ResponseEntity.ok(dtoList);
        }
    }

    @PostMapping(value = "/v1/student/search")
    public ResponseEntity<List<StudentDto>> search() throws Exception {
        try(final Connection conn = dataSource.getConnection()) {
            // Set ReadOnly is true
            conn.setReadOnly(true);

            List<StudentDto> dtoList = studentService.search(conn);

            return ResponseEntity.ok(dtoList);
        }
    }

    @PostMapping(value = "/v1/student")
    public ResponseEntity<UpsertRes> add(@RequestBody StudentDto dto) throws Exception {
        try(final Connection conn = dataSource.getConnection()) {
            // Set Auto Commit is false
            conn.setAutoCommit(false);

            // Get Data From Service
            Long id = studentService.add(conn, dto);

            UpsertRes res = new UpsertRes(
                    id
            );

            // Set Commit Database
            conn.commit();

            return ResponseEntity.ok(res);
        }
    }

    @PutMapping(value = "/v1/student")
    public ResponseEntity<Void> edit(@RequestBody StudentDto dto) throws Exception {
        try(final Connection conn = dataSource.getConnection()) {
            // Set Auto Commit is false
            conn.setAutoCommit(false);

            // Get Data From Service
            studentService.edit(conn, dto);

            // Set Commit Database
            conn.commit();

            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping(value = "/v1/student/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) throws Exception {
        try(final Connection conn = dataSource.getConnection()) {
            // Set Auto Commit is false
            conn.setAutoCommit(false);

            // Get Data From Service
            studentService.delete(conn, id);

            // Set Commit Database
            conn.commit();

            return ResponseEntity.ok().build();
        }
    }

}
