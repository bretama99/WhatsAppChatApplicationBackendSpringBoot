package com.whatsapp.request;

import org.springframework.web.multipart.MultipartFile;

public class WhatsAppChatRequestModel {

	private String message;	
	private Long senderId;		
	private Long receiverId;	
	private boolean seen;
	private MultipartFile file;


	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getSenderId() {
		return senderId;
	}
	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}
	public Long getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}
	public boolean isSeen() {
		return seen;
	}
	public void setSeen(boolean seen) {
		this.seen = seen;
	}
	
	
}
