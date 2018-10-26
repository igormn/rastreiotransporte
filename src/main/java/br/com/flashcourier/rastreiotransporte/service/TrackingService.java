package br.com.flashcourier.rastreiotransporte.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.flashcourier.rastreiotransporte.dao.PurchaseDAO;
import br.com.flashcourier.rastreiotransporte.dao.ShippingCompanyDAO;
import br.com.flashcourier.rastreiotransporte.dao.TrackingDAO;
import br.com.flashcourier.rastreiotransporte.dto.TrackingDTO;
import br.com.flashcourier.rastreiotransporte.model.Purchase;
import br.com.flashcourier.rastreiotransporte.model.ShippingCompany;
import br.com.flashcourier.rastreiotransporte.model.Tracking;
import br.com.flashcourier.rastreiotransporte.model.TrackingList;

@Service
public class TrackingService {
	
	@Autowired
	private TrackingDAO trackingDAO;

	@Autowired
	private ShippingCompanyDAO shippingCompanyDAO;
	
	@Autowired
	private PurchaseDAO purchaseDAO;
	
	public TrackingList findAllTrackingsByCodePurchase(String codePurchase) {
		TrackingList trackingList = new TrackingList();
		trackingList.setTrackings(this.trackingDAO.findAllTrackingsByCodePurchase(codePurchase));
		return trackingList;
	}
	
	public Tracking saveTracking(TrackingDTO trackingDTO) {
		
		Purchase purchase = this.purchaseDAO.find(trackingDTO.getCodePurchase());
		if (purchase == null) {
			throw new RuntimeException("ERROR: It was not possible to register the Tracking, because the Purchase not exist in the database of Flash Courier.");
		}
		
		ShippingCompany shippingCompany = this.shippingCompanyDAO.find(trackingDTO.getCnpjShippingCompany());
		if (shippingCompany == null) {
			throw new RuntimeException("ERROR: It was not possible to register the Tracking, because the Shipping Company not is registered in the Flash Courier.");
		}
		
		Tracking tracking = new Tracking();
		tracking.setPurchase(purchase);
		tracking.setShippingCompany(shippingCompany);
		
		Date dateFormatted;
		try {
			dateFormatted = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(trackingDTO.getDate());
		} catch (ParseException e) {
			throw new RuntimeException("It was not possible to register the Tracking, beacuse it was not possible to format the date.");
		}
		tracking.setDate(dateFormatted);
		tracking.setDescription(trackingDTO.getDescription());
		
		return this.trackingDAO.save(tracking);
	}

}
