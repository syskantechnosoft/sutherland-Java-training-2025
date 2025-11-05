package com.example.batch.processor;

import org.springframework.batch.item.ItemProcessor;

import com.example.batch.model.User;

public class UserProcessor implements ItemProcessor<User, User> {

	@Override
	public User process(User user) {
		user.setName(user.getName().toUpperCase()); // example transformation
		return user;
	}
}
