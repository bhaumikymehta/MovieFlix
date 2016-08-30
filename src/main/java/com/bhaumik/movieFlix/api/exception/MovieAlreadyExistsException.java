package com.bhaumik.movieFlix.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST, reason="Movie Already Exists")
public class MovieAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;
}
