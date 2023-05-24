package com.example.demo.demo1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/DempController")
public class DemoController {
	
	@GetMapping
	private ResponseEntity<String> SayHello(){
		
		
		return ResponseEntity.ok("This is coming from a secured Endpoint");
		
	}
}
