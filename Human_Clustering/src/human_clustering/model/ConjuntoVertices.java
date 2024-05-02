package human_clustering.model;

import java.util.LinkedList;

public class ConjuntoVertices {
	
	LinkedList<Vertice> vertices;

	public ConjuntoVertices() {
		this.vertices = new LinkedList<Vertice>(); //Las personas que se generan en la interfaz, se agregan a esta linkedlist 
	}
	
	
	public void crearVertice(String nombre, 
			int di, int mi, int ei, int ci ) {
		
		Vertice vertice = new Vertice(nombre,di,mi,ei,ci);		
				
		vertices.add(vertice);
		
	}
	
	public void mostrarVertices() {
		
		for(Vertice p:vertices) {
			System.out.println(p);
		}
	}
	
	public LinkedList<Vertice> getVertices(){
		return vertices;
	}
	
	public String[] getUltimaPersona() {
		return vertices.getLast().getDatosPersona();
	}

}
