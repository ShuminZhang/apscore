package com.demo01.service.sys;

import java.util.List;

import com.demo01.entity.sys.User;

public interface IUserService {
	public List<User> getUsers();
	public User getUserByUsrPwd(String username,String password);
	public User getUserByUsername(String username);
	public String getMenusByUsername(String username) throws Exception;
}
