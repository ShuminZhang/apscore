package com.demo01.service.sys.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo01.entity.sys.Role;
import com.demo01.mapper.sys.RoleMapper;
import com.demo01.service.sys.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {
	
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public List<Role> getAllRoles() {
		return roleMapper.getAllRoles();
	}

	@Override
	public Role getRoleById(String roleid) {
		return roleMapper.getRoleById(roleid);
	}

}
