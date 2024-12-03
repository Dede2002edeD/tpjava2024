package aed;

public class Traslado {

    int id; // estos son los atributos
    int origen;
    int destino;
    int gananciaNeta;
    int timestamp;

    // mirko= traslado(1,2,3,4,5)
    // jesus= traslado(4,3,88,4,2)

    // y ese es el constructor
    public Traslado(int id, int origen, int destino, int gananciaNeta, int timestamp) {
        this.id = id; // O(1)
        this.origen = origen; // O(1)
        this.destino = destino; // O(1)
        this.gananciaNeta = gananciaNeta; // O(1)
        this.timestamp = timestamp; // O(1)
    }

 
    public int obtenerId() { // O(1) La unica operacion es O(1)
        return this.id; // O(1)
    }

    public int obtenerOrigen() { // O(1) La unica operacion es O(1)
        return this.origen; // O(1)
    }

    public int obtenerDestino() { // O(1) La unica operacion es O(1)
        return this.destino; // O(1)
    }
 
    public int obtenerGananciaNeta() { // O(1) La unica operacion es O(1)
        return this.gananciaNeta; // O(1)
    }

    public int obtenertimestamp() { // O(1) La unica operacion es O(1)
        return this.timestamp; // O(1)
    }


}