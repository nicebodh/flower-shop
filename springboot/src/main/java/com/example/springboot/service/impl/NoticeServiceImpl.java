package com.example.springboot.service.impl;

import cn.hutool.core.date.DateUtil;
import com.example.springboot.common.config.JwtTokenUtils;
import com.example.springboot.entity.Account;
import com.example.springboot.entity.Notice;
import com.example.springboot.mapper.NoticeMapper;
import com.example.springboot.service.NoticeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl  implements NoticeService {
    @Resource
    NoticeMapper noticeMapper;

    public void add(Notice notice) {
        notice.setTime(DateUtil.today());
        Account account= JwtTokenUtils.getCurrentUser();
        notice.setUserId(account.getId());
        noticeMapper.insert(notice);
    }

    public void deleteById(Integer id) {
        noticeMapper.deleteById(id);
    }

    public void update(Notice notice) {
        noticeMapper.update(notice);
    }

    public List<Notice> selectAll() {
        return noticeMapper.selectAll(null);
    }

    public PageInfo<Notice> selectPage(Notice notice, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Notice> noticeList = noticeMapper.selectAll(notice);
        return PageInfo.of(noticeList);
    }
}