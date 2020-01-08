package com.baizhi;

import com.baizhi.mapper.UserMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
    @Autowired
    UserMapper userMapper;

    @org.junit.Test
    public void name() {
        Integer integer = userMapper.queryUserByTime("0", 1);
        System.out.println(integer);
    }
}
