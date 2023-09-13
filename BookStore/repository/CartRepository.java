package com.BookStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BookStore.entity.CartModel;

@Repository
public interface CartRepository extends JpaRepository<CartModel,Integer> {

}
