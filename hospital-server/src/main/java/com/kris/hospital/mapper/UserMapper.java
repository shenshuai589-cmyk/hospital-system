package com.kris.hospital.mapper;

import com.kris.hospital.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    //新增用户
    int insert(User user);

    User findById(Long id);


    User findByUsername(String username);

    int updatePassword(
            @Param("id") Long id,
            @Param("password") String password
    );

    int deleteById(Long id);

}
