package com.adobe.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	@Size(min=1,max=50,message = "Name size should be between 1-50 characters")
	private String name;
	
	@Email(message = "Enter a Valid Email")
	private String email;
	
	@Size(min=0,max=200 ,message = "Bio size should be between 0-200 characters")
	private String bio;
	
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	private List<Post> posts = new ArrayList<>();
	
}
