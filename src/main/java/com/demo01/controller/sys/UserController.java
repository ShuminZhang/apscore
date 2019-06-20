package com.demo01.controller.sys;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo01.entity.Message;
import com.demo01.entity.PageOptions;
import com.demo01.entity.sys.User;
import com.demo01.service.sys.IUserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
	@Autowired
	private IUserService userService;
	
//	@RequiresRoles(value={"teacher"},logical = Logical.OR)
	@RequestMapping("/test")
	public String test() {
		return "Hello, my first spring boot api!";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public List<User> getUsers(@RequestBody PageOptions opt){
		log.info(""+opt.getPageSize());
		return userService.getUsers();
	}
	
//	@RequiresRoles(value={"admin","user"},logical = Logical.OR)
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public Message insertUser(@RequestBody User user) {
		userService.insertUser(user);
		log.info(user.getUsername());
		return new Message(0,"insert user");
	}

//	@RequiresRoles(value={"admin","sutdent"},logical = Logical.OR)
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
