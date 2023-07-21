package com.zzyy.commerce.db.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzyy.commerce.db.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
