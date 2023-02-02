/*
    Grupo g20:  Diego Ramon Noguera Areco
                Ricardo Sebastian Toledo Barrios
*/
package tarea4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TablaDispersionAbierta<K extends Comparable, T> {
    
    public class Objeto<K extends Comparable, T> {
        // Representara lo que vamos a meter en la tabla de hash
        private K clave; // Clave asociada a un dato
        private T dato; // Dato satelite

        public Objeto(K clave, T dato) {
            this.clave = clave;
            this.dato = dato;
        }

        public K getClave() {
            return clave;
        }

        public void setClave(K clave) {
            this.clave = clave;
        }

        public T getDato() {
            return dato;
        }

        public void setDato(T dato) {
            this.dato = dato;
        }
    }
    
    // Tamanho de la tabla
    private int tamanho;
    
    // Tabla de hash
    private List<LinkedList<Objeto<K, T>>> tabla = new ArrayList<>();
    
    // Constructor
    public TablaDispersionAbierta (int tamanho) {
        this.tamanho = tamanho;
        
        // Inicializacion de la tabla
        for (int i = 0; i < this.tamanho; i++) {
            // LLenamos cada posicion de la tabla con una lista enlazada vacia
            tabla.add(new LinkedList<>());
        }
    }
   
    // Imprimir tabla
    public void imprimirTabla () {
        System.out.println("Tabla: ");
        for (int i = 0; i < tabla.size(); i++) {
            System.out.println("Indice " + i);
            for (int j = 0; j < tabla.get(i).size(); j++) {
                System.out.println("    "+ tabla.get(i).get(j).getClave() + " -> " + tabla.get(i).get(j).getDato());
            }
        }
    }
    
    // obtener clave
    public K obtenerClave (T elemento) {
        System.out.println("Elemento: "+elemento);
        for (int i = 0; i < tabla.size(); i++) {
            //System.out.println("Indice " + i);
            for (int j = 0; j < tabla.get(i).size(); j++) {
                    
                    if(tabla.get(i).get(j).getDato() == elemento){
                        System.out.println("    "+ tabla.get(i).get(j).getClave() + " -> " + tabla.get(i).get(j).getDato());
                        return tabla.get(i).get(j).getClave();
                    }
            }
        }
        return null;
    }
    
    
    
    // Agregar elemento
    public void agregar (K clave, T elemento) {
        // Calculo del hash
        int hash = clave.hashCode();
        
        // Indice de la tabla en el que se almacena
        int indice = Math.abs(hash % this.tamanho);
        
        // Agregamos a la tabla de hash
        this.tabla.get(indice).add(new Objeto<>(clave, elemento));
    }
    
    // Eliminar elemento
    public void eliminar (K clave, T elemento) {
        // Calculo del hash
        int hash = clave.hashCode();
        
        // Indice de la tabla en el que se almacena
        int indice = Math.abs(hash % this.tamanho);
        
        // Indice en la lista enlazada
        int indiceLista = -1;
        
        // Buscamos el objeto que coincida con la clave y el elemento
        for (int i = 0; i < this.tabla.get(indice).size() && indiceLista == -1; i++) {
            if (this.tabla.get(indice).get(i).getClave().equals(clave) && this.tabla.get(indice).get(i).getDato().equals(elemento)) {
                indiceLista = i;
            }
        }
        
        // Eliminacion
        if (indiceLista != -1) {
            this.tabla.get(indice).remove(indiceLista);
        }
    }
    
    // Buscar elemento
    public T buscar (K clave) {
        // Calculo del hash
        int hash = clave.hashCode();
        
        // Indice de la tabla en el que se almacena
        int indice = Math.abs(hash % this.tamanho);

        // Indice en la lista enlazada
        int indiceLista = -1;
        
        // Buscamos el objeto que contenga la clave
        for (int i = 0; i < this.tabla.get(indice).size() && indiceLista == -1; i++) {
            if (this.tabla.get(indice).get(i).getClave() == clave) {
                indiceLista = i;
            }
        }
        
        if (indiceLista != -1) { // Si el elemento se encuentra en la lista
            return this.tabla.get(indice).get(indiceLista).getDato();
        } else { // Si el elemento no se encuentra
            return null;
        }
    }
}
