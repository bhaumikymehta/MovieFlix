package com.bhaumik.movieFlix.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bhaumik.movieFlix.api.entity.Movie;
import com.bhaumik.movieFlix.api.exception.MovieAlreadyExistsException;
import com.bhaumik.movieFlix.api.exception.MovieNotFoundException;
import com.bhaumik.movieFlix.api.repository.MovieRepository;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository repository;
	
	@Override
	public List<Movie> findAllMovie() {
		// TODO Auto-generated method stub
		return repository.findAllMovie();
	}

	@Override
	public List<Movie> findAllMovieByType(String type) {
		// TODO Auto-generated method stub
		List<Movie> movies= repository.findAllMovieByType(type);
		if(movies.size()==0){
			return null;
		}
		return movies;
		
	}

	@Override
	public Movie createMovie(Movie movie) throws MovieAlreadyExistsException {
		// TODO Auto-generated method stub
		Movie existing;
		try {
			existing = repository.findMovieByTitle(movie.getTitle());
			if(existing!=null){
				throw new MovieAlreadyExistsException();
			}
		} catch (MovieNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return repository.createMovie(movie);
	}

	@Override
	public Movie findMovieById(String movieId) throws MovieNotFoundException {
		// TODO Auto-generated method stub
		Movie movie=repository.findMovieById(movieId);
		if(movie==null){
			throw new MovieNotFoundException();
		}
		
		return movie;
	}

	@Override
	public Movie findMovieByTitle(String title) throws MovieNotFoundException {
		// TODO Auto-generated method stub
		Movie movie=repository.findMovieByTitle(title);
		if(movie==null){
			throw new MovieNotFoundException();
		}
		
		return movie;
	}

	@Override
	public Movie updateMovie(String movieId, Movie movie) throws MovieNotFoundException {

		Movie existing=repository.findMovieById(movieId);
		if(existing==null){
			throw new MovieNotFoundException();
		}
		
		
		return repository.updateMovie( movie);
	}

	@Override
	public void deleteMovie(String movieId) throws MovieNotFoundException {
		Movie existing=repository.findMovieById(movieId);
		if(existing==null){
			throw new MovieNotFoundException();
		}
		repository.deleteMovie(existing);
	}

}
