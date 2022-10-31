package com.example.socialWebPro.user;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonIgnoreProperties({"Id","name"})
@Entity
@Table(name = "user_details")
public class user {

	// @JsonIgnore
	@Id
	@GeneratedValue
	private int id;

	@Size(min = 2, message = "Name shoud never be less than 2 characters")
	private String name;
	@Past(message = "Date of birth should be in the past")
	private LocalDate dob;

//	@OneToMany(mappedBy = "User")
//	private List<Post> post;
//
//	public List<Post> getPosts() {
//		return post;
//	}
//
//	public void setPosts(List<Post> post) {
//		this.post = post;
//	}

	public user(int id, String name, LocalDate dob) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
	}

	public user() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "user [Id=" + id + ", name=" + name + ", dob=" + dob + "]";
	}

}
