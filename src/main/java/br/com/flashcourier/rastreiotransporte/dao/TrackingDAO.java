package br.com.flashcourier.rastreiotransporte.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.flashcourier.rastreiotransporte.model.Tracking;

@Repository
@Transactional
public class TrackingDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Tracking> findAllTrackingsByCodePurchase(String codePurchase) {
		
		return manager.createQuery(" select t from Tracking t "
				+ " where t.purchase.code = :pCodePurchase "
				+ " order by t.date desc ", Tracking.class)
				.setParameter("pCodePurchase", codePurchase)
				.getResultList();
		
	}
	
	public Tracking save(Tracking tracking) {
		manager.persist(tracking);
		return tracking;
	}
	
}
