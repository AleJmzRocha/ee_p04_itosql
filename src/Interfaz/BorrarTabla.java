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
 *  Interfaz gr�fica que permite borrar una tabla en espec�fico.
 *
 */
public class BorrarTabla<T> extends JFrame implements ActionListener, ItemListener{
	/**
	 * Variables a ocupar durante la clase.
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> combo1, combo2;
	JButton aceptar, cancelar;
	JLabel jlabel = new JLabel(), jlabel2 = new JLabel();
	JPanel jpanel = (JPanel) this.getContentPane();
	JPanel jpanel4 = (JPanel) this.getContentPane();
	static Lista tablas;

	/**
	 * Constructor auxiliar.
	 */
	public BorrarTabla(){
		this(tablas);
	}
	
	/**
	 * Sobrecarga de constructor que recibe la lista con las tablas actuales.
	 * @param tablas
	 */
	public BorrarTabla(Lista<T> tablas){
		this.tablas = tablas;
		setLayout(null);
		setTitle("Borrar Tabla");
		setSize(320,250); 
		
		jlabel.setBounds(new Rectangle(50, 10, 220, 21));
		jlabel.setText("Tablas ");
		
		combo1 = new JComboBox<String>();
		combo1.setBounds(100,10,150,20);
		combo1.addItem("Tablas");
		listarTablas(tablas);
		add(combo1);
						
		jlabel2.setBounds(new Rectangle(10, 50, 220, 21));
		
		aceptar = new JButton("Aceptar");
		aceptar.addActionListener(this);
		aceptar.setBounds(10,185,80,21);
		
		cancelar = new JButton("Cancelar");
		cancelar.addActionListener(this);
		cancelar.setBounds(100,185,85,21);
		
		jpanel.setLayout(null);
		jpanel.add(jlabel, null);
		jpanel.add(jlabel2, null);
		
		jpanel4.setLayout(null);
   		jpanel4.add(aceptar, null);
   		jpanel4.add(cancelar, null);
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
		if(e.getSource() == cancelar){
			ItoSql<String> i = new ItoSql<String>(tablas);
			i.setVisible(true);
			dispose();
		}
		else if(e.getSource() == aceptar){
				Tabla<T> aux = tablas.buscarTabla(String.valueOf(combo1.getSelectedItem()));
				Nodo<T> temp = tablas.eliminarTabla(aux);
//		    	System.out.println("Tabla "+temp.getTabla().getNombre() + " borrada");
				jlabel2.setText("Tabla " + temp.getTabla().getNombre() + " eliminada.");
//				listarTablas(tablas);
				ItoSql<String> i = new ItoSql<String>(tablas);
				i.setVisible(true);
				dispose();
			}
	}

	/**
	 * M�todo main para prueba de la clase.	
	 * @param args
	 */
	public static void main(String[] args) {
		BorrarTabla bt = new BorrarTabla();
		bt.setVisible(true);
	}

	/**
	 * Sobreescritura del m�todo abstracto al implementar ItemListener.
	 * En �ste m�todo no se har� nada pero se debe sobreescribir. 
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {}
}