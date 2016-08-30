package com.bhaumik.movieFlix.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ratings")
@NamedQueries({
	@NamedQuery(name = "Rating.findRatingByMovieAndUser", query = "SELECT r FROM Rating r WHERE r.movieRating = :movie and r.userRating = :user"),
	@NamedQuery(name = "Rating.findRatingByMovie", query = "SELECT r FROM Rating r WHERE r.movieRating = :movie") })
public class Rating {

	
	
	public Rating() {
	}

	@Id
	@GenericGenerator(strategy="uuid2", name="myuuid")
	@GeneratedValue(generator="myuuid")
	private String ratingId;

	private int value;
	
	@ManyToOne
	@JoinColumn(name="userId")
		private User userRating;
	
	@OneToOne
	@JoinColumn(name="movieId")
	private Movie movieRating;

	public String getRatingId() {
		return ratingId;
	}

	public void setRatingId(String ratingId) {
		this.ratingId = ratingId;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}



	public User getUserRating() {
		return userRating;
	}

	public void setUserRating(User userRating) {
		this.userRating = userRating;
	}

	public Movie getMovieRating() {
		return movieRating;
	}

	public void setMovieRating(Movie movieRating) {
		this.movieRating = movieRating;
	}


	
		
	
}
