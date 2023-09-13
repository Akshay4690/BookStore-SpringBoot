package com.BookStore.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.BookStore.dto.responseDTO;

@ControllerAdvice
public class OrderExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<responseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception)
	{
		List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
		List<String> errMsg = errorList.stream()
				.map(objErr -> objErr.getDefaultMessage())
				.collect(Collectors.toList());
		responseDTO response = new responseDTO(errMsg,"Exception while processing REST Request");
		return new ResponseEntity<responseDTO>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(OrderException.class)
	public ResponseEntity<responseDTO> handleBookStoreException (OrderException exception){
	
		responseDTO response = new responseDTO("Exception while processing REST Request",exception.getMessage());
		return new ResponseEntity<responseDTO>(response,HttpStatus.BAD_REQUEST);
	}
}
