package com.bhaumik.movieFlix.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bhaumik.movieFlix.api.entity.Movie;
import com.bhaumik.movieFlix.api.entity.Rating;
import com.bhaumik.movieFlix.api.entity.User;
import com.bhaumik.movieFlix.api.exception.MovieNotFoundException;
import com.bhaumik.movieFlix.api.exception.UserNotFoundException;
import com.bhaumik.movieFlix.api.service.MovieService;
import com.bhaumik.movieFlix.api.service.RatingService;
import com.bhaumik.movieFlix.api.service.UserService;


@RestController
@RequestMapping(value = "/ratings")
public class RatingController {


	@Autowired
	UserService userService;

	@Autowired
	RatingService ratingService;

	@Autowired
	MovieService movieService;
	
	@RequestMapping(value = "/{movieId}/{userId}/{ratingValue}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Rating addRating(@PathVariable("movieId") String movieId,
			@PathVariable("userId") String userId,
			@PathVariable("ratingValue") int value)
			throws UserNotFoundException, MovieNotFoundException {

		Movie movie = movieService.findMovieById(movieId);
		User user = userService.findUserById(userId);
		Rating rating = ratingService.findRatingByMovieAndUser(movie, user);
		if (rating == null) {
			rating = new Rating();
			rating.setMovieRating(movie);
			rating.setUserRating(user);

		}
		rating.setValue(value);
		return ratingService.addRating(rating);
	}

	@RequestMapping(value = "/{movieId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public int findRatingByMovie(@PathVariable("movieId") String movieId)
			throws MovieNotFoundException {

		Movie movie = movieService.findMovieById(movieId);
		List<Rating> ratings = ratingService.findRatingByMovie(movie);
		int value = 0;
		if (ratings != null) {
			for (Rating rating : ratings) {
				value += rating.getValue();
			}
			value = (value / ratings.size());
		}

		return value;
	}
}
