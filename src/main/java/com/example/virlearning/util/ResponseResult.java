package com.example.virlearning.util;

/**
 * 用于向客户端相应操作结果的类型
 * @author PHP
 * @param <T>	操作结果中包含的数据的类型
 *
 */
public class ResponseResult<T> {
	private Integer state;
	private String message;
	private T Data;
	
	public ResponseResult(){}
	public ResponseResult(Integer state){
		super();
		this.state = state;
	}
	public ResponseResult(Integer state, T data){
		super();
		this.state = state;
		this.Data = data;
	}
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return Data;
	}
	public void setData(T data) {
		Data = data;
	}
	@Override
	public String toString() {
		return "ResponseResult [state=" + state + ", message=" + message + ", Data=" + Data + "]";
	}
		
}
