package com.bhaumik.movieFlix.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bhaumik.movieFlix.api.entity.User;
import com.bhaumik.movieFlix.api.exception.UserAlreadyExistsException;
import com.bhaumik.movieFlix.api.exception.UserNotFoundException;
import com.bhaumik.movieFlix.api.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService service;

	//retriving all users
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<User> findAllUser() {
		
		return service.findAllUser();

	}

	//creating new user
	@RequestMapping(method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public User createUser(@RequestBody User user) throws UserAlreadyExistsException {
		return service.createUser(user);
	}

	@RequestMapping(value="/email/{email}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public User findUserByEmail(@PathVariable("email") String email) throws UserNotFoundException {
		
		System.out.println("inside find user by email and email is :"+email);
		return service.findUserByEmail(email);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET,consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public User findUserById(@PathVariable("id") String userId) throws UserNotFoundException {
		
		System.out.println("inside find user by id and id is :"+userId);
		return service.findUserById(userId);

	}


	@RequestMapping(value = "/{id}", method = RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void updateUser(@PathVariable("id") String userId,@RequestBody User user) throws UserNotFoundException {
		
		service.updateUser(userId, user);

	}
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void deleteUser(@PathVariable("id") String userId) throws UserNotFoundException {
		
		service.deleteUser(userId);

	}
//	@RequestMapping(value="/{email}/{password}",method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public User loginUser(@PathVariable("email")  String email,@PathVariable("password") String password) throws UserNotFoundException {
//
//		return service.loginUser(email, password);
//
//	}
	@RequestMapping(value="/loginForm",method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public User loginUser(@RequestParam("email") String email,@RequestParam("password") String password) throws UserNotFoundException {

		return service.loginUser(email, password);

	}
}
