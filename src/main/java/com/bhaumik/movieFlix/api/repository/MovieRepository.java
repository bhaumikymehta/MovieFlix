package com.bhaumik.movieFlix.api.repository;

import java.util.List;

import com.bhaumik.movieFlix.api.entity.Movie;
import com.bhaumik.movieFlix.api.exception.MovieNotFoundException;

public interface MovieRepository {

	public List<Movie> findAllMovie();

	public List<Movie> findAllMovieByType(String type);
	public Movie createMovie(Movie movie);
	public Movie findMovieById(String movieId)throws MovieNotFoundException;

	public Movie findMovieByTitle(String title)throws MovieNotFoundException;
	public Movie updateMovie(Movie movie )throws MovieNotFoundException;
	public void deleteMovie(Movie movie)throws MovieNotFoundException;
	
}
