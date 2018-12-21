package com.loushy.mall.mapper;

import com.loushy.mall.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> selectUser();
}
