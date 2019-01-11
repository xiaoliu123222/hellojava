package com.imooc.service.impl;

import javax.print.attribute.standard.Sides;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.service.UserService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UsersMapper usersMapper;
	
	@Autowired
	private Sid sid;

	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean queryUsernameIsExist(String username) {
		
		Users user = new Users();
		user.setUsername(username);
		
		Users result = usersMapper.selectOne(user);
		
		//如果用户存在，返回true；如果不存在，返回fales
		return result != null ? true : false;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Users queryUserForLogin(String username, String pwd) {
		
		Example userExample = new Example(Users.class);
		Criteria criteria = userExample.createCriteria();
		
		criteria.andEqualTo("username",username);
		criteria.andEqualTo("password",pwd);
		
		Users result = usersMapper.selectOneByExample(userExample);
		
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Users saveUser(Users user) {
		
		String userId = sid.nextShort();
		user.setQrcode("test");
		user.setId(userId);
		usersMapper.insert(user);
		
		return user;
	}
	
	


}
