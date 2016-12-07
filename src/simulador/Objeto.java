package simulador;

/** 
 * @authors Martínez Carrera Dulce Carolina
 * 			Martinez Hernández Gabriela
 * 			Sánchez Lázcares Perla Melina
 * 			Jiménez Rocha Alejandra
 * 
 *  Clase Objeto que permitirá almacenar la información de la tabla, en este caso.
 *
 */
public class Objeto<T>{
	/**
	 * Variable atributo de tipo Lista.
	 */
    Lista<T> atributo = new Lista<T>();    
    
    public Objeto(String[] objeto,String[] tipos){
        insertarDato(objeto, tipos);
    }
    
    /**
     * Método que permite asignarle un índice al nodo, permitiendo su fácil ubicación. 
     */
    public void asignarIndiceObjeto(){
       Integer i = 1;
       Nodo<T> temporal = atributo.getInicio();
       while(temporal != null){
           temporal.setIndice(i);
           i++;
           temporal = temporal.getSiguiente();
       }
    }
    
    /**
     * Método que permite insertar los datos obtenidos de la interfaz.
     * @param objeto
     * @param tipos
     */
    public void insertarDato(String[] objeto, String[] tipos){
       for (int i = 0; i<objeto.length; i++){
           if (tipos[i].equalsIgnoreCase("Texto"))
               atributo.insertar(new Nodo<T>(objeto[i]));
           else if(tipos[i].equalsIgnoreCase("Numerico"))
               atributo.insertar(new Nodo<T>(Integer.valueOf(objeto[i])));
        }
     }
     
    /**
     * Método que permitirá comparar la información de los nodos.
     * @param nodo
     * @return
     */
    public Integer compararNodos(Nodo<T> nodo){
        Integer comparacion = 0;
        Nodo<T> temporal = atributo.getInicio();
        
  
        while (temporal != null){
            if(temporal.compareTo(nodo) == 0){
                comparacion = 0;
                break;
            }
            temporal = temporal.getSiguiente();
        }
        
        if (temporal == null)
        	comparacion = 1;
        return comparacion;
    }
     
    /**
     * Método que permite buscar un atributo dentro del nodo.
     * @param i
     * @return
     */
    public Nodo<T> buscarAtributo(Integer i){
        Nodo<T> registro, temporal = atributo.getInicio(); 
        
        while (temporal != null && temporal.getIndice() != i)
            temporal = temporal.getSiguiente();

        registro = temporal;
        return registro;
    }
    
    /**
     * Método que permitirá obtener los datos con un formato de tabla.
     * @return
     */
    public String datos(){
        Nodo<T> temporal = atributo.getInicio();
        String s = "";
        while (temporal != null){
           String cad = "" + temporal.getDato();
           s += String.format("%-12s",cad);
           temporal = temporal.getSiguiente();
        }
        
        s += " ";        
        return s;
    }
     
    /**
     * Método toString() sobreescrito de la clase Object.
     */
    public String toString (){
    	return atributo.toString();
    }
}