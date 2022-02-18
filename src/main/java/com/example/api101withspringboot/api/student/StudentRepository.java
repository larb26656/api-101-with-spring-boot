package com.example.api101withspringboot.api.student;

import com.example.api101withspringboot.dto.StudentDto;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    private static final String SEARCH_SQL = "SELECT * FROM tb_student";

    public List<StudentDto> search(Connection conn) throws SQLException {

        try(final PreparedStatement ps = conn.prepareStatement(SEARCH_SQL)) {
            try (final ResultSet rs = ps.executeQuery()) {
                final List<StudentDto> dtoList = new ArrayList<>();

                while (rs.next()) {
                    dtoList.add(populate(rs));
                }

                return dtoList;
            }
        }

    }

    private static final String SEARCH_BY_ID_SQL = "SELECT * FROM tb_student WHERE id = ?";

    public StudentDto getById(Connection conn, Long id) throws SQLException {

        try(final PreparedStatement ps = conn.prepareStatement(SEARCH_BY_ID_SQL)) {

            ps.setLong(1, id);

            try (final ResultSet rs = ps.executeQuery()) {
                final List<StudentDto> dtoList = new ArrayList<>();
                StudentDto dto  = null;

                while (rs.next()) {
                    dto = populate(rs);
                }

                return dto;
            }
        }

    }

    private static final String SEARCH_BY_NAME_SQL = "SELECT * FROM tb_student WHERE name LIKE ?";

    public List<StudentDto> searchByName(Connection conn, String name) throws SQLException {

        try(final PreparedStatement ps = conn.prepareStatement(SEARCH_BY_NAME_SQL)) {

            ps.setString(1, "%" + name +  "%");

            try (final ResultSet rs = ps.executeQuery()) {
                final List<StudentDto> dtoList = new ArrayList<>();

                while (rs.next()) {
                    dtoList.add(populate(rs));
                }

                return dtoList;
            }
        }

    }

    private static final String INSERT_SQL = "INSERT INTO tb_student SET name = ?, description = ?";

    public Long add(Connection conn, StudentDto dto) throws SQLException {
        try(final PreparedStatement ps = conn.prepareStatement(INSERT_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, dto.getName());
            ps.setString(2, dto.getDescription());

            if (ps.executeUpdate() == 0)
                throw new SQLException("Creating tb_student failed, no rows affected.");

            try (final ResultSet generatedKeys = ps.getGeneratedKeys()) {

                if (!generatedKeys.next())
                    throw new SQLException("Creating ID failed, no ID obtained.");

                return generatedKeys.getLong(1);

            }
        }
    }

    private static final String UPDATE_SQL = "UPDATE tb_student SET name = ?, description = ? WHERE id = ?";

    public void edit(Connection conn, StudentDto dto) throws SQLException {
        try(final PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {

            ps.setString(1, dto.getName());
            ps.setString(2, dto.getDescription());
            ps.setLong(3, dto.getId());

            if (ps.executeUpdate() == 0)
                throw new SQLException("Update tb_student failed, no rows affected.");
        }
    }

    private static final String DELETE_SQL = "DELETE FROM tb_student WHERE id = ?";

    public void delete(Connection conn, Long id) throws SQLException {
        try(final PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {

            ps.setLong(1, id);

            if (ps.executeUpdate() == 0)
                throw new SQLException("Delete tb_student failed, no rows affected.");
        }
    }


    public StudentDto populate(ResultSet rs) throws SQLException {
        StudentDto dto = new StudentDto();

        dto.setId(rs.getLong("tb_student.id"));
        dto.setName(rs.getString("tb_student.name"));
        dto.setDescription(rs.getString("tb_student.description"));

        return dto;
    }
}
