package com.bhaumik.movieFlix.api.service;

import java.util.List;

import com.bhaumik.movieFlix.api.entity.Movie;
import com.bhaumik.movieFlix.api.exception.MovieAlreadyExistsException;
import com.bhaumik.movieFlix.api.exception.MovieNotFoundException;


public interface MovieService {
	public List<Movie> findAllMovie();

	public List<Movie> findAllMovieByType(String type);
	public Movie createMovie(Movie movie) throws MovieAlreadyExistsException;
	public Movie findMovieById(String movieId)throws MovieNotFoundException;

	public Movie findMovieByTitle(String title)throws MovieNotFoundException;
	public Movie updateMovie(String movieId,Movie movie )throws MovieNotFoundException;
	public void deleteMovie(String movieId)throws MovieNotFoundException;
	
}
