package Interfaz;

import javax.swing.*;
import simulador.*;
import java.awt.Rectangle;
import java.awt.event.*;

/** 
 * @authors Martínez Carrera Dulce Carolina
 * 			Martinez Hernández Gabriela
 * 			Sánchez Lázcares Perla Melina
 * 			Jiménez Rocha Alejandra
 * 
 *  Interfaz gráfica que permite generar una nueva tabla.
 *
 */
public class Agregar<T> extends JFrame implements ItemListener, ActionListener{
	/**
	 * Variables a utilizar durante la clase.
	 */
	private static final long serialVersionUID = 1L;
	private static Lista tablas = new Lista();
	JLabel jlabel = new JLabel(), label[] = new JLabel[10], jlabel1 = new JLabel();
	JPanel jpanel = (JPanel) this.getContentPane();
	JPanel jpanel4 = (JPanel) this.getContentPane();
	JTextField jtextfield = new JTextField(), text[] = new JTextField[10];
    JComboBox[] combo1 = new JComboBox[10];
	JButton agregar, aceptar, regresar;
	String[] atributos = new String[10], tipos = new String[10];
	
	/**
	 * Sobrecargar de contrsuctores, siendo éste un constructor auxiliar.
	 */
	public Agregar(){
		this(tablas);		
	}
	
	/**
	 * Constructor que recibe una lista que contendrá las tablas que se han creado y éste, iniciliza las variables a ocupar.
	 * @param tablas
	 */
	public Agregar(Lista<T> tablas) {
		Agregar.tablas = tablas;
		setLayout(null);
		setTitle("Agregar Tabla");
		setSize(430,570);        
				
		agregar = new JButton("Agregar campos >");
		agregar.addActionListener(this);
		agregar.setBounds(250,15,150,21);
		aceptar = new JButton("Aceptar");
		aceptar.addActionListener(this);
		aceptar.setBounds(10,500,80,21);
		regresar = new JButton("Regresar");
		regresar.addActionListener(this);
		regresar.setBounds(100,500,100,21);
				
		jlabel.setBounds(new Rectangle(10, 15, 220, 21));
		jlabel.setText("Nombre de la Tabla ");
		
		jlabel1.setBounds(new Rectangle(10, 480, 220, 21));
		
		jpanel.add(agregar,null);
		jpanel.setLayout(null);
		jpanel.add(jlabel, null);		
		jpanel.add(jlabel1, null);
		
		for(int i = 0; i < label.length; i++){
			label[i] = new JLabel();
			text[i] = new JTextField();
			combo1[i] = new JComboBox<String>(); 
    		jpanel.add(label[i], null);
    		jpanel.add(text[i], null);
    		jpanel.add(combo1[i], null);
		}	
		
		jpanel4.setLayout(null);
   		jpanel4.add(aceptar, null);
   		jpanel4.add(regresar, null);
   		
		jtextfield.setBounds(new Rectangle(130, 15, 100, 21));
   		jpanel.add(jtextfield, null);
	}

	/**
	 * Sobreescritura del método abstracto al implementar ItemListener.
	 * En éste método no se hará nada pero se debe sobreescribir. 
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {}
	
	/**
	 * Permite accionar los botones que contiene la ventana.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {	
		if(e.getSource() == agregar){
			for(int i = 0; i < label.length; i++){
				label[i].setBounds(new Rectangle(10, (i+1)*45, 130, 25));
	    		label[i].setText("Nombre del Campo ");             
	    		text[i].setBounds(new Rectangle(130, (i+1)*45, 100, 21));
	    		combo1[i].setBounds(250,(i+1)*45,150,20);
	    		combo1[i].addItem("Tipo");
	    		combo1[i].addItem("Texto");
	    		combo1[i].addItem("Numerico");
	    		combo1[i].addActionListener(this);
			}
		}else
			if(e.getSource() == aceptar){
				for(int j = 0; j < text.length; j++)
					if(text[j].getText() != null)
						atributos[j] = text[j].getText();
				

				for(int i = 0; i < combo1.length; i++)
					tipos[i] = String.valueOf(combo1[i].getSelectedItem());
								
				Tabla<T> t = new Tabla<T>(jtextfield.getText(),atributos);
				Nodo<T> nuevo = new Nodo<T>(t);
				tablas.insertar(nuevo);
				jlabel1.setText("Tabla " + nuevo.getTabla().getNombre() + " creada.");
				AgregarInfo ai = new AgregarInfo(nuevo.getTabla().getNombre(), t.getTablas(), tablas, t, tipos);
				ai.setVisible(true);
				dispose();
			}else
				if(e.getSource() == regresar){
					ItoSql<String> i = new ItoSql<String>(tablas);
					i.setVisible(true);
					dispose();
				}
	}
	    
	/**
	 * Método main para probar su funcionalidad.
	 * @param ar
	 */
	public static void main(String[] ar) {
		Agregar<String> formulario1 = new Agregar<String>();
		formulario1.setVisible(true);
	}    
}