package aed;
import java.util.ArrayList;

public class HeapSuperavit {
    private ArrayList<Nodo> heap; 
    private int[] indices; 

    // Constructor 
    public HeapSuperavit(int cantidadCiudades) { // O(|C|)
        this.heap = new ArrayList<>(); // O(1)
        this.indices = new int[cantidadCiudades]; // O(1)

       
        for (int ciudad = 0; ciudad < cantidadCiudades; ciudad++) { // O(|C|)
            heap.add(new Nodo(ciudad, 0)); // O(1)
            indices[ciudad] = ciudad; // O(1)
        }
    }

    //Para el heap del superavit planteamos un heap de tamaño fijo (cant ciudades) que empieza con todos sus valores en 0
    // y que se va actualizando
    public void actualizarSuperavit(int ciudadOrigen, int ciudadDestino, int gananciaNeta) { // O(log |C|)
        // Esto esta amortizado por la operacion de despachar, no vamos a necesitar esto para obtener la ciudad con mayor superavit.
        // Es para mantener el orden cada vez que despachemos.
        
        heap.get(indices[ciudadOrigen]).superavit += gananciaNeta; // O(1)
        
        heap.get(indices[ciudadDestino]).superavit -= gananciaNeta; // O(1)

        
        subir(indices[ciudadOrigen]); // O(log |C|)
        bajar(indices[ciudadDestino]); // O(log |C|)
    }

    
    private void subir(int indice) { // O(log |C|) en el peor caso, el nodo debe recorrer desde una hoja hasta la raiz, 
        //lo que implica pasar por la altura del heap, que es log(|C|). 
        while (indice > 0) { // O(log |C|) 
            int padre = (indice - 1) / 2; // O(1)

            if (heap.get(indice).superavit > heap.get(padre).superavit || (heap.get(indice).superavit == heap.get(padre).superavit && heap.get(indice).ciudad < heap.get(padre).ciudad)) { // 0(1)
                swap(indice, padre); // O(1)
                indice = padre; // O(1)
            } else {
                break;
            }
        }
    }

    
    private void bajar(int indice) { // O(log |C|) similar a subir, en el peor caso, el nodo debe recorrer desde la raiz hasta una hoja,
        // lo que implica recorrer la altura del heap, es decir, log(|C|).
        int tamanio = heap.size(); //O(1)
        while (true) {  // O(log |C|) 
            int izquierda = 2 * indice + 1; // O(1)
            int derecha = 2 * indice + 2; // O(1)
            int mayor = indice; // O(1)

            if (izquierda < tamanio && (heap.get(izquierda).superavit > heap.get(mayor).superavit || (heap.get(izquierda).superavit == heap.get(mayor).superavit && heap.get(izquierda).ciudad < heap.get(mayor).ciudad))) { // 0(1)
                mayor = izquierda; // O(1)
            }

            if (derecha < tamanio && (heap.get(derecha).superavit > heap.get(mayor).superavit|| (heap.get(derecha).superavit == heap.get(mayor).superavit && heap.get(derecha).ciudad < heap.get(mayor).ciudad))) { // 0(1)
                mayor = derecha; // O(1)
            }

            if (mayor != indice) { // O(1)
                swap(indice, mayor); // O(1)
                indice = mayor; // O(1)
            } else {
                break;
            }
        }
    }

    
    private void swap(int i, int j) { // O(1) (son operaciones en complejidad O(1))
        Nodo temp = heap.get(i); // O(1)
        heap.set(i, heap.get(j)); // O(1)
        heap.set(j, temp); // O(1)

     
        indices[heap.get(i).ciudad] = i; // O(1)
        indices[heap.get(j).ciudad] = j; // O(1)
    }

    
    public int obtenerMaximo() { // O(1)(es una sola operacion en complejidad O(1))
        return heap.get(0).ciudad;  // O(1)
    }

    
    private static class Nodo {
        int ciudad;
        int superavit;

        public Nodo(int ciudad, int superavit) {
            this.ciudad = ciudad;
            this.superavit = superavit;
        }
    }
}