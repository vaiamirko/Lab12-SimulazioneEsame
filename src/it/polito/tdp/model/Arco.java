package it.polito.tdp.model;

public class Arco {
	
	
	public Arco(Distretto d1, Distretto d2, double distanza) {
		super();
		this.d1 = d1;
		this.d2 = d2;
		this.distanza = distanza;
	}
	private Distretto d1;
	private Distretto d2;
	private double distanza;
	public Distretto getD1() {
		return d1;
	}
	public Distretto getD2() {
		return d2;
	}
	public double getDistanza() {
		return distanza;
	}
	@Override
	public String toString() {
		return String.format("Arco %s, %s", d2, distanza);
	}

	
	
	
}
