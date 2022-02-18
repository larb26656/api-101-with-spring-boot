package com.example.api101withspringboot.api.registration;

import com.example.api101withspringboot.dto.RegistrationDto;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class RegistrationRepository {

    private static final String INSERT_SQL = "INSERT INTO tb_registration SET student_code = ?, student_id = ?";

    public Long add(Connection conn, RegistrationDto dto) throws SQLException {
        try(final PreparedStatement ps = conn.prepareStatement(INSERT_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, dto.getStudentCode());
            ps.setLong(2, dto.getStudentId());

            if (ps.executeUpdate() == 0)
                throw new SQLException("Creating tb_registration failed, no rows affected.");

            try (final ResultSet generatedKeys = ps.getGeneratedKeys()) {

                if (!generatedKeys.next())
                    throw new SQLException("Creating ID failed, no ID obtained.");

                return generatedKeys.getLong(1);

            }
        }
    }

}
