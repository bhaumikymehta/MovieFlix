package com.bhaumik.movieFlix.api.repository;

import java.util.List;

import com.bhaumik.movieFlix.api.entity.User;

public interface UserRepository {

	public List<User> findAllUser();
	public User createUser(User user);
	public User findUserByEmail(String email);
	public User findUserByEmailAndPassword(String email,String password);

	public User findUserById(String userId);
	public User updateUser(User user);
	public void deleteUser(String userId);
}
