package com.imooc.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UsersBO;
import com.imooc.service.UserService;
import com.imooc.utils.IMoocJSONResult;
import com.imooc.utils.MD5Utils;

@RestController
@RequestMapping("u")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/registOrLogin")
	public IMoocJSONResult registOrLogin(@RequestBody Users user) throws Exception {
		
		if(StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
			return IMoocJSONResult.errorMsg("用户名或密码不能为空");
		}
		
		boolean usernameIsExist = userService.queryUsernameIsExist(user.getUsername());
		
		Users userResult = null;
		
		if(usernameIsExist) {
			//登录
			userResult = userService.queryUserForLogin(user.getUsername(), MD5Utils.getMD5Str(user.getPassword()));
			
			if(userResult == null) {
				return IMoocJSONResult.errorMsg("用户名或密码不正确");
			}
			
		} else {
			//注册
			
			user.setNickname(user.getUsername());
			user.setFaceImage("");
			user.setFaceImageBig("");
			user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
			userResult = userService.saveUser(user);
		}

		return IMoocJSONResult.ok(userResult);
	}
	
	@PostMapping("/setNickname")
	public IMoocJSONResult setNickname(@RequestBody UsersBO userBO) throws Exception {
		Users user = new Users();
		user.setId(userBO.getUserId());
		user.setNickname(userBO.getNickname());
		
		Users result = userService.updateUserInfo(user);
		
		return IMoocJSONResult.ok(result);
		
	}

}
