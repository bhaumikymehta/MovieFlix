package com.bhaumik.movieFlix.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bhaumik.movieFlix.api.entity.Movie;
import com.bhaumik.movieFlix.api.entity.Rating;
import com.bhaumik.movieFlix.api.entity.User;
import com.bhaumik.movieFlix.api.exception.MovieAlreadyExistsException;
import com.bhaumik.movieFlix.api.exception.MovieNotFoundException;
import com.bhaumik.movieFlix.api.repository.MovieRepository;
import com.bhaumik.movieFlix.api.repository.RatingRepository;

@Service
@Transactional
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository repository;

	@Override
	public Rating addRating(Rating rating) {
		// TODO Auto-generated method stub
		return repository.addRating(rating);
	}

	@Override
	public Rating findRatingByMovieAndUser(Movie movie, User user) {
		// TODO Auto-generated method stub
		return repository.findRatingByMovieAndUser(movie, user);
	}

	@Override
	public List<Rating> findRatingByMovie(Movie movie) {
		// TODO Auto-generated method stub
		return repository.findRatingByMovie(movie);
	}
	
}
