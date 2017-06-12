package com.demien.hibgeneric.dao;

public class DAOException extends Exception {
	private static final long serialVersionUID = 5144264647681662293L;

	public DAOException(String message) {
		super(message);
	}
	
	public DAOException(String message, Exception e) {
		this(message+" "+e.getMessage());
	}	
}
