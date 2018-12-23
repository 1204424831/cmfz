package com.baizhi.service;

import com.baizhi.entity.Chapter;
import com.baizhi.mapper.ChapterDao;
import com.baizhi.util.RandomSaltUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDao chapterDao;
    @Autowired
    private RandomSaltUtil randomSaltUtil;

    @Override
    public void insetOne(Chapter a) {
        String uuid = randomSaltUtil.getCode();
        a.setId(uuid);
        chapterDao.insert(a);
    }

    @Override
    public Chapter getOne(String id) {
        Chapter c = chapterDao.selectByPrimaryKey(id);
        return c;
    }
}
