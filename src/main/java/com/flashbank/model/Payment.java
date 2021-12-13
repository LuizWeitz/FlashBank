// Copyright © FlashBank
// All rights reserved
// Code by Luiz Weitz

package com.flashbank.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Payment implements Serializable{
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String payer;
	
	private String destiny;
	
	private Float amount;
	
	@JoinColumn(insertable=false, updatable=false)
	private String password_card;
	
	private String description;
	
	private String type_of_payment;
	
	private String status;
	
	private String currency;
	
	private String country;
	
	private String establishment;
	
	private int portions;
	
	private String created_in_and_by;
	
	@OneToMany(mappedBy = "payment", cascade = CascadeType.REMOVE)
	private List<Portion> paymentPortions = new ArrayList<Portion>();


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public String getDestiny() {
		return destiny;
	}

	public void setDestiny(String destiny) {
		this.destiny = destiny;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPassword_card() {
		return password_card;
	}

	public void setPassword_card(String password_card) {
		this.password_card = password_card;
	}

	public String getType_of_payment() {
		return type_of_payment;
	}

	public void setType_of_payment(String type_of_payment) {
		this.type_of_payment = type_of_payment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getEstablishment() {
		return establishment;
	}

	public void setEstablishment(String establishment) {
		this.establishment = establishment;
	}

	public int getPortions() {
		return portions;
	}

	public void setPortions(int portions) {
		this.portions = portions;
	}

	public String getCreated_in_and_by() {
		return created_in_and_by;
	}

	public void setCreated_in_and_by(String created_in_and_by) {
		this.created_in_and_by = created_in_and_by;
	}
	
	public List<Portion> getPaymentPortions() {
		return paymentPortions;
	}

	public void setPaymentPortions(List<Portion> paymentPortions) {
		this.paymentPortions = paymentPortions;
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
		Payment other = (Payment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

}
