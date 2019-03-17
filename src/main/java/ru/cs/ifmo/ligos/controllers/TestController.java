package ru.cs.ifmo.ligos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.cs.ifmo.ligos.db.entities.UsersEntity;
import ru.cs.ifmo.ligos.db.repositories.UserRepository;
import ru.cs.ifmo.ligos.exception.CustomException;
import ru.cs.ifmo.ligos.security.oauth2.UserPrincipal;

@RestController
public class TestController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/user/me")
	@PreAuthorize("hasRole('ROLE_USER')")
	public UsersEntity getCurrentUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
		return userRepository.findById(Long.valueOf(userPrincipal.getId()))
				.orElseThrow(() -> new CustomException("User not found", HttpStatus.BAD_REQUEST));
	}
}
