package com.example.NewsApp.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//(exclude = "password")
public class User {
	
	
	private String userName;
	private String email;
	private String password;
	
	
}
