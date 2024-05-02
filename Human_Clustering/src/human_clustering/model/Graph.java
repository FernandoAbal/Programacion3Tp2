package human_clustering.model;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;


import human_clustering.model.Arista;
import human_clustering.model.Graph;
import human_clustering.model.Vertice;


import java.util.Collections;
import java.util.LinkedList;

public class Graph {

	private Integer[][] indicesSimilaridadPersonas;
	private int tamanioGrafo;
	private LinkedList<Arista> aristas;
	public LinkedList<Vertice> vertices;
	private LinkedList<Arista> agmSolucion;
	private LinkedList<Vertice> grupo1;
	private LinkedList<Vertice> grupo2;

	public Graph() {
		this.aristas = new LinkedList<Arista>();
		this.agmSolucion = new LinkedList<Arista>();
		this.grupo1 = new LinkedList<Vertice>();
		this.grupo2 = new LinkedList<Vertice>();
	}

	private void generarMatrizIndiceSimilaridad(LinkedList<Vertice> vertices) { //
		this.tamanioGrafo = vertices.size();// Tamaño grafo es el tamaño de la linkedlist de personas

		inicializarMatrizIndiceSimilaridad(tamanioGrafo); // Le damos el tamaño para crear matriz

		for (int i = 0; i < tamanioGrafo; i++) {
			for (int j = 0; j < tamanioGrafo; j++) {
				if (i == j) {
					indicesSimilaridadPersonas[i][j] = -1; // Kruskal no admite negativos
				} else {// Agregamos la similaridad entre dos personas
					indicesSimilaridadPersonas[i][j] = calcularIndiceSimilaridadEntre(vertices.get(i), vertices.get(j));
				}
			}
		}
		
		
	}

	private void inicializarMatrizIndiceSimilaridad(int tamanioGrafo) {
		indicesSimilaridadPersonas = new Integer[tamanioGrafo][tamanioGrafo];
	}

	public int calcularIndiceSimilaridadEntre(Vertice p1, Vertice p2) { // Calculo indice de similaridad entre 2 															// personas
		int indice = 0;

		indice = Math.abs(p1.getInteresCiencia() - p2.getInteresCiencia())
				+ Math.abs(p1.getInteresDeporte() - p2.getInteresDeporte())
				+ Math.abs(p1.getInteresEspectaculo() - p2.getInteresEspectaculo())
				+ Math.abs(p1.getInteresMusica() - p2.getInteresMusica());

		return indice;
	}

	private int obtenerSimilaridad(int i, int j) {
		return indicesSimilaridadPersonas[i][j];
	}

	private boolean existeArista(Arista a) {
		return aristas.contains(a);
	}

	private void cargarListaDeAristas() {
		for (int i = 0; i < tamanioGrafo; i++) {
			for (int j = 0; j < tamanioGrafo; j++) {
				if (indicesSimilaridadPersonas[i][j] >= 0) {
					Arista arista = new Arista(vertices.get(i), vertices.get(j), obtenerSimilaridad(i, j));
					if (!existeArista(arista)) {
						aristas.add(arista);
					}
				}
			}
		}
	}


	private void ordenarListaDeAristas() {
		Collections.sort(aristas); // Ordena la LinkedList de menor a mayor segun el atributo peso
	}
	
	private void limpiarListas() {
		if(vertices != null && !vertices.isEmpty())
			vertices.clear();
		grupo1.clear();
		grupo2.clear();
		agmSolucion.clear();
		aristas.clear();
	}

	private void preparacionesParaGenerarAGM() {
		generarMatrizIndiceSimilaridad(vertices);
		cargarListaDeAristas();
		ordenarListaDeAristas();
	}
	
	@SuppressWarnings("unchecked")
	private void cargarListaVertices(LinkedList<Vertice> lista) {
		LinkedList<Vertice> vertices = (LinkedList<Vertice>) lista.clone();
		this.vertices = vertices;
	}

	public void generarAGM(LinkedList<Vertice> listavertices) {
		if (listavertices == null || listavertices.isEmpty()) {
			throw new IllegalStateException("Deben existir personas para clusterizar.");
		}
		limpiarListas();
		
		cargarListaVertices(listavertices);

		preparacionesParaGenerarAGM();

		ConnectedComponentsPrinter uf = new ConnectedComponentsPrinter(vertices);

		int aristaAgregadosAGM = 0; // arcos agregados que son parte del AGM
		int i = 0;// Indice para tomar un arco de la linkedlist de arcos
		int vertice = vertices.size();// Cant de vertices que forman parte del AGM
		
		while (aristaAgregadosAGM < vertice - 1) {
			Arista sigArista = aristas.get(i);
			i++;

			Vertice p1 = uf.find(sigArista.getSource());
			Vertice p2 = uf.find(sigArista.getDestino());

			if (!uf.generaCiclo(p1, p2)) {
				agmSolucion.add(sigArista);
				aristaAgregadosAGM++;
				uf.union(p1, p2);
			}
		}
		
		
	}

	public LinkedList<Arista> getAGMSolucion() {
		return this.agmSolucion;
	}

	private Arista eliminarAristaAGM() {
		Arista aristaEliminado = agmSolucion.getLast();
		agmSolucion.removeLast();

		return aristaEliminado;
	}

	public void generarDosGrupos() {
		Arista aristaEliminado = eliminarAristaAGM();

			grupo1.add(aristaEliminado.getSource());

			grupo2.add(aristaEliminado.getDestino());

			while (grupo1.size() + grupo2.size() < vertices.size()) {

				for (Arista aristaAGM : agmSolucion) {
					if (grupo1.contains(aristaAGM.getSource()) && !grupo1.contains(aristaAGM.getDestino())) {
						grupo1.add(aristaAGM.getDestino());
					} else if (!grupo1.contains(aristaAGM.getSource()) && grupo1.contains(aristaAGM.getDestino())) {
						grupo1.add(aristaAGM.getSource());
					} else if (grupo2.contains(aristaAGM.getSource()) && !grupo2.contains(aristaAGM.getDestino())) {

						grupo2.add(aristaAGM.getDestino());
					} else if (!grupo2.contains(aristaAGM.getSource()) && grupo2.contains(aristaAGM.getDestino())) {
						grupo2.add(aristaAGM.getSource());
					}

				}

			}

	}

	public LinkedList<Vertice> obtenerGrupo1() {
		return grupo1;
	}

	public LinkedList<Vertice> obtenerGrupo2() {
		return grupo2;
	}

}
