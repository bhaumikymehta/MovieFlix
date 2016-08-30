package com.bhaumik.MovieFlix.api.controller;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bhaumik.MovieFlix.api.TestConfig;
import com.bhaumik.movieFlix.api.controller.UserController;
import com.bhaumik.movieFlix.api.entity.User;
import com.bhaumik.movieFlix.api.exception.UserAlreadyExistsException;
import com.bhaumik.movieFlix.api.exception.UserNotFoundException;
import com.bhaumik.movieFlix.api.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
@WebAppConfiguration
public class UserControllerTest {

	@Mock
	UserService service;
	
	@InjectMocks
	UserController controller;
	
	private User user;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		user = new User();
		user.setEmail("dummy@dummy.com");
		user.setPassword("abcd");
		user.setFirstName("Dummy");
		user.setLastName("User");
		user.setUserId(UUID.randomUUID().toString());
		mockMvc=MockMvcBuilders.standaloneSetup(controller).build();
		
	
	}
	
	@Test
	public void testFindAllUser() throws Exception{
		
		mockMvc.perform(MockMvcRequestBuilders.get("/users"))
				.andExpect(MockMvcResultMatchers.status().isOk());
		Mockito.verify(service).findAllUser();
	}
//	@Test
//	public void testFindUserById() throws Exception{
//		
//		mockMvc.perform(MockMvcRequestBuilders.get("/users/"+user.getUserId()))
//				.andExpect(MockMvcResultMatchers.status().is(200));
//		Mockito.verify(service).findUserById(user.getUserId());
//	}
	
	@Test
	public void testFindUserByIdNotFound() throws Exception {
		Mockito.when(service.findUserById("testid")).thenThrow(
				new UserNotFoundException());

		mockMvc.perform(MockMvcRequestBuilders.get("/users/testid")).andExpect(
				MockMvcResultMatchers.status().is(415));
	}

	@Test
	public void testFindUserByEmail() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/users/email/" + user.getEmail()
						+ "/")).andExpect(
				MockMvcResultMatchers.status().is(200));
		Mockito.verify(service).findUserByEmail(user.getEmail());
	}

	@Test
	public void testFindUserByEmailNotFound() throws Exception {
		Mockito.when(service.findUserByEmail("test@test.com")).thenThrow(
				new UserNotFoundException());
		mockMvc.perform(
				MockMvcRequestBuilders.get("/users/email/test@test.com/"))
				.andExpect(MockMvcResultMatchers.status().is(404));
	}

	@Test
	public void testCreateUser() throws Exception {
		String requestBody = new ObjectMapper().writeValueAsString(user);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/users")
						.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.content(requestBody)).andExpect(
				MockMvcResultMatchers.status().is(200));

		Mockito.verify(service).createUser(Mockito.any(User.class));
	}
	
	@Test
	public void testCreateUserException() throws Exception {
		Mockito.when(service.createUser(Mockito.any(User.class))).thenThrow(new UserAlreadyExistsException());
		
		String requestBody = new ObjectMapper().writeValueAsString(user);
		mockMvc.perform(MockMvcRequestBuilders.post("/users")
											  .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
											  .content(requestBody))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

}
