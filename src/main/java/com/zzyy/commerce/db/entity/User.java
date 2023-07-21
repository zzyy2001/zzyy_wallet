package com.zzyy.commerce.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String bankAccount;
    private BigDecimal walletBalance;
}
