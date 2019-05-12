package com.demo01.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.demo01.entity.sys.Permission;

@Mapper
public interface PermissionMapper {
	
	@Select(
		"select DISTINCT menuid,id,roleid,`enable` from permission"
		+" where roleid in (#{roles}) and enable=1;"
	)
	public List<Permission> getPermissionByRoles(@Param("roles") String roles);

	@Select(
		"select DISTINCT menuid,id,roleid,`enable` from permission"
		+" where enable=1;"
	)
	public List<Permission> getSuperPermissions();
	
}

