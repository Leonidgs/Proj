package com.example.NewsApp.service;

import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SecurityConfig {
	
	// supplier получает бин SecurityHolder
	@Bean
	public Supplier<SecurityHolder> coockie() {
		return this::handler;
	}
	
	// бин, который хранит куки , который будет жить пока живет сессия (за счет @Scope)
	@Bean
	@Scope("session")
	public SecurityHolder handler() {
		return new SecurityHolder();
	}
}
