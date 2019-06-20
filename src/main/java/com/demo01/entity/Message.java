package com.demo01.entity;

public class Message {
	public int status=0;
	public String msg="";
	
	public Message(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
