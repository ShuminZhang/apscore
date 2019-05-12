package com.demo01.mapper.sys;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.demo01.entity.sys.Menu;

@Mapper
public interface MenuMapper {
	
	@Select("select * from menu where type=1 and id=#{id}")
	public Menu getMenuById(@Param("id") String id);
	
}

