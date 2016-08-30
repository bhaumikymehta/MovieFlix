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
import com.bhaumik.movieFlix.api.controller.MovieController;
import com.bhaumik.movieFlix.api.entity.Movie;
import com.bhaumik.movieFlix.api.exception.MovieAlreadyExistsException;
import com.bhaumik.movieFlix.api.exception.MovieNotFoundException;
import com.bhaumik.movieFlix.api.exception.UserNotFoundException;
import com.bhaumik.movieFlix.api.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
@WebAppConfiguration
public class MovieControllerTest {

	@Mock
	MovieService service;
	
	@InjectMocks
	MovieController controller;
	
	private Movie movie;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		movie = new Movie();
		movie.setActors("actors");
		movie.setAwards("awards");
		movie.setCountry("country");
		movie.setDirector("director");
		movie.setGenre("genre");
		movie.setImdbId("imdbid");
		movie.setImdbRating("imdbrating");
		movie.setImdbVotes("imdbvotes");
		movie.setLanguage("language");
		movie.setMetaScore(1);
		movie.setMovieId(UUID.randomUUID().toString());
		movie.setType("type");
		movie.setYear("1991");
		movie.setTitle("title");
		movie.setRuntime("104 min");
		mockMvc=MockMvcBuilders.standaloneSetup(controller).build();
		
	
	}
	
	@Test
	public void testFindAllMovie() throws Exception{
		
		mockMvc.perform(MockMvcRequestBuilders.get("/movies"))
				.andExpect(MockMvcResultMatchers.status().isOk());
		Mockito.verify(service).findAllMovie();
	}
	@Test
	public void testFindMovieById() throws Exception{
		
		mockMvc.perform(MockMvcRequestBuilders.get("/movies/"+movie.getMovieId()))
				.andExpect(MockMvcResultMatchers.status().isOk());
		Mockito.verify(service).findMovieById(movie.getMovieId());
	}
	
	@Test
	public void testFindMovieByIdNotFound() throws Exception {
		Mockito.when(service.findMovieById("testid")).thenThrow(
				new MovieNotFoundException());

		mockMvc.perform(MockMvcRequestBuilders.get("/movies/testid")).andExpect(
				MockMvcResultMatchers.status().is(404));
	}

	@Test
	public void testFindMovieByTitle() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/movies/title/" + movie.getTitle()
						+ "/")).andExpect(
				MockMvcResultMatchers.status().is(200));
		Mockito.verify(service).findMovieByTitle(movie.getTitle());
	}

	@Test
	public void testFindMovieByTitleNotFound() throws Exception {
		Mockito.when(service.findMovieByTitle("ComedyRocks")).thenThrow(
				new MovieNotFoundException());
		mockMvc.perform(
				MockMvcRequestBuilders.get("/movies/title/ComedyRocks/"))
				.andExpect(MockMvcResultMatchers.status().is(404));
	}

	@Test
	public void testCreateUser() throws Exception {
		String requestBody = new ObjectMapper().writeValueAsString(movie);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/movies")
						.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.content(requestBody)).andExpect(
				MockMvcResultMatchers.status().is(200));

		Mockito.verify(service).createMovie(Mockito.any(Movie.class));
	}
	
	@Test
	public void testCreateUserException() throws Exception {
		Mockito.when(service.createMovie(Mockito.any(Movie.class))).thenThrow(new MovieAlreadyExistsException());
		
		String requestBody = new ObjectMapper().writeValueAsString(movie);
		mockMvc.perform(MockMvcRequestBuilders.post("/users")
											  .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
											  .content(requestBody))
				.andExpect(MockMvcResultMatchers.status().is(404));
	}
	
}
