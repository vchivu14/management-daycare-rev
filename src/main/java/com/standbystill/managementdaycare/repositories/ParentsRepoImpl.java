package com.standbystill.managementdaycare.repositories;

import com.standbystill.managementdaycare.entities.Family;
import com.standbystill.managementdaycare.entities.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class ParentsRepoImpl implements ParentsRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<Parent> fetchAll() {
        String sql="SELECT * FROM parent";
        RowMapper<Parent> rowMapper = new BeanPropertyRowMapper<>(Parent.class);
        return jdbcTemplate.query(sql,rowMapper);
    }

    @Override
    public List<Parent> fetchParentsForFamily(int familyId) {
        String sql="SELECT * FROM parent WHERE Family_id=?";
        RowMapper<Parent> rowMapper = new BeanPropertyRowMapper<>(Parent.class);
        return jdbcTemplate.query(sql,rowMapper,familyId);
    }

    @Override
    public Family fetchFamilyForParent(int familyId) {
        String sql="SELECT * FROM family WHERE id = ?";
        RowMapper<Family> rowMapper = new BeanPropertyRowMapper<>(Family.class);
        return (Family) jdbcTemplate.query(sql,rowMapper,familyId);
    }

    @Override
    public void addParent(Parent parent, int familyId, int personId) {
        String lastName = parent.getLastName();
        String firstName = parent.getFirstName();
        String email = parent.getEmail();
        long phone = parent.getPhone();
        int income = parent.getIncome();
        String sql = "INSERT INTO parent (FirstName, LastName, Email, Phone, Income, Family_id, Person_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] {"id"});
            ps.setString(1,firstName);
            ps.setString(2,lastName);
            ps.setString(3, email);
            ps.setString(4, String.valueOf(phone));
            ps.setString(5, String.valueOf(income));
            ps.setString(6, String.valueOf(familyId));
            ps.setString(7, String.valueOf(personId));
            return ps;
        });
    }

    @Override
    public boolean updateParent(Parent parent, int personId) {
        String lastName = parent.getLastName();
        String firstName = parent.getFirstName();
        String email = parent.getEmail();
        long phone = parent.getPhone();
        int income = parent.getIncome();
        String sql = "UPDATE parent SET FirstName = ?, LastName = ?, Email = ?, Phone = ?, Income = ? WHERE Person_id = ?";
        return jdbcTemplate.update(sql,firstName,lastName,email,phone,income,personId)>=0;
    }

    @Override
    public boolean deleteParent(int personId) {
        String sql = "DELETE FROM parent WHERE Person_id = ?";
        return jdbcTemplate.update(sql,personId)>=0;
    }

    @Override
    public Parent findParentById(int personId) {
        String sql = "SELECT * FROM parent WHERE Person_id = ?";
        RowMapper<Parent> rowMapper = new BeanPropertyRowMapper<>(Parent.class);
        return jdbcTemplate.queryForObject(sql,rowMapper,personId);
    }

    @Override
    public List<Parent> findParentsByLastName(String lastName) {
        String sql = "SELECT * FROM parent WHERE LastName = ?";
        RowMapper<Parent> rowMapper = new BeanPropertyRowMapper<>(Parent.class);
        return jdbcTemplate.query(sql, rowMapper, lastName);
    }
}
