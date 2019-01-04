package domain;

import java.util.ArrayList;

/* Classe responsável pelo mapeamento de interfaces do arquivo XML
 * Existem dois tipos de interfaces:
 * - Interface provedora: que fornece serviço provido por um componente básico;
 * - Interface requisidora: que requer serviço provido por um componente básico;
 * Independente do tipo, uma interface liga dois componentes básicos. 
 * O componente requisidor com o componente provedor;
 * A interface provedora é constituída do método que caracteriza o seu serviço
 */
public class InterfaceComponent extends ArchitecturalElement {

	private ArrayList<Method> methods;
	private String contextid;
	
	public InterfaceComponent(String pName, String pType, String pId) {
		super(pName, pType, pId);
		methods = new ArrayList<Method>();
		contextid = new String();
	}
	
	public InterfaceComponent(){
		methods = new ArrayList<Method>();
		contextid = new String();
	}

	public ArrayList<Method> getMethods() {
		return methods;
	}

	public void setMethods(ArrayList<Method> methods) {
		this.methods = methods;
	}
	
	public void addMethods(Method m){
		this.methods.add(m);
	}
	
	public void removeMethods(Method m){
		this.methods.remove(m);
	}

	public String getContextid() {
		return contextid;
	}

	public void setContextid(String contextid) {
		this.contextid = contextid;
	}
	
}
