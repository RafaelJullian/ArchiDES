package ui;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;

import core.Report;
import core.SpecialisticSystem;

/*
 * Classe principal da aplica��o Archides (Architectural Detection Especialist System)
 * Nesta classe � verificado se o artefato arquitetural � um arquivo com a extens�o ".repository".
 * Arquivos com extens�o ".repository", arquivos gerados pela ferramenta Palladio Bench, possuem a 
 * descri��o da modelagem arquitetural baseada em Modelo de Componentes Palladio Component Model (PCM) 
 * baseada na conceitua��o de Szyperski's.
 * Caso o arquivo fornecido n�o possua a extens�o ".repository"
 * � emetido um aviso de que o arquivo fornecido n�o � um arquivo v�lido
 */

public class UserInterface {
	
	 public static void main(String[] args){

		 Report reportFinal = new Report();//
		 String filename;
		 
		 try{
			 
			SAXBuilder builder = new SAXBuilder();
			//Faz um busca pelo arquivo apontado neste diret�rio
			Document doc = builder.build("C:\\Users\\JULLIAN\\Documents\\Projetos Arquiteturais\\MobileMedia\\MM04.repository");
			
            filename = doc.getBaseURI().substring(doc.getBaseURI().lastIndexOf('/')+1, 
            		doc.getBaseURI().lastIndexOf(".repository"));
            
            //Verificar se o arquivo possui a extens�o .repository
            //Em caso verdadeiro, � aberta a janela principal da ferramenta com o relat�rio da an�lise
            //Em caso negativo � aberta uma janela de di�logo dizendo que o arquivo � inv�lido
			if(doc.getBaseURI().contains("repository")){
				SpecialisticSystem analysis = new SpecialisticSystem();
				reportFinal = analysis.ReaderFile(doc);
				
				if(reportFinal!=null){
					FeedbackWindows windows = new FeedbackWindows(reportFinal, filename);
				}
				
			}else{
				JOptionPane.showMessageDialog(null, "Documento inv�lido! Por favor forne�a um documento com a extens�o '.repository' para ser analisado");				 
			}
			
		 }catch(Exception e){
			 System.out.println(e.getLocalizedMessage());
		 }
		
		
	}
	
}
