package com.example.virlearning.config;
/**
 * 修改异常，如果返回行数不是1，则抛出该异常
 * @author PHP
 *
 */
public class UpdateException extends ServiceException{
	private static final long serialVersionUID = 1L;

	public UpdateException() {
		super();
	}

	public UpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UpdateException(String message, Throwable cause) {
		super(message, cause);
	}

	public UpdateException(String message) {
		super(message);
	}

	public UpdateException(Throwable cause) {
		super(cause);
	}

}
