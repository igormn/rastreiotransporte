package br.com.flashcourier.rastreiotransporte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.flashcourier.rastreiotransporte.dto.TrackingDTO;
import br.com.flashcourier.rastreiotransporte.model.Tracking;
import br.com.flashcourier.rastreiotransporte.model.TrackingList;
import br.com.flashcourier.rastreiotransporte.service.TrackingService;

@RestController
@RequestMapping("/flashcourier/transport/trackings")
public class TrackingRestController {
	
	@Autowired
	private TrackingService trackingService;
	
	@GetMapping("/{codePurchase}")
	public ResponseEntity<TrackingList> listAllTrackingsOfPurchase(@PathVariable String codePurchase) {
		TrackingList trackingList = this.trackingService.findAllTrackingsByCodePurchase(codePurchase);
		return new ResponseEntity<TrackingList>(trackingList, HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Tracking> saveTracking(@RequestBody TrackingDTO trackingDTO) {
		Tracking trackingSaved = this.trackingService.saveTracking(trackingDTO);
		return new ResponseEntity<Tracking>(trackingSaved, HttpStatus.CREATED);
	}
	
}
