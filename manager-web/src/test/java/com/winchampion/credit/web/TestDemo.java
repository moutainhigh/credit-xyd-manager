package com.winchampion.credit.web;

import com.winchampion.credit.user.domain.UserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@RestController()
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDemo {
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void test() {
        redisTemplate.opsForValue().set("a", "b");
        System.out.println(redisTemplate.opsForValue().get("a"));
    }

    @Test
    public void test2(){
        List<UserDO> userDOList = new ArrayList<>();
        UserDO userDO = new UserDO();
        userDO.setUserId(1L);
        userDO.setUsername("zhangsan");

        UserDO userDO1 = new UserDO();
        userDO1.setUserId(1L);
        userDO1.setUsername("zhangsan1");

        UserDO userDO2 = new UserDO();
        userDO2.setUserId(1L);
        userDO2.setUsername("zhangsan2");

        UserDO userDO3 = new UserDO();
        userDO3.setUserId(1L);
        userDO3.setUsername("zhangsan3");
        userDOList.add(userDO);
        userDOList.add(userDO1);
        userDOList.add(userDO2);
        userDOList.add(userDO3);

        userDOList.forEach(u-> System.out.println(u.getUserId()+"" +u.getUsername()));

        String[] strs = new String[10];
        Stream<String> stream = Arrays.stream(strs);
        System.out.printf("strem为；", stream);
    }
}
