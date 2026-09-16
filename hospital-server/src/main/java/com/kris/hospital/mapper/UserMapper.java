package com.kris.hospital.mapper;

import com.kris.hospital.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    int insert(User user);

    User findById(Long id);

    User findByUsername(String username);

    int updatePassword(
            @Param("id") Long id,
            @Param("password") String password
    );

}
