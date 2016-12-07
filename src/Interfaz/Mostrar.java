package Interfaz;

import java.awt.Rectangle;
import java.awt.event.*;
import javax.swing.*;
import simulador.*;

/** 
 * @authors Martínez Carrera Dulce Carolina
 * 			Martinez Hernández Gabriela
 * 			Sánchez Lázcares Perla Melina
 * 			Jiménez Rocha Alejandra
 * 
 *  Interfaz gráfica que permitirá mostrar una tabla, ordenarla por un criterio y un método de ordenación.
 *  Primero se mostrará la tabla completa y después los datos ordenados. 
 *
 */
public class Mostrar<T> extends JFrame implements ItemListener, ActionListener{
	/**
	 * Variables a ocupar durante la clase.
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> combo1, combo2, combo3;
	JButton aceptar, mostrar, regresar;
	JLabel jlabel = new JLabel(), jlabel2 = new JLabel(), jlabel3 = new JLabel(), jlabel4 = new JLabel(), jlabel5 = new JLabel(),jlabel6 = new JLabel();
	JPanel jpanel = (JPanel) this.getContentPane();
	JPanel jpanel4 = (JPanel) this.getContentPane();
	static Lista tablas;
	String[] indices;
	
	/**
	 * Constructor auxiliar.
	 */
	public Mostrar(){
		this(tablas);
	}
	
	/**
	 * Sobrecarga de constructor que recibe la lista con las tablas actuales.
	 * @param tablas
	 */
	public Mostrar(Lista<T> tablas){
		Mostrar.tablas = tablas;
		setLayout(null);
		setTitle("Mostrar Tabla");
		setSize(480,400);
		
		combo1 = new JComboBox<String>();
		combo1.setBounds(105,10,150,20);
		combo1.addItem("Tablas");
		listarTablas(tablas);
		add(combo1);
		
		combo2 = new JComboBox<String>();
		combo2.setBounds(85,40,150,20);
		combo2.addItem("Atributos");
		add(combo2);
		combo3 = new JComboBox<String>();
		combo3.setBounds(250,40,150,20);
		add(combo3);
		combo3.addItem("Ordenamientos");
		combo3.addItem("burbuja");
		combo3.addItem("burbuja_con_señal");
		combo3.addItem("baraja");
		combo3.addItem("baraja_con_bb");
		combo3.addItem("shaker");
		combo3.addItem("seleccion_directa");
		combo3.addItem("shell");
		combo3.addItem("insercion_directa");
		combo3.addItem("insercion_binaria");
		combo3.addItemListener(this);
		
		jlabel.setBounds(new Rectangle(60, 10, 220, 21));
		jlabel.setText("Tablas ");
		jpanel.setLayout(null);
		jpanel.add(jlabel, null);
		
		jlabel2.setBounds(new Rectangle(10, 40, 220, 21));
		jlabel2.setText("Ordenar por ");
		jpanel.setLayout(null);
		jpanel.add(jlabel2, null);
		
		jlabel3.setBounds(new Rectangle(10, 80, 220, 21));
		jpanel.add(jlabel3, null);
		
		jlabel4.setBounds(new Rectangle(10, 100, 320, 21));
		jpanel.add(jlabel4, null);
		
		jlabel5.setBounds(new Rectangle(210, 250, 220, 21));
		jpanel.add(jlabel5, null);
		
		jlabel6.setBounds(new Rectangle(10, 140, 320, 100));
		jpanel.add(jlabel5, null);
		
		aceptar = new JButton("Aceptar");
		aceptar.addActionListener(this);
		aceptar.setBounds(10,335,80,21);
		
		mostrar = new JButton("Mostrar");
		mostrar.addActionListener(this);
		mostrar.setBounds(280,10,85,21);
		
		regresar = new JButton("Regresar");
		regresar.addActionListener(this);
		regresar.setBounds(100,335,100,21);
		
		jpanel4.setLayout(null);
   		jpanel4.add(aceptar, null);
   		jpanel4.add(mostrar, null);
   		jpanel4.add(regresar, null);
	}
		
	/**
	 * Método que permite hacer una lista de las tablas que están almacenadas actualmente.
	 * @param lista
	 */
	 public void listarTablas(Lista<T> lista){
	       Nodo<T> temporal =  lista.getInicio();
	       while(temporal != null){
	    	   combo1.addItem(temporal.getTabla().getNombre());
	           temporal = temporal.getSiguiente();
	       }
	}
	 
	 /**
	 * Sobreescritura del método actionPerformed que permitirá accionar los botones.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == aceptar){
			Tabla<T> aux = tablas.buscarTabla(String.valueOf(combo1.getSelectedItem()));
			Integer sep;
			String[] auxi;
			auxi = String.valueOf(combo2.getSelectedItem()).split(" ");
			sep = Integer.parseInt(auxi[0]);
			Nodo[] arreglo = aux.buscarNodos(sep);
			Ordenamientos ordenar = new Ordenamientos();
			Nodo[] arrodenado = ordenar.eleccion(String.valueOf(combo3.getSelectedItem()),arreglo);
			System.out.println("Datos ordenados por: " + auxi[1]);
			System.out.println(aux.datosOrdenados(arrodenado));
			jlabel3.setText("Datos ordenados por: " + auxi[1]);
			jlabel4.setText(aux.datosOrdenados(arrodenado));
		}else if(e.getSource() == regresar){
				ItoSql<String> i = new ItoSql<String>();
				i.setVisible(true);
				dispose();
			}else if(e.getSource() == mostrar){
				Tabla<T> aux = tablas.buscarTabla(String.valueOf(combo1.getSelectedItem()));
				aux.asignarIndiceTabla();
	            indices = aux.getIndices();
				jlabel3.setText(aux.getNombre());
				jlabel5.setText(aux.tablaSalida());
				jlabel4.setText(aux.atributosSalida());
	            System.out.println(aux.atributosSalida());
	            System.out.println(aux.tablaSalida());
	            System.out.println(aux.getRegistros() + " registro(s).");
				jlabel5.setText(aux.getRegistros() + " registro(s).");
				for(int i = 0; i < indices.length; i++)
					combo2.addItem(indices[i]);
			}
	}

	/**
	 * Sobreescritura del método abstracto al implementar ItemListener.
	 * En éste método no se hará nada pero se debe sobreescribir. 
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {}

	/**
	 * Método main para prueba de la clase.	
	 * @param args
	 */	
	public static void main(String[] args) {
		Mostrar<String> m = new Mostrar<String>();
		m.setVisible(true);
	}
}