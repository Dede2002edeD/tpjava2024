package aed;

import java.util.ArrayList;
import java.util.Comparator;
import aed.HeapTraslados.Handle;

public class Swap {
  public ArrayList<StructTraslado> handles1;
  public ArrayList<StructTraslado> handles2;
  public HeapTraslados heap1;
  public HeapTraslados heap2;

  public Swap(Traslado[] traslados) { // O(|t|)
    heap1 = new HeapTraslados(this, new ComparatorMax()); //O(1)
    heap2 = new HeapTraslados(this, new ComparatorMin()); //O(1)

    handles1 = heap1.heapify(traslados); // O(|t|)
    handles2 = heap2.heapify(traslados); // O(|t|)

//Si se insertara cada elemento uno a uno en un heap vacio usando encolar, el costo seria O(|t| log |t|),
// porque cada insercion tiene un costo log |t| y hay |t| elementos. Al usar heapify aprovechamos que todos los elementos
// estan disponibles de antemano, ajustando desde el ultimo nivel hasta la raiz, permitiendo obtener la operacion 
// nuevoSistema en la complejidad pedida.

    int j = 0; // O(1)
    while (j < handles2.size()) { // O(|t|)
      StructTraslado struct1 = handles1.get(j); // O(1)
      StructTraslado struct2 = handles2.get(j); // O(1)
      struct1.handleHeapOpuesto = struct2.handleHeap; // O(1)
      struct2.handleHeapOpuesto = struct1.handleHeap; // O(1)
      j++; // O(1)
    }
  }

  public class ComparatorMax implements Comparator<StructTraslado> {
    public int compare(StructTraslado struct1, StructTraslado struct2) { // O(1)
      int cmp1 = Integer.compare( // O(1)
          struct1.traslado.obtenerGananciaNeta(), // O(1)
          struct2.traslado.obtenerGananciaNeta() // O(1)
      );
      int cmp2 = Integer.compare(struct2.traslado.obtenerId(), struct1.traslado.obtenerId()); // O(1)
      return cmp1 == 0 ? cmp2 : cmp1; // O(1)
    }
  }

  public class ComparatorMin implements Comparator<StructTraslado> { 
    public int compare(StructTraslado struct1, StructTraslado struct2) { // O(1)
      int cmp1 = Integer.compare( // O(1)
        struct2.traslado.obtenertimestamp(), struct1.traslado.obtenertimestamp() // O(1)
      );
      return cmp1; // O(1)
    }
  }

  public class StructTraslado {
    public Handle handleHeapOpuesto;
    public Handle handleHeap;
    public Traslado traslado;
  }

  public void encolar(Traslado traslado) { // O(log |t|)
    StructTraslado struct1 = heap1.encolar(traslado); // O(log |t|)
    StructTraslado struct2 = heap2.encolar(traslado); // O(log |t|)
    struct1.handleHeapOpuesto = struct2.handleHeap; // O(1)
    struct2.handleHeapOpuesto = struct1.handleHeap; // O(1)
    System.out.println(""); // O(1)
  }

  public StructTraslado desencolarDeHeap1() { // O(log |t|)
    return heap1.desencolar(); // O(log |t|)
  }

  public StructTraslado desencolarDeHeap2() { // O(log |t|)
    return heap2.desencolar(); // O(log |t|)
  }
}