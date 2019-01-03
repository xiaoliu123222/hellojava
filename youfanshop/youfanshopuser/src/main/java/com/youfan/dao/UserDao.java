package com.youfan.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.youfan.mapper.UserMappper;
import com.youfan.model.User;

@Component
public class UserDao {

	@Autowired
	UserMappper userMappper;
	
	public User findUserInfo() {
		return userMappper.findUserInfo();
	}

}
