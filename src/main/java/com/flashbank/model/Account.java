// Copyright © FlashBank
// All rights reserved
// Code by Luiz Weitz

package com.flashbank.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import javax.persistence.ConstraintMode;

import org.springframework.security.core.userdetails.UserDetails;


@Entity
public class Account implements UserDetails{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String name;
	
	// how the user would like to be called

	private String surname;
	
	private String birthday_date;
	
	private String phone_number;
	
	@Column(unique=true)
	private String document;
	
	@Column(unique=true)
	private String email;
	
	private String password_account;
	
	private String address;
	
	private String state;
	
	private String city;
	
	private Boolean active;
	
	private String token;
	
	private String created_in_and_by;
	
	private String last_acess;
	
	private String last_update;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE)
	private List<Wallet> wallet = new ArrayList<Wallet>();
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE)
	private List<Card> card = new ArrayList<Card>();
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE)
	private List<Transference> transference = new ArrayList<Transference>();
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "account_role", uniqueConstraints = @UniqueConstraint (				
	 columnNames = {"account_id", "role_id"}, name = "unique_role_user"),
	 joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id", table = "account", unique = false,
	 foreignKey = @ForeignKey(name = "account_fk", value = ConstraintMode.CONSTRAINT)),
	 inverseJoinColumns = @JoinColumn (name = "role_id", referencedColumnName = "id", table = "role", unique = false, updatable = false,
	 foreignKey = @ForeignKey (name = "role_fk", value =ConstraintMode.CONSTRAINT)))
     private List<Role> roles = new ArrayList<Role>();

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getBirthday_date() {
		return birthday_date;
	}

	public void setBirthday_date(String birthday_date) {
		this.birthday_date = birthday_date;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	
	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword_account() {
		return password_account;
	}

	public void setPassword_account(String password_account) {
		this.password_account = password_account;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCreated_in_and_by() {
		return created_in_and_by;
	}

	public void setCreated_in_and_by(String created_in) {
		this.created_in_and_by = created_in;
	}

	public List<Wallet> getWallet() {
		return wallet;
	}

	public void setWallet(List<Wallet> wallet) {
		this.wallet = wallet;
	}
	
	public List<Card> getCard() {
		return card;
	}

	public void setCard(List<Card> card) {
		this.card = card;
	}
	
	public List<Transference> getTransference() {
		return transference;
	}

	public void setTransference(List<Transference> transference) {
		this.transference = transference;
	}

	public String getLast_acess() {
		return last_acess;
	}

	public void setLast_acess(String last_acess) {
		this.last_acess = last_acess;
	}

	public String getLast_update() {
		return last_update;
	}

	public void setLast_update(String last_update) {
		this.last_update = last_update;
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
		Account other = (Account) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
   // part of security	

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public Collection<Role> getAuthorities() {
		// TODO Auto-generated method stub
		return roles;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password_account;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.document;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.active;
	}

	
	

	

	
	

}
