package br.com.flashcourier.rastreiotransporte.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.flashcourier.rastreiotransporte.dto.TrackingDTO;
import br.com.flashcourier.rastreiotransporte.model.Tracking;
import br.com.flashcourier.rastreiotransporte.model.TrackingList;

@Controller
@RequestMapping("/flashcourier")
public class TrackingController {
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping("/")
	public String index() {
		return "tracking";
	}
	
	@RequestMapping("/search")
	public ModelAndView listAllTrackings(String codePurchase) {
		
		StringBuilder uri = new StringBuilder();
		uri.append(request.getRequestURL().toString().split(request.getRequestURI())[0]);
		uri.append("/flashcourier/transport/trackings/");
		uri.append(codePurchase);
		
		TrackingList response = restTemplate.getForObject(uri.toString(), TrackingList.class);
		
		ModelAndView modelAndView = new ModelAndView("tracking");
		modelAndView.addObject("trackings", response.getTrackings());
		
		return modelAndView;
	}
	
	@RequestMapping("/newtracking")
	public ModelAndView formNewTracking() {
		ModelAndView modelAndView = new ModelAndView("formNewTracking");
		return modelAndView;
	}
	
	@PostMapping("/savenewtracking")
	public ModelAndView saveNewTracking(TrackingDTO trackingDTO, RedirectAttributes redirectAttributes) {
		
		StringBuilder uri = new StringBuilder();
		uri.append(request.getRequestURL().toString().split(request.getRequestURI())[0]);
		uri.append("/flashcourier/transport/trackings/");
		
		try {
			restTemplate.postForEntity(uri.toString(), trackingDTO, Tracking.class);
		} catch (RuntimeException e) {
			throw new RuntimeException("ERROR: It was not possible to register the Tracking, because the Purchase/Shipping Company not exist in the database of Flash Courier.");
		}
		
		return this.listAllTrackings(trackingDTO.getCodePurchase());
		
	}
	
}
