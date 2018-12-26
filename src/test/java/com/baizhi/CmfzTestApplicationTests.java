package com.baizhi;

import com.baizhi.util.Md5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzTestApplicationTests {
    @Autowired
    private Md5Util md5Util;

    @Test
    public void contextLoads1() {
        //字符串转char数组
        char[] str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456".toCharArray();

        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            //随机生成0到str长度之间的数字做为下标
            int randomIndex = random.nextInt(str.length);
            //追加到sb对象
            sb.append(str[randomIndex]);
        }
        String salt = sb.toString();
        System.out.println(salt);
        String password = md5Util.encryption("123456" + salt);
        System.out.println(password);
    }

}

