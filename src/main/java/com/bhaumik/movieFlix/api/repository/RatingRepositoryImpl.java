package com.bhaumik.movieFlix.api.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bhaumik.movieFlix.api.entity.Movie;
import com.bhaumik.movieFlix.api.entity.Rating;
import com.bhaumik.movieFlix.api.entity.User;

@Repository
public class RatingRepositoryImpl implements RatingRepository{

	@PersistenceContext
	private EntityManager em;

	@Override
	public Rating addRating(Rating rating) {
		// TODO Auto-generated method stub
		em.persist(rating);
		return rating;
	}

	@Override
	public Rating findRatingByMovieAndUser(Movie movie, User user) {
		// TODO Auto-generated method stub
		TypedQuery<Rating> query = em.createNamedQuery(
				"Rating.findRatingByMovieAndUser", Rating.class);
		query.setParameter("movie", movie);
		query.setParameter("user", user);
		List<Rating> ratings = query.getResultList();
		if (ratings != null && ratings.size() == 1) {
			return ratings.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Rating> findRatingByMovie(Movie movie) {
		TypedQuery<Rating> query = em.createNamedQuery(
				"Rating.findRatingByMovie", Rating.class);
		query.setParameter("movie", movie);
		List<Rating> ratings = query.getResultList();
		if (ratings != null) {
			return ratings;
		} else {
			return null;
		}
	}

}
