package com.BookStore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.BookStore.dto.LoginDTO;
import com.BookStore.dto.UserModelDTO;
import com.BookStore.dto.responseDTO;
import com.BookStore.entity.UserModel;
import com.BookStore.exception.UserException;
import com.BookStore.repository.UserRespository;
import com.BookStore.util.SendEmailService;
import com.BookStore.util.UserTokenUtil;


@Service
public class UserService implements IUserService {
	
	@Autowired
	UserRespository repository;
	
	@Autowired
	UserTokenUtil tokenUtil;
	
	@Autowired
	SendEmailService emailsender;
	
	//Register All User
	@Override
	public UserModel registerNewUser(UserModelDTO model) {
		UserModel user = new UserModel(model);
		repository.save(user);
		
		String token = tokenUtil.createToken(user.getUserId());
		System.out.println(token);
		
//		emailsender.sendEmail(user.getEmail(), " Conformation Email from AddressBook", "Hello"+" "+user.getFirstName()+
//		"\nGlad to tell you that , We invite you bookstore manager "+" "+
//				"\nWe got your details,"+", "+"\n Please check your information is :  " + "\n\n"+user.getFirstName() + " "+user.getLastName()
//				+"\n"+(user.getAddress())+" \n"+user.getEmail()+
//				"\n\nToken generated of the user is:" +(token)+" "+
//				"\nif this information is true then reply me ok ");
		
		return user;
	}

	// Get All User
	@Override
	public List<UserModel> getAllUser() {
		return repository.findAll();
	}

	// Get User By Id
	@Override
	public UserModel getUserById(int id) {
	
		Optional<UserModel> user = repository.findById(id);
		if (user.isPresent()) {
			
			return user.get();
		}
		else throw new UserException("User by id not found");
	}

	// Get User By Email
	@Override
	public UserModel getUserByEmail(String email) {
		Optional <UserModel> user = repository.findByEmail(email);
		if (user.isPresent())
		{
			return user.get();
		}
		else throw new UserException("User Not found");
	}

	// Delete User By Id
	@Override
	public String deleteById(int id) {
		
		Optional<UserModel> user = repository.findById(id);
		String email = user.get().getEmail();
		if(user.isPresent()) {
			repository.deleteById(id);
			emailsender.sendEmail(email, "  Email deleted Successfull" ,"user deleted "+id);
			return "User deleted Successfully,for id" +id;
		} 
		else throw new UserException ("User Not Found");	
	}

	//  Update User By Email
	@Override
	public UserModel UpdateEmail(String email, UserModelDTO model) {
		
		Optional <UserModel> user = repository.findByEmail(email);
	//	String email = user.get().getEmail();
		if(user.isPresent()) {
		
		user.get().setFirstName(model.getFirstName());
		user.get().setLastName(model.getLastName());
		user.get().setEmail(model.getEmail());
		user.get().setAddress(model.getAddress());
		user.get().setDob(model.getDob());
		user.get().setPassword(model.getPassword());
		repository.save(user.get());
//		emailsender.sendEmail(email,"Updated Successfull","user updated "+email);
		return user.get();
		}
		else throw new UserException("User Not Updated By Email");
		
	}

	// Forget PassWord
	@Override
	public String forgotUserPassword(LoginDTO model,String newpassword) {
		
		Optional<UserModel> user = repository.findByEmail(model.getEmail());
		if(user.isPresent()) {
			user.get().setPassword(newpassword);
			repository.save(user.get());
			return "Password Changed ";
		}	
		throw new UserException("Incorrect EmailId");

	}

	//Login User
	@Override
	public String loginUser(LoginDTO login) {
		
		Optional<UserModel> user = repository.findByEmail(login.getEmail());
		//	String email = user.getEmail();
		if(user.isPresent() && user.get().getPassword().equals(login.getPassword()))
		{	
			//	emailsender.sendEmail(email,"User Login Information","Login successfull !");
			return "Login Successfull";
		}
		else throw new UserException("username or password is incorrect");
	}

	// Reset Password
	@Override
	public UserModel resetUserPassword(LoginDTO login, String newPassword) {
		
		UserModel user = repository.findByEmail(login.getEmail()).orElse(null);
		if (user == null) {
			throw new UserException("Incorrect EmailId");
		}
		else if(user.getPassword().equals(login.getPassword()))
		{
			user.setPassword(newPassword);
			repository.save(user);
			return user;
		}
		else throw new UserException("Invalid EmailId or Password !");
	}
	
}
