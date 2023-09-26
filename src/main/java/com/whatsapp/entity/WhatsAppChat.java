package com.whatsapp.entity;

import com.whatsapp.config.Audit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="whatsapp_chat")
public class WhatsAppChat extends Audit {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long whatsAppId;
	
	@Column(nullable = false)
	private String message;
	
	@Column(nullable = false)
	private long senderId;
	
	@Column
	private String file;
	
	
	@Column(nullable = false)
	private long receiverId;
	
	private boolean seen=false;

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Long getWhatsAppId() {
		return whatsAppId;
	}

	public void setWhatsAppId(Long whatsAppId) {
		this.whatsAppId = whatsAppId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getSenderId() {
		return senderId;
	}

	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}

	public long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(long receiverId) {
		this.receiverId = receiverId;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}
	
	
	
	
	
	
	

}
