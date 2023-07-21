package com.zzyy.commerce.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzyy.commerce.db.entity.TransactionHistory;
import com.zzyy.commerce.db.repository.TransactionHistoryMapper;
import com.zzyy.commerce.service.ITransactionHistoryService;
import org.springframework.stereotype.Service;

@Service
public class TransactionHistoryServiceImpl extends ServiceImpl<TransactionHistoryMapper, TransactionHistory> implements ITransactionHistoryService {
}
