package com.example.socialWebPro.user.jpa;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.socialWebPro.user.Post;
import com.example.socialWebPro.user.UserNotFoundException;
import com.example.socialWebPro.user.user;

@RestController
public class userJpaResources {

	// public userDao usersDao;

	public UserRepository repository;

	public userJpaResources(UserRepository repository) {
		super();
		this.repository = repository;
	}

	@GetMapping("/jpa/users/{id}")
	public EntityModel<user> getUsersById(@PathVariable int id) {

		Optional<user> u = repository.findById(id);

		if (u.isEmpty())
			throw new UserNotFoundException("id:" + id);

		EntityModel<user> entityModel = EntityModel.of(u.get());

		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
		entityModel.add(link.withRel("all-users"));

		return entityModel;
	}

	@GetMapping("/jpa/users")
	public List<user> getAllUsers() {
		return repository.findAll();
	}

	@DeleteMapping("/jpa/users/{Id}")
	public void deleteById(@PathVariable Integer Id) {
		repository.deleteById(Id);
	}

//	@GetMapping("/jpa/users/{id}/Posts")
//	public List<Post> retrivePostsById(@PathVariable Integer id) {
//		Optional<user> u = repository.findById(id);
//
//		if (u.isEmpty())
//			throw new UserNotFoundException("id:" + id);
//
//		return u.get().getPosts();
//
//	}

	@PostMapping("/jpa/users")
	public ResponseEntity<user> saveUser(@Valid @RequestBody user u) {
		user User = repository.save(u);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(User.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

}
