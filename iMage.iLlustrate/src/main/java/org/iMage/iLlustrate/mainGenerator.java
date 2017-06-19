package org.iMage.iLlustrate;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class mainGenerator extends JFrame implements ChangeListener{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panel1;
	private JPanel panel2;
	
	
	public mainGenerator() {
		setLayout(new BorderLayout());
		
		//Manage Upper Pane
		panel1 = new JPanel();
		panel1.setPreferredSize(new Dimension(400,200));
		panel1.setBorder(BorderFactory.createTitledBorder("Images"));
		
		
		
		
		
		add(panel1, BorderLayout.NORTH);
		
		
		//Manage Bottom Pane
		panel2 = new JPanel(new BorderLayout());
		panel2.setPreferredSize(new Dimension(400,180));
		panel2.setBorder(BorderFactory.createTitledBorder("Settings"));
		
		
		final int IT_MIN = 0;
		final int IT_MAX = 2000;
		final int IT_INIT = 20;
		JSlider slidIteration = new JSlider(JSlider.HORIZONTAL,IT_MIN, IT_MAX, IT_INIT);
		slidIteration.setPreferredSize(new Dimension(400,50));
		slidIteration.setMajorTickSpacing(100);
		slidIteration.setPaintTicks(true);
		slidIteration.addChangeListener(this);
		
		final int SAMP_MIN = 0;
		final int SAMP_MAX = 200;
		final int SAMP_INIT = 10;
		JSlider sampsIteration = new JSlider(JSlider.HORIZONTAL,SAMP_MIN, SAMP_MAX, SAMP_INIT);
		sampsIteration.setPreferredSize(new Dimension(400,50));
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton loadButton = new JButton("Load");
		JButton runButton = new JButton("Run");
		buttonPanel.add(loadButton);
		buttonPanel.add(runButton);
		
		add(panel2, BorderLayout.SOUTH);
		panel2.add(slidIteration, BorderLayout.NORTH);
		panel2.add(sampsIteration, BorderLayout.CENTER);
		panel2.add(buttonPanel, BorderLayout.SOUTH);
		
	}
	
	public static void main(String[] args) 
	{
		mainGenerator mg = new mainGenerator();
		mg.setSize(400, 400);
		mg.setResizable(false);
		mg.setTitle("iLlustrate");
		mg.setDefaultCloseOperation(EXIT_ON_CLOSE); 
		mg.setVisible(true);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
		
	
  
}
