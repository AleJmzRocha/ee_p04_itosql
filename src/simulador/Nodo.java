package simulador;

/** 
 * @authors Martínez Carrera Dulce Carolina
 * 			Martinez Hernández Gabriela
 * 			Sánchez Lázcares Perla Melina
 * 			Jiménez Rocha Alejandra
 * 
 *  Clase Nodo que permitirá almacenar los datos de la tabla.
 *
 */
public class Nodo<T> {
    /**
     * Variables a utilizar durante la clase.
     */
    private String texto;
    private Integer numero;
    private Tabla<T> tabla;
    private Objeto<T> objeto;
    private Nodo<T> siguiente;
    private Integer indice;
        
    /**
     * Constructor de la clase.
     * @param tabla
     */
    public Nodo(Tabla<T> tabla){
        this.tabla = tabla;
    }
    
    /**
     * Constructor sobrecargado que recibe como parámetro un dato entero. 
     * @param numero
     */
    public Nodo(Integer numero){
        this.numero = numero;
        siguiente = null;
    }

    /**
     * Constructor sobrecargado que recibe como parámetro un dato de tipo Objeto. 
     * @param numero
     */
    public Nodo(Objeto<T> objeto){
        this.objeto = objeto;
    }
    
    /**
     * Método que permitirá cambiar el índice del nodo.
     * @param indice
     */
    public void setIndice(Integer indice){
        this.indice = indice;
    }
    
    /**
     * Método que permite obtener el índice del nodo.
     * @return
     */
    public Integer getIndice(){
        return indice;
    }
    
    /**
     * Constructor sobrecargado que recibe como parámetro un dato de tipo cadena.
     * @param texto
     */
    public Nodo(String texto){
        this.texto = texto;
        siguiente = null;
    }
    
    /**
     * Método que permite devolver un dato de tipo tabla.
     * @return
     */
    public Tabla<T> getTabla(){
        return tabla;
    }
    
    /**
     * Método que permite devolver un dato de tipo Objeto.
     * @return
     */
    public Objeto<T> getObjeto(){
        return objeto;
    }
        
    /**
     * Método que permite obtener un dato de tipo Object.
     * @return
     */
    public Object getDato(){
        if (texto != null){
            return texto;
        }else if(numero != null) {
            return numero;
        }else if (tabla != null){
            return tabla;
        }else {
            return objeto;
        }
    }
      
    /**
     * Método que permite cambiar el dato del nodo.
     * @param dato
     */
    public void setDato(Object dato){ 
        if (dato instanceof String)
            texto = String.valueOf(dato);
        else 
            numero = (Integer)dato;
    }
    
    /**
     * Método que devuelve el enlace al siguiente nodo.
     * @return
     */
    public Nodo<T> getSiguiente(){
        return siguiente;
    }
    
    /**
     * Método que permite cambiar el enlace al siguiente nodo.
     * @param s
     */
    public void setSiguiente(Nodo<T> s){
        siguiente = s;
    }
    
    /**
     * Método toString() sobreescrito de la clase Object.
     */
    public String toString(){
        String salida;
        
        if (texto != null)
            salida = texto;
        else if (numero != null)
            salida = "" + numero;
        else if (tabla != null)
            salida = tabla.getNombre();
        else
            salida = objeto.toString();                
        return salida;
    }
        
    /**
     * Método compareTo() que servirá para comparar los datos posteriores.
     * @param nodo
     * @return
     */
    public Integer compareTo (Nodo nodo){
        Integer i = 0;
        
        if (this.getDato() instanceof String && nodo.getDato() instanceof String){
            String dato = String.valueOf(getDato()), siguiente = String.valueOf(nodo.getDato());
            i =  dato.compareTo(siguiente);
        }else if(this.getDato() instanceof Integer && nodo.getDato() instanceof Integer){
            Integer dato = (Integer) this.getDato(), siguiente = (Integer) nodo.getDato();
            i = dato.compareTo(siguiente);
        }       
       return i;
    }
}
