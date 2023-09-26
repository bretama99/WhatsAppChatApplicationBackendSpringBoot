package com.whatsapp.service;

import java.util.List;

import com.whatsapp.request.WhatsAppChatRequestModel;
import com.whatsapp.response.WhatsAppChatResponseModel;

public interface WhatsAppChatService {

	WhatsAppChatResponseModel saveChatDetail(WhatsAppChatRequestModel requestDetail);

	WhatsAppChatResponseModel getChatDetail(Long whatsAppChatId);

	WhatsAppChatResponseModel updateChatDetail(Long whatsAppChatId, WhatsAppChatRequestModel requestDetail);

	String deleteChatDetail(Long whatsAppChatId);

	List<WhatsAppChatResponseModel> getWhatsAppChats(int page, int limit);

	WhatsAppChatResponseModel changeChatStatus(long[] whatsAppChatIds);
	

}
