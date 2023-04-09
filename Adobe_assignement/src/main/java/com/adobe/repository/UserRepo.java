package com.adobe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.adobe.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>
{
	public User findByEmail(String email);
	
	@Query("SELECT u.id, u.name, u.email, COUNT(p.id) AS value FROM User u JOIN u.posts p GROUP BY u.id ORDER BY value DESC")
	List<Object[]> getUserPostCounts();
//	@Query("SELECT u, COUNT(p.id) AS value FROM User u JOIN u.posts p GROUP BY u.id ORDER BY value DESC")
//	List<Object[]> getUserPostCounts();
}