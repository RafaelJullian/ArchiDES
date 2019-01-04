package core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;




// -----------------------------------------------
//API do JDOM2 para manipulação de arquivos XML
import org.jdom2.Document;
import org.jdom2.Element;


//------------------------------------------------


import domain.Component;
import domain.Conector;
import domain.InterfaceComponent;
import domain.Method;
import domain.Parameter;


/*
 * Classe responsável pelo Sistema Especialista. 
 * Utilizada para mapear os elementos arquiteturais do arquivo XML ".repository"
 * Os tipos de elementos arquiteturais recuperados são: 
 * -> Componentes Básicos
 * -> Componentes Compostos
 * -> Interfaces Provedoras
 * -> Interfaces Requisidoras
 * -> Conectores
 * -> Métodos
 * -> Parâmetros
 * -> Tipos de dados
 */
public class SpecialisticSystem {

	Keywords Keys = Keywords.getInstance(); //Lista das palavras-chaves 
	ArrayList<Component> Components; //Lista para armazenar componentes mapeados
	List<Element> Elements; //Listas que armazena os elementos do arquivo XML mapeados
	Report reports; //Armazenar relatórios de análise
	
	//Construtor inicializando classe
	public SpecialisticSystem()
	{
		Components = new ArrayList<Component>();
		reports = new Report();
	}
	
