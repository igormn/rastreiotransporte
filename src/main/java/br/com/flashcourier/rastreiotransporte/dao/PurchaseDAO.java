package br.com.flashcourier.rastreiotransporte.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.flashcourier.rastreiotransporte.model.Purchase;

@Repository
@Transactional
public class PurchaseDAO {
	
	@PersistenceContext
	private EntityManager manager;

	public Purchase find(String codePurchase) {
		return manager.find(Purchase.class, codePurchase);
	}
	
}
