package human_clustering.model;

import human_clustering.model.Arista;
import human_clustering.model.Vertice;


public class Arista implements Comparable<Arista> {

	Vertice source;
	Vertice destino;
	int weight;
	
	//CompareTo para ordenar los arcos por peso
	public int compareTo(Arista xArista) {
        return Integer.compare(this.weight, xArista.weight);
    }
	
	public Arista(Vertice source, Vertice destino, int weight) {
		
		this.source = source;
		this.destino = destino;
		this.weight = weight;
	}
	
	 public int getWeight() {
		return weight;
	}

	public Vertice getSource() {
		return source;
	}

	public Vertice getDestino() {
		return destino;
	}

	@Override
	public String toString() {
		return source.getNombre() + ", " + destino.getNombre() + " = " + weight ;
	}

	
}