package simulador;

/** 
 * @authors Mart�nez Carrera Dulce Carolina
 * 			Martinez Hern�ndez Gabriela
 * 			S�nchez L�zcares Perla Melina
 * 			Jim�nez Rocha Alejandra
 * 
 *  Clase Lista que permitir� almacenar datos del programa.
 *
 */
public class Lista<T> {
	/**
	 * Variable inicio de tipo Nodo.
	 */
    public Nodo<T> inicio;
    
    /**
     * M�todo que devuelve el inicio de la lista.    
     * @return
     */
    public Nodo<T> getInicio(){
        return inicio;
    }
    
    /**
     * M�todo que permite cambiar el inicio de la lista.
     * @param nuevo
     */
    public void setInicio(Nodo<T> nuevo){
        inicio = nuevo;
    }
    
    /**
     * M�todo que permite la inserci�n de un elemento a la lista.
     * @param nuevo
     */
    public void insertar(Nodo<T> nuevo){
        Nodo<T> aux = nuevo, temporal = inicio;
        
        if(inicio == null){
            inicio = aux;
        }else{
            while (temporal.getSiguiente() != null){
                temporal = temporal.getSiguiente();
            }            
            temporal.setSiguiente(aux);
        }
    }
        
    /**
     * M�todo que permite obtener el tama�o de la lista.
     * @return
     */
    public Integer tamanio(){
        Integer i = 0;
        Nodo<T> temporal;
        temporal = inicio;
        
        while (temporal != null){
           i++;
           temporal = temporal.getSiguiente();
        }        
        return i;
    }
    
    /**
     * M�todo toString() sobreescrito de la clase Object que permitir� visualizar los elementos.
     * @return
     */
    @Override
    public String toString(){
        String salida = "";
        Nodo<T> temporal = inicio;
        
        while (temporal != null){
           salida += temporal.getDato() +" ";
           temporal = temporal.getSiguiente();
        }        
        return salida;
    }
    
    /**
     * M�todo que permite darle formato de tabla a los elementos de la lista.
     * @return
     */
    public String salida(){
        Nodo<T> temporal = inicio;
        String s = " ";
        
        while (temporal != null){
           s += String.format("%-12s",String.valueOf(temporal.getDato()));
           temporal = temporal.getSiguiente();
        }
        
        s += "\n ";
        return s;
    }
    
    /**
     * M�todo que permite eliminar un elemento de la lista.
     * @param nombre
     * @return
     */
    public Nodo<T> eliminar(String nombre){
       Nodo<T> temporal = inicio, anterior = null;
       Nodo<T> eliminado= null;
       
        while (!temporal.getTabla().getNombre().equalsIgnoreCase(nombre) && temporal != null){
           anterior = temporal;
           temporal = temporal.getSiguiente();
        }
        
        if (temporal != null){
          if (temporal.getSiguiente() != null){
            anterior.setSiguiente(temporal.getSiguiente());  
            eliminado = temporal; 
          }else {
             eliminado = anterior.getSiguiente();
             anterior.setSiguiente(null);
          }
       }
       
       return eliminado;
    }
   
     /**
      * M�todo que permite eliminar una tabla de la lista y retornarla.
      * @param dato
      * @return
      */
     public Nodo<T> eliminarTabla(Tabla<T> dato){
    	if( inicio == null ){
    		return null;
    	}
    	
    	Nodo<T> temporal = inicio;
    	Nodo<T> anterior = null;
    	
    	while(!temporal.getDato().equals(dato)){
    		anterior = temporal;
    		temporal = temporal.getSiguiente();
    	}
    	if(anterior != null){
    		anterior.setSiguiente(temporal.getSiguiente());
        	temporal.setSiguiente( null );
        	return temporal;
        }else if (temporal == inicio){
            if (temporal.getSiguiente() == null){
            inicio =null;
            }else {
                inicio = temporal.getSiguiente();
            }
            return temporal;
        }else{
    		return temporal;
    	}    	
     }
    
     /**
      * M�todo que permite buscar un elemento en la lista, en este caso, una tabla.
      * @param nombre
      * @return
      */
    public Tabla<T> buscarTabla(String nombre){
        Nodo<T> temporal = inicio;
        
        while (temporal  != null && !temporal.getTabla().getNombre().equalsIgnoreCase(nombre))
           temporal = temporal.getSiguiente();
                
        if (temporal != null)
        	return temporal.getTabla();
        else 
        	return null;
    }
}
