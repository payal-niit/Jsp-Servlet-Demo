package com.niit.project.test;

import static org.junit.Assert.assertFalse;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.niit.project.model.User;
import com.niit.project.repository.UserRepository;

public class UserTest {

	UserRepository userRepository;
	User user = new User();

	@Before
	public void setup() {
		userRepository = new UserRepository();
	}

	@After
	public void tearDown() {
		userRepository = null;
	}
	@Ignore
	@Test
	public void addUserTest() {
		Date d = new Date();

		user.setUser_id(4);
		user.setFname("abc");
		user.setLname("Shah");
		user.setAge(23);
		user.setDob(new java.sql.Date(d.getTime()));
		try {
			user.setEmail_id("abe@gmail.com");
		} catch (Exception e1) {
			System.out.println("Email ID is invalid");
			e1.printStackTrace();
		}
		try {
			user.setPhone_number("1234567890");
		} catch (Exception e) {
			System.out.println("Phone number invalid");
			e.printStackTrace();
		}
		user.setTotal_yrs_of_experience(1);
		user.setTotal_nm_of_stdnts_handled(100);

		boolean flag;
		if (flag = true) {
			flag = true;
			userRepository.addUser(user);
			assertFalse("Test case Succeed", flag);
		} else {
			flag = false;
			assertFalse("Test case failed", flag);
		}
	}
	@Ignore
	@Test
	public void getUserTest() {

		userRepository.getUser();
		// assertEquals(user.getUser_id(), actual);

	}

	@Test
	public void getEditUserTest() {
		user.setFname("abc");
		user.setLname("Shah");
		user.setAge(23);		
		
		boolean flag;
		if (flag = true) {
			flag = true;
			userRepository.editUser(1,user);
			System.out.println("User edited is: "+user.getFname()+", " + user.getLname() + ", " + user.getAge());
			assertFalse("Test case Succeed", flag);
		} else {
			flag = false;
			assertFalse("Test case failed", flag);
		}
		
		// assertEquals(user.getUser_id(), actual);

	}
	@Ignore
	@Test
	public void getUserByIdTest() {
		userRepository.getUserById(1);
	}
	
	@Ignore
	@Test
	public void deleteUserTest() {
		userRepository.deleteUser(3);
	}

}
