package org.iMage.iLlustrate;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Object;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.iMage.geometrify.IPointGenerator;
import org.iMage.geometrify.IPrimitiveFilter;
import org.iMage.geometrify.RandomPointGenerator;
import org.iMage.geometrify.TrianglePictureFilter;

public class RunFrame extends JFrame implements ActionListener {
	private BufferedImage finalIM;
	private JLabel labelImage = new JLabel("");
	private JPanel panelUp = new JPanel(new BorderLayout());
	private JCheckBox updates = new JCheckBox("Updates");
	private JButton save = new JButton("save");
	private boolean finished = false;
	private BufferedImage endResult;
	
	
	public RunFrame(File file, BufferedImage preview, int iterations, int samples) {
		setLayout(new BorderLayout());
		setTitle(file.getName() + " (" + iterations + " iterations, " + samples + " samples)");
		setVisible(true);
		setResizable(false);
		labelImage.setSize(new Dimension(preview.getWidth() + 20, preview.getHeight() + 20));
		setSize(new Dimension(labelImage.getWidth() + 20, labelImage.getHeight() + 20));
		panelUp.add(updates, BorderLayout.WEST);
		panelUp.add(save, BorderLayout.EAST);
		
		add(panelUp, BorderLayout.NORTH);
		add(labelImage, BorderLayout.CENTER);
		
		save.addActionListener(new ActionListener() {
		    
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", new String[] {"png", "jpg"});
				fileChooser.setFileFilter(filter);
				
				int retVal = fileChooser.showSaveDialog(null);
				
				if (retVal == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					String extension = "";

					int i = selectedFile.getName().lastIndexOf('.');
					if (i > 0) {
					    extension = selectedFile.getName().substring(i+1);
					}
					
					try {
						ImageIO.write(endResult,extension, selectedFile);
						JOptionPane.showMessageDialog(null, "Saved succesfully");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Invalid Extension");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Cancelled proccess");
				}
			}
		});
	}
	public void setEndResult(BufferedImage im) {
		endResult = im;
	}
	
	public void finito() {
		finished = true;
	}
	
	public void actualizeIcon(BufferedImage im, int x) {
		
		labelImage.setIcon(new ImageIcon(im));
		labelImage.repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
