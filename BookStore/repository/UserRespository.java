package com.BookStore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.BookStore.dto.UserModelDTO;
import com.BookStore.entity.UserModel;

@Repository
public interface UserRespository extends JpaRepository<UserModel, Integer> {

	//@Query(value = "Select * from user_model where email =:email",nativeQuery = true)
	Optional<UserModel> findByEmail(String email);
	
	public UserModel findByUserId(int userId);
	
}
