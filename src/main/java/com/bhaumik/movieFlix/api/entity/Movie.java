package com.bhaumik.movieFlix.api.entity;

import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "movies")
@NamedQueries({ @NamedQuery(name = "Movie.findByTitle", query = "SELECT m FROM Movie m where m.title=:mTitle"),
		@NamedQuery(name = "Movie.findAllMovieByType", query = "SELECT m FROM Movie m where m.type=:mType")

})
public class Movie {

	public Movie() {

	}

	@Id
	@GenericGenerator(strategy = "uuid2", name = "myuuid")
	@GeneratedValue(generator = "myuuid")
	private String movieId;

	private String title;

	private String type;

	private String year;

	private String rated;

	private String released;

	private String runtime;

	private String genre;

	private String director;

	private String writer;

	private String actors;

	@Column(columnDefinition = "TEXT")
	private String plot;

	@Column(columnDefinition = "TEXT")
	private String poster;

	private String language;

	private String country;

	private String awards;

	private int metaScore;

	@OneToOne(mappedBy = "movieRating", fetch = FetchType.EAGER)
	private Rating rating;

	@OneToMany(mappedBy = "movieComment", fetch = FetchType.EAGER)
	private Set<Comment> comments;

	// @OneToOne(mappedBy = "movie", cascade = CascadeType.ALL, fetch =
	// FetchType.EAGER)
	private String imdbRating;

	private String imdbVotes;

	private String imdbId;

	@JsonProperty("imdbRating")
	public String getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}

	@JsonProperty("imdbVotes")
	public String getImdbVotes() {
		return imdbVotes;
	}

	public void setImdbVotes(String imdbVotes) {
		this.imdbVotes = imdbVotes;
	}

	@JsonProperty("imdbID")
	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	@JsonProperty("Title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@JsonProperty("Type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("Year")
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@JsonProperty("Rated")
	public String getRated() {
		return rated;
	}

	public void setRated(String rated) {
		this.rated = rated;
	}

	@JsonProperty("Released")
	public String getReleased() {
		return released;
	}

	public void setReleased(String released) {
		this.released = released;
	}

	@JsonProperty("Runtime")
	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	@JsonProperty("Plot")
	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	@JsonProperty("Writer")
	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	@JsonProperty("Actors")
	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	@JsonProperty("Language")
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@JsonProperty("Country")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@JsonProperty("Awards")
	public String getAwards() {
		return awards;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

	@JsonProperty("Metascore")
	public int getMetaScore() {
		return metaScore;
	}

	public void setMetaScore(int metaScore) {
		this.metaScore = metaScore;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@JsonProperty("Genre")
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@JsonProperty("Poster")
	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	@JsonProperty("Director")
	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

}
