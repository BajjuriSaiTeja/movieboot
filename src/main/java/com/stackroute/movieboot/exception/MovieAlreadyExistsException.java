package com.stackroute.movieboot.exception;

public class MovieAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MovieAlreadyExistsException(String string) {
		super(string);
	}

}