package com.whatsapp.serviceImpl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.whatsapp.entity.UserEntity;
import com.whatsapp.entity.WhatsAppChat;
import com.whatsapp.repository.UserRepository;
import com.whatsapp.repository.WhatsAppChatRepository;
import com.whatsapp.request.WhatsAppChatRequestModel;
import com.whatsapp.response.WhatsAppChatResponseModel;
import com.whatsapp.service.WhatsAppChatService;


@Service
public class WhatsAppChatServiceImpl implements WhatsAppChatService{

	@Autowired
	WhatsAppChatRepository whatsAppChatRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	SimpMessagingTemplate template;
	
	String rootDirectory = "/var/www/html";
	String uploadDir = rootDirectory + "/chatFile/";
	
	@Override
	public WhatsAppChatResponseModel saveChatDetail(WhatsAppChatRequestModel requestDetail) {
		WhatsAppChatResponseModel returnValue = new WhatsAppChatResponseModel();
		WhatsAppChat whatsAppChat = new WhatsAppChat();		
		BeanUtils.copyProperties(requestDetail, whatsAppChat);

		File directory = new File(uploadDir);
		if (!directory.exists()) {
			directory.mkdirs();
		}
		
		byte[] bytes =null;
		try {
			bytes= requestDetail.getFile().getBytes();
		} catch (Exception e) {
		}
		String name = requestDetail.getMessage();
		if(requestDetail.getFile()!=null) {
			String fileName = requestDetail.getFile().getOriginalFilename();
			String extention = (fileName.substring(fileName.lastIndexOf(".") + 1)).toLowerCase();
			String newFileName = name + "." + "png";
			Path path = Paths.get(uploadDir + newFileName);
			try {
				Files.write(path, bytes);
			} catch (Exception e) {
			}
			
			whatsAppChat.setFile(newFileName);

	}
		WhatsAppChat savedDetail = whatsAppChatRepository.save(whatsAppChat);		
		BeanUtils.copyProperties(savedDetail, returnValue);
		template.convertAndSend("/topic/message", returnValue);
		return returnValue;
	}

	@Override
	public WhatsAppChatResponseModel getChatDetail(Long whatsAppChatId) {
		WhatsAppChatResponseModel returnValue = new WhatsAppChatResponseModel();
		WhatsAppChat whatsAppChat = whatsAppChatRepository.findByWhatsAppChatIdAndIsDeleted(whatsAppChatId, false);

		BeanUtils.copyProperties(whatsAppChat, returnValue);
		Optional<UserEntity> userEntity = userRepository.findById(whatsAppChat.getSenderId());
		System.out.print(userEntity.get().getFirstName());
		if(userEntity.isPresent()) {
			returnValue.setSenderName(userEntity.get().getFirstName()+" "+userEntity.get().getLastName());
		}
		
		userEntity = userRepository.findById(whatsAppChat.getReceiverId());
		if(userEntity.isPresent()) {
			returnValue.setReceiverName(userEntity.get().getFirstName()+" "+userEntity.get().getLastName());
		}
		
		return returnValue;
	}

	@Override
	public WhatsAppChatResponseModel updateChatDetail(Long whatsAppChatId, WhatsAppChatRequestModel requestDetail) {
		WhatsAppChatResponseModel returnValue = new WhatsAppChatResponseModel();
		WhatsAppChat whatsAppChat = whatsAppChatRepository.findByWhatsAppChatIdAndIsDeleted(whatsAppChatId, false);

		BeanUtils.copyProperties(requestDetail, whatsAppChat);
		
		File directory = new File(uploadDir);
		if (!directory.exists()) {
			directory.mkdirs();
		}
		
		byte[] bytes =null;
		try {
			bytes= requestDetail.getFile().getBytes();
		} catch (Exception e) {
		}
		String name = requestDetail.getMessage();
		if(requestDetail.getFile()!=null) {
			String fileName = requestDetail.getFile().getOriginalFilename();
			String extention = (fileName.substring(fileName.lastIndexOf(".") + 1)).toLowerCase();
			String newFileName = name + "." + "png";
			Path path = Paths.get(uploadDir + newFileName);
			try {
				Files.write(path, bytes);
			} catch (Exception e) {
			}
			
			whatsAppChat.setFile(newFileName);

	}
		WhatsAppChat savedDetail = whatsAppChatRepository.save(whatsAppChat);
		
		BeanUtils.copyProperties(savedDetail, returnValue);
		template.convertAndSend("/topic/message", returnValue);

		return returnValue;
	}

	@Override
	public String deleteChatDetail(Long whatsAppChatId) {
		
		WhatsAppChat whatsAppChat = whatsAppChatRepository.findByWhatsAppChatIdAndIsDeleted(whatsAppChatId, false);		

		whatsAppChat.setDeleted(true);
		WhatsAppChat savedDetail = whatsAppChatRepository.save(whatsAppChat);
		template.convertAndSend("/topic/message");

		return "Deleted successfully!";
	}

	@Override
	public List<WhatsAppChatResponseModel> getWhatsAppChats(int page, int limit) {
		List<WhatsAppChatResponseModel> returnValue = new ArrayList<>();
		
		if (page > 0)
			page = page - 1;		
		Pageable pageableRequest = PageRequest.of(page, limit, Sort.by("whatsAppChatId").descending());
		Page<WhatsAppChat> whatsAppChatPage = null;
		
		whatsAppChatPage = whatsAppChatRepository.findByIsDeleted(false, pageableRequest);		
		List<WhatsAppChat> whatsAppChats = whatsAppChatPage.getContent();		
		int totalPages = whatsAppChatPage.getTotalPages();
		for (WhatsAppChat whatsAppChat : whatsAppChats) {
			
			WhatsAppChatResponseModel responseModel = new WhatsAppChatResponseModel();
			BeanUtils.copyProperties(whatsAppChat, responseModel);
			if (returnValue.size() == 0) {
				responseModel.setTotalPage(totalPages);
			}			
			returnValue.add(responseModel);
		}
		return returnValue;
	}

	@Override
	public WhatsAppChatResponseModel changeChatStatus(long[] whatsAppChatIds) {
		WhatsAppChatResponseModel returnValue = new WhatsAppChatResponseModel();
		for(long id : whatsAppChatIds) {
	        WhatsAppChat whatsAppChat = whatsAppChatRepository.findByWhatsAppChatIdAndIsDeleted(id, false);		
			if (whatsAppChat == null)
				continue;
			whatsAppChat.setSeen(true);
			WhatsAppChat savedDetail = whatsAppChatRepository.save(whatsAppChat);		
			BeanUtils.copyProperties(savedDetail, returnValue);
		}
		template.convertAndSend("/topic/message", returnValue);

		return returnValue;
	}

}
