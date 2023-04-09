package com.adobe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adobe.exceptions.PostException;
import com.adobe.exceptions.UserException;
import com.adobe.model.Post;
import com.adobe.model.dto.PostDTO;
import com.adobe.model.dto.UpdatePost;
import com.adobe.service.PostService;

//@CrossOrigin(origins = "http://127.0.0.1:5500/**")
@RestController
public class PostController {

	@Autowired
	private PostService pService;
	
	@PostMapping("/posts")
	public ResponseEntity<Post> createPost(@RequestBody PostDTO post_dto) throws  PostException, UserException
	{
		
		Post post = pService.createPost(post_dto);
		
		return new ResponseEntity<Post>(post,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/posts/{id}")
	public ResponseEntity<Post> getPost(@PathVariable("id") Integer id) throws  PostException
	{
		
		Post post = pService.getPostbyId(id);
		
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/posts/{id}")
	public ResponseEntity<Post> updatePost(@PathVariable("id") Integer id,@RequestBody UpdatePost update_post) throws  PostException
	{
		
		Post post = pService.updatePostbyId(id, update_post);
		
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/posts/{id}")
	public ResponseEntity<Post> deletePost(@PathVariable("id") Integer id) throws  PostException
	{
		
		Post post = pService.deletePost(id);
		
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/posts/{id}/like")
	public ResponseEntity<Post> likePost(@PathVariable("id") Integer id) throws  PostException
	{
		
		Post post = pService.likePost(id);
		
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/posts/{id}/unlike")
	public ResponseEntity<Post> unlikePost(@PathVariable("id") Integer id) throws  PostException
	{
		
		Post post = pService.unlikePost(id);
		
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
		
	}

	@GetMapping("/analytics/posts/top-liked")
	public ResponseEntity<List<Post>> getallPosts() throws  PostException, UserException
	{
		
		List<Post> post = pService.getTop5posts();
		
		return new ResponseEntity<List<Post>>(post,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/analytics/posts")
	public ResponseEntity<List<Post>> gettotalPosts() throws  PostException, UserException
	{
		
		List<Post> post = pService.gettotalPosts();
		
		return new ResponseEntity<List<Post>>(post,HttpStatus.ACCEPTED);
		
	}
}
