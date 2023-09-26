package com.whatsapp.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.whatsapp.request.UserDetailRequestModel;
import com.whatsapp.response.UserResponseModel;
import com.whatsapp.service.UserService;


@RestController
@RequestMapping("/api/user")
public class UserControllers {
	
	@Autowired
	UserService userService;
	
	@PostMapping
//	@ApiOperation(value = "Saves user detail to database", notes = "Provide user detail to save")
	public UserResponseModel saveUser(@RequestHeader(value = "header") @RequestBody UserDetailRequestModel userDetails)
	          {
		
		UserResponseModel returnValue = userService.saveUser(userDetails);
		
		return returnValue;
	}
	
	@GetMapping(path="/{userId}")
//	@ApiOperation(value = "Finds user by user id", notes = "Provide an user id to look up specific user from the users", response = UserResponseModel.class)
	public UserResponseModel getUserByUserId(@PathVariable String userId) {
		UserResponseModel returnValue = userService.getUserByUserId(userId);
		return returnValue;
	}

	
	
	@GetMapping
//	@ApiOperation(value = "Fetches users for given limit per page", notes = "Provide limit and page to get required count of users per page", response = UserResponseModel.class)
	public List<UserResponseModel> getUsers(@RequestParam(value = "searchKey", defaultValue = "") String searchKey,
	        @RequestParam(value = "page", defaultValue = "1") int page,
								   @RequestParam(value="limit", defaultValue = "25") int limit){
		
		List<UserResponseModel> returnValue = userService.getUsers(page, limit, searchKey);
		return returnValue;
	}
	
	@PutMapping(path="/{id}")
//	@ApiOperation(value = "Updates user detail based on the given user id", notes = "Provide user detail with user id to update the record", response = UserResponseModel.class)
	public UserResponseModel updateUser(@PathVariable String id, @RequestBody UserDetailRequestModel userDetails) {
		
		UserResponseModel returnValue = userService.updateUser(id, userDetails);
		
		return returnValue;
	}
	
	
}
