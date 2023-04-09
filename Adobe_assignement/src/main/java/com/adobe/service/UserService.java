package com.adobe.service;

import java.util.List;

import com.adobe.exceptions.UserException;
import com.adobe.model.User;
import com.adobe.model.dto.UserDTO;
import com.adobe.model.dto.UserUpdate;

public interface UserService {

	public User registerUser(UserDTO user_dto) throws UserException;
	public User getUserById(Integer id) throws UserException;
	public User updateUserById(Integer id,UserUpdate user_update) throws UserException;
	public User deleteUserById(Integer id) throws UserException;
	public List<User> getAllUsers() throws UserException;
	public List<User> topMostActiveUsers() throws UserException;
}
