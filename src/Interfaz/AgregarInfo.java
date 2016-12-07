package Interfaz;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import simulador.*;

/** 
 * @authors Martínez Carrera Dulce Carolina
 * 			Martinez Hernández Gabriela
 * 			Sánchez Lázcares Perla Melina
 * 			Jiménez Rocha Alejandra
 * 
 *  Interfaz gráfica que permite agregar información a las tablas previamente creadas.
 *
 */
public class AgregarInfo<T> extends JFrame implements ActionListener, ItemListener{
	/**
	 * Variables a ocupar en la clase AgregarInfo.
	 */
	private static final long serialVersionUID = 1L;
	JLabel jlabel = new JLabel(), jlabel2 = new JLabel(), label[];
	JPanel jpanel = (JPanel) this.getContentPane();
	JPanel jpanel4 = (JPanel) this.getContentPane();	
	JTextField jtf[];
	JButton aceptar, agregar, regresar;
	static String nombre;
	static String[] atributos, tipos;
	JComboBox<String> combo1;
	static Lista tablas;
	static Tabla t;
	
	/**
	 * Constructor auxiliar.
	 */
	public AgregarInfo(){			
		this(nombre, atributos, tablas, t, tipos);
	}
	
	/**
	 * Sobrecarga de constructor.
	 * Constructor que recibe como parámetros los datos necesarios de la tabla previamente creada.
	 * @param nombre
	 * @param atributos
	 * @param tablas
	 * @param t
	 * @param tipos
	 */
	public AgregarInfo(String nombre, String[] atributos, Lista<T> tablas,Tabla<T> t, String[] tipos){
		AgregarInfo.t = t;
		AgregarInfo.tablas = tablas;
		AgregarInfo.nombre = nombre;	
		AgregarInfo.atributos = atributos;
		AgregarInfo.tipos = tipos;
		label = new JLabel[atributos.length];
		jtf = new JTextField[atributos.length];
		setLayout(null);
		setTitle("Agregar Información");
		setSize(430,570); 
		
		combo1 = new JComboBox<String>();
		combo1.setBounds(150, 5, 100, 21);
		combo1.addItem("Tablas");
		listarTablas(tablas);
	    add(combo1);
		
		jlabel.setBounds(new Rectangle(10, 5, 220, 21));
		jlabel.setText("Seleccione la Tabla: " );
		
		jlabel2.setBounds(new Rectangle(10, 480, 220, 21));
		
		jpanel.setLayout(null);
		jpanel.add(jlabel, null);
		jpanel.add(jlabel2, null);
		for(int i = 0; i < atributos.length; i++){
			label[i] = new JLabel();
			jtf[i] = new JTextField();
    		jpanel.add(label[i], null);
    		jpanel.add(jtf[i], null);
		}
		
		aceptar = new JButton("Aceptar");
		aceptar.addActionListener(this);
		aceptar.setBounds(5,500,80,21);
		
		agregar = new JButton("Agregar información >");
		agregar.addActionListener(this);
		agregar.setBounds(240,450,170,21);
				
		jpanel4.setLayout(null);
   		jpanel4.add(aceptar, null);
   		jpanel4.add(agregar, null);
	}
	
	/**
	 * Método que permite hacer una lista de las tablas que están almacenadas actualmente.
	 * @param lista
	 */
	public void listarTablas(Lista<T> lista){
	       Nodo<T> temporal =  lista.getInicio();
	       while(temporal != null){
	    	   combo1.addItem(temporal.getTabla().getNombre());
	    	   combo1.addItemListener(this);
	           temporal = temporal.getSiguiente();
	       }
	}
	
	/**
	 * Sobreescritura del método actionPerformed que permitirá accionar los botones.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == aceptar){
			ItoSql<String> i = new ItoSql<String>(tablas);
			i.setVisible(true);
			dispose();
		}else if(e.getSource() == agregar){
			Tabla<T> aux = tablas.buscarTabla(String.valueOf(combo1.getSelectedItem()));
			String[] auxi = aux.getTablas(), entrada = new String[auxi.length];
			for(int i = 0; i < auxi.length; i++)	    		
	    		entrada[i] = jtf[i].getText(); 
			aux.insertarDato(entrada, tipos);
			jlabel2.setText("Datos insertados correctamente.");
            for(int j = 0; j < jtf.length; j++)
				jtf[j].setText("");
		}		
	}
	
	/**
	 * Método main para prueba de la clase.	
	 * @param args
	 */
	public static void main(String[] args) {
		AgregarInfo<String> ai = new AgregarInfo<String>();
		ai.setVisible(true);
	}

	/**
	 * Método sobreescrito que, al seleccionar una tabla, 
	 * mostrará los atributos que ésta tenga para poder almacenar la información. 
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		Tabla<T> auxiliar = tablas.buscarTabla(String.valueOf(combo1.getSelectedItem()));
		String[] aux = auxiliar.getTablas();
		for(int i = 0; i < aux.length; i++){
			label[i].setBounds(new Rectangle(30, (i+1)*45, 130, 25));
    		label[i].setText(aux[i]);             
    		jtf[i].setBounds(new Rectangle(130, (i+1)*45, 100, 21));
		}
	}
}