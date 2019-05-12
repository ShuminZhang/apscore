package com.demo01.service.sys;

import java.util.List;

import com.demo01.entity.sys.Permission;

public interface IPermissionService {
	
	public List<Permission> getPermissionByRoles(String roles);
	public List<Permission> getSuperPermissions();

}
