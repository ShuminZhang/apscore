package com.demo01.service.sys.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo01.entity.sys.Menu;
import com.demo01.entity.sys.MenuTree;
import com.demo01.entity.sys.Permission;
import com.demo01.entity.sys.Role;
import com.demo01.entity.sys.User;
import com.demo01.mapper.sys.UserMapper;
import com.demo01.service.sys.IMenuService;
import com.demo01.service.sys.IPermissionService;
import com.demo01.service.sys.IRoleService;
import com.demo01.service.sys.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private IPermissionService permissionService;
	@Autowired
	private IMenuService menuService;
	@Autowired
	private IRoleService roleService;
	
	@Override
	public List<User> getUsers() {
		return userMapper.getUsers();
	}

	@Override
	public User getUserByUsrPwd(String username, String password) {
		return userMapper.getUserByUsrPwd(username, password);
	}

	@Override
	public User getUserByUsername(String username) {
		return userMapper.getUserByUsername(username);
	}

	@Override
	public String getMenusByUsername(String username) throws Exception {
		Map<String,MenuTree> mt = new HashMap<String,MenuTree>();

		User user = getUserByUsername(username);
		
		List<Permission> permissions = new ArrayList<Permission>();
		String roles  = user.getRoleid().replace(':', ',');
		Role role = roleService.getRoleById(roles);
		log.info(roles);
		if(role!=null && role.getName().equals("super")) {
			permissions = permissionService.getSuperPermissions();
		}else {
			permissions = permissionService.getPermissionByRoles(roles);
		}
		log.info(">>>>"+permissions.size());
		
		permissions.forEach(item->{
			Menu menu = menuService.getMenuById(item.getMenuid());
			MenuTree mtItem = new MenuTree(menu);
			String ids[] = mtItem.getId().split(":");
			if(mt.get(ids[0])==null) {
				if(ids.length==1) {
					mt.put(ids[0], mtItem);
				}else {
					MenuTree mtt = new MenuTree();
					mtt.setId(ids[0]);
					mt.put(ids[0], mtt);
					mt.get(ids[0]).getSubTree().put(ids[1], mtItem);
				}
			}else {
				Map<String,MenuTree> imt = mt.get(ids[0]).getSubTree();
				for(int i=1;i<ids.length-1;i++) {
					imt = imt.get(ids[i]).getSubTree();
				}
				imt.put(ids[ids.length-1], mtItem);
			}
		});
		
		return new ObjectMapper().writeValueAsString(mt); //Map => JSON
	}

}
