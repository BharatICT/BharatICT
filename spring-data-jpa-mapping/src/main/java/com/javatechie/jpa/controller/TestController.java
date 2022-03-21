package com.javatechie.jpa.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
		@RequestMapping("/testUrl")
		public String testUrl() throws Exception
		{
			return "This is tatvasoft company";
		}
}
