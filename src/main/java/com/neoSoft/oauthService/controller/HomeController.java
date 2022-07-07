package com.neoSoft.oauthService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class HomeController {

	@GetMapping("/home")
	public String getHome() {
		return "Its home page";
	}
	
	@GetMapping("/login")
	public String getLogin() {
		return "Its Login page";
	}
	
	@GetMapping("/register")
	public String getRegister() {
		return "Its Register page";
	}
	
}
