package com.bhaumik.movieFlix.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bhaumik.movieFlix.api.entity.Comment;
import com.bhaumik.movieFlix.api.entity.Movie;
import com.bhaumik.movieFlix.api.entity.User;
import com.bhaumik.movieFlix.api.exception.MovieAlreadyExistsException;
import com.bhaumik.movieFlix.api.exception.MovieNotFoundException;
import com.bhaumik.movieFlix.api.repository.CommentRepository;
import com.bhaumik.movieFlix.api.repository.MovieRepository;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository repository;

	@Override
	public Comment createComment(Comment comment) {
		// TODO Auto-generated method stub
		return repository.createComment(comment);
	}

	@Override
	public Comment updateComment(Comment comment) {
		// TODO Auto-generated method stub
		return repository.updateComment(comment);
	}

	@Override
	public void deleteComment(Comment comment) {
		// TODO Auto-generated method stub
		repository.deleteComment(comment);
		
	}

	@Override
	public Comment findCommentByMovieAndUser(Movie movie, User user) {
		// TODO Auto-generated method stub
		return repository.findCommentByMovieAndUser(movie, user);
	}

	@Override
	public List<Comment> findAllCommentByMovie(Movie movie) {
		// TODO Auto-generated method stub
		return repository.findAllCommentByMovie(movie);
	}
	
}
