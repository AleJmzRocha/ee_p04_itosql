package simulador;

/** 
 * @authors Mart�nez Carrera Dulce Carolina
 * 			Martinez Hern�ndez Gabriela
 * 			S�nchez L�zcares Perla Melina
 * 			Jim�nez Rocha Alejandra
 * 
 *  Clase Tabla que permitir� almacenar una Tabla.
 *
 */
public class Tabla<T> {
	/**
	 * Variables a ocupar durante la clase.
	 */
   private String nombre;
   String[] tablas;
   Lista<T> atributos, objetos;
   Integer registros;
   
   /**
    * Constructor que recibe el nombre de la tabla y los atributos que tendr�.
    * @param nombre
    * @param datos
    */
   public Tabla(String nombre, String [] datos){
	   atributos = new Lista<T>();
	   objetos = new Lista<T>();
	   tablas = new String[10];
       this.nombre = nombre;
       atributos = obtenerDatos(datos);
       asignarIndiceTabla();
   }
   
   /**
    * M�todo que devuelve una lista de objetos, es decir lo que se almacen� en las tablas.
    * @return
    */
   public Lista<T> getObjetos(){
       return objetos;
   }
   
   /**
    * M�todo que permite retornar un arreglo con los atributos de las tablas.
    * @return
    */
   public String[] getTablas(){
	   return tablas;
   }
   
   
   /**
    * M�todo que permite asignar un �ndice a la tabla para su f�cil ubicaci�n.
    */
   public void asignarIndiceTabla(){
       Integer i = 1;
       Nodo<T> temporal = atributos.getInicio();
       while(temporal != null){
           temporal.setIndice(i);
           i++;
           temporal = temporal.getSiguiente();
       }
       
       if (objetos != null){
           Nodo<T> aux = objetos.getInicio();
           while(aux != null){
               aux.getObjeto().asignarIndiceObjeto();
               aux = aux.getSiguiente();
           }
       }
   }
   
   /**
    * M�todo que permite obtener los datos recibidos en la interfaz y almacenarlos en una lista.
    * @param datos
    * @return
    */
   public Lista<T> obtenerDatos(String[] datos){
       Lista<T> auxiliar = new Lista<T>();
       for (int i = 0; i<datos.length; i++){
            String aux = datos[i];
            tablas[i] = aux ;
            auxiliar.insertar(new Nodo<T>(aux));
       }
       return auxiliar;
   }
   
   /**
    * M�todo que retorna el nombre de la tabla.
    * @return
    */
   public String getNombre(){
       return nombre;
   }
      
   /**
    * M�todo auxiliar que permite insertar los datos que contendr� la tabla.
    * @param objeto
    * @param tipos
    */
   public void insertarDato(String[] objeto,String[] tipos){
       objetos.insertar(new Nodo<T>(new Objeto<T>(objeto,tipos)));
   }
   
   /**
    * M�todo que permite obtener los datos contenidos en una tabla.
    * @return
    */
   public String getDatosObjeto(){
       return  objetos.toString();
   }

   /**
    * M�todo que devolver� los atributos de la tabla.
    * @return
    */
   public String atributosSalida(){
	   String s ="";
	   s += atributos.salida();
	   return s;
   }
   
   /**
    * M�todo que permitir� devolver los datos de la tabla.
    * @return
    */
   public String tablaSalida(){
       String s = "";	   
       Nodo<T> temporal1 = objetos.getInicio();
       Integer conta = 0;
       while (temporal1 != null){
    	   s += temporal1.getObjeto().datos() + "\n";
           temporal1 = temporal1.getSiguiente();
           conta++;
       }       
       registros = conta;
       return s;
   }
   
   /**
    * M�todo que permite devolver el n�mero de registros de una tabla.
    * @return
    */
   public Integer getRegistros(){
	   return registros;
   }
   
   /**
    * M�todo toString() sobreescrito de la clase Object.
    */
   @Override
   public String toString(){
       return atributos.toString();
   }
   
   /**
    * M�todo que permite obtener los �ndices de los atributos.
    * @return
    */
   public String[] getIndices(){
       Nodo<T> temporal = atributos.getInicio();
       String[] s = new String[atributos.tamanio()];
       Integer i = 0;
       while (temporal != null){
    	   s[i] = temporal.getIndice() + " " + temporal.getDato();
           temporal = temporal.getSiguiente();
           i++;
       }
       return s;
   }
   
   /**
    * M�todo que permitir� almacenar la informaci�n de un s�lo atributo.
    * @param indice
    * @return
    */
   public Nodo[] buscarNodos(Integer indice){
      Nodo<T> temporal = objetos.getInicio();
      Nodo[] nodos = new Nodo[objetos.tamanio()];
      Integer i = 0;
       
      while (temporal != null){
           nodos[i] = temporal.getObjeto().buscarAtributo(indice);
           i++;
           temporal = temporal.getSiguiente();
      }      
      return nodos;
   }
   
   /**
    * M�todo que devolver� los datos ordenados.
    * @param array
    * @return
    */
   public String datosOrdenados(Nodo[] array){
       Nodo[] arrays = array;
       
       String salida = "";
       for(Nodo nodo : array){
           String aux = " " + nodo.getDato();
           salida += String.format("%-12s%s",aux," \n");
       }
       return salida;
   }    
}