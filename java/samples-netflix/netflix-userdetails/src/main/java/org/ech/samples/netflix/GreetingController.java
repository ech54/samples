package org.ech.samples.netflix;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	@RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
	
}
