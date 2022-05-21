package com.in28minutes.jpa.hibernate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerClass {

	@GetMapping("/aloha")
	public String method1()
	{
		return "this is my new computer";
	}
}
