package ui;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;

import core.Report;
import core.SpecialisticSystem;

/*
 * Classe principal da aplicação Archides (Architectural Detection Especialist System)
 * Nesta classe é verificado se o artefato arquitetural é um arquivo com a extensão ".repository".
 * Arquivos com extensão ".repository", arquivos gerados pela ferramenta Palladio Bench, possuem a 
 * descrição da modelagem arquitetural baseada em Modelo de Componentes Palladio Component Model (PCM) 
 * baseada na conceituação de Szyperski's.
 * Caso o arquivo fornecido não possua a extensão ".repository"
 * é emetido um aviso de que o arquivo fornecido não é um arquivo válido
 */

public class UserInterface {
	
	 public static void main(String[] args){

		 Report reportFinal = new Report();//
		 String filename;
		 
		 try{
			 
			SAXBuilder builder = new SAXBuilder();
			//Faz um busca pelo arquivo apontado neste diretório
			Document doc = builder.build("C:\\Users\\JULLIAN\\Documents\\Projetos Arquiteturais\\MobileMedia\\MM04.repository");
			
            filename = doc.getBaseURI().substring(doc.getBaseURI().lastIndexOf('/')+1, 
            		doc.getBaseURI().lastIndexOf(".repository"));
            
            //Verificar se o arquivo possui a extensão .repository
            //Em caso verdadeiro, é aberta a janela principal da ferramenta com o relatório da análise
            //Em caso negativo é aberta uma janela de diálogo dizendo que o arquivo é inválido
			if(doc.getBaseURI().contains("repository")){
				SpecialisticSystem analysis = new SpecialisticSystem();
				reportFinal = analysis.ReaderFile(doc);
				
				if(reportFinal!=null){
					FeedbackWindows windows = new FeedbackWindows(reportFinal, filename);
				}
				
			}else{
				JOptionPane.showMessageDialog(null, "Documento inválido! Por favor forneça um documento com a extensão '.repository' para ser analisado");				 
			}
			
		 }catch(Exception e){
			 System.out.println(e.getLocalizedMessage());
		 }
		
		
	}
	
}
