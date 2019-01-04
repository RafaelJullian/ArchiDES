package core;

import java.util.ArrayList;
import java.util.Locale;


/*Esta classe é responsável pelo armazenamento de palavras-chaves que são utilizadas pela 
 * taxonomia de Anomalias Arquiteturais, Componentes e Conectores
 * 
 * 
 */
public class Keywords {
	
	private static Keywords UniqueInstance;
	
	/*
	 * Strings que armazenam palavras-chaves de categorias de conectores 
	 * Garcia et al. (2009) cita quatro categorias de conectores 
	 * na descoberta de má decisões arquiteturais, sendo elas:
	 * - Comunicação
	 * - Coordenação
	 * - Conversão
	 * - Facilitação
	 */
	ArrayList<String> CommunicationConnector, CoordinationConnector, 
	ConversionConnector, FacilitationConnector;
	
	
	/*
	 * Strings que armazenam informações dos tipos de conectores.
	 * Garcia et al. (2009) cita oito tipos de conectores, sendo eles:
	 * - Chamada de Procedimento
	 * - Eventos
	 * - Acesso a dados
	 * - Enlace
	 * - Stream
	 * - Arbitro
	 * - Adaptador
	 * - Distribuidor
	 */
	ArrayList<String> ProcedureCallType, EventType, DataAcessType, 
	LinkageType, StreamType, ArbitratorType, AdaptorType, DistributorType;
	
	
	/*
	 * Strings que armazenam palavras-chaves para interesses de software. 
	 * Em geral não existe uma taxonomia definida. Aqui estão alguns dos tipos de interesses mais comuns:
	 * - Negócio
	 * - Persistência
	 * - Exceção;
	 * - Transação
	 * - Securança
	 * - Interface de Usuário
	 */
	ArrayList<String> BusinessConcern, PersistenceConcern, ExceptionConcern, SecurityConcern, 
	ControllerConcern, TransactionConcern, UIConcern;
	
	
	//Inicialização das strings que armazenam palavras-chaves de conectores de software
	private Keywords(){
		
		//Categoria de conectores comunicação
		CommunicationConnector = new ArrayList<String>();
		CommunicationConnector.add("persistence");
		CommunicationConnector.add("database");
		CommunicationConnector.add("data");
		CommunicationConnector.add("sender");
		CommunicationConnector.add("sending");
		CommunicationConnector.add("send");
		CommunicationConnector.add("receive");
		CommunicationConnector.add("message");
		CommunicationConnector.add("delivery");
		CommunicationConnector.add("buffering");
		CommunicationConnector.add("buffer");
		CommunicationConnector.add("state");
		CommunicationConnector.add("identify");
		CommunicationConnector.add("locale");
		CommunicationConnector.add("locality");
		CommunicationConnector.add("streaming");
		CommunicationConnector.add("stream");
		CommunicationConnector.add("query");
		CommunicationConnector.add("store");
		CommunicationConnector.add("storing");
		CommunicationConnector.add("repository");
		CommunicationConnector.add("repositories");
		CommunicationConnector.add("acess");
		CommunicationConnector.add("heap");
		CommunicationConnector.add("stack");
		CommunicationConnector.add("delivery");
		CommunicationConnector.add("transfering");
		CommunicationConnector.add("transfers");
		CommunicationConnector.add("transfer");		
		CommunicationConnector.add("pipeline");
		CommunicationConnector.add("pipes");
		CommunicationConnector.add("pipe");		
		CommunicationConnector.add("sockets");
		CommunicationConnector.add("socket");
		CommunicationConnector.add("clients");
		CommunicationConnector.add("client");
		CommunicationConnector.add("servers");
		CommunicationConnector.add("server");
		CommunicationConnector.add("getter");
		CommunicationConnector.add("gets");
		CommunicationConnector.add("get");
		CommunicationConnector.add("setter");
		CommunicationConnector.add("sets");
		CommunicationConnector.add("set");
		CommunicationConnector.add("delete");
		CommunicationConnector.add("create");
		
		//Categoria de conectores de Coordenação
		CoordinationConnector = new ArrayList<String>();
		CoordinationConnector.add("actions");
		CoordinationConnector.add("gui");
		CoordinationConnector.add("center");
		CoordinationConnector.add("ui");
		CoordinationConnector.add("user interface");
		CoordinationConnector.add("user");
		CoordinationConnector.add("action");
		CoordinationConnector.add("calls");
		CoordinationConnector.add("call");
		CoordinationConnector.add("procedures");
		CoordinationConnector.add("procedure");
		CoordinationConnector.add("events");
		CoordinationConnector.add("event");
		CoordinationConnector.add("notify");
		CoordinationConnector.add("notifies");
		CoordinationConnector.add("notifications");
		CoordinationConnector.add("notification");
		CoordinationConnector.add("delivery");
		CoordinationConnector.add("invoke");
		CoordinationConnector.add("invoking");
		CoordinationConnector.add("synchronize");
		CoordinationConnector.add("synchronicity");
		CoordinationConnector.add("accessibility");
		CoordinationConnector.add("accessible");
		CoordinationConnector.add("acess");
		CoordinationConnector.add("exceptions");
		CoordinationConnector.add("exception");
		CoordinationConnector.add("handler");
		CoordinationConnector.add("handling");
		CoordinationConnector.add("callbacks");
		CoordinationConnector.add("callback");
		CoordinationConnector.add("delegation");
		CoordinationConnector.add("publisher");
		CoordinationConnector.add("publish");
		CoordinationConnector.add("subscribe");
		CoordinationConnector.add("update");
		CoordinationConnector.add("interrupt");
		CoordinationConnector.add("commit");
		CoordinationConnector.add("rollback");
		CoordinationConnector.add("add");
		CoordinationConnector.add("controller");
		CoordinationConnector.add("controlling");
		CoordinationConnector.add("control");
		CoordinationConnector.add("thread");
		CoordinationConnector.add("atomicity");
		CoordinationConnector.add("causality");
		CoordinationConnector.add("asynchronous");
		CoordinationConnector.add("faults");
		CoordinationConnector.add("fault");
		CoordinationConnector.add("traps");
		CoordinationConnector.add("trap");
		CoordinationConnector.add("triggered");
		CoordinationConnector.add("triggers");
		CoordinationConnector.add("trigger");
		CoordinationConnector.add("captures");
		CoordinationConnector.add("capture");
		
		//Categoria de conectores de Conversão
		ConversionConnector = new ArrayList<String>();
		ConversionConnector.add("convert");
		ConversionConnector.add("conversion");
		ConversionConnector.add("type");
		ConversionConnector.add("protocols");
		ConversionConnector.add("protocol");
		ConversionConnector.add("format");
		ConversionConnector.add("adaptor");
		ConversionConnector.add("translator");
		ConversionConnector.add("translation");
		
		//Categoria de conectores de Facilitação
		FacilitationConnector = new ArrayList<String>();
		FacilitationConnector.add("load");
		FacilitationConnector.add("linkage");
		FacilitationConnector.add("link");
		FacilitationConnector.add("transaction");
		FacilitationConnector.add("transact");
		FacilitationConnector.add("secure");
		FacilitationConnector.add("security");
		FacilitationConnector.add("authentication"); 
		FacilitationConnector.add("authentic");
		FacilitationConnector.add("route");
		FacilitationConnector.add("routing");
		FacilitationConnector.add("distribuction");
		FacilitationConnector.add("reading");
		FacilitationConnector.add("reader");
		FacilitationConnector.add("read");
		FacilitationConnector.add("writering");
		FacilitationConnector.add("writer");
		FacilitationConnector.add("write");
		FacilitationConnector.add("networking");
		FacilitationConnector.add("network");
		FacilitationConnector.add("transaction");
		FacilitationConnector.add("distributor");
		FacilitationConnector.add("switching");
		FacilitationConnector.add("switch");
		FacilitationConnector.add("monitoring");
		FacilitationConnector.add("monitor");
		FacilitationConnector.add("bus");
		FacilitationConnector.add("safety");
		FacilitationConnector.add("reliability");
		FacilitationConnector.add("process");
		
		//Inicialização das palavras-chaves dos tipos de conectores
		//Tipo Chamada de procedimento
		ProcedureCallType = new ArrayList<String>();
		ProcedureCallType.add("callbacks");
		ProcedureCallType.add("callback");
		ProcedureCallType.add("calls");
		ProcedureCallType.add("call");
		ProcedureCallType.add("procedures");
		ProcedureCallType.add("procedure");
		ProcedureCallType.add("fork");
		ProcedureCallType.add("exec");
		
		//Tipo Evento
		EventType = new ArrayList<String>();
		EventType.add("actions");
		EventType.add("action");
		EventType.add("gui");
		EventType.add("center");
		EventType.add("user interface");
		EventType.add("ui");
		EventType.add("event");
		EventType.add("send");
		EventType.add("receive");
		EventType.add("interrupts");
		EventType.add("interrupt");
		EventType.add("faults");
		EventType.add("fault");
		EventType.add("traps");
		EventType.add("trap");
		EventType.add("triggered");
		EventType.add("trigger");
		EventType.add("captures");
		EventType.add("capture");
		EventType.add("atomicity");
		EventType.add("causality");
		EventType.add("asynchronous");
		EventType.add("notify");
		EventType.add("notify");
		EventType.add("notifies");
		EventType.add("notifications");
		EventType.add("notification");
		
		//Tipo Acesso a Dados
		DataAcessType = new ArrayList<String>();
		DataAcessType.add("persistence");
		DataAcessType.add("database");
		DataAcessType.add("data");
		DataAcessType.add("file");
		DataAcessType.add("add");
		DataAcessType.add("delete");
		DataAcessType.add("commit");
		DataAcessType.add("rollback");
		DataAcessType.add("reading");
		DataAcessType.add("reader");
		DataAcessType.add("read");
		DataAcessType.add("writering");
		DataAcessType.add("writer");
		DataAcessType.add("write");
		DataAcessType.add("getter");
		DataAcessType.add("get");
		DataAcessType.add("setter");
		DataAcessType.add("set");
		DataAcessType.add("rename");
		DataAcessType.add("replace");
		
		//Tipo Enlace
		LinkageType = new ArrayList<String>();
		LinkageType.add("linkage");
		LinkageType.add("linker");
		LinkageType.add("linkading");
		LinkageType.add("link");
		LinkageType.add("ducts");
		LinkageType.add("duct");
		LinkageType.add("channels");
		LinkageType.add("channel");
		
		//Tipo Stream
		StreamType = new ArrayList<String>();
		StreamType.add("stream");
		StreamType.add("streaming");
		StreamType.add("transfer");
		StreamType.add("transfering");
		StreamType.add("socket");
		StreamType.add("sockets");
		StreamType.add("protocols");
		StreamType.add("protocol");
		
		//Tipo Arbitrário
		ArbitratorType = new ArrayList<String>();
		ArbitratorType.add("authentication"); 
		ArbitratorType.add("authentic");
		ArbitratorType.add("monitoring");
		ArbitratorType.add("monitor");
		ArbitratorType.add("bus");
		ArbitratorType.add("safety");
		ArbitratorType.add("reliability");
		ArbitratorType.add("process");
		ArbitratorType.add("thread");
		
		//Tipo Adaptador
		AdaptorType = new ArrayList<String>();
		AdaptorType.add("convert");
		AdaptorType.add("conversion");
		AdaptorType.add("type");
		AdaptorType.add("protocols");
		AdaptorType.add("protocol");
		AdaptorType.add("format");
		AdaptorType.add("adaptor");
		AdaptorType.add("translator");
		AdaptorType.add("translation");
		
		//Tipo Distribuição
		DistributorType = new ArrayList<String>();
		DistributorType.add("route");
		DistributorType.add("routing");
		DistributorType.add("network");
		DistributorType.add("transaction");
		DistributorType.add("networking");
		DistributorType.add("switching");
		DistributorType.add("switch");
		DistributorType.add("service");
		
		//inicialização das strings que armazenam palavras-chaves de interesses de software
		//Interesses de negócios
		BusinessConcern = new ArrayList<String>();
		BusinessConcern.add("business");
		BusinessConcern.add("domain");
		BusinessConcern.add("logistics");
		BusinessConcern.add("payment");
		BusinessConcern.add("search");
		BusinessConcern.add("notepad");
		BusinessConcern.add("font");
		BusinessConcern.add("fonts");
		BusinessConcern.add("undo");
		BusinessConcern.add("redo");
		BusinessConcern.add("print");
		BusinessConcern.add("printer");
		BusinessConcern.add("printable");
		BusinessConcern.add("photo");
		BusinessConcern.add("media");
		BusinessConcern.add("sms");
		BusinessConcern.add("video");
		BusinessConcern.add("music");
		BusinessConcern.add("image");
		BusinessConcern.add("album");
		
		//Interesses de persistência
		PersistenceConcern = new ArrayList<String>();
		PersistenceConcern.add("persistence");
		PersistenceConcern.add("file");
		PersistenceConcern.add("database");
		PersistenceConcern.add("store");
		PersistenceConcern.add("repository");
		PersistenceConcern.add("data");
		PersistenceConcern.add("query");
		PersistenceConcern.add("add");
		PersistenceConcern.add("delete");
		PersistenceConcern.add("commit");
		PersistenceConcern.add("rollback");
		PersistenceConcern.add("reading");
		PersistenceConcern.add("reader");
		PersistenceConcern.add("read");
		PersistenceConcern.add("writering");
		PersistenceConcern.add("writer");
		PersistenceConcern.add("write");
		PersistenceConcern.add("getter");
		PersistenceConcern.add("get");
		PersistenceConcern.add("setter");
		PersistenceConcern.add("set");
		PersistenceConcern.add("rename");
		PersistenceConcern.add("replace");
		
		//Interesses de Exceção
		ExceptionConcern = new ArrayList<String>();
		ExceptionConcern.add("exception");
		ExceptionConcern.add("exceptions");
		ExceptionConcern.add("handling");
		ExceptionConcern.add("notify");
		ExceptionConcern.add("notify");
		ExceptionConcern.add("notifies");
		ExceptionConcern.add("notifications");
		ExceptionConcern.add("notification");
		ExceptionConcern.add("interrupts");
		ExceptionConcern.add("interrupt");
		
		//Interesses de Segurança
		SecurityConcern = new ArrayList<String>();
		SecurityConcern.add("security");
		SecurityConcern.add("safety");
		SecurityConcern.add("reliability");
		SecurityConcern.add("authentication"); 
		SecurityConcern.add("authentic");
		SecurityConcern.add("monitoring");
		SecurityConcern.add("monitor");
		SecurityConcern.add("bus");
		SecurityConcern.add("process");
		
		//Interesses de Controle
		ControllerConcern = new ArrayList<String>();
		ControllerConcern.add("controller");
		ControllerConcern.add("controlling");
		ControllerConcern.add("control");
		ControllerConcern.add("action");
		ControllerConcern.add("actions");
		
		//Interesses de Transação
		TransactionConcern = new ArrayList<String>();
		TransactionConcern.add("route");
		TransactionConcern.add("routing");
		TransactionConcern.add("network");
		TransactionConcern.add("transaction");
		TransactionConcern.add("networking");
		TransactionConcern.add("switching");
		TransactionConcern.add("switch");
		TransactionConcern.add("service");
		TransactionConcern.add("threads");
		TransactionConcern.add("thread");
		TransactionConcern.add("util");
		
		//Interesses de Interface de Usuário
		UIConcern = new ArrayList<String>();
		UIConcern.add("ui");
		UIConcern.add("gui");
		UIConcern.add("interface");
		UIConcern.add("user interface");
		UIConcern.add("graphics user interface");
		UIConcern.add("apresentation");
		UIConcern.add("screens");
		UIConcern.add("screen");
		UIConcern.add("center");
		UIConcern.add("pane");
		UIConcern.add("window");
		UIConcern.add("windows");
		UIConcern.add("menu");
		UIConcern.add("container");
		
	}
	
