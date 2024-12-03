package aed;

import java.util.ArrayList;
import java.util.Comparator;
import aed.Swap.StructTraslado;

public class HeapTraslados {
  private ArrayList<StructTraslado> heap;
  private Comparator<StructTraslado> comparator;
  private Swap swap;
  private int longitud;

  public class Handle { 
    public StructTraslado structTraslado; // O(1)
    private int indice; // O(1)

    public Handle(int indice) { // O(1)
      this.indice = indice; // O(1)
    }

    public void eliminar() { // O(log |t|)
      eliminarNodo(indice); // O(log |t|)
    }
  }

  public HeapTraslados(Swap swap, Comparator<StructTraslado> comparator) { // O(1) todas operaciones en complejidad constante
    this.heap = new ArrayList<>(); // O(1)
    this.comparator = comparator; // O(1)
    this.swap = swap; // O(1)
  }

  public ArrayList<StructTraslado> heapify(Traslado[] traslados) { // O(|t|)
    heap = new ArrayList<>(traslados.length); // O(|t|)
    ArrayList<StructTraslado> handles = new ArrayList<>(traslados.length); // O(|t|)

    longitud = traslados.length; // O(1)

    int j = 0; //O(1)
    while (j < longitud) { // O(|t|)
      StructTraslado structTraslado = crearStructTraslado(j, traslados[j]); // O(1)
      heap.add(j, structTraslado); //O(1) asumimos la operacion add de un arraylist como O(1) en este tp
      handles.add(j, structTraslado); //O(1) asumimos la operacion add de un arraylist como O(1) en este tp
      j++; //O(1)
    }

    int indiceActual = (longitud / 2) - 1; // O(1)
    while (indiceActual >= 0) { // O(|t|)
    // El metodo heapify utiliza el algoritmo de Floyd para construir el heap. 
    // Si bien la operacion Bajar tiene costo logaritmico, este se amortiza garantizando un costo lineal O(|t|)
      bajar(indiceActual); 
      indiceActual--; 
    }

    return handles;
  }

  private void reposicionar(int indice) { // O(log |t)
    bajar(subir(indice)); // O(log |t) + O(log |t|) = O(log|t|)
  }

  private int padre(int indice) { // O(1)
    return (indice - 1) / 2; // O(1)
  }

  private int hijoIzq(int indice) { // O(1)
    return 2 * indice + 1; // O(1)
  }

  private int hijoDer(int indice) { // O(1)
    return 2 * indice + 2; // O(1)
  }

  private int hijoMax(int indice) { // O(1) todas las operaciones son en complejidad constante
    int hijoIzq = hijoIzq(indice); // O(1)
    int hijoDer = hijoDer(indice); // O(1)
    int hijoMax = hijoIzq; // O(1)
    if (hijoDer >= longitud || comparator.compare(heap.get(hijoIzq), heap.get(hijoDer)) > 0) { // O(1)
      hijoMax = hijoIzq; // O(1)
    } else if (hijoDer < longitud) { // O(1)
      hijoMax = hijoDer; // O(1)
    }
    return hijoMax; // O(1)
  }

  private boolean tieneQueBajar(int indice) { // O(1) 
    int hijoMax = hijoMax(indice); // O(1)
    return hijoMax < longitud && comparator.compare(heap.get(hijoMax), heap.get(indice)) > 0; // O(1)
  }

  private boolean tieneQueSubir(int indice) { // O(1)
    int padre = padre(indice); // O(1)
    return indice > 0 && comparator.compare(heap.get(indice), heap.get(padre)) > 0; // O(1)
  }

  private int subir(int indice) { //  O(log|t|)
    int indiceActual = indice; // O(1)
    while (tieneQueSubir(indiceActual)) { // O(log|t|) en el peor caso, el nodo debe recorrer desde una hoja hasta la raiz, 
      //lo que implica pasar por la altura del heap, que es log(|t|). 
      int padre = padre(indiceActual); // O(1)
      swap(indiceActual, padre); // O(1)
      indiceActual = padre; // O(1)
    }
    return 0;
  }

  private void bajar(int indice) {  //  O(log|t|)
    int indiceActual = indice; // O(1)
    while (tieneQueBajar(indiceActual)) { // O(log |t|) en el peor caso, el nodo debe recorrer desde la raiz hasta una hoja,
      // lo que implica recorrer la altura del heap, es decir, log(|t|).
      int hijoMax = hijoMax(indiceActual); // O(1)
      swap(indiceActual, hijoMax); // O(1)
      indiceActual = hijoMax; // O(1)
    }
  }

  private void swap(int indice1, int indice2) { // O(1) todas operaciones en O(1)
    StructTraslado struct1 = heap.get(indice1); // O(1)
    StructTraslado struct2 = heap.get(indice2); // O(1)
    int ind1 = struct1.handleHeap.indice; // O(1)
    int ind2 = struct2.handleHeap.indice; // O(1)
    struct1.handleHeap.indice = ind2; // O(1)
    struct2.handleHeap.indice = ind1; // O(1)
    heap.set(indice2, struct1); // O(1)
    heap.set(indice1, struct2); // O(1)
  }

  private void eliminarNodo(int indice) { //O(log |t|)
    longitud--; // O(1)
    swap(indice, longitud); // O(1)
    reposicionar(indice); // O(log |t|)
  }

  public StructTraslado desencolar() { // O(log|t|)
    longitud--; // O(1)
    int primero = 0; // O(1)
    int ultimo = longitud; // O(1)
    swap(primero, ultimo); // O(1)
    bajar(0); // O(log|t|)
    return heap.get(longitud); // O(1)
  }

  private StructTraslado crearStructTraslado(int indice, Traslado traslado) { // O(1)
    Handle handle = new Handle(longitud); // O(1)
    Swap.StructTraslado structTraslado = swap.new StructTraslado(); // O(1)
    structTraslado.handleHeap = handle; // O(1)
    structTraslado.traslado = traslado; // O(1)
    handle.indice = indice; // O(1)
    return structTraslado; // O(1)
  }

  public StructTraslado encolar(Traslado traslado) { // O(log|t|)
    StructTraslado structTraslado = crearStructTraslado(longitud, traslado); // O(1)
    if (longitud >= heap.size()) { // O(1)
      heap.add(structTraslado); // O(1) agregar un elemento a final de arraylist se va asumir para el tp como O(1)
    } else {
      heap.set(longitud, structTraslado); // O(1)
    }
    subir(longitud - 1); // O(log|t|)
    longitud++; // O(1)
    return structTraslado; // O(1)
  }
}
