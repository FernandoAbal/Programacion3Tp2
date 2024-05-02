package human_clustering.test;

import human_clustering.model.Arista;
import human_clustering.model.Vertice;
import static org.junit.Assert.*;
import org.junit.Test;

public class AristaTest {

    @Test
    public void testAristaIgualdad() {
        Arista arista1 = new Arista("A", "B", 5);
        Arista arista2 = new Arista("B", "A", 5); 
        Arista arista3 = new Arista("B", "C", 3);
        
        assertTrue("arista1 debe ser igual a arista2", arista1.equals(arista2));
        assertFalse("arista1 no debe ser igual a arista3", arista1.equals(arista3));
    }

    @Test
    public void testObtenerVecino() {
        Vertice verticeA = new Vertice("A", 1, 2, 3, 4);
        Vertice verticeB = new Vertice("B", 2, 3, 4, 5);

        Arista arista1 = new Arista("A", "B", 5);
        String vecinoA = arista1.getVecino(verticeA);
        String vecinoB = arista1.getVecino(verticeB);

        assertEquals("Vecino de A en arista1 debe ser B", "B", vecinoA);
        assertEquals("Vecino de B en arista1 debe ser A", "A", vecinoB);
    }

    @Test
    public void testObtenerPesoArista() {
        Arista arista1 = new Arista("A", "B", 5);
        int pesoArista1 = arista1.getWeight();
        assertEquals("El peso de arista1 debe ser 5", 5, pesoArista1);
    }
}

