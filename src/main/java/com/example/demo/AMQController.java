package com.example.demo;

import javax.jms.ConnectionFactory;

import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@EnableJms
@Component	
public class AMQController {

	@Autowired
	public JmsTemplate jmsTemplate;
	@Autowired
	public JmsTemplate jmsTemplate1;
    
	public void sendMessage(String text) {
        System.out.println(String.format("Sending '%s'", text));
        this.jmsTemplate.convertAndSend("queue", text);
        System.out.println(String.format("Sent '%s'", text));
    }
    
	//2nd broker
    public void sendMessage1(String text) {
        System.out.println(String.format("Sending1 '%s'", text));
        
        jmsTemplate1=new JmsTemplate(getSecondaryCF());
        this.jmsTemplate1.convertAndSend("queue1", text);
        System.out.println(String.format("Sent1 '%s'", text));
    }

    public ConnectionFactory getSecondaryCF() {
    	ConnectionFactory cf=new JmsConnectionFactory("amqp://192.168.0.102:5672");
    	return cf;
    }
	/*
	 * @JmsListener(destination = "queue") public void receiveMessage(String text) {
	 * System.out.println(String.format("Received '%s'", text)); }
	 */    
    
}
