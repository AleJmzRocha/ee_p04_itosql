package Interfaz;

import java.awt.event.*;
import javax.swing.*;
import simulador.*;

/** 
 * @authors Martínez Carrera Dulce Carolina
 * 			Martinez Hernández Gabriela
 * 			Sánchez Lázcares Perla Melina
 * 			Jiménez Rocha Alejandra
 * 
 *  Interfaz gráfica. Clase principal que acciona el programa poniendo las opciones que se pueden realizar.
 *
 */
public class ItoSql<T> extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	JButton crear, insertar, borrar, mostrar, salir;
	JPanel jpanel = (JPanel) this.getContentPane();
	static Lista tablas = new Lista();
	
	/**
	 * Constructor auxiliar.
	 */
	public ItoSql(){
		this(tablas);
	}
	
	/**
	 * Sobrecarga de constructor que recibe la lista con las tablas actuales, 
	 * se hace en todas las clases de interfaz gráfica para no perder la información.
	 * @param tablas
	 */
	public ItoSql(Lista<T> tablas){
		ItoSql.tablas = tablas;
		setLayout(null);
		setSize(200,240); 
		setTitle("ITOSql");
		
		crear = new JButton("Crear Tabla");
		insertar = new JButton("Insertar Datos");
		borrar = new JButton("Borrar Tabla");
		mostrar = new JButton("Mostrar Tabla");
		salir = new JButton("Salir");
		
		crear.addActionListener(this);
		insertar.addActionListener(this);
		borrar.addActionListener(this);
		mostrar.addActionListener(this);
		salir.addActionListener(this);
		
		crear.setBounds(30,10,120,21);
		insertar.setBounds(30,50,120,21);
		borrar.setBounds(30,90,120,21);
		mostrar.setBounds(30,130,120,21);
		salir.setBounds(30,170,120,21);
		
		jpanel.add(crear, null);
		jpanel.add(insertar, null);
		jpanel.add(borrar, null);
		jpanel.add(mostrar, null);
		jpanel.add(salir, null);
	}
		
	/**
	 * Sobreescritura del método actionPerformed que permitirá accionar los botones.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {	
		if(e.getSource() == salir)
			System.exit(0);
		else if(e.getSource() == crear){
			Agregar<String> a = new Agregar<String>();
			a.setVisible(true);
			dispose();
			}else if(e.getSource() == insertar){
				AgregarInfo<String> ai = new AgregarInfo<String>();
				ai.setVisible(true);
				dispose();
				}else if(e.getSource() == borrar){
					BorrarTabla<String> bt = new BorrarTabla<String>(tablas);
					bt.setVisible(true);
					dispose();
					}else if(e.getSource() == mostrar){
						Mostrar<String> m = new Mostrar<String>(tablas);
						m.setVisible(true);
						dispose();
					}
	}
	
	/**
	 * Método main que acciona esta clase principal.	
	 * @param args
	 */
	public static void main(String[] args) {
		ItoSql<String> is = new ItoSql<String>();
		is.setVisible(true);
	}
}