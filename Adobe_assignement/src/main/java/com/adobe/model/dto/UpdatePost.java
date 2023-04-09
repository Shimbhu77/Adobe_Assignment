package com.adobe.model.dto;

import com.adobe.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePost {

	private Integer post_id;
	private String content;
}
