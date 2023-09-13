package com.BookStore.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.BookStore.dto.LoginDTO;
import com.BookStore.dto.UserModelDTO;
import com.BookStore.dto.responseDTO;
import com.BookStore.entity.UserModel;

public interface IUserService {

	UserModel registerNewUser(UserModelDTO model);

	List<UserModel> getAllUser();

	UserModel getUserById(int id);

	UserModel getUserByEmail(String email);

	String deleteById(int id);

	UserModel UpdateEmail(String email, UserModelDTO model);

	String forgotUserPassword(LoginDTO model, String newpassword);

	String loginUser(LoginDTO login);

	UserModel resetUserPassword(LoginDTO login, String newPassword);


}
