package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.SwingConstants;

public class About extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Janela About da ferramenta
	 */
	public About(JFrame anterior) {
		initcomponents(anterior);
		this.addWindowListener(new WindowListener(){
            @Override
            public void windowClosing(WindowEvent e) {
                About.this.setVisible(false);
                anterior.setEnabled(true);
                //anterior.setLocation(anterior.getLocationOnScreen());
            }

            @Override
            public void windowOpened(WindowEvent e) {
               
            }

            @Override
            public void windowIconified(WindowEvent e) {
                
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                
            }

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});	
        
        this.setLocationRelativeTo(null);
		
	}

	public void initcomponents(JFrame anterior){
		
		JLabel image = new JLabel();
		JTextArea info = new JTextArea();
		JButton ok = new JButton("OK");
		ok.setBounds(323, 512, 55, 30);
		
		ImageIcon imagem = new ImageIcon("C:\\Users\\JULLIAN\\Documents\\PalladioProjects\\ArchiDES\\icons\\archideslogo.png");
		
		//Configurações do Layout da Janela
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\JULLIAN\\Documents\\PalladioProjects\\Archides\\icons\\archides.png"));
		setBounds(100, 100, 700, 600);
		setLocation(new java.awt.Point(100, 100));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setTitle("About ArchiDES (Architectural Anomalies Detection Expert System)");
		
		image.setIcon(new ImageIcon("C:\\Users\\JULLIAN\\Documents\\PalladioProjects\\ArchiDES\\icons\\archideslogo.png"));
		image.setIcon(new ImageIcon(imagem.getImage().getScaledInstance(350, 500, Image.SCALE_DEFAULT)));
		image.setSize(25, 25);
		
		info.setEditable(false);
		info.setText("ArchiDES (Architectural Anomalies Detection Expert System) is a tool that analyzes software architectures in order to detect architectural anomalies. This tool has the ability to analyze \r\nthe degree of infection of the anomalies in the architectural design.\r\n\r\nVersion: ArchiDES 1.0 Beta \r\n\r\nArchiDES, Architectural Anomalies Detection Expert System\r\nCopyright 2016-2016\r\n\r\nRafael Nascimento, Rio Grande do Norte University State, Brazil\r\nCarlos Fonseca, Rio Grande do Norte University State, Brazil\r\nFrancisco Dantas, Rio Grande do Norte University State, Brazil\r\n");
		info.setBounds(100, 100, 50, 50);
		info.setLineWrap(true);
		
		JPanel pgrid = new JPanel(new GridLayout(0,2));
		pgrid.setBounds(1, 5, 692, 502);
		pgrid.setBorder(new LineBorder(new Color(0, 0, 0)));
		pgrid.add(image);
		pgrid.add(info);
		
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
				anterior.setEnabled(true);
			}
		});
		
		getContentPane().setLayout(null);
		getContentPane().add(pgrid);
		getContentPane().add(ok);
		setResizable(false);
		setVisible(false);
	}
	
}
