package com.adobe.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.adobe.exceptions.UserException;
import com.adobe.model.Post;
import com.adobe.model.User;
import com.adobe.model.dto.UserDTO;
import com.adobe.model.dto.UserUpdate;
import com.adobe.repository.PostRepo;
import com.adobe.repository.UserRepo;
import com.adobe.service.UserService;




@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private PostRepo pRepo;

	@Override
	public User registerUser(UserDTO user_dto) throws UserException {
		
		User user = new User();
		user.setName(user_dto.getName());
		user.setEmail(user_dto.getEmail());
		user.setBio(user_dto.getBio());
		user.setCreated_at(LocalDateTime.now());
		user.setUpdated_at(LocalDateTime.now());
		
		return uRepo.save(user);
	}

	@Override
	public User getUserById(Integer id) throws UserException {
		
		Optional<User> optUser = uRepo.findById(id);
		
		if(optUser.isPresent())
		{
			return optUser.get();
		}
		else
		{
			throw new UserException("no user found with this id : "+id);
		}
	}

	@Override
	public User updateUserById(Integer id, UserUpdate user_update) throws UserException {

		Optional<User> optUser = uRepo.findById(id);
		
		if(optUser.isPresent())
		{
			User user = optUser.get();
			
			user.setName(user_update.getName());
			user.setBio(user_update.getBio());
			user.setUpdated_at(LocalDateTime.now());
			
			return uRepo.save(user);
		}
		else
		{
			throw new UserException("no user found with this id : "+id+" for updating name and bio.");
		}
	}

	@Override
	public User deleteUserById(Integer id) throws UserException {

		Optional<User> optUser = uRepo.findById(id);
		
		if(optUser.isPresent())
		{
			User user = optUser.get();
			
//			List<Post> allposts = pRepo.findAll();
			
//			List<Post> posts = user.getPosts();
//			List<Integer> listofPost = new ArrayList<>();
//			for(int j=0;j<allposts.size();j++)
//			{
//				for(int i=0;i<posts.size();i++)
//				{
//					if(allposts.get(j).getUser_id().getId()==posts.get(i).getUser_id().getId())
//					{
////						posts.get(i).setUser_id(null);
//						listofPost.add(j);
////						allposts.remove(j);
//					}
//				}
//			}
//			
//			for(int i=0;i<listofPost.size();i++)
//			{
//				allposts.remove(listofPost.get(i));
//				pRepo.delete(allposts.get(i));
//			}
			
			
//			user.setPosts(posts);
			
//			pRepo.saveAll(allposts);
			
			
			
//			uRepo.delete(user);
			
			return user;
		}
		else
		{
			throw new UserException("no user found with this id : "+id+" for deleting the user.");
		}
	}

	@Override
	public List<User> getAllUsers() throws UserException {
		
		return uRepo.findAll();
	}

	@Override
	public List<User> topMostActiveUsers() throws UserException {
		
		List<Object[]> result = uRepo.getUserPostCounts();
		
		List<User> top5users = new ArrayList<>();
		for (Object[] row : result) {
		    Integer userId = (Integer)row[0];
		    String name = (String) row[1];
		    String email = (String) row[2];
		    Long postCount = (Long) row[3];
		    
		    Optional<User> user = uRepo.findById(userId);
		    
		    top5users.add(user.get());
		    
		    if(top5users.size()==5)
		    {
		    	break;
		    }
//		    System.out.println("User id: " + userId + ", Name: " + name + ", Email: " + email + ", Post Count: " + postCount);
		}
		
		return top5users;

	}
	


	
	

}
