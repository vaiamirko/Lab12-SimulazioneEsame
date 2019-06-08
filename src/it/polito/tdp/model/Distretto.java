package it.polito.tdp.model;

import com.javadocmd.simplelatlng.LatLng;

public class Distretto {
	
	private int IdDistretto;
	private double g_long;
	private double g_lat;
	public int getIdDistretto() {
		return IdDistretto;
	}
	public double getG_long() {
		return g_long;
	}
	public double getG_lat() {
		return g_lat;
	}
	public Distretto(int idDistretto, double g_long, double g_lat) {
		super();
		IdDistretto = idDistretto;
		this.g_long = g_long;
		this.g_lat = g_lat;
	}
	@Override
	public String toString() {
		return String.format("Distretto =%s ", IdDistretto);
	}
	
	public LatLng CalcolaLatLng() {
		LatLng lt1 = new LatLng(this.g_lat,this.g_long);
		return lt1;
		
	}
	


	
	
	
	

}
