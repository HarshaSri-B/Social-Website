package com.example.socialWebPro.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.socialWebPro.user.jpa.UserRepository;

@RestController
public class userResources {

	public userDao usersDao;

	public userResources(userDao usersDao) {
		super();
		this.usersDao = usersDao;
	}

		@Autowired
		private UserRepository repo;
	/*
	 * @GetMapping("/users/{Id}") public EntityModel<user>
	 * getUsersById(@PathVariable Integer Id) {
	 * 
	 * user User = usersDao.findById(Id);
	 * 
	 * if (User == null) throw new UserNotFoundException("id:" + Id);
	 * 
	 * EntityModel<user> entityModel = EntityModel.of(User);
	 * 
	 * WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
	 * entityModel.add(link.withRel("all-users"));
	 * 
	 * return entityModel; }
	 */
	
	@GetMapping("/users")
	public List<user> getAllUsers() {
		return usersDao.findAll();
	}
	
	@GetMapping("/users/jpa")
	public List<user> getAllJpaUsers(){
		return repo.findAll();
	}

	@DeleteMapping("/users/{Id}")
	public void deleteById(@PathVariable Integer Id) {
		usersDao.deleteId(Id);
	}

	@PostMapping("/users")
	public ResponseEntity<user> saveUser(@Valid @RequestBody user u) {
		user User = userDao.save(u);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(User.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

}
