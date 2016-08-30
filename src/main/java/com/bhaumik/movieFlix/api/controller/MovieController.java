package com.bhaumik.movieFlix.api.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bhaumik.movieFlix.api.entity.Movie;
import com.bhaumik.movieFlix.api.exception.MovieAlreadyExistsException;
import com.bhaumik.movieFlix.api.exception.MovieNotFoundException;
import com.bhaumik.movieFlix.api.service.MovieService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value="/movies")
public class MovieController {

	@Autowired
	private MovieService service;
	
	//retriving all movies
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movie> findAllMovie() {
		System.out.println("Inside find all movies");
		return service.findAllMovie();

	}

	//retriving all movies by type
	@RequestMapping(value="/type/{type}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movie> findAllMoviesByType(@PathVariable("type") String type) {
		System.out.println("Inside find all movies");
		return service.findAllMovieByType(type);

	}

//	// Adding all list of movies in database
//	@RequestMapping(value="/addAll",method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public List<Movie> addAllMovies(@RequestBody List<Movie> movies) throws MovieAlreadyExistsException, JsonParseException, JsonMappingException, IOException {
//		ObjectMapper mapper=new ObjectMapper();
//		List<Movie> moviesList= mapper.readValue((JsonParser) movies, new TypeReference<List<Movie>>(){});
//		List<Movie> returnList= new ArrayList<Movie>();
//		for(Movie m:moviesList){
//			Movie mReturn=service.createMovie(m);
//			returnList.add(mReturn);
//		}
//		
//		return returnList;
//	}

	
	
	//creating new movie
	@RequestMapping(method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Movie createMovie(@RequestBody Movie movie) throws MovieAlreadyExistsException {
		
		
		return service.createMovie(movie);
	}

	@RequestMapping(value="/title/{title}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Movie findMovieByTitle(@PathVariable("title")String title) throws MovieNotFoundException {
		
		System.out.println("inside find movie by title is :"+title);
		return service.findMovieByTitle(title);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Movie findMovieById(@PathVariable("id") String movieId) throws MovieNotFoundException {
		
		System.out.println("inside find movie by id is :"+movieId);
		return service.findMovieById(movieId);

	}


	@RequestMapping(value = "/{id}", method = RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void updateMovie(@PathVariable("id") String movieId,@RequestBody Movie movie) throws MovieNotFoundException {
		
		service.updateMovie(movieId,movie);
		
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void deleteMovie(@PathVariable("id") String movieId) throws MovieNotFoundException {
		service.deleteMovie(movieId);

	}

	
}
