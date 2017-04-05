package com.yoogalu.jeetest.asynctrans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Handle implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String status;

	public Handle() {
	}

	public Handle(String status) {
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
		return "Handle [id=" + id + ", status=" + status + "]";
	}

}
