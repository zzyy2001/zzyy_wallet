package com.zzyy.commerce.controller;

import com.zzyy.commerce.db.entity.TransactionHistory;
import com.zzyy.commerce.request.TransactionRequest;
import com.zzyy.commerce.response.ResponseEntity;
import com.zzyy.commerce.service.impl.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/balance/{userId}")
    public ResponseEntity<BigDecimal> getWalletBalance(@PathVariable("userId") Long userId) {
        BigDecimal balance = walletService.getWalletBalance(userId);
        return new ResponseEntity<>(HttpStatus.OK, balance);
    }

    @PostMapping("/expense")
    public ResponseEntity<String> makeExpense(@RequestBody TransactionRequest transactionRequest) {
        walletService.makeExpense(transactionRequest.getUserId(), transactionRequest.getAmount());
        return new ResponseEntity<>(HttpStatus.OK, "Expense made successfully");
    }

    @PostMapping("/refund")
    public ResponseEntity<String> makeRefund(@RequestBody TransactionRequest transactionRequest) {
        walletService.makeRefund(transactionRequest.getUserId(), transactionRequest.getAmount(), transactionRequest.getRefundId());
        return new ResponseEntity<>(HttpStatus.OK,"Refund made successfully");
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<TransactionHistory>> getTransactionHistory(@PathVariable("userId") Long userId) {
        List<TransactionHistory> transactionHistory = walletService.getTransactionHistory(userId);
        return new ResponseEntity<>(HttpStatus.OK, transactionHistory);
    }
}
