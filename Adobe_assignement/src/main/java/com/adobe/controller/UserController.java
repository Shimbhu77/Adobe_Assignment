package com.adobe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adobe.exceptions.UserException;
import com.adobe.model.User;
import com.adobe.model.dto.UserDTO;
import com.adobe.model.dto.UserUpdate;
import com.adobe.repository.UserRepo;
import com.adobe.service.UserService;

//@CrossOrigin(origins = "http://127.0.0.1:5500/**")
@RestController
public class UserController {

	@Autowired
	private UserService sService;
	
	@Autowired
	private UserRepo sRepo;
	
	
	@PostMapping("/users")
	public ResponseEntity<User> registerStudent(@RequestBody UserDTO user_dto) throws UserException
	{
		
		User user = sService.registerUser(user_dto);
		
		return new ResponseEntity<User>(user,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> retrieveUser(@PathVariable("id") Integer id) throws UserException
	{
		User user = sService.getUserById(id);
		return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Integer id,@RequestBody UserUpdate user_update) throws UserException
	{
		User user = sService.updateUserById(id, user_update);
		return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") Integer id) throws UserException
	{
		User user = sService.deleteUserById(id);
		return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/analytics/users")
	public ResponseEntity<List<User>> getAllUsers() throws UserException
	{
		List<User> users = sService.getAllUsers();
		return new ResponseEntity<List<User>>(users,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/analytics/users/top-active")
	public ResponseEntity<List<User>> getMostActiveUsers() throws UserException
	{
		List<User> users = sService.topMostActiveUsers();
		return new ResponseEntity<List<User>>(users,HttpStatus.ACCEPTED);
		
	}
	
}
