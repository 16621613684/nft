package com.example.nft.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nft.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where (username = #{username} or phone=#{username} or email=#{username})and password = #{password}")
    public User login(User user);

    public void addUser(User user);
    @Select("select * from user where id=#{id}")
    User selectUserById(Integer id);

    @Insert("INSERT INTO `user` (`id`, `username`, `phone`,`password`, `balance`) VALUES (0,#{username},#{phone},#{password},1000);")
    void addUserByPhone(User user);
    @Insert("INSERT INTO `user` (`id`, `username`, `email`,`password`, `balance`) VALUES (0,#{username},#{email},#{password},1000);")
    void addUserByEmail(User user);

    void addBalanceById(Double change,Integer id);
    void minusBalanceById(Double change,Integer id);

    String getUsernameById(@Param("id") Integer id);

}
