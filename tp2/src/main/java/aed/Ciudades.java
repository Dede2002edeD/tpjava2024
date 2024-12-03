package aed;
import java.util.ArrayList;

public class Ciudades {
    private int[] gananciasPorCiudad; 
    private int[] perdidasPorCiudad;  
    private ArrayList<Integer> ciudadConMayorGanancia; 
    private ArrayList<Integer> ciudadConMayorPerdida;  

  // Constructor
    public Ciudades(int cantidadCiudades) {
        this.gananciasPorCiudad = new int[cantidadCiudades]; // O(|C|)
        this.perdidasPorCiudad = new int[cantidadCiudades]; // O(|C|)
        this.ciudadConMayorGanancia = new ArrayList<>(); // O(1)
        this.ciudadConMayorPerdida = new ArrayList<>();  // O(1)
    }

   
    public void registrarGanancia(int ciudadOrigen, int gananciaNeta) { // O(1) 
        
        int viejaGanancia = gananciasPorCiudad[ciudadOrigen]; // O(1)


        actualizarCiudadConMayorGanancia(ciudadOrigen,viejaGanancia, gananciaNeta); // O(1) todas las operaciones en O(1) y no usamos ciclos
        
        gananciasPorCiudad[ciudadOrigen] += gananciaNeta; // O(1)
    }



    private void actualizarCiudadConMayorGanancia(int ciudad, int viejaGanancia,int gananciaNeta) { // O(1)
        int gananciaActual = gananciasPorCiudad[ciudad] + gananciaNeta; // O(1)
        
        if (ciudadConMayorGanancia.isEmpty()) {  // O(1)
            ciudadConMayorGanancia.add(ciudad); // O(1) en este tp se asume que agregar un elemento al final de un arraylist es O(1)
            return;
        }
        
        int mayorGanancia = gananciasPorCiudad[ciudadConMayorGanancia.get(0)]; // O(1)

        if(viejaGanancia == mayorGanancia){ // O(1)
            ciudadConMayorGanancia.clear(); // O(1)
            ciudadConMayorGanancia.add(ciudad); // O(1) en este tp se asume que agregar un elemento al final de un arraylist es O(1)
            return;
        }

        else{
            if(gananciaActual < mayorGanancia){ // O(1)
                return;
            }
            if(gananciaActual == mayorGanancia){ // O(1)
                ciudadConMayorGanancia.add(ciudad); // O(1) en este tp se asume que agregar un elemento al final de un arraylist es O(1)
                return;
            }
            else{
                ciudadConMayorGanancia.clear(); // O(1)
                ciudadConMayorGanancia.add(ciudad); // O(1) en este tp se asume que agregar un elemento al final de un arraylist es O(1)
                return;
            }
        }


        }

    public void registrarPerdida(int ciudadDestino, int gananciaNeta) { // O(1) 

        int viejaPerdida = perdidasPorCiudad[ciudadDestino]; // O(1)
        
        actualizarCiudadConMayorPerdida(ciudadDestino,viejaPerdida,gananciaNeta); // O(1) todas las operaciones en 0(1) y no usamos ciclos

        perdidasPorCiudad[ciudadDestino] -= gananciaNeta; // O(1)
    }
    private void actualizarCiudadConMayorPerdida(int ciudad,int viejaPerdida,int gananciaNeta) { // O(1)

        int perdidaActual = perdidasPorCiudad[ciudad] - gananciaNeta; // O(1)
    
        
        if (ciudadConMayorPerdida.isEmpty()) { // O(1)
            ciudadConMayorPerdida.add(ciudad);// O(1) en este tp se asume que agregar un elemento al final de un arraylist es O(1)
            return;
        }
        
        int mayorPerdida = perdidasPorCiudad[ciudadConMayorPerdida.get(0)]; // O(1)

        if(viejaPerdida == mayorPerdida){ // O(1)
            ciudadConMayorPerdida.clear(); // O(1)
            ciudadConMayorPerdida.add(ciudad); // O(1) en este tp se asume que agregar un elemento al final de un arraylist es O(1)
            return;
        }

        else{
            if(perdidaActual > mayorPerdida){ // O(1)
                return;
            }
            if(perdidaActual == mayorPerdida){ // O(1)
                ciudadConMayorPerdida.add(ciudad); // O(1) en este tp se asume que agregar un elemento al final de un arraylist es O(1)
                return;
            }
            else{
                ciudadConMayorPerdida.clear(); // O(1)
                ciudadConMayorPerdida.add(ciudad); // O(1) en este tp se asume que agregar un elemento al final de un arraylist es O(1)
                return;
            }
        }



    }

    public int[] getGananciasPorCiudad() { // O(1)(es una sola operacion en O(1))
        return gananciasPorCiudad; // O(1)
    }

    public int[] getPerdidasPorCiudad() { // O(1)(es una sola operacion en O(1))
        return perdidasPorCiudad; // O(1)
    }

    public ArrayList<Integer> getCiudadConMayorGanancia() { // O(1)(es una sola operacion en O(1)
        return ciudadConMayorGanancia; // O(1)
    }

    public ArrayList<Integer> getCiudadConMayorPerdida() { // O(1)(es una sola operacion en O(1))
        return ciudadConMayorPerdida; // O(1)
    }
}