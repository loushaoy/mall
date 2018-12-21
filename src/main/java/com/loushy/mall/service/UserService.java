package com.loushy.mall.service;


import com.loushy.mall.model.File;
import com.loushy.mall.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface UserService {

    List<User> selectUser();

    void testThread(Integer i);
}
