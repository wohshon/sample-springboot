package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@Autowired
	AMQController amqController;
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!\n";
    }

    @RequestMapping("/products")
    public String products() {
    	System.out.println("SENDING************************");
    	amqController.sendMessage("123");
    	amqController.sendMessage1("456");
        return "Greetings from Spring Boot - products!\n";
    }
	
}
