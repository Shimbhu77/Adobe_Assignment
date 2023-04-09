package com.adobe.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adobe.exceptions.PostException;
import com.adobe.exceptions.UserException;
import com.adobe.model.Post;
import com.adobe.model.User;
import com.adobe.model.dto.PostDTO;
import com.adobe.model.dto.UpdatePost;
import com.adobe.repository.PostRepo;
import com.adobe.repository.UserRepo;
import com.adobe.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private PostRepo pRepo;

	@Override
	public Post createPost(PostDTO post_dto) throws PostException, UserException {
		
		Optional<User> optUser = uRepo.findById(post_dto.getUser_id());
		
		if(optUser.isPresent())
		{
			Post post = new Post();
			
			post.setContent(post_dto.getContent());
			post.setLikes(0);
			post.setCreated_at(LocalDateTime.now());
			post.setUpdated_at(LocalDateTime.now());
			post.setUser_id(optUser.get());
			
			User user = optUser.get();
			
			user.getPosts().add(post);
			
			return pRepo.save(post);
			
		}
		else
		{
			throw new UserException("No user is found with this id : "+post_dto.getUser_id());
		}
		
		
	}

	@Override
	public Post getPostbyId(Integer id) throws PostException {
		
		Optional<Post> optPost  = pRepo.findById(id);
		
		if(optPost.isPresent())
		{
			return optPost.get();
		}
		else
		{
			throw new PostException("no post found with this id : "+id);
		}
		
	}

	@Override
	public Post updatePostbyId(Integer id, UpdatePost update_post) throws PostException {

		Optional<Post> optPost  = pRepo.findById(id);
		
		if(optPost.isPresent())
		{
			Post post= optPost.get();
			
			post.setContent(update_post.getContent());
			post.setUpdated_at(LocalDateTime.now());
			
			return pRepo.save(post);
		}
		else
		{
			throw new PostException("no post found with this id : "+id+" for updating post.");
		}
	}

	@Override
	public Post deletePost(Integer id) throws PostException {

		Optional<Post> optPost  = pRepo.findById(id);
		
		if(optPost.isPresent())
		{
			Post post= optPost.get();
			
			Optional<User> optUser  = uRepo.findById(post.getUser_id().getId());
			
			User user = optUser.get();
			
			 
			List<Post> posts = user.getPosts();
			
			int ind = 0;
			for(int i=0;i<posts.size();i++)
			{
				if(posts.get(i).getId()==id)
				{
					ind = i;
					break;
				}
			}
			System.out.println("***************** L "+ind);
			user.getPosts().remove(ind);
			post.setUser_id(null);
//			uRepo.save(user);
			pRepo.delete(post);
			
			return post; 
		}
		else
		{
			throw new PostException("no post found with this id : "+id+" for deleting post.");
		}	
	}

	@Override
	public Post likePost(Integer id) throws PostException {

		Optional<Post> optPost  = pRepo.findById(id);
		
		if(optPost.isPresent())
		{
			Post post= optPost.get();
			int likes = post.getLikes();
			if(likes>=0)
			{
				likes++;
			}
			post.setLikes(likes);
			
			pRepo.save(post);
			 
			return post; 
		}
		else
		{
			throw new PostException("no post found with this id : "+id+" for liking post.");
		}	
	}

	@Override
	public Post unlikePost(Integer id) throws PostException {

		Optional<Post> optPost  = pRepo.findById(id);
		
		if(optPost.isPresent())
		{
			Post post= optPost.get();
			int likes = post.getLikes();
			if(likes>0)
			{
				likes--;
			}
			post.setLikes(likes);
			pRepo.save(post);
			return post; 
		}
		else
		{
			throw new PostException("no post found with this id : "+id+" for unliking post.");
		}	
	}


	@Override
	public List<Post> gettotalPosts() throws PostException, UserException {
		
		return pRepo.findAll();
	}

	@Override
	public List<Post> getTop5posts() throws PostException, UserException {
		
		List<Post> posts =  pRepo.findAllByOrderByLikesDesc();
		
		List<Post> top5posts = new ArrayList<>();
		
		for(int i=0;i<5;i++)
		{
			top5posts.add(posts.get(i));
		}
		
		return top5posts;
	}

	
}
