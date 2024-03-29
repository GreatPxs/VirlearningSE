package com.example.virlearning.config;
/**
 *	 业务异常,是当前项目中业务层抛出的异常的基类
 * @author PHP
 *
 */
public class ServiceException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public ServiceException(){
		super();
	}
	public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	public ServiceException(String message) {
		super(message);
	}
	public ServiceException(Throwable cause) {
		super(cause);
	}
	
}
