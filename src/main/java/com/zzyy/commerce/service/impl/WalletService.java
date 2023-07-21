package com.zzyy.commerce.service.impl;

import com.zzyy.commerce.db.entity.TransactionHistory;
import com.zzyy.commerce.db.entity.User;
import com.zzyy.commerce.db.repository.TransactionHistoryMapper;
import com.zzyy.commerce.db.repository.UserMapper;
import com.zzyy.commerce.service.ITransactionHistoryService;
import com.zzyy.commerce.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TransactionHistoryMapper transactionHistoryMapper;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private TransactionHistoryServiceImpl transactionHistoryService;


    public BigDecimal getWalletBalance(Long userId) {
        User user = userMapper.selectById(userId);
        return user.getWalletBalance();
    }

    public void makeExpense(Long userId, BigDecimal amount) {

        boolean update = userService.lambdaUpdate()
                .eq(User::getId, userId)
                .setSql("wallet_balance = wallet_balance - " + amount)
                .apply("wallet_balance > 0 and wallet_balance-{0} >= 0", amount).update();

        if (update){
            addTransactionHistory(userId, "01", amount, null);
        }
    }

    public void makeRefund(Long userId, BigDecimal amount, Long refundId) {

        TransactionHistory history = transactionHistoryService.lambdaQuery()
                .eq(TransactionHistory::getId, refundId).one();
        BigDecimal balance = history.getAmount();
        if (history == null){
            return;
            //throw error;
        }
        List<TransactionHistory> historyList = transactionHistoryService.lambdaQuery()
                .eq(TransactionHistory::getRelatedId, refundId).list();
        for (TransactionHistory transactionHistory : historyList) {
            balance = balance.subtract(transactionHistory.getAmount());
        }
        if (balance.compareTo(amount) >= 0){
            addTransactionHistory(userId, "03", amount, refundId);
            userService.lambdaUpdate().eq(User::getId, userId)
                    .setSql("wallet_balance = wallet_balance + " + amount).update();
        }else {
            return;//throw error;
        }

    }

    public List<TransactionHistory> getTransactionHistory(Long userId) {
        return transactionHistoryService.lambdaQuery().eq(TransactionHistory::getUserId, userId).list();
    }

    private void addTransactionHistory(Long userId, String transactionType, BigDecimal amount, Long refundId) {
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setUserId(userId);
        transactionHistory.setTransactionType(transactionType);
        transactionHistory.setAmount(amount);
        transactionHistory.setCreateTime(new Date());
        transactionHistory.setRelatedId(refundId);
        transactionHistoryService.save(transactionHistory);
    }
}
