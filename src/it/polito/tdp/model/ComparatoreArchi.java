package it.polito.tdp.model;

import java.util.Comparator;

import org.jgrapht.graph.DefaultWeightedEdge;

public class ComparatoreArchi implements Comparator<Arco> {

	@Override
	public int compare(Arco o1, Arco o2) {
		// TODO Auto-generated method stub
		return (int) (o1.getDistanza()-o2.getDistanza());
	}

	
	

	

}
