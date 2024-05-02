package human_clustering.test;

import human_clustering.model.ArbolGeneradorMinimo;
import human_clustering.model.Graph;
import static org.junit.Assert.*;
import org.junit.Test;

public class ArbolGeneradorMinimoTest {

    @Test
    public void testGenerarArbolGeneradorMinimo() {
        Graph graph = new Graph();
        graph.addVertice("A", 1, 2, 3, 4);
        graph.addVertice("B", 2, 3, 4, 5);
        graph.addVertice("C", 3, 4, 5, 6);
        

        Graph minimumSpanningTree = ArbolGeneradorMinimo.generarPorPrim(graph);
    }
}
