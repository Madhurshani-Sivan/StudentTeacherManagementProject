package com.example.StudentTeacherManagement.model;

import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.jpa.repository.query.InvalidJpaQueryMethodException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Transactional
@Repository
public class StudentDaoImpl {

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ResourceLoader resourceLoader;

    public JasperPrint export() throws SQLException, JRException, InvalidJpaQueryMethodException, IOException,NullPointerException {
        Connection con = jdbcTemplate.getDataSource().getConnection();
        String path = resourceLoader.getResource("classpath:students.jrxml").getURI().getPath();
        JasperReport jasperReport = JasperCompileManager.compileReport(path);
        Map<String, Object> parameters = new HashMap<String, Object>();
        JasperPrint pdf = JasperFillManager.fillReport(jasperReport, parameters, con);
        return pdf;
    }
}
