package aed;

import java.util.ArrayList;
import aed.Swap.StructTraslado;

public class BestEffort {
    private Ciudades ciudades;
    private Estadisticas stats;
    private HeapSuperavit heapsuperavitistico;
    private Swap swap;

    public BestEffort(int cantCiudades, Traslado[] traslados) { // O (|C| + |T|)
        this.swap = new Swap(traslados); // O(|T|)
        this.ciudades = new Ciudades(cantCiudades); // O (|C|)
        this.stats = new Estadisticas(); // O(1)
        this.heapsuperavitistico = new HeapSuperavit(cantCiudades); // O(|C|)
    }

    public void registrarTraslados(Traslado[] traslados) { // // O(|traslados| log|t|)
        int i = 0; // O(1)
        while (i < traslados.length) { // O(|traslados| log|t|) Esto se debe a que cada traslado que encolo tiene complejidad log |t| y encolo una cantidad de |traslados| traslados
            swap.encolar(traslados[i]); // O(log|t|)
            i++; // O(1)
        }
    }

    public int[] despacharMasRedituables(int n) { // O(n (log |T| + log |c|))
        int i = 0; // O(1)
        int[] array = new int[n]; // O(n)
        while (i < n) { // O(n (log |T| + log |c|))
            StructTraslado struct1 = swap.desencolarDeHeap1(); // O(log|T|)
            Traslado traslado = struct1.traslado; // O(1)

            struct1.handleHeapOpuesto.eliminar(); // O(log|T|))
            array[i] = traslado.obtenerId(); // O(1)

            stats.actualizarEstadisticas(traslado.obtenerGananciaNeta()); // O(1)
            ciudades.registrarGanancia(traslado.obtenerOrigen(), traslado.obtenerGananciaNeta()); // O(1)
            ciudades.registrarPerdida(traslado.obtenerDestino(), traslado.obtenerGananciaNeta()); // O(1)
            heapsuperavitistico.actualizarSuperavit(traslado.obtenerOrigen(), traslado.obtenerDestino(),
                    traslado.obtenerGananciaNeta()); // O(log |C|)

            i++; // O(1)
        }
        return array; // O(1)
    }

    public int[] despacharMasAntiguos(int n) { // O(n (log |T| + log |c|))
        int i = 0; // O(1)
        int[] array = new int[n]; // O(n)
        while (i < n) { // O(n (log |T| + log |c|))
            StructTraslado struct2 = swap.desencolarDeHeap2(); // O(log|T|)
            Traslado traslado = struct2.traslado; // O(1)

            struct2.handleHeapOpuesto.eliminar(); // O(log|T|)
            array[i] = traslado.obtenerId(); // O(1)

            stats.actualizarEstadisticas(traslado.obtenerGananciaNeta()); // O(1)
            ciudades.registrarGanancia(traslado.obtenerOrigen(), traslado.obtenerGananciaNeta()); // O(1)
            ciudades.registrarPerdida(traslado.obtenerDestino(), traslado.obtenerGananciaNeta()); // O(1)
            heapsuperavitistico.actualizarSuperavit(traslado.obtenerOrigen(), traslado.obtenerDestino(),
                    traslado.obtenerGananciaNeta()); // O(log |C|)

            i++; // O(1)
        }
        return array; // O(1)
    }

    public int ciudadConMayorSuperavit() { // O(1)
        int res = heapsuperavitistico.obtenerMaximo(); // O(1)
        return res; // O(1)
    }

    public ArrayList<Integer> ciudadesConMayorGanancia() { // O(1)
        ArrayList<Integer> res = ciudades.getCiudadConMayorGanancia(); // O(1)
        return res; // O(1)
    }

    public ArrayList<Integer> ciudadesConMayorPerdida() { // O(1)
        ArrayList<Integer> res = ciudades.getCiudadConMayorPerdida(); // O(1)
        return res; // O(1)
    }

    public int gananciaPromedioPorTraslado() { // O(1)
        int res = stats.getGananciasTotales() / stats.getTrasladosDespachados(); // O(1)
        return res; // O(1)
    }

}