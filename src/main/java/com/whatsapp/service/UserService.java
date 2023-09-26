package com.whatsapp.service;
import java.util.List;

import com.whatsapp.request.UserDetailRequestModel;
import com.whatsapp.response.UserResponseModel;


public interface UserService{
	
	UserResponseModel saveUser(UserDetailRequestModel user);
	
	UserResponseModel getuser(String email);
	
	UserResponseModel getUserByUserId(String userId);
	
	UserResponseModel updateUser(String userId, UserDetailRequestModel userDetails);	
	List<UserResponseModel> getUsers(int page, int limit, String searchKey);
	
	UserResponseModel updateUserStatus(String id, UserDetailRequestModel userDetails);
	String checkEmail(String email);

	
	
}
