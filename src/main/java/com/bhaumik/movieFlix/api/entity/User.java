package com.bhaumik.movieFlix.api.entity;

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
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name= "users")
@NamedQueries(
		{
		@NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email=:uemail"),
		@NamedQuery(name = "User.findByEmailAndPassword", query = "SELECT u FROM User u WHERE u.email=:uemail AND u.password=:upassword")
		}
		)
public class User {

	
	public User() {
	}

	@Id
	@GenericGenerator(strategy = "uuid2", name = "myuuid")
	@GeneratedValue(generator = "myuuid")
	private String userId;

	private String role;
	private String firstName;
	private String lastName;


	// email should be unique and null value is not allowed
	@Column(unique = true, nullable=false)
	private String email;

	// password should not be null
	@Column(nullable=false)
	private String password;

	
	private Date dob;
	
	@OneToMany(mappedBy = "userComment",fetch = FetchType.EAGER)
	private Set<Comment> comments;

	
	@OneToMany(mappedBy = "userRating",fetch = FetchType.EAGER)
	private Set<Rating> ratings;
	
	// getters and setters
	
	public String getRole() {
		return role;
	}

	

	public Set<Comment> getComments() {
		return comments;
	}



	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}



	public Set<Rating> getRatings() {
		return ratings;
	}



	public void setRatings(Set<Rating> ratings) {
		this.ratings = ratings;
	}



	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

}
