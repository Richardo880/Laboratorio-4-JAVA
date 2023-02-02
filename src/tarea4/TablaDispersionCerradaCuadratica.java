/*
    Grupo g20:  Diego Ramon Noguera Areco
                Ricardo Sebastian Toledo Barrios
 */
package tarea4;

import java.util.ArrayList;
import java.util.List;

public class TablaDispersionCerradaCuadratica<K extends Comparable, T> {

    public class Objeto<K extends Comparable, T> {

        // Representara lo que vamos a meter en la tabla de hash
        private K clave = null; // Clave asociada a un dato
        private T dato = null; // Dato satelite

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

    private int elementos = 0;

    // Tabla de hash
    private List<Objeto<K, T>> tabla = new ArrayList<>();

    // Factor de carga maximo
    private Double factorCargaMax;

    // Constructor
    public TablaDispersionCerradaCuadratica(int tamanho, Double factorCargaMax) throws Exception {
        if (factorCargaMax <= 0 || factorCargaMax > 1) {
            throw new Exception("Error... factor de carga no valido");
        }

        if (!esPrimo(tamanho)) {
            throw new Exception("Error... tamanho debe ser primo");
        }

        this.tamanho = tamanho;
        this.factorCargaMax = factorCargaMax;

        // Inicializacion de la tabla
        for (int i = 0; i < this.tamanho; i++) {
            // LLenamos cada posicion de la tabla con objetos nulos
            tabla.add(null);
        }
    }

    // Imprimir tabla
    public void imprimirTabla() {
        System.out.println("Tabla: ");
        System.out.println("    Elementos: " + this.elementos);
        System.out.println("    Tamanho: " + this.tamanho);
        System.out.println("    Factor de carga: " + (double) this.elementos / (double) this.tamanho);
        System.out.println("    Factor de carga max: " + this.factorCargaMax);
        for (int i = 0; i < tabla.size(); i++) {
            System.out.println("Indice " + i);
            if (this.tabla.get(i) != null) {
                System.out.println("    " + tabla.get(i).getClave() + " -> " + tabla.get(i).getDato());
            }
        }
    }

    // Agregar elemento
    public void agregar(K clave, T elemento) {
        // Calculo del hash
        int hash = clave.hashCode();

        // Indice de la tabla en el que se almacena
        int indice = Math.abs(hash % this.tamanho);

        // Agregamos a la tabla de hash
        int c = 0;
        int i = 0;
        int indiceNuevo;
        while (c < this.tamanho && c != -1) {
            indiceNuevo = (indice + (int) Math.pow(i, 2)) % this.tamanho;
            if (this.tabla.get(indiceNuevo) == null || this.tabla.get(indiceNuevo).getClave() == null) {
                this.tabla.set(indiceNuevo, new Objeto<>(clave, elemento));
                c = -2; // Para indicar que ya se agrego
                this.elementos++;
            }
            i++;
            c++;
        }

        if ((double) this.elementos / (double) this.tamanho >= this.factorCargaMax) {
            rehash();
        }
    }

    // Eliminar elemento
    public void eliminar(K clave, T elemento) {
        // Calculo del hash
        int hash = clave.hashCode();

        // Indice de la tabla en el que se almacena
        int indice = Math.abs(hash % this.tamanho);

        // Buscamos el objeto que coincida con la clave y el elemento
        int c = 0;
        int i = 0;
        int indiceNuevo;
        while (c < this.tamanho && c != -1) {
            indiceNuevo = (indice + (int) Math.pow(i, 2)) % this.tamanho;
            if (this.tabla.get(indiceNuevo) != null) {
                if (this.tabla.get(indiceNuevo).getClave() != null) {
                    if (this.tabla.get(indiceNuevo).getClave().equals(clave) && this.tabla.get(indiceNuevo).getDato().equals(elemento)) {
                        this.tabla.get(indiceNuevo).setClave(null);
                        this.tabla.get(indiceNuevo).setDato(null);
                        c = -2; // Para indicar que ya se elimino
                        this.elementos--;
                    }
                }
            } else {
                c = -2; // Para indicar que ya no hay que buscar
            }

            i++;
            c++;
        }
    }

    // Buscar elemento
    public T buscar(K clave) {
        // Calculo del hash
        int hash = clave.hashCode();

        // Indice de la tabla en el que se almacena
        int indice = Math.abs(hash % this.tamanho);

        // Indice del elemento que queremos buscar
        int indiceLista = -1;

        // Buscamos el objeto que coincida con la clave
        int c = 0;
        int i = 0;
        int indiceNuevo;
        while (c < this.tamanho && c != -1) {
            indiceNuevo = (indice + (int) Math.pow(i, 2)) % this.tamanho;
            if (this.tabla.get(indiceNuevo) != null) {
                if (this.tabla.get(indiceNuevo).getClave() != null) {
                    if (this.tabla.get(indiceNuevo).getClave().equals(clave)) {
                        indiceLista = indiceNuevo;
                        c = -2; // Para indicar que ya no hay que buscar
                    }
                }
            } else {
                c = -2; // Para indicar que ya no hay que buscar
            }

            i++;
            c++;
        }

        if (indiceLista != -1) { // Si el elemento se encuentra en la lista
            return this.tabla.get(indiceLista).getDato();
        } else { // Si el elemento no se encuentra
            return null;
        }
    }

    private void rehash() {
        // Primero agregamos todos los objetos a una lista
        List<Objeto<K, T>> listaAux = new ArrayList<>();

        // Leemos la lista y vamos vaciando a la vez que copiamos los objetos
        for (int i = 0; i < this.tabla.size(); i++) {
            if (this.tabla.get(i) != null) {
                if (this.tabla.get(i).getClave() != null) {
                    listaAux.add(this.tabla.get(i));
                    this.tabla.set(i, null);
                } else {
                    this.tabla.set(i, null);
                }
            }
        }
        this.elementos = 0;

        // Aumentamos el tamanho de la tabla al primer numero primo mayor o igual al doble del tamanho anterior
        int tamanhoNuevo = this.tamanho * 2;
        while (!esPrimo(tamanhoNuevo)) {
            tamanhoNuevo++;
        }

        for (int i = 0; i < tamanhoNuevo - this.tamanho; i++) {
            // LLenamos cada posicion de la tabla con objetos nulos
            tabla.add(null);
        }
        this.tamanho = tamanhoNuevo;

        // Por ultimo leemos la lista auxiliar y vamos agregando de vuelta los objetos
        for (int i = 0; i < listaAux.size(); i++) {
            agregar(listaAux.get(i).getClave(), listaAux.get(i).getDato());
        }
    }

    private boolean esPrimo(int N) {
        for (int i = 2; i < N; i++) {
            if (N % i == 0) {
                return false;
            }
        }

        return true;
    }

    public List<Integer> longitudAgrupamientos() {
        List<Integer> tamanhos = new ArrayList<>();
        boolean abierto = false;
        int indice = 0;
        int contador;
        while (indice < this.tamanho) {
            if (this.tabla.get(indice) != null) {
                if (this.tabla.get(indice).getClave() != null) {// Es una cubeta ocupada
                    abierto = true;
                    contador = 0;
                    while (abierto) {
                        contador++;
                        if (indice + 1 < this.tamanho)
                            indice++;
                        else
                            break;

                        if (this.tabla.get(indice) != null) {
                            if (this.tabla.get(indice).getClave() != null) {
                                abierto = true;
                            } else {
                                abierto = false;
                            }
                        } else {
                            abierto = false;
                        }
                    }

                    tamanhos.add(contador);
                }
            }
            indice++;
        }

        return tamanhos;
    }
}
