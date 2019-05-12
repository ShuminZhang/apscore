package com.demo01.service.sys.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo01.entity.sys.Menu;
import com.demo01.mapper.sys.MenuMapper;
import com.demo01.service.sys.IMenuService;

@Service
public class MenuServiceImpl implements IMenuService {
	
	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	public Menu getMenuById(String id) {
		return menuMapper.getMenuById(id);
	}

}
