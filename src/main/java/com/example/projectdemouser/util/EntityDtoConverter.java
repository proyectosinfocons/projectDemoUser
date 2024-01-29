package com.example.projectdemouser.util;


import com.example.projectdemouser.dto.SignupRequest;
import com.example.projectdemouser.dto.UserRequest;
import com.example.projectdemouser.models.Users;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class EntityDtoConverter {

	@Autowired
	private ModelMapper modelMapper;




	public Users convertDtoToEntity(UserRequest userRequest) {
		return modelMapper.map(userRequest, Users.class);
	}
	
	public Users convertDtoToEntity(SignupRequest signupRequest) {
		return modelMapper.map(signupRequest, Users.class);
	}

	
}
