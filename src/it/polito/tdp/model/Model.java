package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.db.EventsDao;

public class Model {
	
	Graph <Distretto,DefaultWeightedEdge> grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class); ;
	
	public void model() {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
	}
	public void creaGrafo(int anno) {
		EventsDao dao = new EventsDao();
		
		
		//List<Integer> distretti = new ArrayList<Integer>(dao.getAllDistretti());
		
	List<Distretto> vertici = new ArrayList<Distretto>(dao.getAllDistretti(anno));
	
	Graphs.addAllVertices(grafo, dao.getAllDistretti(anno));
	
	
	for(Distretto dpartenza : grafo.vertexSet()) {
		
		for(Distretto darrivo : grafo.vertexSet()) {
			if(!dpartenza.equals(darrivo) && !grafo.containsEdge(dpartenza, darrivo)) {
			LatLng lt1 = new LatLng(dpartenza.getG_lat(), dpartenza.getG_long());
			LatLng lt2= new LatLng(darrivo.getG_lat(), darrivo.getG_long());
			
				double distanza = LatLngTool.distance(lt1, lt2, LengthUnit.KILOMETER);
			
			Graphs.addEdgeWithVertices(grafo, dpartenza, darrivo, distanza);
			}
			
		}
		
		}	
			System.out.format("il grafo contiene %d vertici e %d archi",grafo.vertexSet().size(),grafo.edgeSet().size());
		
	}
	
	
	public List<Distretto> getVicini(Distretto d ){
		
		
		List<Distretto> vicini = new ArrayList();
		List<Distretto> finale = new LinkedList<>();
		List<DefaultWeightedEdge> archi = new ArrayList<>(grafo.edgesOf(d));
		List<Arco> archi2 = new ArrayList<>();
		
		for( DefaultWeightedEdge e : archi) {
			archi2.add(new Arco(d,grafo.getEdgeTarget(e),grafo.getEdgeWeight(e)));
		}
		
		Collections.sort(archi2, new ComparatoreArchi());
		
		for(Arco a1 : archi2) {
			
			vicini.add(a1.getD2());
			
		}
		return vicini;
		
		
			
		
		
		
		
		
		
		
	}
	
public List<Arco> getVicini2(Distretto d ){
		
		
		List<Distretto> vicini = new ArrayList();
		List<Distretto> finale = new LinkedList<>();
		List<DefaultWeightedEdge> archi = new ArrayList<>(grafo.edgesOf(d));
		List<Arco> archi2 = new ArrayList<>();
		
		for( DefaultWeightedEdge e : archi) {
			archi2.add(new Arco(d,grafo.getEdgeTarget(e),grafo.getEdgeWeight(e)));
		}
		
		Collections.sort(archi2, new ComparatoreArchi());
		
		return archi2;
		
		
			
		
		
		
		
		
		
		
	}
	public Graph<Distretto, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}
	
}
