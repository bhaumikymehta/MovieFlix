package com.bhaumik.movieFlix.api.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bhaumik.movieFlix.api.entity.Comment;
import com.bhaumik.movieFlix.api.entity.Movie;
import com.bhaumik.movieFlix.api.entity.User;

@Repository
public class CommentRepositoryImpl implements CommentRepository{

	@PersistenceContext
	private EntityManager em;

	@Override
	public Comment createComment(Comment comment) {
		// TODO Auto-generated method stub
		em.persist(comment);
		return comment;
	}

	@Override
	public Comment updateComment(Comment comment) {
		// TODO Auto-generated method stub
		return em.merge(comment);
	}

	@Override
	public void deleteComment(Comment comment) {
		// TODO Auto-generated method stub
		em.remove(comment);
	}

	@Override
	public Comment findCommentByMovieAndUser(Movie movie, User user) {
		TypedQuery<Comment> query = em.createNamedQuery(
				"Comment.findCommentByMovieAndUser", Comment.class);
		query.setParameter("movie", movie);
		query.setParameter("user", user);
		List<Comment> comments = query.getResultList();
		if (comments != null && comments.size() == 1) {
			return comments.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Comment> findAllCommentByMovie(Movie movie) {
		TypedQuery<Comment> query = em.createNamedQuery(
				"Comment.findCommentByMovie", Comment.class);
		query.setParameter("movie", movie);
		List<Comment> comments = query.getResultList();
		if (comments != null) {
			return comments;
		} else {
			return null;
		}
	}
	
}