	//Método utilizado para leitura do arquivo XML
	/*
	 * Em uma única iteração os componentes são analisados e são mapeados as
	 * categorias de conectores, tipos de conectores, interesses de componentes
	 * 
	 * Após isso, é feita o processo de identificação por tipo de Anomalia Arquitetural
	 * Ambiguous Interface, Conector Envy, Extraneous Connector, 
	 * Scattered Parasitic Funcionality, Concern Componente Overlaoad,
	 * respectivamente
	 */
	public Report ReaderFile(Document pDoc) throws Exception
	{
	
		try{
            Elements = pDoc.getRootElement().getChildren();
            ParserComponents();
            MappingCategoriesConnectors();
            MappingTypesConnectors();
            MappingConcernsComponents();
            ConsoleImpress();
            
            IdentifyAnomalyAmbiguousInterface();
            IdentifyAnomalyConectorEnvy();
            IdentifyAnomalyExtraneousConnector();
            IdentifyAnomalyScatteredParasiticFunctionality();    
            IdentifyAnomalyComponentConcernOverload();
            Components.clear();
            Elements.clear();
            return reports;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	//Método utilizado para mapear os elementos arquiteturais
	private void ParserComponents() throws Exception{
	
		for(Element e : Elements){
			
			Component comp = new Component();
			
			if(e.getAttributeValue("type", e.getNamespace("xsi")).contains(ElementsXMLPCM.REPOSITORY_BASICCOMPONENT) &&
					e.getAttributeValue("entityName").contains("SoMoX System-Level Dummy Component"))
			{	
				//O Dummy Component é ignorado pois não é aplicável
			}
			//Verifica se o elemento XML lido é um Componente Básico
			else if(e.getAttributeValue("type", e.getNamespace("xsi")).contains(ElementsXMLPCM.REPOSITORY_BASICCOMPONENT))
			{
				
				comp.setName(e.getAttributeValue("entityName")); 
				comp.setType("BASIC_COMPONENT");
				comp.setId(e.getAttributeValue("id"));
				
				/*
				 * Recupera os elementos filhos deste elemento XML 
				 * em busca das interfaces provedoras e requisidoras
				 */
				List<Element> childrens = e.getChildren();
				
				if(!childrens.isEmpty())
				{
					
					for(Element children : childrens)
					{
						
						InterfaceComponent ic = new InterfaceComponent();
						
						//Verifica se o elemento XML é uma interface provedora
						if(children.getName().contains(ElementsXMLPCM.PROVIDEDROLES_INTERFACEPROVIDINGENTITY))
						{	
							ic.setId(children.getAttributeValue("providedInterface__OperationProvidedRole"));
							ic.setContextid(children.getAttributeValue("id"));
							ic.setType("INTERFACE_PROVIDE");
							comp.addInterface(ic);
						}
						// Verifica se o elemento XML é uma interface requisidora
						else if(children.getName().contains(ElementsXMLPCM.REQUIREDROLES_INTERFACEREQUIRINGENTITY))
						{	
							ic.setId(children.getAttributeValue("requiredInterface__OperationRequiredRole"));
							ic.setContextid(children.getAttributeValue("id"));
							ic.setType("INTERFACE_REQUIRE");
							comp.addInterface(ic);	
						}
						
					}
					
				}	
				
				Components.add(comp);
				
			}
			//Verifica se o elemento XML lido é um Componente Composto
			else if(e.getAttributeValue("type", e.getNamespace("xsi")).contains(ElementsXMLPCM.REPOSITORY_COMPOSITECOMPONENT))
			{
				
				comp.setName(e.getAttributeValue("entityName")); 
				comp.setType("COMPOSITE_COMPONENT");
				comp.setId(e.getAttributeValue("id"));
				
				/*
				 * Recupera os elementos filhos deste elemento XML 
				 * em busca das interfaces provedoras, requisidoras e conectores
				 */
				List<Element> childrens = e.getChildren();
				
				if(!childrens.isEmpty())
				{
				
					for(Element children : childrens)
					{
						
						/*
						 * Verifica se o elemento XML é um conector assembly
						 * Conector de ligação entre componentes básicos
						 */
						
						if(children.getAttributeValue("type", children.getNamespace("xsi"))!= null && children.getAttributeValue("type", children.getNamespace("xsi")).contains(ElementsXMLPCM.COMPOSITION_ASSEMBLYCONNECTOR))
						{	
							Conector  con = new Conector();
							con.setId(children.getAttributeValue("id"));
							con.setName(children.getAttributeValue("entityName"));
							con.setType("CONNECTOR_ASSEMBLY");
							con.setComponentRequire(children.getAttributeValue("entityName").substring(
									children.getAttributeValue("entityName").indexOf(" from ")+7,
									children.getAttributeValue("entityName").indexOf(" to ")));
							con.setComponentProvide(children.getAttributeValue("entityName").substring(
									children.getAttributeValue("entityName").indexOf(" to ")+5,
									children.getAttributeValue("entityName").length()));
							con.getInterfaceProvide().setId(children.getAttributeValue("providedRole_AssemblyConnector"));
							con.getInterfaceRequire().setId(children.getAttributeValue("requiredRole_AssemblyConnector"));
							comp.addConector(con);
						}
						//Verifica se o elemento XML é uma interface provedora
						else if(children.getName().contains(ElementsXMLPCM.PROVIDEDROLES_INTERFACEPROVIDINGENTITY))
						{
							InterfaceComponent ic = new InterfaceComponent();
							ic.setId(children.getAttributeValue("providedInterface__OperationProvidedRole"));
							ic.setType("CONNECTOR_PROVIDE");
							comp.addInterface(ic);
						}
						//Verifica se o elemento XML é uma interface requisidora
						else if(children.getName().contains(ElementsXMLPCM.REQUIREDROLES_INTERFACEREQUIRINGENTITY))
						{	
							InterfaceComponent ic = new InterfaceComponent();
							ic.setId(children.getAttributeValue("requiredInterface__OperationRequiredRole"));
							ic.setType("CONNECTOR_REQUIRE");
							comp.addInterface(ic);	
						}
						
					}
					
				}
				
				Components.add(comp);
				
			}
			//Verifica os próximos elementos XML para completar informações sobre as interfaces
			else if(e.getAttributeValue("type", e.getNamespace("xsi")).contains(ElementsXMLPCM.REPOSITORY_OPERATIONINTERFACE))
			{
				
				for(Component c: Components)
				{
					
					for(InterfaceComponent ic: c.getInterfaces())
					{
												
						if(ic.getId().contains(e.getAttributeValue("id")))
						{
							
							//Verificar elementos filhos em busca de métodos utilizados pelas interfaces
							ic.setName(e.getAttributeValue("entityName"));
							List<Element> childrens = e.getChildren();
							
							if(!childrens.isEmpty())
							{
							
								for(Element children : childrens)
								{
									
									Method m = new Method();
									m.setId(children.getAttributeValue("id"));
									m.setName(children.getAttributeValue("entityName"));
									
									//Verifica se o método possui algum tipo de retorno ou não
									if(children.getAttributeValue("returnType__OperationSignature")==null)
									{ 
										
										if(children.getChild("returnType__OperationSignature")==null)
										{
											m.setReturning_type("void");
										}
										else 
										{
											
											if(children.getChild("returnType__OperationSignature").getAttributeValue("href").contains("@dataTypes__Repository.0"))
											{
												m.setReturning_type("int");
											}
											else if(children.getChild("returnType__OperationSignature").getAttributeValue("href").contains("@dataTypes__Repository.1"))
											{
												m.setReturning_type("string");
											}
											else if(children.getChild("returnType__OperationSignature").getAttributeValue("href").contains("@dataTypes__Repository.2"))
											{
												m.setReturning_type("bool");
											}
											else if(children.getChild("returnType__OperationSignature").getAttributeValue("href").contains("@dataTypes__Repository.3"))
											{
												m.setReturning_type("double");
											}
											else if(children.getChild("returnType__OperationSignature").getAttributeValue("href").contains("@dataTypes__Repository.5"))
											{
												m.setReturning_type("byte");
											}
											else 
											{
												m.setReturning_type("object");
											}
											
											
										}
											 
									}
									else 
									{
										m.setReturning_type(children.getAttributeValue("returnType__OperationSignature"));
									}
									
									List<Element> child = children.getChildren();
									
									//Verificar se os métodos possuem parâmetros
									if(!child.isEmpty())
									{
										
										for(Element ch : child)
										{
											
											if(ch.getName().contains(ElementsXMLPCM.PARAMETERS__OPERATIONSIGNATURE))
											{
												
												Parameter p = new Parameter();
												p.setName(ch.getAttributeValue("parameterName"));
												p.setId(ch.getAttributeValue("dataType__Parameter"));
												
												if(ch.getAttributeValue("dataType__Parameter")==null)
												{ 
													
														if(ch.getChild("dataType__Parameter").getAttributeValue("href").contains("@dataTypes__Repository.0"))
														{
															p.setType("int");
														}
														else if(ch.getChild("dataType__Parameter").getAttributeValue("href").contains("@dataTypes__Repository.1"))
														{
															p.setType("string");
														}
														else if(ch.getChild("dataType__Parameter").getAttributeValue("href").contains("@dataTypes__Repository.2"))
														{
															p.setType("bool");
														}
														else if(ch.getChild("dataType__Parameter").getAttributeValue("href").contains("@dataTypes__Repository.3"))
														{
															p.setType("double");
														}
														else if(ch.getChild("dataType__Parameter").getAttributeValue("href").contains("@dataTypes__Repository.5"))
														{
															p.setType("byte");
														}
														else 
														{
															p.setType("object");
														}
													
												}
												
												m.AddParameters(p);
												
											}											
											
										}
										
									}
									ic.addMethods(m);
								}
								
							}
							
						}
						
					}
					
				}
				
			/*
			 * Faz busca por tipos de dados que podem está relacionados ao 
			 * tipos de retorno e parâmetros do métodos das interface
			 */
			}
			else if(e.getName().contains(ElementsXMLPCM.DATATYPES__REPOSITORY))
			{
				
				for(Component c: Components)
				{
					
					for(InterfaceComponent ic : c.getInterfaces())
					{
						
						for(Method m: ic.getMethods())
						{
							//Recupera o tipo de dado do retorno de um método
							if(m.getReturning_type().contains(e.getAttributeValue("id")) &&
									e.getAttributeValue("type", e.getNamespace("xsi")).contains(ElementsXMLPCM.REPOSITORY_COMPOSITEDATATYPE) && e.getChildren().size()!=0) 
							{ 	
								m.setReturning_type(e.getChild("innerDeclaration_CompositeDataType").getAttributeValue("entityName")); 
							}
							else if(m.getReturning_type().contains(e.getAttributeValue("id")) &&
									e.getAttributeValue("type", e.getNamespace("xsi")).contains(ElementsXMLPCM.REPOSITORY_COMPOSITEDATATYPE) && e.getChildren().size()==0) 
							{ 	
								m.setReturning_type(e.getAttributeValue("entityName")); 
							}
							else if(m.getReturning_type().contains(e.getAttributeValue("id")) &&
									e.getAttributeValue("type", e.getNamespace("xsi")).contains(ElementsXMLPCM.REPOSIROTY_COLLECTIONDATATYPE)) 
							{
								if(e.getAttributeValue("entityName").contains("."))
								{
									m.setReturning_type(
											e.getAttributeValue("entityName").substring(e.getAttributeValue("entityName").lastIndexOf(".")+1, 
											e.getAttributeValue("entityName").lastIndexOf("]")+1)
									);
								}
								else
								{
									m.setReturning_type(e.getAttributeValue("entityName"));
								}				
							}
							
							
							if(!m.getParameters().isEmpty())
							{
								
								for(Parameter p: m.getParameters())
								{	
									
									//Recupera o tipo de dado de um parâmetro
									if(p.getId() != null && p.getId().contains(e.getAttributeValue("id")) 
											&& e.getAttributeValue("type", e.getNamespace("xsi")).contains(ElementsXMLPCM.REPOSITORY_COMPOSITEDATATYPE) 
											&& e.getChildren().size()!=0)
									{ 
										p.setType(e.getChild("innerDeclaration_CompositeDataType").getAttributeValue("entityName"));
									}
									else if(p.getId() != null && p.getId().contains(e.getAttributeValue("id")) 
											&& e.getAttributeValue("type", e.getNamespace("xsi")).contains(ElementsXMLPCM.REPOSITORY_COMPOSITEDATATYPE) 
											&& e.getChildren().size()==0)
									{ 
										p.setType(e.getAttributeValue("entityName"));
									}
									else if (p.getId() != null && p.getId().contains(e.getAttributeValue("id")) && 
											e.getAttributeValue("type", e.getNamespace("xsi")).contains(ElementsXMLPCM.REPOSIROTY_COLLECTIONDATATYPE))
									{
										if(e.getAttributeValue("entityName").contains("."))
										{
											p.setType(
													e.getAttributeValue("entityName").substring(e.getAttributeValue("entityName").lastIndexOf(".")+1, 
													e.getAttributeValue("entityName").lastIndexOf("]")+1)
													);
										}
										else
										{
											p.setType(e.getAttributeValue("entityName"));
										}
										
										
									}	
								
								}
								
							}
							
						}
						
					}
					
				}
				
			}
			
		}
		
		
		//Mapear interfaces dos conectores assembly dos componentes compostos
		for(Component c : Components)
		{
			if(c.getType().equals("COMPOSITE_COMPONENT"))
			{
				for(Component c_: Components)
				{
					//Evita comparação entre componentes iguais
					if(c_ != c )
					{
						for(Conector con: c.getConectors())
						{
							//Verifica somente os componentes assembly
							if(con.getType().equals("CONNECTOR_ASSEMBLY"))
							{
								
								for(InterfaceComponent ic: c_.getInterfaces())
								{
									if(con.getInterfaceProvide().getId().equals(ic.getContextid()))
									{
										con.setInterfaceProvide(ic);
										con.setInterfaceRequire(ic);
									}
									if(con.getInterfaceRequire().getId().equals(ic.getContextid()))
									{
										con.setInterfaceProvide(ic);
										con.setInterfaceRequire(ic);
									}
								}
							}
						}
					}
				}
			}	
		}
		
	}
	
	
	/*
	 * Método utilizado para mapear categorias dos conectores dos componentes compostos
	 * Garcia et al. (2009), faz uso do catálogo de Mehta et al. (2005). 
	 * Este catálogo descreve 4 tipos de categorias de conectores:
	 * -> Comunicação 
	 * -> Coordenação
	 * -> Conversão
	 * -> Facilitação
	 */
	private void MappingCategoriesConnectors()
	{
		ArrayList<String> Categories; //Armazenar categorias de conectores
		for(Component c: Components)
		{
			if(c.getType().equals("COMPOSITE_COMPONENT"))
			{
				for(Conector con: c.getConectors())
				{
					
					Categories = new ArrayList<String>();
					
					//Verifica em qual categoria o nome do conector pertence
					Categories.addAll(Keys.SearchCategoriesConnectors(con.getName()));
					
					/*
					 * Faz uma mapeamento das categorias cujo os métodos das 
					 * interfaces provedoras dos conectores pertencem
					 */
					for(Method m : con.getInterfaceProvide().getMethods())
					{
						Categories.addAll(Keys.SearchCategoriesConnectors(m.getName()));
					}
					
					//Adiciona as categorias do conector removendo categorias duplicatas
					con.setCategories(new ArrayList<String>(new HashSet<String>(Categories)));
					con.setType(Keys.SearchTypesConnectors(con.getName()));
					
				}
			}
		}
	}
	
	/*
	 * Método utilizado para mapear categorias dos conectores dos componentes compostos
	 * Garcia et al. (2009), faz uso do catálogo de Mehta et al. (2005). 
	 * Este catálogo cita 4 tipos de conectores:
	 * - Chamada de Procedimento
	 * - Eventos
	 * - Acesso a dados
	 * - Enlace
	 * - Stream
	 * - Arbitro
	 * - Adaptador
	 * - Distribuidor
	 */
	private void MappingTypesConnectors()
	{
		
		for(Component c: Components)
		{
			if(c.getType().equals("COMPOSITE_COMPONENT"))
			{
				for(Conector con: c.getConectors())
				{
					/*
					 * Faz uma busca por tipos de conectores
					 */
					con.setType(new String(Keys.SearchTypesConnectors(con.getInterfaceProvide().getName())));				
				}
			}
		}
	}

	
	/*
	 * Método utilizado para mapear tipos de interesses presentes em componentes de software.
	 * Neste trabalho foi definido alguns interesses de software como: 
	 * - Negócio
	 * - Persistência
	 * - Exceção
	 * - Transação
	 * - Securança
	 * - Interface de Usuário
	 */
	private void MappingConcernsComponents()
	{
		ArrayList<String> Concerns = new ArrayList<String>();
		
		for(Component c: Components)
		{
			//Verifica em qual interesse pertence o nome do componente
			Concerns.addAll(Keys.SearchComponentConcern(c.getName()));
			for(InterfaceComponent ic: c.getInterfaces())
			{
				Concerns.addAll(Keys.SearchComponentConcern(ic.getName()));
			}
			
			c.setConcerns(new ArrayList<String>(new HashSet<String>(Concerns)));
			
		}
	}
	
	
	//Impressão de dados no console
	private void ConsoleImpress(){
		
		for(Component c: Components)
		{
			
			System.out.println("Componente: " + c.getName() + " Id:" + c.getId() + " Tipo:" + c.getType());
			System.out.print("Interesses do componente:");
			for(String s: c.getConcerns())
			{
				System.out.print(s + " ");
			}
			System.out.println("\n");
			for(InterfaceComponent ic: c.getInterfaces())
			{
				System.out.println("Interface: " + ic.getName() + " Id:" + ic.getId() + " Tipo:" + ic.getType());
				
				for(Method m: ic.getMethods())
				{
					System.out.println("Método: " + m.getName() + " Id:" + m.getId() + " Tipo retorno:" + m.getReturning_type());
					
					for(Parameter p: m.getParameters())
					{
						System.out.println("Parameter: " + p.getName() + " Id:" + p.getId() + " Tipo:" + p.getType());						
					}
					System.out.println();
				}
			
				System.out.println();
				
			}
			
			for(Conector con: c.getConectors())
			{
				System.out.println("Conector: " + con.getName() + " Id:" + con.getId() + " Tipo:" + con.getType());
				System.out.println("Origem: " + con.getComponentRequire() + " Destino: " + con.getComponentProvide());
				System.out.println("Interface Requisidora: " + con.getInterfaceRequire().getName() + " Interface Provedora: " + con.getInterfaceProvide().getName());
				System.out.println("Tipo: " + con.getType());
				System.out.print("Categorias: ");
				for(String s: con.getCategories())
				{
					System.out.print(s + " ");
				}
				System.out.println("\n");
			}
			
		
		}
	}
	
	
	/*
	 * Método utilizado para reconhecer padrões que indicam a existência ou 
	 * tendência de determinado componente sofrer de anomalia Ambiguous Interface.
	 * Um componente sofre de interface ambígua quando possui uma interface com um único 
	 * método público contendo um parâmetro genérico
	 */
	public void IdentifyAnomalyAmbiguousInterface() throws Exception{
		
		String Result;
		
		for(Component c : Components)
		{
			
				// Busca por interfaces
				for(InterfaceComponent ic: c.getInterfaces())
				{
					//Filtra somente as interfaces provedoras que só possuem um método público
					if(ic.getType().equals("INTERFACE_PROVIDE") && ic.getMethods().size()==1)
					{	
						ArrayList<Parameter> parametros = ic.getMethods().get(0).getParameters();
						
						//Filtra métodos que só possuem um único parâmetro
						if(!ic.getMethods().get(0).getParameters().isEmpty() && 
								ic.getMethods().get(0).getParameters().size()==1)
						{
							Result = new String("Componente '" + 
									c.getName() + "' sofrendo de anomalia Ambiguous Interface por satisfazer aos dois seguintes critérios: \n" + 
									"Método público único \t [ok] \nÚnico Parâmetro Genérico \t [ok] \n");
							Result = Result + "Interface infectada: '" + ic.getName() + "'\n";
							reports.setReportElementAI(Result);
						}
						else if(!ic.getMethods().get(0).getParameters().isEmpty() && 
								parametros.contains("object"))
						{
							Result = new String("Componente '" + 
									c.getName() + "' sofrendo de anomalia Ambiguous Interface por satisfazer aos dois seguintes critérios: \n" + 
									"Método público único \t [ok] \n Parâmetro Genérico \t [ok] \n");
							Result = Result + "Interface infectada: '" + ic.getName() + "'\n";
							reports.setReportElementAI(Result);
						}
						else 
						{	
							Result = new String("Componente '" + 
									c.getName() + "' tende a sofrer da anomalia Ambiguous Interface por satisfazer a um dos dois critérios: \n" + 
									"Método público único \t [ok] \nÚnico Parâmetro Genérico \t [] \n");
							Result = Result + "Interface infectada: '" + ic.getName() + "'\n";
							reports.setReportElementAIP(Result);	
						}				
							
					}
					//filtra interfaces que não possuem métodos públicos
					else if(ic.getType().equals("INTERFACE_PROVIDE") && ic.getMethods().size()==0) 
					{	
							//Filtra métodos que só possuem um único parâmetro
							Result = new String("Componente '" + 
									c.getName() + "' sofrendo de anomalia Ambiguous Interface por satisfazer aos dois seguintes critérios: \n" + 
									"Método público único \t [ok] \nÚnico Parâmetro Genérico \t [ok] \n");
							Result = Result + "Interface infectada: '" + ic.getName() + "'\n";
							reports.setReportElementAI(Result);;								
					}					
					
				}							
				
		}
		
	}
	
	
	/*
	 * Método utilizado para reconhecer a anomalia Conector Envy
	 * Existem duas possbilidade de ocorrência desta anomalia:
	 * - Quando os métodos da interface provedora de um conector desempenha várias categorias
	 * - Quando um componente composto possui conectores cujas interfaces provedoras desempenham uma mesma categoria
	 */
	public void IdentifyAnomalyConectorEnvy()
	{
		ArrayList<Conector> Conectors = new ArrayList<Conector>();
		ArrayList<Conector> CommunicationConectors = new ArrayList<Conector>();
		ArrayList<Conector> CoordinationConnectors = new ArrayList<Conector>();
		ArrayList<Conector> ConversionConnectors = new ArrayList<Conector>();
		ArrayList<Conector> FacilitationConnectors = new ArrayList<Conector>();
		Hashtable<String,ArrayList<Conector>> ConnectorsComponents = new Hashtable<String,ArrayList<Conector>>();
		
		String Result; 
		boolean Match;
		
		for(Component c: Components)
		{
			if(c.getType().equals("COMPOSITE_COMPONENT"))
			{
				
				/*
				 * Faz uma pré-seleção dos conectores cuja interfaces provedoras sejam diferentes
				 * Evita duplicação de conectores que usam uma mesma interface provedora
				 */
				for(Conector con: c.getConectors())
				{
					if(Conectors.isEmpty())
					{
						Conectors.add(con);
					}
					else
					{
						Match = false;
						
						//Faz uma busca dentro do array conectors procurando por 
						for(int x=0; x < Conectors.size(); x++)
						{
							
							if(con.getInterfaceProvide().getName().equals(Conectors.get(x).getInterfaceProvide().getName()))
							{
								Match = true;
								x = Conectors.size();
							}
						}
						
						if(Match==false)
						{
							Conectors.add(con);
						}
					}
				}
				
							
				/*
				 * Busca por conectores que possuem mais de uma categoria
				 * pois estão sobrecarregados e sofrendo de Connector Envy
				 */
				for(Conector con: Conectors)
				{
					if(con.getCategories().size()>1)
					{
						Result = new String("Conector: '" + con.getName() + "' presente no Componente: '" 
								+ c.getName() + "' estar sofrendo de anomalia 'Conector Envy', porque estar fazendo uso de mais de uma categoria de conectores: \n");
						for(String s: con.getCategories())
						{
							Result = Result + s + " \n";
						}
						Result = Result + "Interface provedora: " + con.getInterfaceProvide().getName() + " \n";
						Result = Result + "Grau de infecção: " + (con.getCategories().size()-1) + "\n";
						reports.setReportElementCEn(Result);
					}
				}
				
				
				/*
				 * Organiza os conectores que fazem parte de um mesmo componente
				 */
				for(Conector con: Conectors)
				{
					ConnectorsComponents.put(con.getComponentProvide(), new ArrayList<Conector>());
					ConnectorsComponents.get(con.getComponentProvide()).add(con);
					for(Conector con_: Conectors)
					{
						if(con!=con_ &&
								con.getComponentProvide().equals(con_.getComponentProvide()))
						{
							ConnectorsComponents.get(con.getComponentProvide()).add(con_);
						}
					}
				}
				
				
				Set<String> KeysComponents = ConnectorsComponents.keySet();
				for(String s: KeysComponents)
				{
					for(Conector con: ConnectorsComponents.get(s))
					{
						for(String sCategories: con.getCategories())
						{
							if(sCategories.equals("Comunicação"))
							{
								CommunicationConectors.add(con);
							}
							else if(sCategories.equals("Coordenação"))
							{
								CoordinationConnectors.add(con);
							}
							else if(sCategories.equals("Conversão"))
							{
								ConversionConnectors.add(con);
							}
							else if(sCategories.equals("Facilitação"))
							{
								FacilitationConnectors.add(con);
							}
						}
					}
					
					/*
					 * Verifica se existem conectores que fazem uso de mesma categoria
					 */
					if(CommunicationConectors.size()>1)
					{
						Result = new String("Componente: '" 
								+ c.getName() + "' estar sofrendo de anomalia 'Conector Envy', porque existe mais de um conector desempenhando a categoria 'Comunicação': \n");
						for(Conector con: CommunicationConectors)
						{
							Result = Result + con.getName() + " \n";
							Result = Result + "Interface Provedora: " + con.getInterfaceProvide().getName() + "\n";
						}
						Result = Result + "Grau de Infecção: " + (CommunicationConectors.size()-1) + "\n";
						reports.setReportElementCEn(Result);
					}
					else if(CoordinationConnectors.size()>1)
					{
						Result = new String("Componente: '" 
								+ c.getName() + "' estar sofrendo de anomalia 'Conector Envy', porque existe mais de um conector desempenhando a categoria 'Coordenação': \n");
						for(Conector con: CoordinationConnectors)
						{
							Result = Result + con.getName() + " \n";
							Result = Result + "Interface Provedora: " + con.getInterfaceProvide().getName() + "\n";
						}
						Result = Result + "Grau de Infecção: " + (CoordinationConnectors.size()-1) + "\n";
						reports.setReportElementCEn(Result);
					}
					else if(ConversionConnectors.size()>1)
					{
						Result = new String("Componente: '" 
								+ c.getName() + "' estar sofrendo de anomalia 'Conector Envy', porque existe mais de um conector desempenhando a categoria 'Conversão': \n");
						for(Conector con: ConversionConnectors)
						{
							Result = Result + con.getName() + " \n";
							Result = Result + "Interface Provedora: " + con.getInterfaceProvide().getName() + "\n";
						}
						Result = Result + "Grau de Infecção: " + (ConversionConnectors.size()-1) + "\n";
						reports.setReportElementCEn(Result);
					}
					else if(FacilitationConnectors.size()>1)
					{
						Result = new String("Componente: '" 
								+ c.getName() + "' estar sofrendo de anomalia 'Conector Envy', porque existe mais de um conector desempenhando a categoria 'Facilitação': \n");
						for(Conector con: FacilitationConnectors)
						{
							Result = Result + con.getName() + " \n";
							Result = Result + "Interface Provedora: " + con.getInterfaceProvide().getName() + "\n";
						}
						Result = Result + "Grau de Infecção: " + (FacilitationConnectors.size()-1) + "\n";
						reports.setReportElementCEn(Result);
					}
					
					CommunicationConectors.clear();
					CoordinationConnectors.clear();
					ConversionConnectors.clear();
					FacilitationConnectors.clear();
					
				}
						
			}
		}
	}
	

	/*
	 * Método utilizado para identificar conectores que sofrem da anomalia Extraneous Connector 
	 */
	public void IdentifyAnomalyExtraneousConnector()
	{
		ArrayList<Conector> ConnectorsExtraneous = new ArrayList<Conector>();
		ArrayList<Conector> ParsConnector = new ArrayList<Conector>();
		String Result;
		int Different;
		
		//Percorrer componentes
		for(Component c :  Components)
		{
			//Analisar somente componentes compostos
			if(c.getType().equals("COMPOSITE_COMPONENT"))
			{				
				ConnectorsExtraneous = new ArrayList<Conector>(c.getConectors());
				for(Conector con : c.getConectors())
				{
					ParsConnector.add(con);
					Different = 0;
					
					//Busca por pares de componentes que ligam conectores
					for(Conector con_ : ConnectorsExtraneous)
					{
						/*
						 * Verifica se os conectores são diferentes,
						 *  mas se possuem os mesmos componentes de origem e destino 
						 */
						if(con!=con_ && 
								con.getComponentProvide().equals(con_.getComponentProvide()) &&
								con.getComponentRequire().equals(con_.getComponentRequire()))
						{
							ParsConnector.add(con_);
						}
					}
					
					/*
					 * Verifica se existem pares de conectores
					 */
					if(ParsConnector.size()>=1)
					{
						for(int x = 0; x < ParsConnector.size(); x++)
						{
							for(int y = 0; y < ParsConnector.size(); y++)
							{
								//É feita uma análise se os tipos dos pares dos conectores são diferentes
								if(ParsConnector.get(x) != ParsConnector.get(y) &&
									!ParsConnector.get(x).getType().equals(ParsConnector.get(y).getType()))
								{
									Different=1;
									x = ParsConnector.size();
									y = ParsConnector.size();
								}
							}
						}
					}
					
					//Se existem pares de conectores de tipos diferentes imprime
					if(Different==1)
					{
						Result = new String("Componente: '" 
								+ c.getName() + "' estar sofrendo de anomalia 'Extraneous Connector', porque existem pares de conectores de tipos diferentes ligando componentes básicos. \n");
						for(Conector pr: ParsConnector)
						{
							Result = Result + "Nome do Conector: '" + pr.getName() + "'\n";
							Result = Result + "Tipo do Conector: '" + pr.getType() + "'\n";
							Result = Result + "Componente Requerido: '" + pr.getComponentRequire() + "'\n";
							Result = Result + "Componente Proverido: '" + pr.getComponentProvide() + "'\n";
						}
						Result = Result + "Grau da Infecção: " + (ParsConnector.size()-1) + "\n";
						reports.setReportElementEC(Result);
					}
					
					//Limpa as variáveis de interação
					ConnectorsExtraneous.removeAll(ParsConnector);
					ParsConnector.clear();						
				}	
			}	
		}
	
	}

	
	/*
	 * Método que verifica se componentes podem estar sofrendo 
	 * da anomalia Scattered Parasitic Functionality
	 * Verifica se funcionalidades estão sendo executadas em vários componentes
	 */
	public void IdentifyAnomalyScatteredParasiticFunctionality() throws Exception
	{
		
		String Result;
		
		ArrayList<Component> ScatteredConcernComponent = new ArrayList<Component>();
		ArrayList<String> concerns = new ArrayList<String>();
		ArrayList<String> concernsfilters = new ArrayList<String>();
		ArrayList<Component> componentsinfecteds = new ArrayList<Component>();
		
		for(Component c: Components){
			if(c.getType().equals("BASIC_COMPONENT"))
			{
				concerns.addAll(c.getConcerns());
			}
		}
		
		concernsfilters.addAll(new HashSet<String>(concerns));
		
		for(String s: concernsfilters)
		{
			for(Component c: Components)
			{
				if(c.getConcerns().contains(s))
				{
					componentsinfecteds.add(c);
				}
			}
			
			if(componentsinfecteds.size()>0)
			{
				Result = new String("Existem componentes sofrendo de anomalia 'Scattered Parasitic Functionality', "
						+ "devido o interesse '" + s + "' estar espalhado entre eles:\n");
				for(Component ci: componentsinfecteds)
				{
					Result = Result + ci.getName() + "\n";
				}
				Result = Result + "Grau de espalhamento: " + componentsinfecteds.size() + " de " 
						+ Components.size() + " componentes\n";
				reports.setReportElementSFP(Result);
				componentsinfecteds.clear();
			}
		}
		
		/*
		//Faz uma busca por interesses sendo executados em vários componentes
		for(Component c: Components)
		{
		
			if(c.getType().equals("BASIC_COMPONENT"))
			{
				for(String s: c.getConcerns())
				{
					for(Component c_: Components)
					{
						if(c!=c_ && c_.getType().equals("BASIC_COMPONENT") && c_.getConcerns().contains(s))
						{
							ScatteredConcernComponent.add(c_);
						}
					}
					
					if(!ScatteredConcernComponent.isEmpty())
					{
						Result = new String("Componente '" + c.getName() + 
								"' pode estar infectando outros componentes com anomalia 'Scattered Parasitic Functionality', pois o interesse '" + s + "' estar espalhado em outros componentes:\n");
						for(Component xc: ScatteredConcernComponent)
						{
							Result = Result + xc.getName() + "\n";
						}
						Result = Result + "Grau de espalhamento: " + ScatteredConcernComponent.size() + " de " 
								+ Components.size() + " componentes\n";
						reports.setReportElementSFP(Result);
						ScatteredConcernComponent.clear();
					}
		
				}
			}
		}
		*/									
	}
	
	
	/*
	 * Metódo para reconhecimento de anomalia Component Concern Overload.
	 * Quando um componente desempenha dois ou mais interesses de software
	 */
	public void IdentifyAnomalyComponentConcernOverload()
	{	
		String Result;
		
		/*
		 * Faz uma varredura por componentes com dois ou mais interesses de software
		 */
		for(Component c: Components)
		{	
			if(c.getConcerns().size()>1)
			{
				Result = "Componente '" + c.getName() + "' estar sofrendo de 'Concern Component Overload' por estar desempenhando mais de um interesse.\n"; 
				Result = Result + "Tipos de interesses desempenhados: \n";
				for(String s: c.getConcerns())
				{
					Result = Result + s + "\n";
				}
				Result = Result + "Grau da Infecção: " + (c.getConcerns().size()-1);
				reports.setReportElementCCO(Result);
			}
		}
	}
	
}
