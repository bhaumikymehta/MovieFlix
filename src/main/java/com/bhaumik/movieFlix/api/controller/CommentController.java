package com.bhaumik.movieFlix.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bhaumik.movieFlix.api.entity.Comment;
import com.bhaumik.movieFlix.api.entity.Movie;
import com.bhaumik.movieFlix.api.entity.User;
import com.bhaumik.movieFlix.api.exception.MovieNotFoundException;
import com.bhaumik.movieFlix.api.exception.UserNotFoundException;
import com.bhaumik.movieFlix.api.service.CommentService;
import com.bhaumik.movieFlix.api.service.MovieService;
import com.bhaumik.movieFlix.api.service.UserService;


@RestController
@RequestMapping(value = "/comments")
public class CommentController {
	@Autowired
	UserService userService;

	@Autowired
	MovieService movieService;

	@Autowired
	CommentService commentService;

	@RequestMapping(value = "/{movieId}/{userId}/{comment}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Comment createComment(@PathVariable("movieId") String movieId,
			@PathVariable("userId") String userId,
			@PathVariable("comment") String message)
			throws UserNotFoundException, MovieNotFoundException {

		Movie movie = movieService.findMovieById(movieId);
		User user = userService.findUserById(userId);
		
		Comment comment = commentService.findCommentByMovieAndUser(movie, user);
		if (comment == null) {
			comment = new Comment();
			comment.setMovieComment(movie);
			comment.setUserComment(user);

		}
		comment.setMessage(message);
		return commentService.createComment(comment);

	}

	@RequestMapping(value = "/{movieId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Comment> findCommentsByMovie(
			@PathVariable("movieId") String movieId)
			throws MovieNotFoundException {

		Movie movie = movieService.findMovieById(movieId);
	
		List<Comment> comments = commentService.findAllCommentByMovie(movie);
		return comments;
	}
}
