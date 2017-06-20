package org.iMage.iLlustrate;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.iMage.geometrify.IPointGenerator;
import org.iMage.geometrify.IPrimitiveFilter;
import org.iMage.geometrify.RandomPointGenerator;
import org.iMage.geometrify.TrianglePictureFilter;


public class MainGenerator extends JFrame implements ChangeListener, ActionListener{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panel1;
	private JPanel panel2;
	
	BufferedImage preview;
	BufferedImage after;
	ImageIcon shortPreview;
	ImageIcon shortAfter;
	
	private JPanel iterationsPanel;
	private JLabel iterationStatus = new JLabel("Iterations (100)");
	private final int IT_MIN = 0;
	private final int IT_MAX = 2000;
	private final int IT_INIT = 100;
	private JSlider slidIteration = new JSlider(JSlider.HORIZONTAL,IT_MIN, IT_MAX, IT_INIT);
	int valueIterations = 100;
	
	
	private JPanel sampsPanel;
	private JLabel samplesStatus = new JLabel("Samples (30)");
	private final int SAMP_MIN = 0;
	private final int SAMP_MAX = 200;
	private final int SAMP_INIT = 30;
	private JSlider slidSamps = new JSlider(JSlider.HORIZONTAL,SAMP_MIN, SAMP_MAX, SAMP_INIT);
	int valueSamps = 30;
	
	
	private JPanel buttonPanel = new JPanel(new FlowLayout());
	private JButton loadButton = new JButton("Load");
	private JButton runButton = new JButton("Run");
	
	private File previewFile;
	
	private JLabel empty1 = new JLabel("EMPTY");
	private JLabel empty2 = new JLabel("EMPTY");
	
	public MainGenerator() {
		setLayout(new BorderLayout());
		
		//Manage Upper Pane
		panel1 = new JPanel(new BorderLayout());
		panel1.setPreferredSize(new Dimension(400, 200));
		panel1.setBorder(BorderFactory.createTitledBorder("Images"));
		
		empty1.setPreferredSize(new Dimension(150, 150));
		
		
		empty2.setPreferredSize(new Dimension(150, 150));
		
		panel1.add(empty1, BorderLayout.WEST);
		panel1.add(empty2, BorderLayout.EAST);
		
		
        add(panel1, BorderLayout.NORTH);
		
		
		//Manage Bottom Pane
		panel2 = new JPanel(new BorderLayout());
		panel2.setPreferredSize(new Dimension(400, 180));
		panel2.setBorder(BorderFactory.createTitledBorder("Settings"));
	
		
		createPanelIteration();
		createPanelSamples();
		createPanelButtons();
		
		add(panel2, BorderLayout.SOUTH);
		panel2.add(iterationsPanel, BorderLayout.NORTH);
		panel2.add(sampsPanel, BorderLayout.CENTER);
		panel2.add(buttonPanel, BorderLayout.SOUTH);
		

	}
	
	
	//MISSSING//
	public void createPanelIteration() {
		iterationsPanel = new JPanel(new BorderLayout());
		
		slidIteration.setMajorTickSpacing(2000);
		slidIteration.setPaintTicks(true);
		slidIteration.addChangeListener(this);
		slidIteration.setPaintLabels(true);
		
		iterationsPanel.add(iterationStatus, BorderLayout.NORTH);
		iterationsPanel.add(slidIteration, BorderLayout.SOUTH);
		
		
		slidIteration.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    	valueIterations = slidIteration.getValue();
		        iterationStatus.setText("Iterations (" + valueIterations + ")");
		    }
		});

		
	}
	
	
	//MISSING
	public void createPanelSamples() {
		
		sampsPanel = new JPanel(new BorderLayout());
		
		slidSamps.setMajorTickSpacing(200);
		slidSamps.setPaintTicks(true);
		slidSamps.addChangeListener(this);
		slidSamps.setPaintLabels(true);
		
		sampsPanel.add(samplesStatus, BorderLayout.NORTH);
		sampsPanel.add(slidSamps, BorderLayout.SOUTH);
		
		
		slidSamps.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    	valueSamps = slidSamps.getValue();
		        samplesStatus.setText("Samples (" + valueSamps + ")");
		    }
		});
	}
	
	public void createPanelButtons() {
		
		buttonPanel.add(loadButton);
		buttonPanel.add(runButton);
		
		loadButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = 
						new FileNameExtensionFilter("Image Files", new String[] {"png", "jpg"});
				fileChooser.setFileFilter(filter);
				
				int retVal = fileChooser.showOpenDialog(null);
				
				if (retVal == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					try { 
		
					    preview = ImageIO.read(selectedFile); 
					    shortPreview = new ImageIcon(resize(preview, 150, 150));
					    empty1.setIcon(shortPreview);
					    previewFile = selectedFile;
					} 
					catch (IOException ex) {
						JOptionPane.showMessageDialog(null, "Error occured reading the image");
					}

				}
				
			}
		});
		
		runButton.addActionListener(new ActionListener() {
		    
			public void actionPerformed(ActionEvent e) {
				if (preview == null || previewFile == null) {
					JOptionPane.showMessageDialog(null, "Nothing to run yet");
				}
				else {
					RandomPointGenerator rPG = new RandomPointGenerator(preview.getWidth(),preview.getHeight());
					ComplexTriangleFilter filter = new ComplexTriangleFilter(rPG);
					BufferedImage output = filter.apply1(preview, valueIterations, valueSamps, previewFile);
					after = output;
					shortAfter = new ImageIcon(resize(after, 150, 150));
				    empty2.setIcon(shortAfter);
				}
				
			}	
		});
		
		
	}
	
	
	
	public static BufferedImage resize(BufferedImage input, int maxWidth, int maxHeight) {
		Image image = null;
		
		if (input.getHeight() > input.getWidth()) {
			image = input.getScaledInstance(-1, maxHeight, BufferedImage.SCALE_DEFAULT);
		}
		else {
			image = input.getScaledInstance(maxWidth, -1, BufferedImage.SCALE_DEFAULT);
		}
		
		BufferedImage finalShort = new BufferedImage(image.getWidth(null), image.getHeight(null), input.getType());
		
		finalShort.getGraphics().drawImage(image, 0, 0, null);
		
		return finalShort;
	}
	
	
	public static void main(String[] args) 
	{
		MainGenerator mg = new MainGenerator();
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


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
