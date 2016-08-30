package com.bhaumik.MovieFlix.api.service;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bhaumik.MovieFlix.api.TestConfig;
import com.bhaumik.movieFlix.api.entity.User;
import com.bhaumik.movieFlix.api.exception.UserAlreadyExistsException;
import com.bhaumik.movieFlix.api.exception.UserNotFoundException;
import com.bhaumik.movieFlix.api.repository.UserRepository;
import com.bhaumik.movieFlix.api.repository.UserRepositoryImpl;
import com.bhaumik.movieFlix.api.service.UserService;
import com.bhaumik.movieFlix.api.service.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class UserServiceTest {
	@Mock
	private UserRepository repository;

	@InjectMocks
	private UserService service=new UserServiceImpl();

	@Mock
	private TypedQuery<User> query;
	
	private User user;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		user = new User();
		user.setEmail("dummy@dummy.com");
		user.setPassword("abcd");
		user.setFirstName("Dummy");
		user.setLastName("User");
		user.setUserId(UUID.randomUUID().toString());
	}
	
	@Test(expected = UserAlreadyExistsException.class)
	public void testCreateUserException() throws UserAlreadyExistsException {
		Mockito.when(repository.findUserByEmail(user.getEmail())).thenReturn(user);
		service.createUser(user);
	}

	@Test
	public void testCreateUser() throws UserAlreadyExistsException {
		Mockito.when(repository.findUserByEmail(user.getEmail())).thenReturn(null);
		service.createUser(user);
		Mockito.verify(repository).createUser(user);
	}

	@Test
	public void testFindUserByEmail() throws UserNotFoundException {
		Mockito.when(repository.findUserByEmail(user.getEmail())).thenReturn(user);
		User actual = service.findUserByEmail(user.getEmail());
		Assert.assertEquals(user, actual);

	}

	@Test(expected = UserNotFoundException.class)
	public void testFindUserByEmailExcpetion() throws UserNotFoundException {
		Mockito.when(repository.findUserByEmail(user.getEmail())).thenReturn(null);
		service.findUserByEmail(user.getEmail());
	}

	@Test
	public void testFindUserById() throws UserNotFoundException {
		Mockito.when(repository.findUserById(user.getUserId())).thenReturn(user);
		User actual = service.findUserById(user.getUserId());
		Assert.assertEquals(user, actual);

	}

	@Test(expected = UserNotFoundException.class)
	public void testFindUserByIdExcpetion() throws UserNotFoundException {
		Mockito.when(repository.findUserById(user.getUserId())).thenReturn(null);
		service.findUserById(user.getUserId());
	}

	@Test
	public void testFindUserByEmailAndPassword() throws UserNotFoundException {
		Mockito.when(
				repository.findUserByEmailAndPassword(user.getEmail(),
						user.getPassword())).thenReturn(user);
		User actual = service.loginUser(user.getEmail(),
				user.getPassword());
		Assert.assertEquals(user, actual);
	}

	

	
	
}
