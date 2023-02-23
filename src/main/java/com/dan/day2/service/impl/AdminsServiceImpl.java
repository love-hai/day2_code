package com.dan.day2.service.impl;

import com.dan.day2.entity.Admins;
import com.dan.day2.service.AdminsService;
import com.dan.day2.util.PageHelper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dan.day2.mapper.AdminsMapper;

@Service
public class AdminsServiceImpl implements AdminsService {

    @Autowired
    private AdminsMapper adminsMapper;

    /**
     * 
     */
    public Admins login(Admins admins) {

        return adminsMapper.findByUsernameAndPassword(admins);

    }

    @Override
    public int getTotalPages(Admins admins) {

        // 查询总数据数量
        int totalPages = 0;
        int totalCount = adminsMapper.findCount();
        if (totalCount > 0) {
            PageHelper pageHelper = new PageHelper();
            pageHelper.setPageSize(admins.getPageSize());
            pageHelper.setRecordCount(totalCount);
            totalPages = pageHelper.getTotalPages();
        }
        return totalPages;
    }

    @Override
    public List<Admins> findByPage(Admins admins) {

        List<Admins> adminsList;
        int totalCount = adminsMapper.findCount();
        if (totalCount > 0) {
            PageHelper pageHelper = new PageHelper();
            pageHelper.setPageSize(admins.getPageSize());
            pageHelper.setRecordCount(totalCount);
            admins.setStart(pageHelper.getOffset(admins.getCurrentPage()));
            admins.setLimit(admins.getPageSize());
            adminsList = adminsMapper.findByPage(admins);
        } else {
            adminsList = new ArrayList<Admins>();
        }

        return adminsList;

    }

}
