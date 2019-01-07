package com.baizhi.util;

import com.baizhi.mapper.LuceneDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleConfig {
    @Bean
    public LuceneDao getLuceneDao() {
        return new LuceneDao();
    }
}
