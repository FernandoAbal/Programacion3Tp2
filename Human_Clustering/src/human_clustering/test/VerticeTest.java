package human_clustering.test;

import human_clustering.model.Vertice;
import static org.junit.Assert.*;
import org.junit.Test;

public class VerticeTest {

    @Test
    public void testVerticeIgualdad() {
        Vertice vertice1 = new Vertice("A", 1, 2, 3, 4);
        Vertice vertice2 = new Vertice("B", 2, 3, 4, 5);
        Vertice vertice3 = new Vertice("A", 1, 2, 3, 4);

        assertTrue("vertice1 debe ser igual a vertice3", vertice1.equals(vertice3));
        assertFalse("vertice1 no debe ser igual a vertice2", vertice1.equals(vertice2));
    }

    @Test
    public void testObtenerIndices() {
        Vertice vertice1 = new Vertice("A", 1, 2, 3, 4);
        int[] indicesVertice1 = vertice1.Indices();
        int[] expectedIndices = {1, 2, 3, 4};
        assertArrayEquals("Los índices de vertice1 deben ser {1, 2, 3, 4}", expectedIndices, indicesVertice1);
    }
}
