package com.bhaumik.MovieFlix.api.repository;

import java.util.Arrays;
import java.util.List;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bhaumik.MovieFlix.api.TestConfig;
import com.bhaumik.movieFlix.api.entity.User;
import com.bhaumik.movieFlix.api.repository.UserRepository;
import com.bhaumik.movieFlix.api.repository.UserRepositoryImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class UserRepositoryTest {
	
	@Mock
	private EntityManager em;

	@InjectMocks
	private UserRepository repository=new UserRepositoryImpl();

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
	
	@Test
	public void testFindAllUser(){
		
		Mockito.when(em.createQuery("SELECT u FROM User u ORDER BY u.email asc",User.class)).thenReturn(query);
		repository.findAllUser();
		Mockito.verify(query).getResultList();
		
	}
	@Test
	public void testFindUserById(){
		
		Mockito.when(em.find(User.class,user.getUserId())).thenReturn(user);
		User actual=repository.findUserById(user.getUserId());
		Assert.assertEquals(user, actual);
	}
	
	@Test
	public void testFindUserByEmail(){
		List<User> expected=Arrays.asList(user);
		Mockito.when(em.createNamedQuery("User.findByEmail", User.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		User actual=repository.findUserByEmail(user.getEmail());
		Assert.assertEquals(user, actual);
	}
	@Test
	public void testFindUserByEmailNull(){
		Mockito.when(em.createNamedQuery("User.findByEmail", User.class)).thenReturn(query);
		User actual=repository.findUserByEmail(user.getEmail());
		Assert.assertEquals(null, actual);
	}
	
	
	@Test
	public void testCreateUser(){
		repository.createUser(user);
		Mockito.verify(em).persist(user);
	
	}
	@Test
	public void testUpdateUser(){
		repository.updateUser(user);
		Mockito.verify(em).merge(user);
	}
	
	@Test
	public void testFindUserByEmailAndPassword(){
		
		List<User> expected=Arrays.asList(user);
		Mockito.when(em.createNamedQuery("User.findByEmailAndPassword", User.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		User actual=repository.findUserByEmailAndPassword(user.getEmail(),user.getPassword());
		Assert.assertEquals(user, actual);
	}
}
