package com.whatsapp.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.whatsapp.entity.WhatsAppChat;

@Repository
public interface WhatsAppChatRepository extends PagingAndSortingRepository<WhatsAppChat, Long>{

	Page<WhatsAppChat> findByIsDeleted(boolean b, Pageable pageableRequest);

	WhatsAppChat save(WhatsAppChat whatsAppChat);

	WhatsAppChat findByWhatsAppChatIdAndIsDeleted(Long whatsAppChatId, boolean b);

}
