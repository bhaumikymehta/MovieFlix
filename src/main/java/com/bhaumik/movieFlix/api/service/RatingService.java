package com.bhaumik.movieFlix.api.service;

import java.util.List;

import com.bhaumik.movieFlix.api.entity.Movie;
import com.bhaumik.movieFlix.api.entity.Rating;
import com.bhaumik.movieFlix.api.entity.User;


public interface RatingService {
	

	public Rating addRating(Rating rating);

	public Rating findRatingByMovieAndUser(Movie movie, User user);

	public List<Rating> findRatingByMovie(Movie movie);

}
