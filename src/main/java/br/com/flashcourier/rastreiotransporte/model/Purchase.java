package br.com.flashcourier.rastreiotransporte.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Purchase implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String code;
	
	@OneToOne(cascade = CascadeType.ALL, targetEntity = Client.class, fetch = FetchType.EAGER)
	private Client client;
	
	private BigDecimal value;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
}
