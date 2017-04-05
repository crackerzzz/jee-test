package com.yoogalu.jeetest.asyncprocessing;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MessageStatus implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String status;

	public MessageStatus() {
	}

	public MessageStatus(String status) {
		this.status = status;
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", status=" + status + "]";
	}

}
