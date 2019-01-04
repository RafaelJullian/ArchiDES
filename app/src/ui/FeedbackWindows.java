package ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;

import core.Report;

import java.awt.SystemColor;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.ImageIcon;

/* Esta classe é responsável pela interface gráfica do sistema 
 * Ela exibe o relatório das análises feitas pelo Sistema Especialista no modelo arquitetural * 
 */

public class FeedbackWindows {

	private JFrame frame;
	JLabel lblPreapredictionAnomaly;
	JButton btnImprimirRelatorio;
	JTextArea txtrRelatrioDe;
	JScrollPane scrollPane;
	JFileChooser SaveDialog;
	About about;
	int selection;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnHelp;
	private JMenuItem mntmSave;
	private JMenuItem mntmExit;
	private JMenuItem mntmAbout;
	
	/**
	 * Inicialização da janela interativa.
	 */
	public FeedbackWindows(Report reports, String filename) {
		initialize(reports, filename);
		frame.setVisible(true);
	}

	/**
	 * Inicialização do conteúdo do frame.
	 */
	private void initialize(Report reports, String filename) {
		
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\JULLIAN\\Documents\\PalladioProjects\\ArchiDES\\icons\\archides.png"));
		frame.setBounds(100, 100, 820, 688);
		frame.setLocation(new java.awt.Point(50, 50));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		lblPreapredictionAnomaly = new JLabel("ArchiDES Results");
		lblPreapredictionAnomaly.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreapredictionAnomaly.setForeground(new Color(143, 188, 143));
		lblPreapredictionAnomaly.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		
		txtrRelatrioDe = new JTextArea();
		txtrRelatrioDe.setBounds(10, 81, 794, 248);
		txtrRelatrioDe.setWrapStyleWord(true);
		txtrRelatrioDe.setLineWrap(true);
		txtrRelatrioDe.setEditable(false);
		txtrRelatrioDe.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		txtrRelatrioDe.setText("\t\t\t==============================\r\n\tRelat\u00F3rio de an\u00E1lise do artefato arquitetural: '<dynamic>'  na detec\u00E7\u00E3o de anomalias arquiteturais:\r\n\r\n");
		
		if(reports.getReportAI().size()== 0 &&
				reports.getReportAIP().size() == 0 &&
				reports.getReportCCO().size() == 0 &&
				reports.getReportCEn().size() == 0 &&
				reports.getReportEC().size() == 0 &&
				reports.getReportSFP().size() == 0){
			
			txtrRelatrioDe.setText("Nenhuma anomalia foi detectada neste projeto arquitetural");
			
		}else {
			
			//Imprime resultados de predição da Anomalia Interface Ambígua
			for(String s: reports.getReportAIP()){
				txtrRelatrioDe.setText(txtrRelatrioDe.getText() + "\n" + s + "\n");
			}
			
			//Imprime resultados da Anomalia Interface Ambígua
			for(String s: reports.getReportAI()){
				txtrRelatrioDe.setText(txtrRelatrioDe.getText() + "\n" + s + "\n");
			}
			
			//Imprime resultados da Anomalia Connector Envy
			for(String s: reports.getReportCEn()){
				txtrRelatrioDe.setText(txtrRelatrioDe.getText() + "\n" + s + "\n");
			}
			
			//Imprime resultados da Anomalia Extraneous Connector
			for(String s: reports.getReportEC()){
				txtrRelatrioDe.setText(txtrRelatrioDe.getText() + "\n" + s + "\n");
			}
			
			//Imprime resultados da Anomalia Scattered Funcionality Parasitic
			for(String s: reports.getReportSFP()){
				txtrRelatrioDe.setText(txtrRelatrioDe.getText() + "\n" + s + "\n");
			}
			
			//Imprime resultados da Anomalia Concern Component Overload
			for(String s: reports.getReportCCO()){
				txtrRelatrioDe.setText(txtrRelatrioDe.getText() + "\n" + s + "\n");
			}
		}
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(txtrRelatrioDe);
		
		//==== Procedimento para salvar o relatório em formato de texto ===== 
		SaveDialog = new javax.swing.JFileChooser();
		SaveDialog.setDialogTitle("Save Report");
        SaveDialog.setApproveButtonText("Save");
        SaveDialog.setMultiSelectionEnabled(false);
        SaveDialog.addChoosableFileFilter(new FileNameExtensionFilter("Word Document (.docx)", ".docx"));
        SaveDialog.addChoosableFileFilter(new FileNameExtensionFilter("Word Document 97-2003 (.doc)", ".doc"));
        SaveDialog.addChoosableFileFilter(new FileNameExtensionFilter("Text Document (.txt)", ".txt"));
        SaveDialog.addChoosableFileFilter(new FileNameExtensionFilter("Rich Text Format Document (.rtf)", ".rtf"));
        SaveDialog.setFileFilter(new FileNameExtensionFilter("Document files", "docx", "doc", "txt", "rtf"));
        SaveDialog.setAcceptAllFileFilterUsed(false);
        		
		btnImprimirRelatorio = new JButton("Save Report");
		btnImprimirRelatorio.setForeground(new Color(0, 0, 0));
		btnImprimirRelatorio.setBackground(new Color(173, 216, 230));
		btnImprimirRelatorio.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnImprimirRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				boolean file_save = false;
				
				while(!file_save){
					
					selection = SaveDialog.showSaveDialog(frame);       
			        
			        if (selection == JFileChooser.APPROVE_OPTION)
			        {
			            
			        	File file = new File(SaveDialog.getSelectedFile() + 
		                		SaveDialog.getFileFilter().getDescription().substring(
		                		SaveDialog.getFileFilter().getDescription().indexOf("(")+1,SaveDialog.getFileFilter().getDescription().indexOf(")")));
			            
			        	//Verifica se o arquivo já existe no diretório que deseja salvar
			        	if(file.exists())
			        	{
			        		
			        		int confirm = JOptionPane.showConfirmDialog(null, "Este arquivo já existe. Deseja sobrescrever?", "Sim ou não?", JOptionPane.YES_NO_OPTION);
			        		if(confirm == JOptionPane.YES_OPTION){
			        			
			        			try{
					                try (
					                	FileWriter writer = new FileWriter(SaveDialog.getSelectedFile() + 
					                		SaveDialog.getFileFilter().getDescription().substring(
					                		SaveDialog.getFileFilter().getDescription().indexOf("(")+1,SaveDialog.getFileFilter().getDescription().indexOf(")")),false)) {
					                	writer.flush();
					                	writer.write("");
					                    writer.write(txtrRelatrioDe.getText());//Escreve o arquivo
					                    writer.close();
					                }
					                JOptionPane.showMessageDialog(null, "Arquivo salvo com sucesso!");                
					                file_save = true;
					            }catch (IOException | HeadlessException ex){
					                JOptionPane.showMessageDialog(null, "Erro ao salvar arquivo. Arquivo corrompido!");
					            }
			        		}else{
			        			
			        		}
			        	
			        	}
			        	else 
			        	{		        		
			        		
			        		try{
				                
			        			//File inputFile = new File(SaveDialog.getSelectedFile() + ".txt");
			        			//File outputFile = new File(SaveDialog.getSelectedFile() + 
			        			//		SaveDialog.getFileFilter().getDescription().substring(
				                //		SaveDialog.getFileFilter().getDescription().indexOf("(")+1,SaveDialog.getFileFilter().getDescription().indexOf(")")));
				                
			        			try (FileWriter writer = new FileWriter(SaveDialog.getSelectedFile() + 
				                		SaveDialog.getFileFilter().getDescription().substring(
				                		SaveDialog.getFileFilter().getDescription().indexOf("(")+1,SaveDialog.getFileFilter().getDescription().indexOf(")")),true)) {
				                	
				                	writer.flush();
				                	writer.write("");
				                    writer.write(txtrRelatrioDe.getText());//escreve no arquivo
				                    writer.close();
				                } catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
				                JOptionPane.showMessageDialog(null, "Arquivo salvo com sucesso!");                
				                file_save = true;
				            }catch (HeadlessException ex){
				            	System.out.println(ex.getLocalizedMessage());
				                JOptionPane.showMessageDialog(null, "Erro ao salvar arquivo. Arquivo corrompido!");
				            }
			        	}
			            
			        }
			        else if(selection == JFileChooser.CANCEL_OPTION)
			        {
			        	file_save = true;
			        }
				}
		
			}
		});
		//==== Procedimento para salvar o relatório em formato de texto ===== 
		
		JLabel lblCopyright = new JLabel("Rafael Jullian \r\n Copyright 2016");
		lblCopyright.setHorizontalAlignment(SwingConstants.CENTER);
		
		//== Área de Texto que mostra a quantidade estimada de Anomalias Arquiteturais que foram detectadas
		JTextPane txtpnEstimatedAmout = new JTextPane();
		txtpnEstimatedAmout.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtpnEstimatedAmout.setEditable(false);
		txtpnEstimatedAmout.setBackground(SystemColor.menu);
		txtpnEstimatedAmout.setText("Estimated Amount:\r\n");
		txtpnEstimatedAmout.setText(txtpnEstimatedAmout.getText() + "Interfaces with a tendency to suffer from Ambiguous Interface" + "\t\t\t\t" + reports.getReportAIP().size() + "\n");
		txtpnEstimatedAmout.setText(txtpnEstimatedAmout.getText() + "Ambiguous Interface" + "\t\t\t" + reports.getReportAI().size() + "\n");
		txtpnEstimatedAmout.setText(txtpnEstimatedAmout.getText() + "Connector Envy" + "\t\t\t" + reports.getReportCEn().size() + "\n");
		txtpnEstimatedAmout.setText(txtpnEstimatedAmout.getText() + "Extraneous Connector" + "\t\t" + reports.getReportEC().size() + "\n");
		txtpnEstimatedAmout.setText(txtpnEstimatedAmout.getText() + "Scattered Funcionality Parasitic" + "\t\t" + reports.getReportSFP().size() + "\n");
		txtpnEstimatedAmout.setText(txtpnEstimatedAmout.getText() + "Concern Component Overload" + "\t\t" + reports.getReportCCO().size() + "\n");
		//== Área de Texto que mostra a quantidade estimada de Anomalias Arquiteturais que foram detectadas
		
		
		//==== Procedimento para salvar o relatório em formato de texto =====
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPreapredictionAnomaly, GroupLayout.PREFERRED_SIZE, 522, GroupLayout.PREFERRED_SIZE)
							.addGap(140))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblCopyright, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtpnEstimatedAmout, GroupLayout.PREFERRED_SIZE, 496, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
							.addComponent(btnImprimirRelatorio, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
							.addGap(38))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addComponent(lblPreapredictionAnomaly, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(txtpnEstimatedAmout, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(68)
							.addComponent(btnImprimirRelatorio, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(lblCopyright)
					.addGap(21))
		);
		frame.getContentPane().setLayout(groupLayout);
		frame.getContentPane().setLayout(groupLayout);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmSave = new JMenuItem("Save Report");
		mntmSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				boolean file_save = false;
				
				while(!file_save){
					
					selection = SaveDialog.showSaveDialog(frame);       
			        
			        if (selection == JFileChooser.APPROVE_OPTION)
			        {
			            
			        	File file = new File(SaveDialog.getSelectedFile() + 
		                		SaveDialog.getFileFilter().getDescription().substring(
		                		SaveDialog.getFileFilter().getDescription().indexOf("(")+1,SaveDialog.getFileFilter().getDescription().indexOf(")")));
			            
			        	//Verifica se o arquivo já existe
			        	if(file.exists())
			        	{
			        		
			        		int confirm = JOptionPane.showConfirmDialog(null, "Este arquivo já existe. Deseja sobrescrever?", "Sim ou não?", JOptionPane.YES_NO_OPTION);
			        		if(confirm == JOptionPane.YES_OPTION){
			        			
			        			try{
					                try (
					                	FileWriter writer = new FileWriter(SaveDialog.getSelectedFile() + 
					                		SaveDialog.getFileFilter().getDescription().substring(
					                		SaveDialog.getFileFilter().getDescription().indexOf("(")+1,SaveDialog.getFileFilter().getDescription().indexOf(")")),false)) {
					                	writer.flush();
					                	writer.write("");
					                    writer.write(txtrRelatrioDe.getText());//escreve no arquivo
					                    writer.close();
					                }
					                JOptionPane.showMessageDialog(null, "Arquivo salvo com sucesso!");                
					                file_save = true;
					            }catch (IOException | HeadlessException ex){
					                JOptionPane.showMessageDialog(null, "Erro ao salvar arquivo. Arquivo corrompido!");
					            }
			        		}else{
			        			
			        		}
			        	
			        	}
			        	else 
			        	{
			        		try{
				                try (FileWriter writer = new FileWriter(SaveDialog.getSelectedFile() + 
				                		SaveDialog.getFileFilter().getDescription().substring(
				                		SaveDialog.getFileFilter().getDescription().indexOf("(")+1,SaveDialog.getFileFilter().getDescription().indexOf(")")),true)) {
				                	
				                	writer.flush();
				                	writer.write("");
				                    writer.write(txtrRelatrioDe.getText());//escreve no arquivo
				                    writer.close();
				                }
				                JOptionPane.showMessageDialog(null, "Arquivo salvo com sucesso!");                
				                file_save = true;
				            }catch (IOException | HeadlessException ex){
				                JOptionPane.showMessageDialog(null, "Erro ao salvar arquivo. Arquivo corrompido!");
				            }
			        	}
			            
			        }
			        else if(selection == JFileChooser.CANCEL_OPTION)
			        {
			        	file_save = true;
			        }
				}
			}
		});
		
		mntmSave.setIcon(new ImageIcon("C:\\Users\\JULLIAN\\Documents\\PalladioProjects\\ArchiDES\\icons\\diqueste.jpg"));
		mnFile.add(mntmSave);
		//==== Procedimento para salvar o relatório em formato de texto =====
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		mnFile.add(mntmExit);
		
		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		
		//Cria janela About
		mntmAbout = new JMenuItem("About ArchiDES");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				about = new About(frame);
				frame.setEnabled(false);
				about.setVisible(true);
				
				/*
				JFrame about = new JFrame();
				JLabel image = new JLabel();
				JTextArea info = new JTextArea();
				JButton ok = new JButton("OK");
				
				//configurações da janela
				about.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\JULLIAN\\Documents\\PalladioProjects\\ACubed\\icons\\ESDA2Icon.png"));
				about.setBounds(100, 100, 700, 600);
				about.setLocation(new java.awt.Point(100, 100));
				about.setDefaultCloseOperation(about.DISPOSE_ON_CLOSE);
				about.setVisible(true);
				about.setTitle("About ESDA2(Expert System Detector Architectural Anomalies)");
				
				image.setIcon(new ImageIcon("C:\\Users\\JULLIAN\\Documents\\PalladioProjects\\ACubed\\icons\\ESDA2.jpg"));
				image.setBounds(50, 50, 50, 50);
				image.setSize(50, 50);
				
				info.setEditable(false);
				info.setText("ESDA2(Expert System Detector Architectural Anomalies) is a tool that analyzes software architectures in order to \n"
						+ "detect architectural anomalies. This tool has the ability to analyze \n"
						+ "the degree of infection of the anomalies in the architectural design.\n\n" 
						+ "Version: ESDA2 1.0 Beta \n\n"
						+ "ESDA2, Expert System Detector Architectural Anomalies\n" 
						+ "Copyright 2016-2016\n\n" 
						+ "Rafael Nascimento, Rio Grande do Norte University State, Brazil\n"
						+ "Carlos Fonseca, Rio Grande do Norte University State, Brazil\n"
						+ "Francisco Dantas, Rio Grande do Norte University State, Brazil\n");
				info.setBounds(50, 50, 50, 50);
				info.setLineWrap(true);
				
				JPanel pgrid = new JPanel(new GridLayout(0,2));
				pgrid.add(image);
				pgrid.add(info);
				
				ok.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						about.dispose();	
					}
				});
								
				
				about.getContentPane().add(pgrid);
				about.getContentPane().add(ok);
				about.getContentPane().setLayout(new FlowLayout());
				about.setResizable(false);
				*/
			}
		});
		mnHelp.add(mntmAbout);
		frame.pack();
		
	}
	
}
