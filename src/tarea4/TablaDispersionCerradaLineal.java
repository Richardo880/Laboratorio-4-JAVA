/*
    Grupo g20:  Diego Ramon Noguera Areco
                Ricardo Sebastian Toledo Barrios
 */
package tarea4;

import java.util.ArrayList;
import java.util.List;

public class TablaDispersionCerradaLineal<K extends Comparable, T> {

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
    public TablaDispersionCerradaLineal (int tamanho, Double factorCargaMax) throws Exception{
        if (factorCargaMax <= 0 || factorCargaMax > 1) {
            throw new Exception("Error... factor de carga no valido");
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
    public void imprimirTabla () {
        System.out.println("Tabla: ");
        System.out.println("    Elementos: " + this.elementos);
        System.out.println("    Tamanho: " + this.tamanho);
        System.out.println("    Factor de carga: " + (double)this.elementos / (double)this.tamanho);
        System.out.println("    Factor de carga max: " + this.factorCargaMax);
        for (int i = 0; i < tabla.size(); i++) {
            System.out.println("Indice " + i);
            if (this.tabla.get(i) != null) {
                System.out.println("    " + tabla.get(i).getClave() + " -> " + tabla.get(i).getDato());
            }
        }
    }
    
    // funcion para obtener la clave, recibiendo como parametro un dato
    public K obtenerClave (T pal) {
        for (int i = 0; i < tabla.size(); i++) {
            if (this.tabla.get(i) != null) {
                if(tabla.get(i).getDato() == pal){
                    //retornamos el numero de la clave
                    return tabla.get(i).getClave();
                }
                
            }
        }
        return null;//retornamos nulo si no existe el dato
    }
    
    //funcion que separa las palabras cuando hay numeros entre medio
    public void separarpalabras(String palabra, TablaDispersionCerradaLineal t1){
        String[] numero = palabra.split("([0-9]+)");//quitamos los numeros de la palabra
        int clave;
        int contador = 0;
        for(int i = 0;i<numero.length;i++){
            String palabraaux = numero[i];//palabras extraidas
            //consultamos si esta en el diccionario
            if(t1.obtenerClave(palabraaux.intern()) == null){   
                    clave = 51;
               }else{
                     clave = (int) t1.obtenerClave(palabraaux.intern());
                    }
                String buscar1 = (String) t1.buscar(clave);//si la palabra esta en el diccionario
                if(buscar1 != null){
                   contador++;//si esta en el diccionario sumamos 1
               }
        }
        if(contador == 2){//quinto errror, si el contador da 2, hay 2 palabras del diccionario con un digito entre medio
           System.out.println("La contrasenha "+palabra+" es insegura porque hay un digito en medio de 2 palabras del diccionario\n");//si esta

        }else{//finalmente, si ninguna de las otras condiciones fue verdadera, la contrasenha es segura
          System.out.println("La contrasenha "+palabra+" es segura\n");//imprimimos la confirmacion

        }
        
    } 
    
    public void ultimodigito(String palabra, TablaDispersionCerradaLineal t1){
                    
                    int clave;
                    //variable que nos indica si el ultimo caraceter es un digito del 0 al 9
                    int esdigito = 0;//por defecto es 0
                    //quitamos solamente el ultimo caracter para ver si es un digito
                    String lastdigito = palabra.substring(palabra.length()-1, palabra.length());
                    for(int i = 0;i<10;i++){
                        if( String.valueOf(i) == lastdigito){//en caso de que el ultimo digito no sea un numero usamos valueof
                            esdigito = 1; //si es digito, cambiamos a 1
                        }
                    }
                    
                    String palabraaux = palabra.substring(0, palabra.length()-1);
                    //quitamos el ultimo digito de la palabra para consultar
                    if(t1.obtenerClave(palabraaux.intern()) == null){   //repetimos el proceso de busqueda
                            clave = 51;
                    }else{
                        clave = (int) t1.obtenerClave(palabraaux.intern());
                    }

                    String buscar1 = (String) t1.buscar(clave);//vemos si la palabra recortada esta en el diccionario
                    if(buscar1 != null && esdigito == 1){
                        //cuarto error, la palabra si esta en la palabra con un digito del 0 al 9 como ultimo caracter
                        System.out.println("La contrasenha "+palabra+" es insegura porque hay un digito despues de una palabra del diccionario\n");//si esta
                    }else{
                        //sino vamos a la quinta prueba donde veremos si hay un digito entre 2 palabras del diccionario
                        separarpalabras(palabra, t1);//llamamos a la funcion pasandole como parametros la palabra actual y la tabla
                    }
    
    }
    
    // Agregar elemento
    public void agregar (K clave, T elemento) {
        // Calculo del hash
        int hash = clave.hashCode();

        // Indice de la tabla en el que se almacena
        int indice = Math.abs(hash % this.tamanho);

        // Agregamos a la tabla de hash
        int c = 0;
        while (c < this.tamanho && c != -1) {
            if (this.tabla.get(indice) == null || this.tabla.get(indice).getClave() == null) {
                this.tabla.set(indice, new Objeto<>(clave, elemento));
                c = -2; // Para indicar que ya se agrego
                this.elementos++;
            }
            indice++;
            if (indice == this.tamanho) {
                indice = 0;
            }
            c++;
        }
        
        if ((double)this.elementos / (double)this.tamanho >= this.factorCargaMax) {
            rehash();
        }
    }

    // Eliminar elemento
    public void eliminar (K clave, T elemento) {
        // Calculo del hash
        int hash = clave.hashCode();

        // Indice de la tabla en el que se almacena
        int indice = Math.abs(hash % this.tamanho);

        // Buscamos el objeto que coincida con la clave y el elemento
        int c = 0;
        while (c < this.tamanho && c != -1) {
            if (this.tabla.get(indice) != null) {
                if (this.tabla.get(indice).getClave() != null) {
                    if (this.tabla.get(indice).getClave().equals(clave) && this.tabla.get(indice).getDato().equals(elemento)) {
                        this.tabla.get(indice).setClave(null);
                        this.tabla.get(indice).setDato(null);
                        c = -2; // Para indicar que ya se elimino
                        this.elementos--;
                    }
                }
            } else {
                c = -2; // Para indicar que ya no hay que buscar
            }

            indice++;
            if (indice == this.tamanho) {
                indice = 0;
            }
            c++;
        }
    }

    // Buscar elemento
    public T buscar (K clave) {
        // Calculo del hash
        int hash = clave.hashCode();

        // Indice de la tabla en el que se almacena
        int indice = Math.abs(hash % this.tamanho);
        
        // Indice del elemento que queremos buscar
        int indiceLista = -1;

        // Buscamos el objeto que coincida con la clave
        int c = 0;
        while (c < this.tamanho && c != -1) {
            if (this.tabla.get(indice) != null) {
                if (this.tabla.get(indice).getClave() != null) {
                    if (this.tabla.get(indice).getClave().equals(clave)) {
                        indiceLista = indice;
                        c = -2; // Para indicar que ya no hay que buscar
                    }
                }
            } else {
                c = -2; // Para indicar que ya no hay que buscar
            }

            indice++;
            if (indice == this.tamanho) {
                indice = 0;
            }
            c++;
        }

        if (indiceLista != -1) { // Si el elemento se encuentra en la lista
            return this.tabla.get(indiceLista).getDato();
        } else { // Si el elemento no se encuentra
            return null;
        }
    }
    
    private void rehash () {
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
        
        // Aumentamos el tamanho de la tabla al doble
        for (int i = 0; i < this.tamanho; i++) {
            // LLenamos cada posicion de la tabla con objetos nulos
            tabla.add(null);
        }
        this.tamanho = this.tamanho * 2;
        
        // Por ultimo leemos la lista auxiliar y vamos agregando de vuelta los objetos
        for (int i = 0; i < listaAux.size(); i++) {
            agregar(listaAux.get(i).getClave(), listaAux.get(i).getDato());
        }
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
