package com.bhaumik.movieFlix.api.service;

import java.util.List;

import com.bhaumik.movieFlix.api.entity.Comment;
import com.bhaumik.movieFlix.api.entity.Movie;
import com.bhaumik.movieFlix.api.entity.User;


public interface CommentService {


	public Comment createComment(Comment comment);

	public Comment updateComment(Comment comment);
	
	public void deleteComment(Comment comment);
	
	public Comment findCommentByMovieAndUser(Movie movie, User user);
	
	public List<Comment> findAllCommentByMovie(Movie movie);

}
