package com.demo01.service.sys;

import java.util.List;

import com.demo01.entity.sys.Role;

public interface IRoleService {
	
	public List<Role> getAllRoles();
	public Role getRoleById(String roleid);

}

