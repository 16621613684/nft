package com.example.nft.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nft.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where (username = #{username} or phone=#{phone} or email=#{email})and password = #{password}")
    public User login(User user);

    public void addUser(User user);
    @Select("select * from user where id=#{id}")
    User selectUserById(Integer id);

    @Insert("INSERT INTO `user` (`id`, `username`, `phone`,`password`) VALUES (0,#{username},#{phone},#{password});")
    void addUserByPhone(User user);
    @Insert("INSERT INTO `user` (`id`, `username`, `email`,`password`) VALUES (0,#{username},#{email},#{password});")
    void addUserByEmail(User user);
}
