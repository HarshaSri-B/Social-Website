package com.example.socialWebPro.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class userDao {

	private static List<user> users = new ArrayList<user>();

	static int IdCnt = 1;
	static {
		users.add(new user(IdCnt++, "Harsha", LocalDate.now().minusYears(21)));
		users.add(new user(IdCnt++, "deepu", LocalDate.now().minusYears(21)));
		users.add(new user(IdCnt++, "srujana", LocalDate.now().minusYears(21)));
	}

	public static user save(user u) {

		u.setId(IdCnt++);
		users.add(u);
		return u;
	}

	public List<user> findAll() {
		return users;
	}

	/*
	 * public user findById(Integer id) {
	 * 
	 * Predicate<? super user> predicate = User -> User.getId() == equals(id);
	 * return users.stream().filter(predicate).findFirst().orElse(null);
	 * 
	 * for(user u : users) { if(u.getId() == id) return u; } return null;
	 * 
	 * }
	 */
	public void deleteId(Integer id) {
		// TODO Auto-generated method stub
		Predicate<? super user> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);

	}
}
