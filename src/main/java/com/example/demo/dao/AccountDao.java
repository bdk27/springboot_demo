package com.example.demo.dao;

public interface AccountDao {

    void decreaseMoney(int id, int money);

    void addMoney(int id, int money);
}
