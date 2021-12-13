// Copyright © FlashBank
// All rights reserved
// Code by Luiz Weitz
package com.flashbank.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Card implements Serializable{

	private static final long serialVersionUID =1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(unique=true)
	private int number;
	
	private String name;
	
	private String good_thru;
	
	private String password;
	
	@Column(unique=true) 
	private int cod_security;
	
	private float limit_money;
	
	private String document;
	
	private String type;
	
	private Boolean active;
	
	@Column(name= "account_id", nullable=false)
	private Long account_id;
	
	@JsonIgnore
	@JoinColumn(name = "account_id", insertable=false, updatable=false)
	@ManyToOne(optional = false)
	private Account account;

	private String update_in_and_by;
	
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getGood_thru() {
		return good_thru;
	}

	public void setGood_thru(String good_thru) {
		this.good_thru = good_thru;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getCod_security() {
		return cod_security;
	}
	
	public void setCod_security(int cod_security) {
		this.cod_security = cod_security;
	}
	
	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public float getLimit_money() {
		return limit_money;
	}

	public void setLimit_money(float limit_money) {
		this.limit_money = limit_money;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Long getAccount_id() {
		return account_id;
	}

	public void setAccount_id(Long account_id) {
		this.account_id = account_id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public String getUpdate_in_and_by() {
		return update_in_and_by;
	}

	public void setUpdate_in_and_by(String update_in_and_by) {
		this.update_in_and_by = update_in_and_by;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
