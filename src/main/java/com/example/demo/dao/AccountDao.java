package com.example.demo.dao;

public interface AccountDao {

    void decreaseMoney(Integer userid, Integer money);

    void addMoney(Integer userid, Integer money);

}
