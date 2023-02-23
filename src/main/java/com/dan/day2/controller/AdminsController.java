package com.dan.day2.controller;


import com.dan.day2.mapper.AdminsMapper;
import com.dan.day2.entity.Admins;


import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.dan.day2.service.AdminsService;
import com.dan.day2.util.ResultCode;
import com.dan.day2.util.WebResult;

@RestController
@RequestMapping("/admins")
public class AdminsController {

    @Autowired
    private AdminsService adminsService;

    @Autowired
    private AdminsMapper adminsMapper;

    


    @RequestMapping("/find")
    public Admins findById(Long id){
        //System.out.println("/find/id=" + id);
        Admins admins;
        if(id != null){
            admins =adminsMapper.findById(id);
        }else{
            admins = new Admins();
            admins.setId(0);
            admins.setPwd("11111");
            admins.setUsername("haha");
        }
        return admins;
    }

    @RequestMapping(method = RequestMethod.POST,path = "/add")
    public Admins addAdmin(Admins admins){
        Admins result = null;
        if(admins != null && !StringUtils.isEmpty(admins.getUsername()) && !StringUtils.isEmpty(admins.getPwd())){
            Admins adminsInDB = adminsMapper.findByUsername(admins.getUsername());
            if(adminsInDB == null){
                adminsMapper.insert(admins);
                result = adminsMapper.findByUsername(admins.getUsername());
            }
        }
        if(result == null){
            result = new Admins();
            result.setId(0);
            result.setPwd("11111");
            result.setUsername("haha");
        }
        return result;
    }

    @PostMapping("/login")
    public WebResult login(Admins admins){

        WebResult webResult = new WebResult();

        Admins loginAdmin = adminsService.login(admins);

        if(loginAdmin != null){
            webResult.setCode(0);
            webResult.setMessage("登录成功");
            webResult.setData(loginAdmin);
        }else{
            webResult.setCode(-1);
            webResult.setMessage("登录失败");
        }

        return webResult;
    }

    @RequestMapping("findByPage")
    public WebResult findByPage(Admins admins){

        WebResult webResult = new WebResult();
        if(admins.getCurrentPage() != null && admins.getPageSize() != null){
            webResult.setCode(ResultCode.Succeed.value());
            webResult.setMessage("返回管理员列表");
            webResult.setCurrentPage(admins.getCurrentPage());
            webResult.setPageSize(admins.getPageSize());
            webResult.setData(adminsService.findByPage(admins));
        }else{
            webResult.setCode(ResultCode.Failure.value());
            webResult.setMessage("缺失当前页或页面数量入参");
        }
        return webResult;

    }


    
}
