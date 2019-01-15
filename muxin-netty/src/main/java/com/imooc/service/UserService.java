package com.imooc.service;

import com.imooc.pojo.Users;

public interface UserService {
	
	/**
	 * 判断用户名是否存在
	 * @param username
	 * @return
	 */
	public boolean queryUsernameIsExist(String username);
	
	/**
	 * 查询用户是否存在
	 * @param username
	 * @param pwd
	 * @return
	 */
	public Users queryUserForLogin(String username, String pwd);
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	public Users saveUser(Users user);
	
	/**
	 * 修改用户记录
	 * @param user
	 * @return
	 */
	public Users updateUserInfo(Users user);
	
	
}
