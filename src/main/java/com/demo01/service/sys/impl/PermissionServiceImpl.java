package com.demo01.service.sys.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo01.entity.sys.Permission;
import com.demo01.mapper.sys.PermissionMapper;
import com.demo01.service.sys.IPermissionService;

@Service
public class PermissionServiceImpl implements IPermissionService {
	
	@Autowired
	private PermissionMapper permissionMapper;
	
	@Override
	public List<Permission> getPermissionByRoles(String roles) {
		return permissionMapper.getPermissionByRoles(roles);
	}

	@Override
	public List<Permission> getSuperPermissions() {
		return permissionMapper.getSuperPermissions();
	}
}
