package br.com.flashcourier.rastreiotransporte.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.flashcourier.rastreiotransporte.model.ShippingCompany;

@Repository
@Transactional
public class ShippingCompanyDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public ShippingCompany find(String cnpj) {
		return manager.find(ShippingCompany.class, cnpj);
	}
	
}
