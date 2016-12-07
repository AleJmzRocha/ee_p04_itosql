package simulador;

/** 
 * @authors Mart�nez Carrera Dulce Carolina
 * 			Martinez Hern�ndez Gabriela
 * 			S�nchez L�zcares Perla Melina
 * 			Jim�nez Rocha Alejandra
 * 
 *  Clase Nodo que permitir� almacenar los datos de la tabla.
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
     * Constructor sobrecargado que recibe como par�metro un dato entero. 
     * @param numero
     */
    public Nodo(Integer numero){
        this.numero = numero;
        siguiente = null;
    }

    /**
     * Constructor sobrecargado que recibe como par�metro un dato de tipo Objeto. 
     * @param numero
     */
    public Nodo(Objeto<T> objeto){
        this.objeto = objeto;
    }
    
    /**
     * M�todo que permitir� cambiar el �ndice del nodo.
     * @param indice
     */
    public void setIndice(Integer indice){
        this.indice = indice;
    }
    
    /**
     * M�todo que permite obtener el �ndice del nodo.
     * @return
     */
    public Integer getIndice(){
        return indice;
    }
    
    /**
     * Constructor sobrecargado que recibe como par�metro un dato de tipo cadena.
     * @param texto
     */
    public Nodo(String texto){
        this.texto = texto;
        siguiente = null;
    }
    
    /**
     * M�todo que permite devolver un dato de tipo tabla.
     * @return
     */
    public Tabla<T> getTabla(){
        return tabla;
    }
    
    /**
     * M�todo que permite devolver un dato de tipo Objeto.
     * @return
     */
    public Objeto<T> getObjeto(){
        return objeto;
    }
        
    /**
     * M�todo que permite obtener un dato de tipo Object.
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
     * M�todo que permite cambiar el dato del nodo.
     * @param dato
     */
    public void setDato(Object dato){ 
        if (dato instanceof String)
            texto = String.valueOf(dato);
        else 
            numero = (Integer)dato;
    }
    
    /**
     * M�todo que devuelve el enlace al siguiente nodo.
     * @return
     */
    public Nodo<T> getSiguiente(){
        return siguiente;
    }
    
    /**
     * M�todo que permite cambiar el enlace al siguiente nodo.
     * @param s
     */
    public void setSiguiente(Nodo<T> s){
        siguiente = s;
    }
    
    /**
     * M�todo toString() sobreescrito de la clase Object.
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
     * M�todo compareTo() que servir� para comparar los datos posteriores.
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
