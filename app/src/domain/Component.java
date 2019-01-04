package domain;

import java.util.ArrayList;


/* Classe utilizada para mapeamento de componentes do arquivo XML
 * Existem dois tipos de componentes:
 * - Componente Básico: composto por interface provedoras e requisidoras;
 * - Componente Composto: composto de componentes básicos, conectores, interfaces provedoras e requisidoras;
 */
public class Component extends ArchitecturalElement {

	private ArrayList<String> concerns;
	private ArrayList<Conector> conectors;
	private ArrayList<InterfaceComponent> interfaces;

	
	public Component(String pName, String pType, String pId) {
		super(pName, pType, pId);
		concerns = new ArrayList<String>();
		conectors = new ArrayList<Conector>();
		interfaces = new ArrayList<InterfaceComponent>();
	}
	
	
	public Component() {
		concerns = new ArrayList<String>();
		conectors = new ArrayList<Conector>();
		interfaces = new ArrayList<InterfaceComponent>();
	}


	public void addConcern(String c){
		 getConcerns().add(c);
	}
	
	public void removeConvern(String c){
		getConcerns().remove(c);
	}
	
	public void addConector(Conector c){
		 getConectors().add(c);
	}
	
	public void removeConector(Conector c){
		getConectors().remove(c);
	}
	
	public void addInterface(InterfaceComponent i){
		 getInterfaces().add(i);
	}
	
	public void removeInterface(InterfaceComponent i){
		getInterfaces().remove(i);
	}
	

	public ArrayList<String> getConcerns() {
		return concerns;
	}


	public void setConcerns(ArrayList<String> concerns) {
		this.concerns = concerns;
	}

	public ArrayList<Conector> getConectors() {
		return conectors;
	}


	public void setConectors(ArrayList<Conector> conectors) {
		this.conectors = conectors;
	}


	public ArrayList<InterfaceComponent> getInterfaces() {
		return interfaces;
	}


	public void setInterfaces(ArrayList<InterfaceComponent> interfaces) {
		this.interfaces = interfaces;
	}


}
