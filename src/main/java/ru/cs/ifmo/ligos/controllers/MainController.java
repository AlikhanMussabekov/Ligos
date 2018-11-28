package ru.cs.ifmo.ligos.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.cs.ifmo.ligos.db.entities.UserEntity;
import ru.cs.ifmo.ligos.db.services.UserService;

@Controller
public class MainController {

	Logger logger = LoggerFactory.getLogger(MainController.class);

	private final UserService userService;

	public MainController(UserService userService){
		this.userService = userService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public String get(){

		UserEntity user = new UserEntity();
		user.setId(1);
		user.setEmail("email");
		user.setPassword("password");
		user.setName("Alikhan");
		userService.save(user);

		return "hello";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseBody
	public String getUser(){

		UserEntity user = userService.getUserByEmail("email");

		return user.getName();
	}

}
