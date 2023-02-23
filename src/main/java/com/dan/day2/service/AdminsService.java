package com.dan.day2.service;

import com.dan.day2.entity.Admins;
import java.util.List;

public interface AdminsService {

    Admins login(Admins admins);

    /**
     * 查询总数据页数
     * @param admins
     * @return
     */
    int getTotalPages(Admins admins);

    /**
     * 分页返回数据
     * @param admins
     * @return
     */
    List<Admins> findByPage(Admins admins);
    
}
