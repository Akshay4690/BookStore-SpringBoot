package com.BookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.BookStore.dto.CartModelDTO;
import com.BookStore.dto.LoginDTO;
import com.BookStore.dto.UserModelDTO;
import com.BookStore.dto.responseDTO;
import com.BookStore.entity.UserModel;
import com.BookStore.service.IUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	IUserService service;

	//Register All User
	@PostMapping("/register")
	public ResponseEntity<responseDTO> registerUser(@Valid @RequestBody UserModelDTO model)
	{	
		UserModel details = service.registerNewUser(model);
		responseDTO response = new responseDTO (details,"User Register successfully");
		return new ResponseEntity<responseDTO>(response,HttpStatus.CREATED);
	}
	
	// Get All User
	@GetMapping("/getAll")
	public ResponseEntity<responseDTO> getAll()
	{	
		List<UserModel> details = service.getAllUser();
		responseDTO response = new responseDTO(details,"User Showing Successfully");
		return new ResponseEntity<responseDTO> (response,HttpStatus.OK);
	}
	
	//Get user by id
	@GetMapping("/getById/{id}")
	public ResponseEntity<responseDTO> getById(@PathVariable int id)
	{
		UserModel details = service.getUserById(id);
		responseDTO response = new responseDTO (details,"User get by id successfully");
		return new ResponseEntity<responseDTO>(response,HttpStatus.OK);
	}
	
	// Get user by email
	@GetMapping("/getByEmailID/{email}")
	public ResponseEntity<responseDTO> getByEmail(@PathVariable String email)
	{
		UserModel details = service.getUserByEmail(email);
		responseDTO response = new responseDTO(details,"Get Email by Information Successfully ");
		return new ResponseEntity<responseDTO> (response,HttpStatus.OK);
	}
	
	// Forget password
	@PutMapping ("/forgotPassword")
	public ResponseEntity<responseDTO> forgotPassword (@RequestBody LoginDTO model,@RequestParam String newpassword)
	{
		String details = service.forgotUserPassword(model,newpassword);
		responseDTO response = new responseDTO(details,"Password Changed");
		return new ResponseEntity<responseDTO>(response,HttpStatus.OK);
	}
	
	// Update User By Email
	@PutMapping("/updateUserByEmail/{email}")
	public ResponseEntity<responseDTO> UpdateUserByEmail (@PathVariable String email,@RequestBody UserModelDTO model)
	{
		UserModel details = service.UpdateEmail(email,model);
		responseDTO response = new responseDTO(details,"Update User by email successfully");
		return new ResponseEntity<responseDTO>(response,HttpStatus.OK);
	}
	
	// Delete User By Id
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<responseDTO> UserDeleteById(@PathVariable int id)
	{
		String details = service.deleteById(id);
		responseDTO response = new responseDTO(details,"User Deleted By id Successfully");
		return new ResponseEntity<responseDTO>(response,HttpStatus.OK);
	}
	
//	 Login User 
	@GetMapping("/login")
	public ResponseEntity<responseDTO> login(@RequestBody LoginDTO login) 
	{
		String details = service.loginUser(login);
		responseDTO response = new responseDTO(details,"User log in Successfully");
		return new ResponseEntity<responseDTO>(response,HttpStatus.OK);
	}
	
	// Reset Password
	@PutMapping("/resetPassword")
	public ResponseEntity<responseDTO> resetPassword(@RequestBody LoginDTO login,@RequestParam String newPassword)
	{
		UserModel details = service.resetUserPassword(login, newPassword);
		responseDTO response = new responseDTO(details,"password reset password !");
		return new ResponseEntity<responseDTO>(response,HttpStatus.CREATED);
	}
	
	
}
