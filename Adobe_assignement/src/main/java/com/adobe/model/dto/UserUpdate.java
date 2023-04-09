package com.adobe.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.adobe.model.Post;
import com.adobe.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdate {

	private Integer id;
	private String name;
	private String bio;
}
