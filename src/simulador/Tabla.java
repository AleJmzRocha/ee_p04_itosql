package simulador;

/** 
 * @authors Martínez Carrera Dulce Carolina
 * 			Martinez Hernández Gabriela
 * 			Sánchez Lázcares Perla Melina
 * 			Jiménez Rocha Alejandra
 * 
 *  Clase Tabla que permitirá almacenar una Tabla.
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
    * Constructor que recibe el nombre de la tabla y los atributos que tendrá.
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
    * Método que devuelve una lista de objetos, es decir lo que se almacenó en las tablas.
    * @return
    */
   public Lista<T> getObjetos(){
       return objetos;
   }
   
   /**
    * Método que permite retornar un arreglo con los atributos de las tablas.
    * @return
    */
   public String[] getTablas(){
	   return tablas;
   }
   
   
   /**
    * Método que permite asignar un índice a la tabla para su fácil ubicación.
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
    * Método que permite obtener los datos recibidos en la interfaz y almacenarlos en una lista.
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
    * Método que retorna el nombre de la tabla.
    * @return
    */
   public String getNombre(){
       return nombre;
   }
      
   /**
    * Método auxiliar que permite insertar los datos que contendrá la tabla.
    * @param objeto
    * @param tipos
    */
   public void insertarDato(String[] objeto,String[] tipos){
       objetos.insertar(new Nodo<T>(new Objeto<T>(objeto,tipos)));
   }
   
   /**
    * Método que permite obtener los datos contenidos en una tabla.
    * @return
    */
   public String getDatosObjeto(){
       return  objetos.toString();
   }

   /**
    * Método que devolverá los atributos de la tabla.
    * @return
    */
   public String atributosSalida(){
	   String s ="";
	   s += atributos.salida();
	   return s;
   }
   
   /**
    * Método que permitirá devolver los datos de la tabla.
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
    * Método que permite devolver el número de registros de una tabla.
    * @return
    */
   public Integer getRegistros(){
	   return registros;
   }
   
   /**
    * Método toString() sobreescrito de la clase Object.
    */
   @Override
   public String toString(){
       return atributos.toString();
   }
   
   /**
    * Método que permite obtener los índices de los atributos.
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
    * Método que permitirá almacenar la información de un sólo atributo.
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
    * Método que devolverá los datos ordenados.
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