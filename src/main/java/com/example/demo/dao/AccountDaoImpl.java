package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void decreaseMoney(int id, int money) {
        String sql = "UPDATE account SET balance = balance - :money WHERE id = :id";

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("money", money);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void addMoney(int id, int money) {
        String sql = "UPDATE account SET balance = balance + :money WHERE id = :id";

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("money", money);

        namedParameterJdbcTemplate.update(sql, map);
    }
}
