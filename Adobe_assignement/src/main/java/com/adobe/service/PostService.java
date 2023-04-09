package com.adobe.service;

import java.util.List;

import com.adobe.exceptions.PostException;
import com.adobe.exceptions.UserException;
import com.adobe.model.Post;
import com.adobe.model.dto.PostDTO;
import com.adobe.model.dto.UpdatePost;


public interface PostService {
	
	public Post createPost(PostDTO post_dto) throws PostException, UserException;
	public Post getPostbyId(Integer id) throws PostException;
	public Post updatePostbyId(Integer id,UpdatePost update_post) throws PostException;
	public Post deletePost(Integer id) throws PostException;
	public Post likePost(Integer id) throws PostException;
	public Post unlikePost(Integer id) throws PostException;
	public List<Post> getTop5posts() throws PostException, UserException;
	public List<Post> gettotalPosts() throws PostException, UserException;

}
