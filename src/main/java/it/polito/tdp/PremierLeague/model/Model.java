package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	private PremierLeagueDAO dao;
	private Graph<Team,DefaultWeightedEdge> grafo;
	private Map<Integer,Team> teamsMap;
	
	public Model() {
		super();
		this.dao = new PremierLeagueDAO();
	}
	
	public void creaGrafo() {
		this.grafo = new SimpleDirectedWeightedGraph<Team,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		this.teamsMap = new HashMap<>();
		
		// vertici
		Graphs.addAllVertices(this.grafo, dao.listAllTeams(this.teamsMap));
		
		// archi
		
		// prepariamo le info di cui abbiamo bisogno per fare i collegamenti!
		for(Team ti : this.teamsMap.values()) {
			dao.getPunteggioClassifica(ti);
		}
		
		for(Team t1 : this.teamsMap.values()) {
			for(Team t2 : this.teamsMap.values()) {
				if(!t1.equals(t2)) {
					int peso = 0;
					if(t1.getPuntiClassifica()>t2.getPuntiClassifica()) {
						peso = t1.getPuntiClassifica() - t2.getPuntiClassifica();
						if(peso != 0)
							Graphs.addEdgeWithVertices(this.grafo, t1, t2, peso);
					}else {
						peso = t2.getPuntiClassifica() - t1.getPuntiClassifica();
						if(peso != 0)
							Graphs.addEdgeWithVertices(this.grafo, t2, t1, peso);
					}
				}
			}
		}
		
	}
	
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public List<Team> getAllVertices(){
		List<Team> vertici = new ArrayList<>(this.grafo.vertexSet());
		Collections.sort(vertici);
		return vertici;
	}
	
	public List<Avversario> getSquadrePeggiori(Team t){
		List<Avversario> peggiori = new ArrayList<>();
		
		for(DefaultWeightedEdge e : this.grafo.outgoingEdgesOf(t)) {
			
			Team team = this.grafo.getEdgeTarget(e);
			Avversario a = new Avversario (team,(int)this.grafo.getEdgeWeight(e));
			peggiori.add(a);	
		}
		Collections.sort(peggiori);
		return peggiori;
	}
	
	public List<Avversario> getSquadreMigliori(Team t){
		List<Avversario> migliori = new ArrayList<>();
		
		for(DefaultWeightedEdge e : this.grafo.incomingEdgesOf(t)) {
			
			Team team = this.grafo.getEdgeSource(e);
			Avversario a = new Avversario (team,(int)this.grafo.getEdgeWeight(e));
			migliori.add(a);	
		}
		Collections.sort(migliori);
		return migliori;
	}
	
	
	
}
