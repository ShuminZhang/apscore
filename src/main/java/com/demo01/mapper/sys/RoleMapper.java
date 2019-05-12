package com.demo01.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.demo01.entity.sys.Role;

@Mapper
public interface RoleMapper {

	@Select("select * from role")
	public List<Role> getAllRoles();

	@Select("select * from role where id=#{id}")
	public Role getRoleById(@Param("id") String id);

}

