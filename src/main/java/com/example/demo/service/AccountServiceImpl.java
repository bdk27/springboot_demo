package com.example.demo.service;

import com.example.demo.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountDao accountDao;

    @Transactional
    @Override
    public void transfer(int fromAccountId, int toAccountId, int money) {
        accountDao.decreaseMoney(fromAccountId, money);

        accountDao.addMoney(toAccountId, money);
    }
}
