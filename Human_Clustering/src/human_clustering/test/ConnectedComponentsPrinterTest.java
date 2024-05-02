package human_clustering.test;

import human_clustering.model.ConnectedComponentsPrinter;
import human_clustering.model.Graph;
import human_clustering.model.Vertice;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


public class ConnectedComponentsPrinterTest {

    @Test
    public void testImpresionComponentesConectados() {
        Graph graph = new Graph();
        graph.addVertice("A", 1, 2, 3, 4);
        graph.addVertice("B", 2, 3, 4, 5);
        graph.addVertice("C", 3, 4, 5, 6);
        

        Set<Vertice> visited = new HashSet<>();
        List<Set<Vertice>> components = new ArrayList<>();

        for (Vertice vertex : graph.vertices.values()) {
            if (!visited.contains(vertex)) {
                Set<Vertice> component = new HashSet<>();
                dfs(graph, vertex, visited, component);
                components.add(component);
            }
        }

        StringBuilder expectedOutput = new StringBuilder();
        int componentNumber = 1;
        for (Set<Vertice> component : components) {
            expectedOutput.append("Grupo " + componentNumber + ":\n");
            for (Vertice vertex : component) {
                expectedOutput.append(vertex.nombre + "\n");
            }
            componentNumber++;
        }

     
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ConnectedComponentsPrinter.printConnectedComponents(graph);

        String printedOutput = outContent.toString();

        //assertEquals("La salida impresa debe ser ", expectedOutput.toString(), printedOutput);
    }

    private void dfs(Graph graph, Vertice vertex, Set<Vertice> visited, Set<Vertice> component) {
        visited.add(vertex);
        component.add(vertex);

        for (Vertice neighbor : graph.vecinos.get(vertex)) {
            if (!visited.contains(neighbor)) {
                dfs(graph, neighbor, visited, component);
            }
        }
    }
}

