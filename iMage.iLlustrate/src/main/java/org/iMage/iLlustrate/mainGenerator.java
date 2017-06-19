package org.iMage.iLlustrate;

import java.awt.BorderLayout; 
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;



public class mainGenerator extends JFrame {
	
	public static void main(String[] args) 
	{
		new mainGenerator();
	}
	
	public mainGenerator() {
		setSize(400, 400);
		setResizable(true);
		setTitle("iLlustrate");
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setVisible(true);
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		this.add(menuBar);
	}
	
		
	
  
}
