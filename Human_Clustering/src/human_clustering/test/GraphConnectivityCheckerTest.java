package human_clustering.test;

import human_clustering.model.Graph;
import human_clustering.model.GraphConnectivityChecker;
import static org.junit.Assert.*;
import org.junit.Test;

public class GraphConnectivityCheckerTest {

    @Test
    public void testGrafoConectado() {
        Graph graph = new Graph();
        graph.addVertice("A", 1, 2, 3, 4);
        graph.addVertice("B", 2, 3, 4, 5);

        boolean isConnected = GraphConnectivityChecker.isGraphConnected(graph);

        assertTrue("El grafo debe estar conectado", isConnected);
    }

    @Test
    public void testGrafoNoConectado() {
        Graph graph = new Graph();
        graph.addVertice("A", 1, 2, 3, 4);
        graph.addVertice("B", 2, 3, 4, 5);
        graph.removeArista(graph.aristas.get(0));;
        boolean isConnected = GraphConnectivityChecker.isGraphConnected(graph);

        //assertFalse("El grafo no debe estar conectado", isConnected);
    }
}

