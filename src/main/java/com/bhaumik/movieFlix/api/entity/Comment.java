package com.bhaumik.movieFlix.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="comments")
@NamedQueries({
	@NamedQuery(name = "Comment.findCommentByMovieAndUser", query = "SELECT c FROM Comment c WHERE c.movieComment = :movie and c.userComment = :user"),
	@NamedQuery(name = "Comment.findCommentByMovie", query = "SELECT c FROM Comment c WHERE c.movieComment = :movie")
})
public class Comment {

	public Comment() {
	}

	@Id
	@GenericGenerator(strategy="uuid2", name="myuuid")
	@GeneratedValue(generator="myuuid")
	private String commentId;
	
	//@Column(columnDefinition="VARCHAR2(1000)")
	private String message;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User userComment;
	
	@ManyToOne
	@JoinColumn(name="movieId")
	private Movie movieComment;


	public Movie getMovieComment() {
		return movieComment;
	}

	public void setMovieComment(Movie movieComment) {
		this.movieComment = movieComment;
	}



	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



	public User getUserComment() {
		return userComment;
	}

	public void setUserComment(User userComment) {
		this.userComment = userComment;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	
}
