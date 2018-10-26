package br.com.flashcourier.rastreiotransporte.dto;

import java.io.Serializable;

public class TrackingDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String codePurchase;
	private String cnpjShippingCompany;
	private String date;
	private String description;
	
	public String getCodePurchase() {
		return codePurchase;
	}
	public void setCodePurchase(String codePurchase) {
		this.codePurchase = codePurchase;
	}
	
	public String getCnpjShippingCompany() {
		return cnpjShippingCompany;
	}
	public void setCnpjShippingCompany(String cnpjShippingCompany) {
		this.cnpjShippingCompany = cnpjShippingCompany;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
