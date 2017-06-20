package org.iMage.iLlustrate;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.iMage.geometrify.IPointGenerator;
import org.iMage.geometrify.IPrimitiveFilter;
import org.iMage.geometrify.RandomPointGenerator;
import org.iMage.geometrify.TrianglePictureFilter;

public class RunFrame extends JFrame implements ActionListener {
	private BufferedImage finalIM;
	private JLabel labelImage = new JLabel("");
	
	public RunFrame(File file, BufferedImage preview, int iterations, int samples) {
		setLayout(new BorderLayout());
		setTitle(file.getName() + " (" + iterations + " iterations, " + samples + " samples)");
		setVisible(true);
		setResizable(false);
		labelImage.setSize(new Dimension(preview.getWidth() + 20, preview.getHeight() + 20));
		setSize(new Dimension(labelImage.getWidth() + 20, labelImage.getHeight() + 20));
		add(labelImage, BorderLayout.CENTER);
	}
	
	public void actualizeIcon(BufferedImage im) {
		
		labelImage.setIcon(new ImageIcon(im));
		labelImage.repaint();
	}
	

	
	public BufferedImage returnFinalImage() {
		return finalIM;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
