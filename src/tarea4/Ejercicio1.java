/*
    Grupo g20:  Diego Ramon Noguera Areco
                Ricardo Sebastian Toledo Barrios
*/
package tarea4;



public class Ejercicio1 {
    public static void main (String [] args) throws Exception{
        
        //vector de palabras de prueba que vamos a cargar
        String [] palabras = {"hola","quetalpioo","password","charlie1","dieguito3","qwertyuio","jennifer0","andrew2batman","killer0hunter","admin","contrasenha","master0ofpuppets","supermana","robert5deniro"};
        
        //creamos nuestra tabla de dispersion
        TablaDispersionCerradaLineal<Integer, String> t1 = new TablaDispersionCerradaLineal<>(10, 0.8);
        //agregamos nuestro diccionario de palabras
        String[] diccionario = {"123456","password","12345678","qwertyuio","123456789","12345","1234","111111","1234567","dragon","123123","123123","baseball","abc123","football",
"monkey","letmein","shadow","master","ricardo","michael","mustang","dieguito","qwertyuiop","2123321","1234 ... 890",
"p * s * y","superman","22706","54321","1qaz2wsx","666","f * cky * u","rock","jordan","jennifer","123qwe",
"121212","killer","trustno1","hunter","harley","zxcvbnm","asdfgh","buster","andrew","batman","soccer","tigger","charlie","robert"};
        int c = 0;
        
        //agregamos a la tabla de dispersion
        for(int i=0;i<50;i++){
            t1.agregar(c, diccionario[i]);
            c++;
        }
        
       //recorremos nuestro vector con los casos de prueba
        for(int j=0;j<palabras.length;j++){
            String palabra = palabras[j];
            int clave;
            if(palabra.length()>=8){//primero verificamos que la longitud sea mayor o igual a 8
                //obtenemos la clave pasandole como parametro la palabra
                 if(t1.obtenerClave(palabra) == null){//en caso de que no exista ponemos un valor aleatorio
                    clave = 120;
                }else{
                    clave = (int) t1.obtenerClave(palabra);//si existe guardamos la clave de esa palabra
                }
                
                String buscar1 = t1.buscar(clave); //buscamos si la palabra esta en el diccionario
                if(buscar1 != null){
                    //segundo error, la palabra si esta en el diccionario
                    System.out.println("La contrasenha "+palabra+" es insegura porque esta en el diccionario\n");//la palabra si esta
                }else{
                    //Si no esta veamos si pasa la tercera prueba
                    t1.ultimodigito(palabra, t1);//llamamos a la funcion donde buscamos la palabra menos el ultimo digito
                }
                
            }else{
                System.out.println("La contrasenha "+palabra+" es insegura porque tiene menos de 8 caracteres\n");//tiene menos de 8 caracteres
            }
        }
        

        
           
    }
}
