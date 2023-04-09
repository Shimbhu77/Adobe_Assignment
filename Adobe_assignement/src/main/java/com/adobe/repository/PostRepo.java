package com.adobe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.adobe.model.Post;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

	 @Query("SELECT p FROM Post p ORDER BY p.likes DESC")
	 List<Post> findAllByOrderByLikesDesc();
}
