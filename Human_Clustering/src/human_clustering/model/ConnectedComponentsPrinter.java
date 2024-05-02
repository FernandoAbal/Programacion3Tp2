package human_clustering.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import human_clustering.model.Graph;
import human_clustering.model.Vertice;

public class ConnectedComponentsPrinter {
	private HashMap<Vertice, Integer> tamanioCompConexa;
	private int cantComponentesConexas;
	private HashMap <Vertice, Vertice> listaDePadres;
	
	public ConnectedComponentsPrinter(LinkedList<Vertice> vertices) {
		
		this.cantComponentesConexas = vertices.size();
		this.tamanioCompConexa = new HashMap<Vertice, Integer>();
		
		listaDePadres = new HashMap<Vertice, Vertice>();
		inicializarListaPadres(vertices);
		inicializarListaTamanioCompConexas(vertices);
		
	}
	private void inicializarListaTamanioCompConexas(LinkedList<Vertice> vertices) {
		for(Vertice vertice : vertices) {
			tamanioCompConexa.put(vertice, 1);
		}
	}
	private void inicializarListaPadres(LinkedList<Vertice> vertices) {
	
		for(Vertice vertice : vertices) {
			listaDePadres.put(vertice, null);
		}
	}
	
	public Vertice find(Vertice vertice) {// 
		Vertice padre = vertice;
		
		
		while(listaDePadres.get(padre) != null ) {//Si es != null, no es padre
			padre = listaDePadres.get(padre);//Ahora el nuevo "padre" 
		}
		
		//PathCompression
		while(listaDePadres.get(vertice) != listaDePadres.get(padre)) {
			Vertice siguiente = listaDePadres.get(vertice);
			listaDePadres.put(vertice, padre);
			vertice = siguiente;
		}
		
		return padre;
	}

	public boolean generaCiclo(Vertice v1, Vertice v2) {
		return find(v1).equals(find(v2));
	}
	
	public int getCantComponentes() {
		return cantComponentesConexas;
	}
	
	//Unir las componentes conexas que contiene los v1 y v2
	public void union(Vertice v1, Vertice v2) {
		Vertice padreV1 = find(v1);
		Vertice padreV2 = find(v2);
		
		int tamaniov1 = tamanioCompConexa.get(padreV1);
		int tamaniov2 = tamanioCompConexa.get(padreV2);
		
		if(tamaniov1 < tamaniov2) {
			listaDePadres.put(padreV1, padreV2); //Ahora v2 es padre de v1
			tamanioCompConexa.put(padreV2, tamaniov1+tamaniov2);
		}else {
			listaDePadres.put(padreV2, padreV1); //Ahora v1 es padre de v2
			tamanioCompConexa.put(padreV1, tamaniov1+tamaniov2);
		}
		
	}
	
	
}