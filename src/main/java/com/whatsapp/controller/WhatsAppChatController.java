package com.whatsapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.whatsapp.request.WhatsAppChatRequestModel;
import com.whatsapp.response.WhatsAppChatResponseModel;
import com.whatsapp.service.WhatsAppChatService;

@RestController
@RequestMapping("/api/whatsapp-chat")
public class WhatsAppChatController {

	@Autowired
	WhatsAppChatService whatsAppChatService;
	
	@PostMapping
//	@ApiOperation(value = "Saves chat detail to database", notes = "Provide chat detail to save", response = WhatsAppChatResponseModel.class)
	public WhatsAppChatResponseModel saveChatDetail(@RequestBody WhatsAppChatRequestModel requestDetail) {
		
		WhatsAppChatResponseModel returnValue = whatsAppChatService.saveChatDetail(requestDetail);
		
		return returnValue;
		
	}
	
	@GetMapping(path = "/{whatsAppChatId}")
//	@ApiOperation(value = "Finds chat detail by id", notes = "Provide an id to look up specific caht detail from the chats", response = WhatsAppChatResponseModel.class)
	public WhatsAppChatResponseModel getChatDetail(@PathVariable Long whatsAppChatId) {
		WhatsAppChatResponseModel returnValue = whatsAppChatService.getChatDetail(whatsAppChatId);
		return returnValue;
	}
	
	@GetMapping
//	@ApiOperation(value = "Fetches chats for given limit per page", notes = "Provide limit and page to get required count of chats per page", response = WhatsAppChatResponseModel.class)
	public List<WhatsAppChatResponseModel> getWhatsAppChats(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "1000") int limit) {
		List<WhatsAppChatResponseModel> returnValue = whatsAppChatService.getWhatsAppChats( page, limit);
		return returnValue;
	}
	
	@PutMapping(path = "/{whatsAppChatId}")
//	@ApiOperation(value = "Updates chat detail based on the given id", notes = "Provide chats detail with id to update the record", response = WhatsAppChatResponseModel.class)
	public WhatsAppChatResponseModel updateChatDetail(@RequestBody WhatsAppChatRequestModel requestDetail, @PathVariable Long whatsAppChatId) {
		WhatsAppChatResponseModel returnValue = whatsAppChatService.updateChatDetail(whatsAppChatId, requestDetail);
		return returnValue;
	}
	
	@DeleteMapping(path = "/{whatsAppChatId}")
//	@ApiOperation(value = "Deletes chat detail for given id", notes = "Provide an id to delete specific chat detail from database", response = String.class)
	public String deleteChatDetail(@PathVariable Long whatsAppChatId) {
		String returnValue = whatsAppChatService.deleteChatDetail(whatsAppChatId);
		return returnValue;
	}
	
	@PutMapping(path = "/change-status")
//	@ApiOperation(value = "Updates chat status based on the given id", notes = "Provide chat status with id to update the record", response = WhatsAppChatResponseModel.class)
	public WhatsAppChatResponseModel changeChatStatus(@RequestBody long[] whatsAppChatIds) {
		WhatsAppChatResponseModel returnValue = whatsAppChatService.changeChatStatus(whatsAppChatIds);
		return returnValue;
	}
}
