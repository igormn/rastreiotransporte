package br.com.flashcourier.rastreiotransporte.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ShippingCompany implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String cnpj;
	
	private String name;
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
