/*
    Grupo g20:  Diego Ramon Noguera Areco
                Ricardo Sebastian Toledo Barrios
*/
package tarea4;

public class Pruebas2 {
    public static void main (String [] args) throws Exception{
        /////////////////////
        /////////////////////
        // Dispersion abierta
        /////////////////////
        /////////////////////
        /*
        TablaDispersionAbierta<Integer, String> t1 = new TablaDispersionAbierta<>(10);
        
        t1.agregar(10, "Diego");
        t1.agregar(11, "Ramon");
        t1.agregar(12, "Noguera");
        t1.agregar(13, "Areco");
        t1.agregar(14, "Ricardo");
        t1.agregar(15, "Sebatian");
        t1.agregar(16, "Toledo");
        t1.agregar(17, "Barrios");
        t1.agregar(100, "Pepe");
        
        t1.eliminar(11, "Ramon");
        t1.eliminar(100, "Pepe");
        
        
        String buscar = t1.buscar(11);
        if (buscar != null) {
            System.out.println("Encontrado!! "+buscar);
        } else {
            System.out.println("Errorrrr");
        }
        t1.imprimirTabla();
        */
        
        /*
        TablaDispersionAbierta<String, Integer> t2 = new TablaDispersionAbierta<>(10);
        t2.agregar("Diego", 5497729);
        t2.agregar("Ramon", 5234523);
        t2.agregar("Noguera", 54234529);
        t2.agregar("Areco", 5497098);
        
        t2.eliminar("Noguera", 54234529);
        
        t2.imprimirTabla();
        */
        
        /////////////////////
        /////////////////////
        // Dispersion cerrada con exploracion lineal
        /////////////////////
        /////////////////////
       
        /*
        TablaDispersionCerradaLineal<Integer, String> t2 = new TablaDispersionCerradaLineal<>(10, 0.8);
        
               
        t2.agregar(10, "Max");
        t2.agregar(11, "Verstappen");
        t2.agregar(12, "Lewis");
        t2.agregar(13, "Hamilton");
        t2.agregar(14, "Fernando");
        t2.agregar(15, "Alonso");
        t2.agregar(16, "Mick");
        t2.agregar(17, "Schumacher");
        t2.agregar(18, "Latifi");
        t2.agregar(19, "Fungencio");
        t2.agregar(20, "Yegros");
        t2.agregar(21, "Nicholasso");
        t2.agregar(22, "Copernico");
        t2.agregar(23, "Francisco");
        t2.agregar(24, "Solamno");
        t2.agregar(25, "Lopez");
        t2.agregar(26, "Lopezasdfasdf");
        t2.agregar(27, "gsLopezasdfasdf");
        t2.agregar(28, "Lossdfgpezasdfasdf");
        t2.agregar(29, "Lopezassdfgsddfasdf");
        t2.agregar(30, "Lopezasdfasdsdfgsdf");
        t2.agregar(31, "Lopezasdfasdsdfgsdfgf");
        t2.agregar(34, "Ldopezasdfasdsdfgsdfgf");
        t2.agregar(37, "Jose Gaspar");
        t2.agregar(38, "Rodriguez de francia");
             
        t2.imprimirTabla();
        
        System.out.println(t2.longitudAgrupamientos());
        */
        
        /////////////////////
        /////////////////////
        // Dispersion cerrada con exploracion cuadratica
        /////////////////////
        /////////////////////
        /*
        TablaDispersionCerradaCuadratica<Integer, String> t3 =  new TablaDispersionCerradaCuadratica<>(13, 0.8);
        
        t3.agregar(0, "Diego");
        t3.agregar(10, "Ricardo");
        t3.agregar(101, "Toledo");
        t3.agregar(114, "Barrios");
        t3.agregar(127, "Sebastian");
        
        t3.eliminar(10, "Ricardo");
        t3.eliminar(127, "Sebastian");
        
        
        String buscar = t3.buscar(114);
        if (buscar != null) {
            System.out.println("Encontrado!! "+buscar);
        } else {
            System.out.println("Errorrrr");
        }
        
        
        t3.imprimirTabla();
        */
                
        /////////////////////
        /////////////////////
        // Dispersion cerrada con doble hashing
        /////////////////////
        /////////////////////
        
        TablaDispersionDobleHashing<Integer, String> t4 = new TablaDispersionDobleHashing<>(13, 0.8);
        
        t4.agregar(0, "Diego");
        t4.agregar(1, "Ramon");
        t4.agregar(2, "Noguera");
        t4.agregar(3, "Areco");
        t4.agregar(4, "Ricardo");
        t4.agregar(45, "Sebastian");
        t4.agregar(44, "Toledo");
        t4.agregar(43, "Barrios");
        t4.agregar(42, "Carmen");
        
        t4.eliminar(44, "Toledo");
        t4.eliminar(45, "Sebastian");
        
        String buscar = t4.buscar(43);
        if (buscar != null) {
            System.out.println("Encontrado!! "+buscar);
        } else {
            System.out.println("Errorrrr");
        }
        
        t4.imprimirTabla();
        
          
    }
}
