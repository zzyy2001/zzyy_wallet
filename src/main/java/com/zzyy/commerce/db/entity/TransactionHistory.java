package com.zzyy.commerce.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransactionHistory {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long userId;

    //交易类型01-支出  02-收入  03-退款
    private String transactionType;
    private BigDecimal amount;
    private Date createTime;
    private Long relatedId;
}
