package simulador;

/** 
 * @authors Mart�nez Carrera Dulce Carolina
 * 			Martinez Hern�ndez Gabriela
 * 			S�nchez L�zcares Perla Melina
 * 			Jim�nez Rocha Alejandra
 * 
 *  Clase Objeto que permitir� almacenar la informaci�n de la tabla, en este caso.
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
     * M�todo que permite asignarle un �ndice al nodo, permitiendo su f�cil ubicaci�n. 
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
     * M�todo que permite insertar los datos obtenidos de la interfaz.
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
     * M�todo que permitir� comparar la informaci�n de los nodos.
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
     * M�todo que permite buscar un atributo dentro del nodo.
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
     * M�todo que permitir� obtener los datos con un formato de tabla.
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
     * M�todo toString() sobreescrito de la clase Object.
     */
    public String toString (){
    	return atributo.toString();
    }
}