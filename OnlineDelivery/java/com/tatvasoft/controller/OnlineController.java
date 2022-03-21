package com.tatvasoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
@RequestMapping("/online")
public class OnlineController {

	@PostMapping("/onlinetesturl")
	public String helloReturn()
	{
		return "web service working..";
	}
}
