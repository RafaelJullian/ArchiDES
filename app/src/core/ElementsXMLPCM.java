package core;

/* Esta classe contem as palavras-chaves que são utilizadas pelo arquivo XML com extensão .repository
 * para reperesentação de um modelo arquitetural PCM
 */

public class ElementsXMLPCM {
	
	//Tags dos elementos XMI utilizados pelo SoMoX
	final static String COMPONENTS_REPOSITORY = "components__Repository";
	final static String ASSEMBLYCONTEXTS__COMPOSEDSTRUTURED = "assemblyContexts__ComposedStructure";
	final static String CONNECTORS_COMPOSEDSTRUCTURE = "connectors__ComposedStructure";
    final static String PROVIDEDROLES_INTERFACEPROVIDINGENTITY = "providedRoles_InterfaceProvidingEntity";
    final static String REQUIREDROLES_INTERFACEREQUIRINGENTITY = "requiredRoles_InterfaceRequiringEntity";
    final static String SERVICEEFFECTSPECIFICATION__BASICCOMPONENT = "serviceEffectSpecifications__BasicComponent";
    final static String SERVICEEFFECTSPECIFICATION__COMPOSITECOMPONENT = "serviceEffectSpecifications__CompositeComponent";
    final static String STEPS_BEHAVIOUR = "steps_Behaviour";
    final static String INTERFACES__REPOSITORY = "interfaces__Repository";
    final static String SIGNATURES__OPERATIONINTERFACE = "signatures__OperationInterface";
    final static String RETURNTYPE__OPERATIONSIGNATURE = "returnType__OperationSignature";
    final static String PARAMETERS__OPERATIONSIGNATURE = "parameters__OperationSignature";
    final static String DATATYPE__PARAMETER = "dataType__Parameter";
    final static String DATATYPES__REPOSITORY = "dataTypes__Repository";
    final static String INNERDECLARATION_COMPOSITEDATATYPE = "innerDeclaration_CompositeDataType";
    final static String DATATYPE_INNERDECLARATION = "datatype_InnerDeclaration";
    
    //Tipos dos elementos XMI utilizados pelo SoMoX
    final static String REPOSITORY_BASICCOMPONENT = "repository:BasicComponent";
    final static String REPOSITORY_COMPOSITECOMPONENT = "repository:CompositeComponent";
    final static String COMPOSITION_ASSEMBLYCONNECTOR = "composition:AssemblyConnector";
    final static String COMPOSITION_PROVIDDEDELEGATIONCONNECTOR = "composition:ProvidedDelegationConnector";
    final static String COMPOSITION_REQUIREDDELEGATIONSCONNECTOR = "composition:RequiredDelegationsConnector";
    final static String REPOSITORY_OPERATIONPROVIDEDROLE = "repository:OperationProvidedRole";
    final static String REPOSITORY_OPERATIONREQUIREDROLE = "repository:OperationRequiredRole";
    final static String SEFF_RESOURCEDEMANDINGSEFF = "seff:ResourceDemandingSEFF";
    final static String SEFF_STARTACTION = "seff:StartAction";
    final static String SEFF_EXTERNALACTION = "seff:ExternalCallAction";
    final static String SEFF_INTERNALACTION = "seff:InternalAction";
    final static String SEFF_STOPACTION = "seff:StopAction";
    final static String REPOSITORY_OPERATIONINTERFACE = "repository:OperationInterface";
    final static String REPOSITORY_PRIMITIVEDATATYPE = "repository:PrimitiveDataType";
    final static String REPOSITORY_COMPOSITEDATATYPE = "repository:CompositeDataType";
    final static String REPOSIROTY_COLLECTIONDATATYPE = "repository:CollectionDataType";

}
