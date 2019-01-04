package domain;

import java.util.ArrayList;

/* Classe respons�vel pelo mapeamento de m�todos de interfaces e conectores do arquivo XML
 * Os m�todos s�o caracterizados pela lista de par�metros e tipo de retorno;
 */
public class Method extends ArchitecturalElement{
	
	private String returning_type;
	private ArrayList<Parameter> parameters;
	
	public Method(){ parameters = new ArrayList<Parameter>(); }
	
	public String getReturning_type() {
		return returning_type;
	}
	
	public void setReturning_type(String returning_type) {
		this.returning_type = returning_type;
	}
	
	public ArrayList<Parameter> getParameters() {
		return parameters;
	}
	
	public void setParameters(ArrayList<Parameter> parameters) {
		this.parameters = parameters;
	}
	
	public void AddParameters(Parameter p){
		this.parameters.add(p);
	}
	
	public void RemoveParameters(Parameter p){
		this.parameters.remove(p);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
