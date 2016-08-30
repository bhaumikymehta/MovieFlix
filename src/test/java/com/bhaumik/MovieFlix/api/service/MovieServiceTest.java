package com.bhaumik.MovieFlix.api.service;

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
import com.bhaumik.movieFlix.api.entity.Movie;
import com.bhaumik.movieFlix.api.exception.MovieNotFoundException;
import com.bhaumik.movieFlix.api.repository.MovieRepository;
import com.bhaumik.movieFlix.api.repository.MovieRepositoryImpl;
import com.bhaumik.movieFlix.api.service.MovieService;
import com.bhaumik.movieFlix.api.service.MovieServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfig.class})
public class MovieServiceTest {

	@Mock
	private MovieRepository repository;

	@InjectMocks
	private MovieService service = new MovieServiceImpl();

	@Mock
	private TypedQuery<Movie> query;

	private Movie movie;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		movie = new Movie();
		movie.setActors("actors");
		movie.setAwards("awards");
		// movie.setComments(comments);
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
	}
	@Test
	public void testCreateMovie() {
		repository.createMovie(movie);
		Mockito.verify(repository).createMovie(movie);
	}

	@Test
	public void testFindMovieById() throws MovieNotFoundException {
		Mockito.when(repository.findMovieById(movie.getMovieId())).thenReturn(movie);
		Movie actual = service.findMovieById(movie.getMovieId());
		Assert.assertEquals(movie, actual);
	}

	@Test(expected=MovieNotFoundException.class)
	public void testFindMovieByIdException() throws MovieNotFoundException {
		Mockito.when(repository.findMovieById(movie.getMovieId())).thenReturn(null);
		service.findMovieById(movie.getMovieId());
	}
	
	@Test
	public void testFindMovieByType(){
		List<Movie> expected = Arrays.asList(movie);
		Mockito.when(repository.findAllMovieByType(movie.getType())).thenReturn(expected);
		List<Movie> actual = service.findAllMovieByType(movie.getType());
		Assert.assertEquals(expected, actual);
		
	}

	@Test
	public void testFindAllMovie(){
		List<Movie> expected = Arrays.asList(movie);
		Mockito.when(repository.findAllMovie()).thenReturn(expected);
		List<Movie> actual = service.findAllMovie();
		Assert.assertEquals(expected, actual);
	}
}
