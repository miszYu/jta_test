package com.example.demo.service.impl;

import com.example.demo.dao.AccountDao;
import com.example.demo.service.AccountService;
import com.example.demo.service.AccountService2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private AccountService2 accountService2;


    @Override
    public void transfer(Integer fromAccountId, Integer toAccountId, Integer money) {

        // User A 扣除轉帳金額
        accountDao.decreaseMoney(fromAccountId, money);

        //Transactional看進入點是否有下@Transactional，而會走Transactiona。
        //accountService2沒有@Transactional。
        //可是進入點有@Transactional一樣會roll back
        accountService2.decreaseMoney(3,1);
//        int i = 1/0;

        // User B 收到轉入金額
        accountDao.addMoney(toAccountId, money);
    }

}
