package com.imooc.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UsersBO;
import com.imooc.service.UserService;
import com.imooc.utils.FastDFSClient;
import com.imooc.utils.FileUtils;
import com.imooc.utils.IMoocJSONResult;
import com.imooc.utils.MD5Utils;

@RestController
@RequestMapping("u")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FastDFSClient fastDFSClient;

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
	
	/**
	 * @Description: 上传用户头像
	 */
	@PostMapping("/uploadFaceBase64")
	public IMoocJSONResult uploadFaceBase64(@RequestBody UsersBO userBO) throws Exception {
		
		// 获取前端传过来的base64字符串, 然后转换为文件对象再上传
		String base64Data = userBO.getFaceData();
		String userFacePath = "C:\\" + userBO.getUserId() + "userface64.png";
		FileUtils.base64ToFile(userFacePath, base64Data);
		
		// 上传文件到fastdfs
		MultipartFile faceFile = FileUtils.fileToMultipart(userFacePath);
		String url = fastDFSClient.uploadBase64(faceFile);
		System.out.println(url);
		
//		"dhawuidhwaiuh3u89u98432.png"
//		"dhawuidhwaiuh3u89u98432_80x80.png"
		
		// 获取缩略图的url
		String thump = "_80x80.";
		String arr[] = url.split("\\.");
		String thumpImgUrl = arr[0] + thump + arr[1];
		
		// 更细用户头像
		Users user = new Users();
		user.setId(userBO.getUserId());
		user.setFaceImage(thumpImgUrl);
		user.setFaceImageBig(url);
		
		Users result = userService.updateUserInfo(user);
		
		return IMoocJSONResult.ok(result);
	}

}
