package com.example.virlearning.config;
/**
 *	 插入数据异常，如果返回行数不是1，则抛出该异常
 * @author PHP
 *
 */
public class InsertException extends ServiceException{
	
	private static final long serialVersionUID = 1L;

	public InsertException() {
		super();
	}

	public InsertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InsertException(String message, Throwable cause) {
		super(message, cause);
	}

	public InsertException(String message) {
		super(message);
	}

	public InsertException(Throwable cause) {
		super(cause);
	}

}
