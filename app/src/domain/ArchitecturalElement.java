package domain;


/* Classe utilizada no mapeamento de elementos arquiteturais do documento XML
 * Cada elemento arquitetural possui nome, tipo (componente, conector, interface, parametro, método) e Id para identificação
 * 
 */
public class ArchitecturalElement {
	
	protected String name, type, id;

	public ArchitecturalElement(){}
	
	public ArchitecturalElement(String pName, String pType, String pId) {
		this.name = pName;
		this.type = pType;
		this.id = pId;
	}

	
	public void setName(String pName) {
		name = pName;		
	}

	public void setType(String pType) {
		type = pType;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
