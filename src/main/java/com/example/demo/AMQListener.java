package com.example.demo;
	
import javax.jms.ConnectionFactory;

import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.stereotype.Component;

@EnableJms
@Component
public class AMQListener {

	
	@Autowired
	AMQController amqController;
	 
	  @JmsListener(destination = "queue") 
	  public void receiveMessage(String text) {
		  System.out.println(String.format("Received '%s'", text)); 
	  }

	  //2nd broker
	  @JmsListener(destination = "queue1", containerFactory = "myFactory") 
	  public void receiveMessage1(String text) {
		  System.out.println(String.format("Received from another broker'%s'", text)); 
	  }	  
	  
	  	//random CF to simulate a 2nd broker, just for testing
	  	@Bean
	    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
	                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
	        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	        // This provides all boot's default to this factory, including the message converter
	        configurer.configure(factory, amqController.getSecondaryCF());
	        // You could still override some of Boot's default if necessary.
	        return factory;
	    } 
	    
	    
        //ConnectionFactory cf=new JmsConnectionFactory("amqp://192.168.0.102:5672");
	    
	    
}
