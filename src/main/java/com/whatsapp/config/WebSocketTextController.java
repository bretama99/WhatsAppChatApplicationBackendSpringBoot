package com.whatsapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.whatsapp.entity.WhatsAppChat;

@RestController
public class WebSocketTextController {
	
	@Autowired
	SimpMessagingTemplate template;
	
	@PostMapping("/send")
	public ResponseEntity<Void> sendMessage(@RequestBody WhatsAppChat requestDetail) {
		System.out.print(requestDetail.getMessage()+ " "+requestDetail.getSenderId()+" "+requestDetail.getReceiverId()+ requestDetail.isSeen()+"APIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
		template.convertAndSend("/topic/message", requestDetail);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@MessageMapping("/sendMessage")
	public void receiveMessage(@Payload WhatsAppChat requestDetail) {
		System.out.print(requestDetail.getMessage()+ " "+requestDetail.getSenderId()+" "+requestDetail.getReceiverId()+ requestDetail.isSeen()+"WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
		// receive message from client
	}
	
	
	@SendTo("/topic/message")
	public WhatsAppChat broadcastMessage(@Payload WhatsAppChat requestDetail) {
		System.out.print(requestDetail.getMessage()+ " "+requestDetail.getSenderId()+" "+requestDetail.getReceiverId()+ requestDetail.isSeen()+"WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
		return requestDetail;
	}
}