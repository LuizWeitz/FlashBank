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
public class Wallet implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private float amount;
	
	private String type_wallet;
	
	@Column(name= "account_id", nullable=false)
	private Long account_id;
	
	@JsonIgnore
	@JoinColumn(name = "account_id", insertable=false, updatable=false)
	@ManyToOne(optional = false)
	private Account account;
	
	private String created_in_and_by;
	
	private String update_in_and_by;
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}


	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Long getAccount_id() {
		return account_id;
	}

	public void setAccount_id(Long account_id) {
		this.account_id = account_id;
	}

	public String getType_wallet() {
		return type_wallet;
	}

	public void setType_wallet(String type_wallet) {
		this.type_wallet = type_wallet;
	}

	public String getCreated_in_and_by() {
		return created_in_and_by;
	}

	public void setCreated_in_and_by(String created_in_and_by) {
		this.created_in_and_by = created_in_and_by;
	}

	public String getUpdate_in_and_by() {
		return update_in_and_by;
	}

	public void setUpdate_in_and_by(String update_in_and_by) {
		this.update_in_and_by = update_in_and_by;
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
		Wallet other = (Wallet) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	
	
}
