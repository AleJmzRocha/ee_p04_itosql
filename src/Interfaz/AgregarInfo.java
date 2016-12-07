package Interfaz;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import simulador.*;

/** 
 * @authors Mart�nez Carrera Dulce Carolina
 * 			Martinez Hern�ndez Gabriela
 * 			S�nchez L�zcares Perla Melina
 * 			Jim�nez Rocha Alejandra
 * 
 *  Interfaz gr�fica que permite agregar informaci�n a las tablas previamente creadas.
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
	 * Constructor que recibe como par�metros los datos necesarios de la tabla previamente creada.
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
		setTitle("Agregar Informaci�n");
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
		
		agregar = new JButton("Agregar informaci�n >");
		agregar.addActionListener(this);
		agregar.setBounds(240,450,170,21);
				
		jpanel4.setLayout(null);
   		jpanel4.add(aceptar, null);
   		jpanel4.add(agregar, null);
	}
	
	/**
	 * M�todo que permite hacer una lista de las tablas que est�n almacenadas actualmente.
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
	 * Sobreescritura del m�todo actionPerformed que permitir� accionar los botones.
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
	 * M�todo main para prueba de la clase.	
	 * @param args
	 */
	public static void main(String[] args) {
		AgregarInfo<String> ai = new AgregarInfo<String>();
		ai.setVisible(true);
	}

	/**
	 * M�todo sobreescrito que, al seleccionar una tabla, 
	 * mostrar� los atributos que �sta tenga para poder almacenar la informaci�n. 
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