package com.baizhi.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Md5Util {
    @Bean
    public String encryption(String src) {
        return DigestUtils.md5Hex(src);
    }

}
