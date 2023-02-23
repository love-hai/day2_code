package com.dan.day2.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.dan.day2.entity.Admins;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import java.util.List;

@Mapper
public interface AdminsMapper {

    @Select("select id,username,pwd,addtime from admins where id = #{id}")
    Admins findById(long id);

    @Select("select id,username,pwd,addtime from admins where username = #{username}")
    Admins findByUsername(String username);

    @Insert("INSERT INTO admins (username, pwd, addtime) VALUES(#{username}, #{pwd}, CURRENT_TIMESTAMP())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Admins admins);


    @Select("select id,username,pwd,addtime from admins where username = #{username} and pwd = #{pwd}")
    Admins findByUsernameAndPassword(Admins admins);

    @Select("select count(1) from admins")
    int findCount();

    @Select("select id, username,pwd,addtime from admins order by id desc limit #{start},#{limit} " )
    List<Admins> findByPage(Admins admins);

}
