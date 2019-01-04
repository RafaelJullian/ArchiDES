package domain;

import java.util.ArrayList;

/* Classe responsável pelo mapeamento de conectores do arquivo XML
 * Existem dois tipos de conectores:
 * - Conector provedor: que representa uma interface provedora de componente básico;
 * - Conector requisidor: que representa uma interface requisidora de componente básico;
 * Independente do tipo, um conector liga dois componentes básicos. O componente requisidor
 * com o componente provedor;
 * O conector provedor é constituído de método que caracteriza o seu serviço, a categoria e o tipo de serviço
 */

public class Conector extends ArchitecturalElement {

	private String ComponentRequire, ComponentProvide;
	private InterfaceComponent InterfaceRequire, InterfaceProvide;
	private ArrayList<String> CategoriesProvide;
	private ArrayList<Method> MethodsProvide;
	private String ConectorType;

	
	public Conector(String pName, String pType, String pId) {
		super(pName, pType, pId);
		ComponentRequire = new String();
		ComponentProvide = new String();
		CategoriesProvide = new ArrayList<String>();
		MethodsProvide = new ArrayList<Method>();
		InterfaceProvide = new InterfaceComponent();
		InterfaceRequire = new InterfaceComponent();
		setConectorType(new String());
	}

	
	public Conector(){
		ComponentRequire = new String();
		ComponentProvide = new String();
		CategoriesProvide = new ArrayList<String>();
		MethodsProvide = new ArrayList<Method>();
		InterfaceProvide = new InterfaceComponent();
		InterfaceRequire = new InterfaceComponent();
		setConectorType(new String());
	}
	
	
	public String getComponentRequire() {
		return ComponentRequire;
	}

	
	public void setComponentRequire(String componentFrom) {
		ComponentRequire = componentFrom;
	}

	
	public String getComponentProvide() {
		return ComponentProvide;
	}

	
	public void setComponentProvide(String componentDestiny) {
		ComponentProvide = componentDestiny;
	}


	public ArrayList<String> getCategories() {
		return CategoriesProvide;
	}


	public void setCategories(ArrayList<String> categories) {
		CategoriesProvide = categories;
	}


	public ArrayList<Method> getMethods() {
		return MethodsProvide;
	}


	public void setMethods(ArrayList<Method> methods) {
		MethodsProvide = methods;
	}


	public InterfaceComponent getInterfaceProvide() {
		return InterfaceProvide;
	}


	public void setInterfaceProvide(InterfaceComponent interfaceProvide) {
		InterfaceProvide = interfaceProvide;
	}


	public InterfaceComponent getInterfaceRequire() {
		return InterfaceRequire;
	}


	public void setInterfaceRequire(InterfaceComponent interfaceRequire) {
		InterfaceRequire = interfaceRequire;
	}


	public String getConectorType() {
		return ConectorType;
	}


	public void setConectorType(String conectorType) {
		ConectorType = conectorType;
	}
	
	
	

}
