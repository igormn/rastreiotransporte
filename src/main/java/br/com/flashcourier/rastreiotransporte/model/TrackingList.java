package br.com.flashcourier.rastreiotransporte.model;

import java.util.ArrayList;
import java.util.List;

public class TrackingList {
	
	private List<Tracking> trackings;
	
	public TrackingList() {
		this.trackings = new ArrayList<Tracking>();
	}

	public List<Tracking> getTrackings() {
		return trackings;
	}

	public void setTrackings(List<Tracking> trackings) {
		this.trackings = trackings;
	}
	
}
