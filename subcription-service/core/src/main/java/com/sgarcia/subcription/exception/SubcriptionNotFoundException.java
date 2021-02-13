package com.sgarcia.subcription.exception;

public class SubcriptionNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7518557360980197800L;

	public SubcriptionNotFoundException(Long id) {
		super(String.format("Subcription with id %s not found!", id));
	}

}
