package com.example.nft.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nft.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where username = #{username} and password = #{password}")
    public User login(User user);

    public void addUser(User user);
    @Select("select * from user where id=#{id}")
    User selectUserById(Integer id);


}
