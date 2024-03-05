package com.example.demo.controller;

//練習@ransactional的使用

import com.example.demo.model.dto.TransferRequest;
import com.example.demo.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactional")
public class TransactionalController {

    private final static Logger logger = LoggerFactory.getLogger(TransactionalController.class);

    @Autowired
    private AccountService accountService;

    @PostMapping("/transfer")
    public String transfer(@RequestBody TransferRequest transferRequest) {

        accountService.transfer(
                transferRequest.getFrom(),
                transferRequest.getTo(),
                transferRequest.getMoney()
        );

        return "轉帳成功!";
    }
}
