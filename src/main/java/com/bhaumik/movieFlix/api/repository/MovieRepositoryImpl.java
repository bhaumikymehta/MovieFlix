package com.bhaumik.movieFlix.api.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bhaumik.movieFlix.api.entity.Movie;
import com.bhaumik.movieFlix.api.exception.MovieNotFoundException;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Movie> findAllMovie() {
		// TODO Auto-generated method stub
		TypedQuery<Movie> query=em.createQuery("SELECT m FROM Movie m ORDER BY m.rating asc",Movie.class);
		List<Movie> movies=query.getResultList();
		
		return movies;
	}

	@Override
	public List<Movie> findAllMovieByType(String type) {
		// TODO Auto-generated method stub
		TypedQuery<Movie> query=em.createNamedQuery("Movie.findAllMovieByType", Movie.class);
		query.setParameter("mType",type);
		List<Movie> movies=query.getResultList();
		
		return movies;
	}

	@Override
	public Movie createMovie(Movie movie) {
		// TODO Auto-generated method stub
		 em.persist(movie);
		 return movie;
	}

	@Override
	public Movie findMovieById(String movieId) throws MovieNotFoundException {
		// TODO Auto-generated method stub
		
		
		return em.find(Movie.class,movieId);
	}

	@Override
	public Movie findMovieByTitle(String title) throws MovieNotFoundException {
		// TODO Auto-generated method stub
		TypedQuery<Movie> query=em.createNamedQuery("Movie.findByTitle", Movie.class);
		query.setParameter("mTitle",title);
		List<Movie> movies=query.getResultList();
		if(movies.size()==1 && movies!=null){
			return movies.get(0);
		}
		return null;
	}

	
	

	@Override
	public Movie updateMovie(Movie movie) throws MovieNotFoundException {
		// TODO Auto-generated method stub
	
		return em.merge(movie);
		
	
	}

	@Override
	public void deleteMovie(Movie movie) throws MovieNotFoundException {
		// TODO Auto-generated method stub
		em.remove(movie);
		
	}

}
