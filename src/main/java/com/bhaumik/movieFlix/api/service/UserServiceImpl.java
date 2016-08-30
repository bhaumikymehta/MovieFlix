package com.bhaumik.movieFlix.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bhaumik.movieFlix.api.entity.User;
import com.bhaumik.movieFlix.api.exception.UserNotFoundException;
import com.bhaumik.movieFlix.api.repository.UserRepository;



@Service
@Transactional
	public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return repository.findAllUser();
	}

	@Override
	public User createUser(User user) throws com.bhaumik.movieFlix.api.exception.UserAlreadyExistsException {
		// TODO Auto-generated method stub
		User existing = repository.findUserByEmail(user.getEmail());
		if (existing != null) {

			// throw exception
			throw new com.bhaumik.movieFlix.api.exception.UserAlreadyExistsException();
		}

		return repository.createUser(user);
	}

	@Override
	public User findUserByEmail(String email) throws UserNotFoundException {
		// TODO Auto-generated method stub
		User user = repository.findUserByEmail(email);
		if (user == null) {

			// throw exception
			throw new UserNotFoundException();
			
		}

		return user;
	}

	@Override
	public User findUserById(String userId) throws UserNotFoundException{
		User user = repository.findUserById(userId);
		if (user == null) {

			// throw exception
			throw new UserNotFoundException();
		}

		return user;
	}

	@Override
	public User updateUser(String userId, User user)throws UserNotFoundException {
		User existing=repository.findUserById(userId);
		if(existing !=null){
			
			//throw exception
			throw new UserNotFoundException();
		}
		
		return repository.updateUser( user);
	}

	@Override
	public void deleteUser(String userId) throws UserNotFoundException{
		User user = repository.findUserById(userId);
		if (user == null) {

			// throw exception

			throw new UserNotFoundException();
		}

		repository.deleteUser(userId);
	}

	@Override
	public User loginUser(String email, String password) {
		User user = repository.findUserByEmailAndPassword(email, password);
		if (user == null) {

			// throw exception
			return null;
		}

		return user;
	
	}

}
