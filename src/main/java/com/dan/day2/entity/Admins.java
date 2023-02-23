package com.dan.day2.entity;

import java.io.Serializable;

public class Admins extends BaseEntity implements Serializable {

    /*
     * 主键
     */
    private Integer id;

    /*
     * 用户名
     */
    private String username;

    /*
     * 密码
     */
    private String pwd;

    /*
     * 添加时间
     */
    private String addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    @Override
    public String toString() {
        return "Admins [id=" + id + ", username=" + username + ", pwd=" + pwd + ", addtime=" + addtime + "]";
    }

}
