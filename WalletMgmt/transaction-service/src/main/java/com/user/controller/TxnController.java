package com.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.user.dto.TransactionCreateRequest;
import com.user.service.TxntranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TxnController {

    @Autowired
    TxntranService txntranService;
    @PostMapping("/transaction")
    public String transact(@RequestBody @Valid TransactionCreateRequest request) throws JsonProcessingException {
        return txntranService.transact(request);
    }
}
