package com.demo01.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.demo01.entity.sys.User;

@Mapper
public interface UserMapper {
	
	@Select("select * from user")
	public List<User> getUsers();
	
	@Select("select * from user where (username=#{username} or email=#{username} ) and password=#{password}")
	public User getUserByUsrPwd(@Param("username") String username,@Param("password") String password);

	@Select("select * from user where (username=#{username} or email=#{username} )")
	public User getUserByUsername(@Param("username") String username);
	
	@Insert("insert into user (username,password,name,email,phone,roleid,notes,cdate)"+"value (#{username},#{password},#{name},#{email},#{phone},#{roleid},#{notes},#{cdate})")
	public void insert(User user);

	
}
