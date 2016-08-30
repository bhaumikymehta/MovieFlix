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
import com.bhaumik.movieFlix.api.entity.Movie;
import com.bhaumik.movieFlix.api.entity.User;
import com.bhaumik.movieFlix.api.exception.MovieNotFoundException;
import com.bhaumik.movieFlix.api.repository.MovieRepository;
import com.bhaumik.movieFlix.api.repository.MovieRepositoryImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class MovieRepositoryTest {

	@Mock
	private EntityManager em;

	@InjectMocks
	private MovieRepository repository = new MovieRepositoryImpl();

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
	public void testFindAllMovie() {

		Mockito.when(em.createQuery("SELECT m FROM Movie m ORDER BY m.rating asc", Movie.class)).thenReturn(query);
		repository.findAllMovie();
		Mockito.verify(query).getResultList();

	}

	@Test
	public void testFindMovieById() throws MovieNotFoundException {

		Mockito.when(em.find(Movie.class, movie.getMovieId())).thenReturn(movie);
		Movie actual = repository.findMovieById(movie.getMovieId());
		Assert.assertEquals(movie, actual);
	}

	@Test
	public void testfindAllMovieByType() {
		//List<Movie> expected = Arrays.asList(movie);
		Mockito.when(em.createNamedQuery("Movie.findAllMovieByType", Movie.class)).thenReturn(query);
		//Mockito.when(query.getResultList()).thenReturn(expected);
		repository.findAllMovieByType(movie.getType());

		Mockito.verify(query).getResultList();
	}

	@Test
	public void testFindMovieByTitle() throws MovieNotFoundException {
		List<Movie> expected=Arrays.asList(movie);
		Mockito.when(em.createNamedQuery("Movie.findByTitle", Movie.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(expected);
		Movie actual = repository.findMovieByTitle(movie.getTitle());
		Assert.assertEquals(movie, actual);
	}
	@Test
	public void testFindMovieByTitleNull() throws MovieNotFoundException {
		Mockito.when(em.createNamedQuery("Movie.findByTitle", Movie.class)).thenReturn(query);
		Movie actual = repository.findMovieByTitle(movie.getTitle());
		Assert.assertEquals(null, actual);
	}

	@Test
	public void testCreateMovie() {
		repository.createMovie(movie);
		Mockito.verify(em).persist(movie);

	}

	@Test
	public void testUpdateMovie() throws MovieNotFoundException {
		repository.updateMovie(movie);
		Mockito.verify(em).merge(movie);
	}

	@Test
	public void testDeleteMovie() throws MovieNotFoundException {
		repository.deleteMovie(movie);
		Mockito.verify(em).remove(movie);
	}


}
