package com.youfan.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youfan.model.User;
import com.youfan.service.UserService;

@Controller
public class UserControl {

	@Autowired
    UserService userServive;


    @RequestMapping("/getUserInfo")
    @ResponseBody
    public User getUserInfo() {
        User user = userServive.findUserInfo();
        if(user!=null){
            System.out.println("user.getName():"+user.getName());
        }
        return user;
    }
    
    @RequestMapping("/hello")
	public Object hello() {
		return "hello springboot..121";
	}

}
