package ru.cs.ifmo.ligos.exception;

public class IsSameUserException extends Exception {
	private static final long serialVersionUID = 1L;
	public IsSameUserException() { super(); }
	public IsSameUserException(String message) { super(message);  }
	public IsSameUserException(String message, Throwable cause) { super(message, cause); }
	public IsSameUserException(Throwable cause) { super(cause); }
}
