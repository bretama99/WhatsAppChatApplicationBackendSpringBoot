package com.whatsapp.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.whatsapp.entity.UserEntity;
import com.whatsapp.repository.UserRepository;
import com.whatsapp.request.UserDetailRequestModel;
import com.whatsapp.response.UserResponseModel;
import com.whatsapp.service.UserService;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	
	@Autowired
	EntityManager entityManager;

	
	@Override
	public UserResponseModel saveUser(UserDetailRequestModel user) {
		
		UserResponseModel returnValue = new UserResponseModel();
			
    		UserEntity userEntity = new UserEntity();
    		BeanUtils.copyProperties(user, userEntity);
    		String defaultUserStatus = "NotVerified";
    		userEntity.setUserStatus(defaultUserStatus);
    		userEntity.setUserId("pppppppppppppppppppppppppppp");
    		UserEntity storedUserDetailsEntity = userRepository.save(userEntity);
    		
    		BeanUtils.copyProperties(storedUserDetailsEntity, returnValue);
		
		return returnValue;
		
	}


	@Override
	public UserResponseModel getuser(String email) {
		
		UserResponseModel returnValue = new UserResponseModel();
		UserEntity userEntity = userRepository.findByEmail(email);

		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

	@Override
	public UserResponseModel getUserByUserId(String UserId) {
			
		UserResponseModel returnValue = new UserResponseModel();
		UserEntity userEntity = userRepository.findByUserId(UserId);

		BeanUtils.copyProperties(userEntity, returnValue); 


		return returnValue;
	}

	@Override
	public UserResponseModel updateUser(String userId, UserDetailRequestModel userDetail) {
		
		UserResponseModel returnValue = new UserResponseModel();
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		if(userEntity == null) 
			throw new RuntimeException("User not found.");
		BeanUtils.copyProperties(userDetail, userEntity);
		userEntity.setEmail(userDetail.getEmail());
		userEntity.setFirstName(userDetail.getFirstName());
		userEntity.setMiddleName(userDetail.getMiddleName());
		userEntity.setLastName(userDetail.getLastName());
		userEntity.setMobilePhone(userDetail.getMobilePhone());
		userEntity.setEmail(userDetail.getEmail());
		userEntity.setUserType(userDetail.getUserType());
		userEntity.setRestaurantId(userDetail.getRestaurantId());
		
		UserEntity updatesUserDetailsEntity = userRepository.save(userEntity);
		BeanUtils.copyProperties(updatesUserDetailsEntity, returnValue);
		return returnValue;
	}
	
	@Override
	public UserResponseModel updateUserStatus(String userId, UserDetailRequestModel userDetail) {
		UserResponseModel returnValue = new UserResponseModel();
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		if(userEntity == null) 
			throw new RuntimeException("User not found.");
		
		userEntity.setUserStatus(userDetail.getUserStatus());		
		UserEntity updatesUserDetailsEntity = userRepository.save(userEntity);
		BeanUtils.copyProperties(updatesUserDetailsEntity, returnValue); 
		return returnValue;
	}
	
	@Override
	public List<UserResponseModel> getUsers(int page, int limit, String searchKey) {
		 
		List<UserResponseModel> returnValue = new ArrayList<>();
	    if(page > 0) page = page - 1; 
	    Pageable pageableRequest = PageRequest.of(page, limit, Sort.by("id").descending());
	    StringUtils.countOccurrencesOf(searchKey, " ");
	    searchKey.split(" ");
	    
	    if("".equals(searchKey)) {
	    	Page<UserEntity> usersPage = null;
		
	    		usersPage = userRepository.findAll(pageableRequest);
			int totalPages = usersPage.getTotalPages();
	 	    List<UserEntity> users = usersPage.getContent();
	 	    for(UserEntity userEntity : users) {
				UserResponseModel userResponseModel = new UserResponseModel();
		
				BeanUtils.copyProperties(userEntity, userResponseModel);
				userResponseModel.setEmail(userEntity.getEmail());
				if (returnValue.size() == 0) {
					userResponseModel.setTotalPages(totalPages);
				}
				

				returnValue.add(userResponseModel);
				//	 	    	BeanUtils.copyProperties(userEntity, userRest);
	 	    }
	    }
		return returnValue;
	}

	@Override
	public String checkEmail(String email) {
		if(userRepository.findByEmail(email) == null) {
			return "Email doesn't exist";
		}else {
			return "Email exists";
		}
	}
}
