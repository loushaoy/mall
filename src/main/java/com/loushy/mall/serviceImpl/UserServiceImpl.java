package com.loushy.mall.serviceImpl;

import com.loushy.mall.mapper.UserMapper;
import com.loushy.mall.model.User;
import com.loushy.mall.service.UserService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> selectUser() {
        return userMapper.selectUser();
    }


    @Async
    public void testThread(Integer i){
            System.out.println("线程名称："+Thread.currentThread().getName()+i);
            try{
                Thread.sleep(2000);
            }catch (Exception e){

            }

    }

}
