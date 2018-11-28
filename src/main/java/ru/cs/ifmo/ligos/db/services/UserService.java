package ru.cs.ifmo.ligos.db.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cs.ifmo.ligos.db.entities.UserEntity;
import ru.cs.ifmo.ligos.db.repositories.UserRepository;

@Service
public class UserService {

	private final UserRepository repository;

	@Autowired
	UserService(UserRepository repository){
		this.repository = repository;
	}

	public UserEntity getUserByEmail(String email){
		return repository.findByEmail(email);
	}

	public void save(UserEntity user){
		repository.save(user);
	}

}
