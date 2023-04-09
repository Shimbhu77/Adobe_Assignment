package com.adobe;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.adobe.exceptions.UserException;
import com.adobe.model.User;
import com.adobe.model.dto.UserDTO;
import com.adobe.repository.UserRepo;
import com.adobe.service.UserService;

@SpringBootTest
@RunWith(SpringRunner.class)
class AdobeAssignementApplicationTests {

	@Autowired
	private  UserService uService;
	
	@MockBean
	private UserRepo uRepo;

	@Test
	public void getAllUserTests() throws UserException {
		
		List<User> list = new ArrayList<>();
		list.add(new User(1,"Ram","ram@gmail.com","Hii, I am Ram.",LocalDateTime.now(),LocalDateTime.now(),null));
		list.add(new User(2,"krishna","krishna@gmail.com","Hii, I am krishna.",LocalDateTime.now(),LocalDateTime.now(),null));
		
		when(uRepo.findAll()).thenReturn(list);
		assertEquals(2,uService.getAllUsers().size());
	}
	
//	@Test
//	public void getUserTests() throws UserException {
//		
//		User user = new User(1,"Ram","ram@gmail.com","Hii, I am Ram.",LocalDateTime.now(),LocalDateTime.now(),null);
//		
//		when(uRepo.findById(1).get()).thenReturn(user);
//		assertEquals(user,uService.getUserById(1));
//	}

}
