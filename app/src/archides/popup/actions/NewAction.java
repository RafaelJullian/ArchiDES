package archides.popup.actions;

import javax.swing.JOptionPane;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;

import ui.FeedbackWindows;
import core.Report;
import core.SpecialisticSystem;

public class NewAction implements IObjectActionDelegate {

	private Shell shell;
	private IWorkbenchPart _targetPart;
	/**
	 * Constructor for Action1.
	 */
	public NewAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
		_targetPart = targetPart;
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		IStructuredSelection arquivoSelecionado = (IStructuredSelection) _targetPart.getSite().getSelectionProvider().getSelection();
		if (arquivoSelecionado.getFirstElement() instanceof IFile) {
		    IFile file = (IFile) arquivoSelecionado.getFirstElement();
		    // Caminho do arquivo
		    IPath caminhoDoArquivo = file.getLocation();
		    //aqui vc chama a classe que faz a análise do arquivo passando a variável caminhoDoArquivo
		    //System.out.println(caminhoDoArquivo.toPortableString());
		    Report reportFinal = new Report();//
			String filename;
			 
			 try{
				 
				SAXBuilder builder = new SAXBuilder();
				Document doc = builder.build(caminhoDoArquivo.toPortableString());
				
				if(doc.getBaseURI().contains("repository")){
					
					filename = doc.getBaseURI().substring(doc.getBaseURI().lastIndexOf('/')+1, 
		            		doc.getBaseURI().lastIndexOf(".repository"));
		            
					System.out.println("Arquivo analisado: " + doc.getBaseURI());
					SpecialisticSystem analysis = new SpecialisticSystem();
					reportFinal = analysis.ReaderFile(doc);
					
					if(reportFinal!=null){
						FeedbackWindows windows = new FeedbackWindows(reportFinal, filename);
					}
					
				}else{
					System.out.println("Arquivo errado!");
					MessageDialog.openInformation(
							shell,
							"ACubedPlugin",
							"Documento inválido! Por favor forneça um documento com a extensão '.repository' para ser analisado.");
					//JOptionPane.showMessageDialog(null, "Documento inválido! Por favor forneça um documento com a extensão '.repository' para ser analisado");				 
				}
				
			 }catch(Exception e){
				 System.out.println(e.getLocalizedMessage());
			 }
		    
		}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

}
