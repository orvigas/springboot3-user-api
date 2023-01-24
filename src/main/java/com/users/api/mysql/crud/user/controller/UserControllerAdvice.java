package com.users.api.mysql.crud.user.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.users.api.mysql.crud.user.exception.NotFoundException;

@ControllerAdvice
public class UserControllerAdvice {

	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<ProblemDetail> notFoundException(final NotFoundException xcp) {
		return ResponseEntity
				.of(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, xcp.getLocalizedMessage())).build();
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public final ResponseEntity<ProblemDetail> httpRequestMethodNotSupportedException(
			final HttpRequestMethodNotSupportedException xcp) {
		return ResponseEntity
				.of(ProblemDetail.forStatusAndDetail(HttpStatus.METHOD_NOT_ALLOWED, xcp.getLocalizedMessage())).build();
	}

	@ExceptionHandler({ HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class })
	public final ResponseEntity<ProblemDetail> httpMessageNotReadableException(
			final HttpMessageNotReadableException xcp) {
		return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, xcp.getLocalizedMessage()))
				.build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<ProblemDetail> methodArgumentNotValidException(
			final MethodArgumentNotValidException xcp) {
		return ResponseEntity
				.of(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, String.join(", ",
						xcp.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()))))
				.build();
	}

}
