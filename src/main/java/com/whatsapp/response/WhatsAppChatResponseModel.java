package com.whatsapp.response;

public class WhatsAppChatResponseModel {

	private Long privateChatId;
	private String message;	
	private Long senderId;	
	private String senderName;
	private Long receiverId;
	private String receiverName;
	private String createdAt;
	private boolean seen;
	private Integer totalPage;
	private String file;

	
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public Long getPrivateChatId() {
		return privateChatId;
	}
	public void setPrivateChatId(Long privateChatId) {
		this.privateChatId = privateChatId;
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
	
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
	
}
