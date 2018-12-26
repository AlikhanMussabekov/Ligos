package ru.cs.ifmo.ligos.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import ru.cs.ifmo.ligos.db.dto.UserDto;
import ru.cs.ifmo.ligos.db.entities.UserEntity;
import ru.cs.ifmo.ligos.db.services.UserService;

@Controller
public class MainController {

	Logger logger = LoggerFactory.getLogger(MainController.class);

	private final UserService userService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public MainController(UserService userService,
						  BCryptPasswordEncoder bCryptPasswordEncoder){
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public String home(){

		return "home";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseBody
	public String getUser(){

		UserEntity user = userService.getUserByEmail("email");

		return user.getName();
	}

	@RequestMapping(value="/chooseLogin")
	public String chooseLogin(){
		return "chooseLogin.html";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseBody
	public void login(){

	}

	/*@RequestMapping(value = "/user/registration", method = RequestMethod.GET)
	@ResponseBody
	public String showRegistrationForm(WebRequest request, Model model){

		UserDto userDto = new UserDto();
		model.addAttribute("user", userDto);
		return "registration";
	}*/

	@PostMapping("/user/registration")
	@ResponseBody
	public String registration(@RequestBody UserEntity user){
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userService.save(user);
		return "OK";
	}

}
