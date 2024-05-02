package human_clustering.test;

import human_clustering.model.Graph;
import human_clustering.model.Arista;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class GraphTest {

    @Test
    public void testAgregarVerticesYAristas() {
        Graph graph = new Graph();

        graph.addVertice("A", 1, 2, 3, 4);
        graph.addVertice("B", 2, 3, 4, 5);
        graph.addVertice("C", 3, 4, 5, 6);

        
        assertEquals(3, graph.tamanio());

       
        Arista[][] weights = graph.getWeights();
        Arista[][] expectedWeights = new Arista[3][3];
        expectedWeights[0][0] = new Arista("A", "A", 0);
        expectedWeights[0][1] = new Arista("A", "B", 4);
        expectedWeights[0][2] = new Arista("A", "C", 8);
        expectedWeights[1][0] = new Arista("B", "A", 4);
        expectedWeights[1][1] = new Arista("B", "B", 0);
        expectedWeights[1][2] = new Arista("B", "C", 4);
        expectedWeights[2][0] = new Arista("C", "A", 8);
        expectedWeights[2][1] = new Arista("C", "B", 4);
        expectedWeights[2][2] = new Arista("C", "C", 0);
        System.out.println(weights[0][1].getWeight());
        System.out.println(weights[0][2].getWeight());
        System.out.println(weights[1][2].getWeight());
        System.out.println(expectedWeights[0][1].getWeight());
        System.out.println(expectedWeights[0][2].getWeight());
        System.out.println(expectedWeights[1][2].getWeight());
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(expectedWeights[i][j], weights[i][j]);
            }
        }

      
        ArrayList<String> vertices = graph.getVertices();
        ArrayList<String> expectedVertices = new ArrayList<>();
        expectedVertices.add("A");
        expectedVertices.add("B");
        expectedVertices.add("C");
        assertEquals(expectedVertices, vertices);

        
        LinkedList<Arista> aristas = graph.getAristas();
        LinkedList<Arista> expectedAristas = new LinkedList<>();
        expectedAristas.add(new Arista("A", "A", 0));
        expectedAristas.add(new Arista("A", "B", 4));
        expectedAristas.add(new Arista("A", "C", 8));
        expectedAristas.add(new Arista("B", "B", 0));
        expectedAristas.add(new Arista("B", "C", 4));
        expectedAristas.add(new Arista("C", "C", 0));
        

      
        Arista aristaMaxPeso = graph.getAristaDeMayorPeso();
        graph.removeArista(aristaMaxPeso);
        

       
        assertEquals(3, graph.tamanio());
    }
}


