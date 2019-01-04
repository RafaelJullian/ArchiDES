package domain;

/* Classe respons�vel pelo mapeamento de par�metros dos m�todos de interfaces e conectores do arquivo XML
 * Os par�metros s�o caracterizados pelo o seu tipo (string, inteiro, objeto);
 */
public class Parameter extends ArchitecturalElement{

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
	
}
