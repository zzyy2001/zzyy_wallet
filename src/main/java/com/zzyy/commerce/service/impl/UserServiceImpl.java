package com.zzyy.commerce.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzyy.commerce.db.entity.User;
import com.zzyy.commerce.db.repository.UserMapper;
import com.zzyy.commerce.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
