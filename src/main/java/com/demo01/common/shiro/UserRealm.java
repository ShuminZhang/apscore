package com.demo01.common.shiro;

import java.util.ArrayList;
import java.util.List;

import javax.naming.AuthenticationException;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.demo01.entity.sys.Role;
//import com.apscore.entity.sys.Role;
import com.demo01.entity.sys.User;
import com.demo01.service.sys.IRoleService;
//import com.apscore.service.sys.IRoleService;
import com.demo01.service.sys.IUserService;


public class UserRealm extends AuthorizingRealm{
	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		String username = (String)pc.getPrimaryPrincipal();
		User user = userService.getUserByUsername(username);
		
		List<String> roleList = new ArrayList<String>();
		String roles[] = user.getRoleid().split(":");
		Role role = roleService.getRoleById(roles[0]);
		
		if(role.getName().equals("super")) {
			List<Role> list = roleService.getAllRoles();
			list.forEach(item->{
				roleList.add(item.getName());
			});
		}else {
			for(int i=0;i<roles.length;i++) {
				role = roleService.getRoleById(roles[i]);
				roleList.add(role.getName());
			}
		}
		info.addRoles(roleList);
		
		List<String> permList = new ArrayList<String>();
		info.addStringPermissions(permList);
		
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken at) {
		AuthenticationInfo info = null;
		String username = ((UsernamePasswordToken)at).getUsername();
		String password = String.valueOf(((UsernamePasswordToken)at).getPassword());
		User user = userService.getUserByUsrPwd(username, password);
		if(user != null) {
			info = new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),user.getEmail());
		}
		return info;
	}
//   @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//	    return null;
//}

}