	//Instância de uma classe Singleton, que só pode ser instanciada uma vez
	public static Keywords getInstance(){
		if(UniqueInstance==null){
			UniqueInstance = new Keywords();
		}
		return UniqueInstance;
	}
	
	
	//Método utilizado para a busca pelas categorias de conectores existentes
	public ArrayList<String> SearchCategoriesConnectors(String pWord)
	{	
		ArrayList<String> categories = new ArrayList<String>();
		
		for(int x=0; x < CommunicationConnector.size(); x++)
		{
			
			if(pWord.toLowerCase(Locale.ROOT).contains(CommunicationConnector.get(x).toLowerCase(Locale.ROOT)))
			{
				categories.add("Comunicação");
				x = CommunicationConnector.size();
			}	
		}
		
		for(int x=0; x < CoordinationConnector.size(); x++)
		{
			if(pWord.toLowerCase(Locale.ROOT).contains(CoordinationConnector.get(x).toLowerCase(Locale.ROOT)))
			{
				categories.add("Coordenação");
				x = CoordinationConnector.size();
			}
		}
		
		
		for(int x=0; x < ConversionConnector.size(); x++)
		{
			if(pWord.toLowerCase(Locale.ROOT).contains(ConversionConnector.get(x).toLowerCase(Locale.ROOT)))
			{
				categories.add("Conversão");
				x = ConversionConnector.size();
			}
		}
	
		
		for(int x=0; x < FacilitationConnector.size(); x++)
		{
			if(pWord.toLowerCase(Locale.ROOT).contains(FacilitationConnector.get(x).toLowerCase(Locale.ROOT)))
			{
				categories.add("Facilitação");
				x = FacilitationConnector.size();
			}
		}
		
		return categories;	
	}
	
	
	//Método utilizada para busca pelos tipos de conectores existentes
	public String SearchTypesConnectors(String pWord)
	{	
		String type = new String();
		
		for(int x=0; x < ProcedureCallType.size(); x++)
		{	
			if(pWord.toLowerCase(Locale.ROOT).contains(ProcedureCallType.get(x).toLowerCase(Locale.ROOT)))
			{
				type = "Chama de Procedimento";
				x = ProcedureCallType.size();
			}	
		}
			
		for(int x=0; x < EventType.size(); x++)
		{
			if(pWord.toLowerCase(Locale.ROOT).contains(EventType.get(x).toLowerCase(Locale.ROOT)))
			{
				type = "Evento";
				x = EventType.size();
			}
		}
			
			
		for(int x=0; x < DataAcessType.size(); x++)
		{
			if(pWord.toLowerCase(Locale.ROOT).contains(DataAcessType.get(x).toLowerCase(Locale.ROOT)))
			{
				type = "Acesso a Dados";
				x = DataAcessType.size();
			}
		}
		
			
		for(int x=0; x < LinkageType.size(); x++)
		{
			if(pWord.toLowerCase(Locale.ROOT).contains(LinkageType.get(x).toLowerCase(Locale.ROOT)))
			{
				type = "Enlace";
				x = LinkageType.size();
			}
		}
			
		for(int x=0; x < StreamType.size(); x++)
		{
			if(pWord.toLowerCase(Locale.ROOT).contains(StreamType.get(x).toLowerCase(Locale.ROOT)))
			{
				type = "Stream de Dados";
				x = StreamType.size();
			}
		}
			
		for(int x=0; x < ArbitratorType.size(); x++)
		{
			if(pWord.toLowerCase(Locale.ROOT).contains(ArbitratorType.get(x).toLowerCase(Locale.ROOT)))
			{
				type = "Árbitro";
				x = ArbitratorType.size();
			}
		}
			
		for(int x=0; x < AdaptorType.size(); x++)
		{
			if(pWord.toLowerCase(Locale.ROOT).contains(AdaptorType.get(x).toLowerCase(Locale.ROOT)))
			{
				type = "Adaptador";
				x = AdaptorType.size();
			}
		}
			
		for(int x=0; x < DistributorType.size(); x++)
		{
			if(pWord.toLowerCase(Locale.ROOT).contains(DistributorType.get(x).toLowerCase(Locale.ROOT)))
			{
				type = "Distribuidor";
				x = DistributorType.size();
			}
		}
			
			return type;	
		}
	
	
	/*
	 * Verifica se a palavra informada é algum interesse de negócio
	 * Nesta aplicação foi levada em consideração os seguintes interesses:
	 * - Negócio
	 * - Persistência
	 * - Exceção
	 * - Transação
	 * - Securança
	 * - Interface de Usuário
	 */
	//Método utilizado para a busca pelo interesse de software
	public ArrayList<String> SearchComponentConcern(String pWord)
	{
		ArrayList<String> concerns = new ArrayList<String>();
		
		for(int x=0; x < BusinessConcern.size(); x++)
		{
			if(pWord.toLowerCase(Locale.ROOT).contains(BusinessConcern.get(x).toLowerCase(Locale.ROOT)))
			{
				concerns.add("Negócio");
				x = BusinessConcern.size();
			}
		}
		
		
		for(int x=0; x < PersistenceConcern.size(); x++)
		{
			if(pWord.toLowerCase(Locale.ROOT).contains(PersistenceConcern.get(x).toLowerCase(Locale.ROOT)))
			{
				concerns.add("Persistência");
				x = PersistenceConcern.size();
			}
		}
		
		for(int x=0; x < ExceptionConcern.size(); x++)
		{
			if(pWord.toLowerCase(Locale.ROOT).contains(ExceptionConcern.get(x).toLowerCase(Locale.ROOT)))
			{
				concerns.add("Exceção");
				x = ExceptionConcern.size();
			}
		}
		
		
		for(int x=0; x < SecurityConcern.size(); x++)
		{
			if(pWord.toLowerCase(Locale.ROOT).contains(SecurityConcern.get(x).toLowerCase(Locale.ROOT)))
			{
				concerns.add("Segurança");
				x = SecurityConcern.size();
			}
		}
		
		for(int x=0; x < ControllerConcern.size(); x++)
		{
			if(pWord.toLowerCase(Locale.ROOT).contains(ControllerConcern.get(x).toLowerCase(Locale.ROOT)))
			{
				concerns.add("Controle");
				x = ControllerConcern.size();
			}
		}
		
		
		for(int x=0; x < TransactionConcern.size(); x++)
		{
			if(pWord.toLowerCase(Locale.ROOT).contains(TransactionConcern.get(x).toLowerCase(Locale.ROOT)))
			{
				concerns.add("Transação");
				x = TransactionConcern.size();
			}
		}
		
		for(int x=0; x < UIConcern.size(); x++)
		{
			if(pWord.toLowerCase(Locale.ROOT).contains(UIConcern.get(x).toLowerCase(Locale.ROOT)))
			{
				concerns.add("Interface de Usuário");
				x = UIConcern.size();
			}
		}
		return concerns;
	}
	
}
