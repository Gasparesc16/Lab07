package it.polito.tdp.dizionario.model;


import java.util.LinkedList;
import java.util.List;


import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionario.db.WordDAO;

public class Model {
	
	private List<String> parole;
	private List<String> vicini;
	
	private int lunghezza = 0;
	
	private UndirectedGraph <String, DefaultEdge> grafo;
	
	
	public Model() {
		
		parole = new LinkedList<String>();
		vicini = new LinkedList<String>();
		
	}

	public List<String> createGraph(int numeroLettere) {
		
		WordDAO wordDao = new WordDAO();
		
		if(numeroLettere != lunghezza){
			
			lunghezza = numeroLettere;
		
		parole.clear();
		parole.addAll(wordDao.getAllWordsFixedLength(numeroLettere));
		
		//System.out.println(parole);
		
		// Creo un nuovo grafo
		grafo = new SimpleGraph <String, DefaultEdge>(DefaultEdge.class);
		
		// Aggiungo i vertici al grafo
		Graphs.addAllVertices(grafo, parole);
		
		for(String p1: parole){
			
			for(String p2: parole){
				
				if(this.simili(p1,p2)){
					
					grafo.addEdge(p1, p2);
				}
			}
		}
		
		}
		
		return parole;
	}

	/**
	 * @return the lunghezza
	 */
	public int getLunghezza() {
		return lunghezza;
	}

	private boolean simili(String p1, String p2) {
		
		int diff = 0;
		
		for(int i=0; i<p1.length();i++){
			
			if( p1.charAt(i) != p2.charAt(i))
				diff++;
		}
		
		if(diff == 1)
			return true;
		
		
		return false;
	}

	public List<String> displayNeighbours(String parolaInserita) {
		
		vicini.clear();
		vicini.addAll(Graphs.neighborListOf(grafo, parolaInserita));

		return vicini;
	}

	public String findMaxDegree() {
		
		String st = "";
		
		String risultato = "";
		
		int grado = 0;
		
		
		for(int i=0; i<vicini.size();i++){
			
			String s = vicini.get(i);
			
			if(grafo.degreeOf(s) > grado){
				grado = grafo.degreeOf(s);
				st = s;
			}
			
		}
		
		risultato += st + ": " + "grado " + grado;
		
		
		//System.out.println("Model -- TODO");
		
		return risultato;
	}
}
