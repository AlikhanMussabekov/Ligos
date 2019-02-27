package ru.cs.ifmo.ligos.db.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.cs.ifmo.ligos.db.entities.UsersEntity;
import ru.cs.ifmo.ligos.db.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

	private final UserRepository repository;

	@Autowired
	UserService(UserRepository repository){
		this.repository = repository;
	}

	public UsersEntity getUserByEmail(String email){
		return repository.findByEmail(email);
	}

	public void save(UsersEntity user){
		repository.save(user);
	}


	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

		UsersEntity user = repository.findByEmail(s);

		if(user == null){
			throw new UsernameNotFoundException("User not found");
		}

		List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));

		return new User(user.getEmail(),user.getPassword(),authorities);
	}
}
