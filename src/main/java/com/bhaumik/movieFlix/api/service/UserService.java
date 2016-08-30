package com.bhaumik.movieFlix.api.service;

import java.util.List;

import com.bhaumik.movieFlix.api.entity.User;
import com.bhaumik.movieFlix.api.exception.UserAlreadyExistsException;
import com.bhaumik.movieFlix.api.exception.UserNotFoundException;

public interface UserService {
	public List<User> findAllUser();
	public User createUser(User user)throws UserAlreadyExistsException;
	public User findUserByEmail(String email)throws UserNotFoundException;

	public User loginUser(String email, String password)throws UserNotFoundException;
	public User findUserById(String userId)throws UserNotFoundException;
	public User updateUser(String userId,User user)throws UserNotFoundException;
	public void deleteUser(String userId)throws UserNotFoundException;
	
	
}
