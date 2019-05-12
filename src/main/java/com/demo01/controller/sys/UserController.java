package com.demo01.controller.sys;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo01.entity.sys.User;
import com.demo01.service.sys.IUserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
	@Autowired
	private IUserService userService;
	
	@RequiresRoles(value={"teacher"},logical = Logical.OR)
	@RequestMapping("/test")
	public String test() {
		return "Hello, my first spring boot api!";
	}
	
	@RequestMapping("/list")
	public List<User> getUsers(){
		return userService.getUsers();
	}
	
	@RequiresRoles(value={"admin","user"},logical = Logical.OR)
	@RequestMapping("/insert")
	public String insertUser() {
		return "insert user";
	}

	@RequiresRoles(value={"admin","sutdent"},logical = Logical.OR)
	@RequestMapping("/delete")
	public String deleteUser() {
		return "delete user";
	}
	
	@RequiresPermissions(value={"add","update"},logical = Logical.AND)
	@RequestMapping("/update")
	public String updateUser() {
		return "update user";
	}
	
	
	@RequestMapping("/login")
	public String doLogin(String username,String password) {
		if(username==null || password==null) {
			return "username or password is blank!";
		}
		Subject subject = SecurityUtils.getSubject();
		AuthenticationToken token = new UsernamePasswordToken(username,password);
		try {
			subject.login(token);
			return userService.getMenusByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
			return "login Failed!";
		}
	}
}
