package br.com.flashcourier.rastreiotransporte.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Client implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String cpf;
	
	private String name;
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL, targetEntity = Address.class, fetch = FetchType.EAGER)
	private Address address = new Address();
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
}
