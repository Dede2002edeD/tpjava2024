package aed;

public class Estadisticas {
    // Atributos privados
    private int gananciasTotales;
    private int trasladosDespachados;

    // Constructor
    // Inicializo las ganancias totales y los traslados despachados en 0
    public Estadisticas() { // O(1) Dos operaciones en O(1)
        this.gananciasTotales = 0; // O(1)
        this.trasladosDespachados = 0; // O(1)
    }

    public int getGananciasTotales() { // O(1) Unica operacion en O(1)
        return gananciasTotales; // O(1)
    }

    public void setGananciasTotales(int gananciasTotales) { // O(1) Unica operacion en O(1)
        this.gananciasTotales = gananciasTotales; // O(1)
    }

    public int getTrasladosDespachados() { // O(1) Unica operacion en O(1)
        return trasladosDespachados; // O(1)
    }

    public void setTrasladosDespachados(int trasladosDespachados) { // O(1) Unica operacion en O(1)
        this.trasladosDespachados = trasladosDespachados; // O(1)
    }

    public void actualizarEstadisticas(int ganancia) { //O(1)
        this.gananciasTotales += ganancia; // Incrementa la ganancia total  O(1)
        this.trasladosDespachados++;       // Incrementa en 1 el contador de traslados despachados  O(1)
    }
}