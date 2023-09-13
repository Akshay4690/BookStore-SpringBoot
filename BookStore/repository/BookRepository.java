package com.BookStore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.BookStore.entity.BookModel;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Integer>{

	@Query(value = "Select * from book_model where book_name LIKE %:bookName%",nativeQuery=true )
	BookModel findByBookName(String bookName);


}
