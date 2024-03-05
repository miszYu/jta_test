package com.example.demo.service.impl;

import com.example.demo.dao.AccountDao;
import com.example.demo.service.AccountService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//@Transactional
public class AccountService2Impl implements AccountService2 {

    @Autowired
    private AccountDao accountDao;

    @Override
    public void decreaseMoney(Integer id, Integer money){
        accountDao.decreaseMoney(id, money);
    }


}
